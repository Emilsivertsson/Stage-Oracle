package org.codeforpizza.productionservice.modell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPerformerRequestDTO {

    private Long performerId;
    private String username;
}
