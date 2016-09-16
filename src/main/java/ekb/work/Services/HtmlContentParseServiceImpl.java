package ekb.work.Services;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ekb.work.Config.SettingsConfig;
import ekb.work.Domain.CategoryDto;
import ekb.work.Domain.ResponseDto;
import ekb.work.Domain.ResumeDto;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

/**
 * Created by juliaskvortsova on 9/4/16.
 */
@Service
public class HtmlContentParseServiceImpl implements HtmlContentParseService {

    public static final String LI = "li";
    public static final String RA_ELEMENTS_LIST_BOX_RESUME = "ra-elements-" + LI + "st-box ra-elements-list-box_resume";
    public static final String RA_RESUME_CARD_VIEW_ACTIVE = "ra-resume__card-view-tpl ra-elements-" + LI + "st__item_active";
    public static final String RA_RESUME_MAIN_HEADER = "ra-resume__main-header";
    public static final String RA_RESUME_AGE = "ra-resume__age";
    public static final String GT = " > ";
    public static final String SPAN = "span";
    private final static String RUBRICATOR_ENTITY_ITEM = "ra-rubricator-entity-item";
    private final static String DATA_CATEGORY_ID = "data-categoryid";
    private final static String RUBRICATOR_ENTITY_CATEGORIES_RESUME = "ra-rubricator-entity-categories-resume";
    public static final String UL = "ul";
    private final static String SEARCH_RESULT_LINK = "ra-search-res" + UL + "t-i-" + LI + "nk";
    private final static String DATA_ID_ATTR = "data-id";
    private final static String RESUME_BOX = "ra-elements-" + LI + "st-box_resume";
    private final static String RESUME_TITLE_LINK = "ra-elements-" + LI + "st__title__link";

    private static final String HREF = "href";
    public static final String DIV = "div";
    private static final String XPATH_BY_CLASS_STRING_FORMAT = "//" + DIV + "[@class='%s']";


    private SettingsConfig settingsConfig;
    private Logger logger = org.slf4j.LoggerFactory.getLogger(HtmlContentParseServiceImpl.class);

    @Autowired
    public HtmlContentParseServiceImpl(SettingsConfig settingsConfig){
        this.settingsConfig = settingsConfig;
    }

    public List<ResumeDto> parse() throws Exception {
        Document doc;
        try {
            doc = Jsoup.connect(settingsConfig.getUrl()).get();
            String title = doc.title();

            List<CategoryDto> categories = getCategories(doc);
            List<ResumeDto> resumes = getResumes(categories);

            categories.stream().map(CategoryDto::toString).forEach(s -> logger.info(s));
            return resumes;
        } catch (IOException e) {
            throw new Exception("Cannot get data. Reason: " + e.getMessage());
        }

    }

    private List<ResumeDto> getResumes(List<CategoryDto> categories) {
        List<ResumeDto> resumeList = new ArrayList<>();
        for (CategoryDto category : categories) {
            String url = settingsConfig.getUrl() + category.getLink();

            String xpathExpr = String.format(XPATH_BY_CLASS_STRING_FORMAT, RA_ELEMENTS_LIST_BOX_RESUME);
            Function<HtmlPage, Boolean> expression = page -> page.getByXPath(xpathExpr).get(0) != null
                    && ((HtmlDivision) page.getByXPath(xpathExpr).get(0)).getChildElementCount() <= 1;
            HtmlPage page = getHtmlPageWaitingForJavascript(url, expression);
            Document parse = Jsoup.parse(page.asXml());
            Element resumeBox = parse.getElementsByClass(RESUME_BOX).first();
            Iterator<Element> resumeIterator = resumeBox.children().iterator();
            while (resumeIterator.hasNext()){
                Element resumeElement = resumeIterator.next();
                Element resumeLink = resumeElement.getElementsByClass(RESUME_TITLE_LINK).first();
                String href = resumeLink.attr(HREF);
                ResumeDto resume = getResume(href);
                resumeList.add(resume);
            }
        }
        return resumeList;
    }

    private HtmlPage getHtmlPageWaitingForJavascript(String url, Function<HtmlPage, Boolean> expression){
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.setAjaxController(new AjaxController() {
                @Override
                public boolean processSynchron(HtmlPage page, WebRequest request, boolean async) {
                    return true;
                }
            });

            HtmlPage page = webClient.getPage(url);
            int tries = 5;  // Amount of tries to avoid infinite loop
            while (tries > 0 && expression.apply(page)) {
                tries--;
                synchronized (page) {
                    try {
                        page.wait(2000);  // How often to check
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (tries == 0) {
                logger.warn("Page wasn't loaded");
            }
            return page;
        }
        catch (IOException e){
            return null;
        }
    }

    private ResumeDto getResume(String href){
        ResumeDto resume = new ResumeDto();
        String url = settingsConfig.getUrl() + href;
        String xpathExpr = String.format(XPATH_BY_CLASS_STRING_FORMAT, RA_RESUME_CARD_VIEW_ACTIVE);
        Function<HtmlPage, Boolean> expression = page -> page.getByXPath(xpathExpr) == null
                || page.getByXPath(xpathExpr).size() == 0
                || ((HtmlDivision) page.getByXPath(xpathExpr).get(0)).getChildElementCount() <= 1;
        HtmlPage htmlPage = getHtmlPageWaitingForJavascript(url, expression);
        if(htmlPage == null){
            logger.error("Cannot load page");
            return resume;
        }
        Document parse = Jsoup.parse(htmlPage.asXml());
        String header = parse.getElementsByClass(RA_RESUME_MAIN_HEADER).first().text();
        Element ageBox = parse.select(RA_RESUME_AGE + GT + SPAN).first();
        String dateOfBirth = ageBox == null ? "" : ageBox.text().replaceAll("\\(", "").replaceAll("\\)", "");

        resume.setName(header);
        resume.setDateOfBirth(dateOfBirth);
        return resume;
    }

    private List<CategoryDto> getCategories(Document doc) {
        List<CategoryDto> categories = new ArrayList<>();
        Iterator<Element> elementsIterator = doc.select("nav#" + RUBRICATOR_ENTITY_CATEGORIES_RESUME + GT + DIV + GT + UL + GT + LI).iterator();
        while (elementsIterator.hasNext()){
            Element element = elementsIterator.next();
            String dataCategoryId = element.attr(DATA_CATEGORY_ID);
            Element link = element.children().first();
            String categoryName = link.text();
            String href = link.attr(HREF);

            CategoryDto category = new CategoryDto(dataCategoryId, categoryName, href);
            categories.add(category);

        }
        return categories;
    }
}
