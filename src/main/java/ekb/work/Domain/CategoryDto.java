package ekb.work.Domain;

import java.util.List;

/**
 * Created by juliaskvortsova on 9/5/16.
 */
public class CategoryDto {
    private String id;
    private String name;
    private String link;
    private List<CategoryDto> subCategories;

    public CategoryDto(String id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<CategoryDto> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoryDto> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'';
    }
}
