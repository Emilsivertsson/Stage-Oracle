package org.codeforpizza.productionservice.modell.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.codeforpizza.productionservice.modell.entitys.Act;

import java.io.Serializable;

/**
 * DTO for {@link Act}
 */

public record ActDto(
        @NotNull(message = "Title can´t be Empty") @NotEmpty(message = "Title can´t be Empty") @NotBlank String title) implements Serializable {
    @JsonCreator
    public ActDto(String title) {
        this.title = title;
    }
}