package ekb.work.service;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ekb.work.config.SettingsConfig;
import ekb.work.domain.CategoryDto;
import ekb.work.domain.ResumeDto;
import ekb.work.entity.ResumeEntity;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static String DATA_CATEGORY_ID = "data-categoryid";
    private final static String RUBRICATOR_ENTITY_CATEGORIES_RESUME = "ra-rubricator-entity-categories-resume";
    public static final String UL = "ul";
    private final static String RESUME_BOX = "ra-elements-" + LI + "st-box_resume";
    private final static String RESUME_TITLE_LINK = "ra-elements-" + LI + "st__title__link";

    private static final String HREF = "href";
    public static final String DIV = "div";
    private static final String XPATH_BY_CLASS_STRING_FORMAT = "//" + DIV + "[@class='%s']";
    public static final String RA_ELEMENTS_LIST_CARD_INFO_CITIES = "ra-elements-list__card__info-cities";
    public static final String DOT = ".";
    public static final String RA_RESUME_BLOCK_EXPERIENCE_LENGTH = "ra-resume__block-experience-length";
    public static final String RA_RESUME_DESCRIPTION = "ra-resume__description";
    public static final String RA_RESUME_BLOCK_HEADER = "ra-resume__block-header";
    public static final String RA_RESUME_LOCATION = "ra-resume__location";
    public static final String DATA_ID = "data-id";

    private SettingsConfig settingsConfig;
    private Logger logger = LoggerFactory.getLogger(HtmlContentParseServiceImpl.class);

    private MongoService mongoService;

    @Autowired
    public HtmlContentParseServiceImpl(SettingsConfig settingsConfig, MongoService mongoService){
        this.settingsConfig = settingsConfig;
        this.mongoService = mongoService;
    }

    public List<CategoryDto> getCategories() throws Exception {
        Document doc;
        try {
            doc = Jsoup.connect(settingsConfig.getUrl()).get();

            List<CategoryDto> categories = getCategories(doc);
            return categories;
        } catch (IOException e) {
            throw new Exception("Cannot get data. Reason: " + e.getMessage());
        }

    }

    public List<ResumeDto> getResumes(List<CategoryDto> categories) {
        List<ResumeDto> resumeList = new ArrayList<>();
        for (CategoryDto category : categories) {
            String url = settingsConfig.getUrl() + category.getLink();

            String xpathExpr = String.format(XPATH_BY_CLASS_STRING_FORMAT, RA_ELEMENTS_LIST_BOX_RESUME);
            Function<HtmlPage, Boolean> expression = page -> page.getByXPath(xpathExpr).get(0) != null
                    && ((HtmlDivision) page.getByXPath(xpathExpr).get(0)).getChildElementCount() <= 1;
            HtmlPage page = getHtmlPageWaitingForJavascript(url, expression);
            if(page == null){
                logger.error("Cannot load page for url: " + url);
                continue;
            }
            Document parse = Jsoup.parse(page.asXml());
            Element resumeBox = parse.getElementsByClass(RESUME_BOX).first();
            Iterator<Element> resumeIterator = resumeBox.children().iterator();
            Integer max = settingsConfig.getMaxResumeCount();
            while (resumeIterator.hasNext() && (max == null || max > 0)){
                if(max != null)
                {
                    max--;
                }
                Element resumeElement = resumeIterator.next();
                Element resumeLink = resumeElement.getElementsByClass(RESUME_TITLE_LINK).first();
                String resumeId = resumeElement.attr(DATA_ID);
                String href = resumeLink.attr(HREF);
                ResumeDto resume = getResume(href, resumeId, category.getId());
                resumeList.add(resume);
            }
        }
        return resumeList;
    }

    private HtmlPage getHtmlPageWaitingForJavascript(String url, Function<HtmlPage, Boolean> expression){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        try {
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
                        logger.error(e.getMessage());
                        return null;
                    }
                }
            }

            if (tries == 0) {
                logger.warn("Page wasn't loaded");
            }
            return page;
        }
        catch (IOException e){
            logger.error(e.getMessage());
            return null;
        }
        finally {
            webClient.close();
        }
    }

    private ResumeDto getResume(String href, String id, String categoryId){
        ResumeDto resume = new ResumeDto();
        String url = settingsConfig.getUrl() + href;
        String xpathExpr = String.format(XPATH_BY_CLASS_STRING_FORMAT, RA_RESUME_CARD_VIEW_ACTIVE);
        Function<HtmlPage, Boolean> expression = page -> page.getByXPath(xpathExpr) == null
                || page.getByXPath(xpathExpr).size() == 0
                || ((HtmlDivision) page.getByXPath(xpathExpr).get(0)).getChildElementCount() <= 1;
        HtmlPage htmlPage = getHtmlPageWaitingForJavascript(url, expression);
        if(htmlPage == null){
            logger.error("Cannot load page for url: " + url);
            return resume;
        }
        Document parse = Jsoup.parse(htmlPage.asXml());
        Element headerBox = parse.getElementsByClass(RA_RESUME_MAIN_HEADER).first();
        String header = headerBox == null ? "" : headerBox.text();
        Element ageBox = parse.select(DIV + DOT + RA_RESUME_AGE + GT + SPAN).first();
        String dateOfBirth = ageBox == null ? "" : ageBox.text().replaceAll("\\(", "").replaceAll("\\)", "");
        Element citiesBox = parse.getElementsByClass(RA_ELEMENTS_LIST_CARD_INFO_CITIES).first();
        Element locationBox = parse.getElementsByClass(RA_RESUME_LOCATION).first();
        String location = citiesBox == null
                ? (locationBox == null ? "" : locationBox.text())
                : citiesBox.text();
        String jobAreaExperience = parse.select(DIV + DOT + RA_RESUME_BLOCK_EXPERIENCE_LENGTH + GT + SPAN).text();
        String description = parse.getElementsByClass(RA_RESUME_DESCRIPTION).text();
        String educationLevel = parse.select(DIV + DOT + RA_RESUME_BLOCK_HEADER + GT + SPAN).text();

        resume.setId(id);
        resume.setLink(href);
        resume.setCategoryId(categoryId);
        resume.setName(header);
        resume.setDateOfBirth(dateOfBirth);
        resume.setLocation(location);
        resume.setJobAreaExperience(jobAreaExperience);
        resume.setDescription(description);
        resume.setEducationLevel(educationLevel);
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
