package org.codeforpizza.registrationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementsDTO {

    double height;
    double shoeSize;
    double jacketSize;
    double pantSize;
    double head;
}
