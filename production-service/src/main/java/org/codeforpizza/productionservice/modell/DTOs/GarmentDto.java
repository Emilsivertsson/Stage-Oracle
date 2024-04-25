package org.codeforpizza.productionservice.modell.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.codeforpizza.productionservice.modell.entitys.Garment;
import java.io.Serializable;

/**
 * DTO for {@link Garment}
 */


public record GarmentDto(
        @NotNull(message = "Name can´t be empty") @NotEmpty(message = "Name can´t be empty") @NotBlank String name,
        String description, Boolean isDone) implements Serializable {

}