package org.codeforpizza.productionservice.modell;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link Act}
 */

@Getter
@ToString
public class ActDto implements Serializable {
    @NotNull(message = "Title can´t be Empty")
    @NotEmpty(message = "Title can´t be Empty")
    @NotBlank
    private final String title;

    @JsonCreator
    public ActDto(String title) {
        this.title = title;
    }
}