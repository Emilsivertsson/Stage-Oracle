package org.codeforpizza.productionservice.modell.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionDTO(@NotBlank @NotNull String question) {
}
