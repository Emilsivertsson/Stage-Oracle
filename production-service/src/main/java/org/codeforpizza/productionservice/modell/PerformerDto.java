package org.codeforpizza.productionservice.modell;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link Performer}
 */
@AllArgsConstructor
@Getter
@ToString
public class PerformerDto implements Serializable {
    @NotNull(message = "First name can´t be empty")
    @NotEmpty(message = "First name can´t be empty")
    @NotBlank(message = "First name can´t be empty")
    private final String firstName;
    @NotNull(message = "Last name can´t be empty")
    @NotEmpty(message = "Last name can´t be empty")
    @NotBlank(message = "Last name can´t be empty")
    private final String lastName;
}