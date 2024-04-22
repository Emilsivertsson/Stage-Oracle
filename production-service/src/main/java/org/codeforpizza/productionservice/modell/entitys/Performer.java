package org.codeforpizza.productionservice.modell.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="performers")
public class Performer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone_nr")
    private String phoneNr;

    @Column(name="department")
    private String department;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cast_id")
    private Cast cast;

    @JsonIgnore
    @OneToMany(mappedBy = "performer", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Act> acts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "measurements_id")
    private Measurements measurements;

    public Performer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Performer(String firstName, String lastName, String email, String phoneNr, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNr = phoneNr;
        this.department = department;
    }
}
