package org.codeforpizza.productionservice.modell;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link Cast}
 */

@Getter
@Setter
@ToString
public class CastDto implements Serializable {
    @NotNull(message = "Name can´t be empty")
    @NotEmpty(message = "Name can´t be empty")
    @NotBlank
    private final String name;

    @JsonCreator
    public CastDto(String name) {
        this.name = name;
    }

}