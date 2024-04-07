package org.codeforpizza.productionservice.modell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cast_id")
    private Cast cast;

    @JsonIgnore
    @OneToMany(mappedBy = "performer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Act> acts = new ArrayList<>();

    public Performer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
