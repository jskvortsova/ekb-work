package ekb.work.Domain;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/5/16.
 */
public class ResumeDto {
    private String name;
    private String dateOfBirth;
    private String personalInfo;
    private String location;
    private String jobTitle;
    private String salary;
    private List<String> workTypes;
    private String totalExperience;
    private String jobAreaExperience;
    private List<ExperienceDto> experience;
    private String description;
    private List<EducationDto> education;
    private String additionalInformation;
    private List<LanguageLevelDto> languages;
    private String locationPreference;

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

    public List<ExperienceDto> getExperience() {
        return experience;
    }

    public void setExperience(List<ExperienceDto> experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EducationDto> getEducation() {
        return education;
    }

    public void setEducation(List<EducationDto> education) {
        this.education = education;
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
}
