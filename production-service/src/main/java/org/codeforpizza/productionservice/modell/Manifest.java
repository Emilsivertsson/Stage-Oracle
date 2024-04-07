package org.codeforpizza.productionservice.modell;

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
@Table(name="manifests")
public class Manifest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="year")
    private long year;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;

    @JsonIgnore
    @OneToMany(mappedBy = "manifest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cast> casts = new ArrayList<>();

    public Manifest(String title, long year) {
        this.title = title;
        this.year = year;
    }

}
