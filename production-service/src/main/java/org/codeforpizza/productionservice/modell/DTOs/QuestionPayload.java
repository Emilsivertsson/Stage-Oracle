package org.codeforpizza.productionservice.modell.DTOs;

import java.util.List;

public class QuestionPayload {
    private final String model;
    private final List<Message> messages;

    public QuestionPayload(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public static class Message {
        private final String role;
        private final String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
