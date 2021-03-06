package ekb.work.domain;

import ekb.work.entity.ResumeEntity;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/5/16.
 */
public class ResumeDto {
    private String id;
    private String name;
    private String dateOfBirth;
    private String personalInfo;
    private String location;
    private String jobTitle;
    private String salary;
    private List<String> workTypes;
    private String totalExperience;
    private String jobAreaExperience;
    private String description;
    private String educationLevel;
    private String additionalInformation;
    private List<LanguageLevelDto> languages;
    private String locationPreference;
    private String categoryId;
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public List<String> getWorkTypes() {
        return workTypes;
    }

    public void setWorkTypes(List<String> workTypes) {
        this.workTypes = workTypes;
    }

    public String getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        this.totalExperience = totalExperience;
    }

    public String getJobAreaExperience() {
        return jobAreaExperience;
    }

    public void setJobAreaExperience(String jobAreaExperience) {
        this.jobAreaExperience = jobAreaExperience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public List<LanguageLevelDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageLevelDto> languages) {
        this.languages = languages;
    }

    public String getLocationPreference() {
        return locationPreference;
    }

    public void setLocationPreference(String locationPreference) {
        this.locationPreference = locationPreference;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ResumeEntity toResumeEntity(){
        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setId(getId());
        resumeEntity.setCategoryId(getCategoryId());
        resumeEntity.setName(getName());
        resumeEntity.setAdditionalInformation(getAdditionalInformation());
        resumeEntity.setDateOfBirth(getDateOfBirth());
        resumeEntity.setDescription(getDescription());
        resumeEntity.setEducationLevel(getEducationLevel());
        resumeEntity.setTotalExperience(getTotalExperience());
        resumeEntity.setJobAreaExperience(getJobAreaExperience());
        resumeEntity.setJobTitle(getJobTitle());
        resumeEntity.setLanguages(getLanguages());
        resumeEntity.setLocation(getLocation());
        resumeEntity.setLocationPreference(getLocationPreference());
        resumeEntity.setPersonalInfo(getPersonalInfo());
        resumeEntity.setSalary(getSalary());
        resumeEntity.setWorkTypes(getWorkTypes());
        return resumeEntity;
    }
}
