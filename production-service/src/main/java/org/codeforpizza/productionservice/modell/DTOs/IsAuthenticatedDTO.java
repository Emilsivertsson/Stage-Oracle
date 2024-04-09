package org.codeforpizza.productionservice.modell.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class IsAuthenticatedDTO {

    private String username;

    @JsonCreator
    public IsAuthenticatedDTO(String username) {
        this.username = username;
    }
}
