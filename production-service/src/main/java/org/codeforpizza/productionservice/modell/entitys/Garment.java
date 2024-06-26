package org.codeforpizza.productionservice.modell.entitys;

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
@Table(name="garments")
public class Garment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="is_done")
    private Boolean isDone;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "costume_id")
    private Costume costume;

    public Garment(String name, String description, Boolean isDone) {
        this.name = name;
        this.description = description;
        this.isDone = isDone;
    }
}
