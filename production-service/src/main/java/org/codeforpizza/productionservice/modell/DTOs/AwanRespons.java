package org.codeforpizza.productionservice.modell.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AwanRespons {

    private String id;
    private String object;
    private long created;
    private String model;
    private Choice[] choices;
    private Usage usage;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class Choice {
        private int index;
        private Message message;
        private String finish_reason;

        @Getter
        @Setter
        @NoArgsConstructor
        @ToString
        public static class Message {
            private String role;
            private String content;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class Usage {
        private int prompt_tokens;
        private int total_tokens;
        private int completion_tokens;
    }
}