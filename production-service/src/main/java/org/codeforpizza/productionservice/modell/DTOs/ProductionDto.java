package org.codeforpizza.productionservice.modell.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;
import org.codeforpizza.productionservice.modell.entitys.Production;

import java.io.Serializable;

/**
 * DTO for {@link Production}
 */

public record ProductionDto(long year,
                            @NotNull(message = "Title can´t be empty") @NotEmpty(message = "Title can´t be empty") @NotBlank(message = "Title can´t be empty") String title,
                            Boolean inRotation, String description) implements Serializable {

}