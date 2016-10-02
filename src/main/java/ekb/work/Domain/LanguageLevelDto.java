package ekb.work.domain;

/**
 * Created by juliaskvortsova on 9/9/16.
 */
public class LanguageLevelDto {
    private String language;
    private String level;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", language, level);
    }
}
