package org.codeforpizza.productionservice.modell.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="awan_chats")
public class AwanChats {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name = "question",length = 5000)
    private String question;


    @Column(name = "answer",length = 5000)
    private String answer;

    public AwanChats(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "application_user_user_id")
    private ApplicationUser applicationUser;

}
