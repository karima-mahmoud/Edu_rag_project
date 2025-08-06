package com.yourname;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.data.embedding.Embedding;
import io.github.cdimascio.dotenv.Dotenv;

public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("OPENAI_API_KEY");

        embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("text-embedding-ada-002")
                .build();
    }

    public Embedding embed(String text) {
        return embeddingModel.embed(text).content();
    }
}
