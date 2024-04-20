package org.codeforpizza.registrationservice.models.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PerformerDTO {
    String firstName;
    String lastName;
    String email;
    String phoneNr;
    String department;
}
