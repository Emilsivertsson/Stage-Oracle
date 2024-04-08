package org.codeforpizza.productionservice.modell;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link Production}
 */

@Getter
@ToString
public class ProductionDto implements Serializable {
    @Min(message = "That´s not close enough in time", value = 1950)
    @Max(message = "That´s to far away in time", value = 2056)
    private final long year;
    @NotNull(message = "Title can´t be empty")
    @NotEmpty(message = "Title can´t be empty")
    @NotBlank(message = "Title can´t be empty")
    private final String title;
    private final Boolean inRotation;
    private final String description;

    @JsonCreator
    public ProductionDto(long year, String title, Boolean inRotation, String description) {
        this.year = year;
        this.title = title;
        this.inRotation = inRotation;
        this.description = description;
    }
}