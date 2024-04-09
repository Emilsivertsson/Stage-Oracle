package org.codeforpizza.productionservice.modell.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PerformerResponsDTO {

    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNr;
    String department;

    MeasurementsDTO measurements;
}
