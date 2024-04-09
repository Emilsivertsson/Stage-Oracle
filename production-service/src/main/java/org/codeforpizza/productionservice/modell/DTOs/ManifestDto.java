package org.codeforpizza.productionservice.modell.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.codeforpizza.productionservice.modell.entitys.Manifest;

import java.io.Serializable;

/**
 * DTO for {@link Manifest}
 */

@Getter
@Setter
@ToString
public class ManifestDto implements Serializable {
    @NotNull(message = "Title can´t be empty")
    @NotEmpty(message = "Title can´t be empty")
    @NotBlank
    private final String title;
    private final long year;

    @JsonCreator
    public ManifestDto(String title, long year) {
        this.title = title;
        this.year = year;
    }
}