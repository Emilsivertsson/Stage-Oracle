package org.codeforpizza.productionservice.modell.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.codeforpizza.productionservice.modell.entitys.Costume;

import java.io.Serializable;

/**
 * DTO for {@link Costume}
 */

@Getter
@ToString
public class CostumeDto implements Serializable {
    @NotNull(message = "Name can´t be empty")
    @NotEmpty(message = "Name can´t be empty")
    @NotBlank
    private final String name;

    @JsonCreator
    public CostumeDto(String name) {
        this.name = name;
    }
}