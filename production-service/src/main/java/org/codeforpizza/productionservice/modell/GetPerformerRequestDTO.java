package org.codeforpizza.productionservice.modell;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetPerformerRequestDTO {

    private Long performerId;
    private String username;
}
