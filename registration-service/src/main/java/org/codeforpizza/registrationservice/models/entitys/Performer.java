package org.codeforpizza.registrationservice.models.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "performers")
public class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "phone_nr")
    String phoneNr;

    @Column(name = "department")
    String department;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "measurements_id", nullable = false)
    private Measurements measurements;

    public Performer(String firstName, String lastName, String email, String phoneNr, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNr = phoneNr;
        this.department = department;
    }

    public Performer(String firstName, String lastName, String email, String phoneNr, String department, Measurements measurements) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNr = phoneNr;
        this.department = department;
        this.measurements = measurements;
    }

    public Performer(ApplicationUser applicationUser) {
    }
}
