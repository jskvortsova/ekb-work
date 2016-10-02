package ekb.work.entity;

import ekb.work.domain.CategoryDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by juliaskvortsova on 9/21/16.
 */
@Document(collection = "categories")
public class CategoryEntity {
    @Id
    private String _id;
    private String id;
    private String name;

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
}
