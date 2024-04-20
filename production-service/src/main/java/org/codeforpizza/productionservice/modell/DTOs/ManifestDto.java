package org.codeforpizza.productionservice.modell.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.codeforpizza.productionservice.modell.entitys.Manifest;

import java.io.Serializable;

/**
 * DTO for {@link Manifest}
 */


public record ManifestDto(
        @NotNull(message = "Title can´t be empty") @NotEmpty(message = "Title can´t be empty") @NotBlank String title,
        long year) implements Serializable {

}