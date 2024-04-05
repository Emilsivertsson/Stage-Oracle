package org.codeforpizza.productionservice.modell;

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
@Table(name="productions")
public class Production {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="year")
    private long year;

    @Column(name="title")
    private String title;

    @Column(name="in_rotation")
    Boolean inRotation;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manifest> manifests = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "application_user_user_id")
    private ApplicationUser applicationUser;

}
