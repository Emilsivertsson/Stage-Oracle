package org.codeforpizza.productionservice.modell.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.codeforpizza.productionservice.modell.entitys.Performer;

import java.io.Serializable;

/**
 * DTO for {@link Performer}
 */

public record PerformerDto(
        @NotNull(message = "First name can´t be empty") @NotEmpty(message = "First name can´t be empty") @NotBlank(message = "First name can´t be empty") String firstName,
        @NotNull(message = "Last name can´t be empty") @NotEmpty(message = "Last name can´t be empty") @NotBlank(message = "Last name can´t be empty") String lastName) implements Serializable {

}