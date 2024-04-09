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
@Table(name="casts")
public class Cast {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manifest_id")
    private Manifest manifest;

    @JsonIgnore
    @OneToMany(mappedBy = "cast", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Performer> performers = new ArrayList<>();

    public Cast(String name) {
        this.name = name;
    }

}
