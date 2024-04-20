package org.codeforpizza.productionservice.modell.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.codeforpizza.productionservice.modell.entitys.Garment;

import java.io.Serializable;

/**
 * DTO for {@link Garment}
 */


public record GarmentDto(
        @NotNull(message = "Name can´t be empty") @NotEmpty(message = "Name can´t be empty") @NotBlank String name,
        String description) implements Serializable {
    @JsonCreator
    public GarmentDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}