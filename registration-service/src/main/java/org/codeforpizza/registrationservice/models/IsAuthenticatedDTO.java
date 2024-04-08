package org.codeforpizza.registrationservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
