package productshop.domein.entities.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CategorySeedDto {
    @Expose
    private String name;

    public CategorySeedDto() {
    }


    @NotNull(message = "Name cannot null")
    @Length(min = 3,max = 15)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
