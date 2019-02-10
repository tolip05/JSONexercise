package productshop.domein.entities.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserSeedDto implements Serializable {
    @Expose
    private String firstName;
    @Expose
    @NotNull(message = "Last name cannot be empty")
    @Length(min = 3)
    private String lastName;
    @Expose
    private Integer age;

    public UserSeedDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
