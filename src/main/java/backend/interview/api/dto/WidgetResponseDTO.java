package backend.interview.api.dto;

import java.math.BigDecimal;

public class WidgetResponseDTO {
    private String name;
    private String description;
    private BigDecimal price;

    public WidgetResponseDTO(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
}
