package ekb.work.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by juliaskvortsova on 9/4/16.
 */
@Component
@ConfigurationProperties("settings")
public class SettingsConfig {
    private String url;
    private Integer maxResumeCount;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMaxResumeCount() {
        return maxResumeCount;
    }

    public void setMaxResumeCount(Integer maxResumeCount) {
        this.maxResumeCount = maxResumeCount;
    }
}
