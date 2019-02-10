package productshop.domein.entities.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductSeedDto {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductSeedDto() {
    }

    @NotNull
    @Length(min = 3)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DecimalMin(value = "0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
