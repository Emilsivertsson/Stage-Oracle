package org.codeforpizza.registrationservice.models.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "measurements")
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double height;
    double shoeSize;
    double jacketSize;
    double pantSize;
    double head;

    public Measurements(double height, double shoeSize, double jacketSize, double pantSize, double head) {
        this.height = height;
        this.shoeSize = shoeSize;
        this.jacketSize = jacketSize;
        this.pantSize = pantSize;
        this.head = head;
    }
}
