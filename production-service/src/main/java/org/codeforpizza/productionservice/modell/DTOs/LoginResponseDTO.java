package org.codeforpizza.productionservice.modell.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used to represent the response from the login route
 * It contains the user and the jwt token
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String username;
    private String jwt;

}