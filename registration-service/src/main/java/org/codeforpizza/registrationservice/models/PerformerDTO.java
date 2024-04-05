package org.codeforpizza.registrationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformerDTO {
    String firstName;
    String lastName;
    String email;
    String phoneNr;
    String department;
}
