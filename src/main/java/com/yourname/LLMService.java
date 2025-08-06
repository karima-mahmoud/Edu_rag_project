package com.yourname;

import dev.langchain4j.data.chat.ChatCompletion;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class LLMService {

    private final ChatLanguageModel impl;

    public LLMService() {
        
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("OPENAI_API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OPENAI_API_KEY is not set in .env");
        }

        
        impl = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-3.5-turbo")
                .build();
    }

    public String ask(String question, String context) {
        String prompt = "Context:\n" + context + "\nQuestion: " + question;
        ChatCompletion completion = impl.chat().prompt(prompt).execute();
        return completion.first().content();
    }
}
