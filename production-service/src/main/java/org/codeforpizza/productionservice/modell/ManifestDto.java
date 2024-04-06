package org.codeforpizza.productionservice.modell;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link Manifest}
 */
@AllArgsConstructor
@Getter
@ToString
public class ManifestDto implements Serializable {
    @NotNull(message = "Title can´t be empty")
    @NotEmpty(message = "Title can´t be empty")
    @NotBlank
    private final String title;
    private final long year;
}