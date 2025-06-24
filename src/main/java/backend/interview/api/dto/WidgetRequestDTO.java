package backend.interview.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class WidgetRequestDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 5, max = 1000)
    private String description;

    @NotNull
    @DecimalMin("1.00")
    @DecimalMax("20000.00")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
