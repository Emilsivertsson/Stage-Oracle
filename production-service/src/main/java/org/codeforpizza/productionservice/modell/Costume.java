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
@Table(name="costumes")
public class Costume {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "act_id")
    private Act act;

    @OneToMany(mappedBy = "costume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Garment> garments = new ArrayList<>();

}
