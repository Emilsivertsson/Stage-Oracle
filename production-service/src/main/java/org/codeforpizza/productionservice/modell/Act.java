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
@Table(name="acts")
public class Act {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "performer_id")
    private Performer performer;

    @JsonIgnore
    @OneToMany(mappedBy = "act", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Costume> costumes = new ArrayList<>();

    public Act(String title) {
        this.title = title;
    }
}
