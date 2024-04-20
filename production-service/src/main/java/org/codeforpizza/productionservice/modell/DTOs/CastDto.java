package org.codeforpizza.productionservice.modell.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.codeforpizza.productionservice.modell.entitys.Cast;

import java.io.Serializable;

/**
 * DTO for {@link Cast}
 */


public record CastDto(
        @NotNull(message = "Name can´t be empty") @NotEmpty(message = "Name can´t be empty") @NotBlank String name) implements Serializable {
}