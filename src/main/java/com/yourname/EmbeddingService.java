package main.java.com.yourname;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.data.embedding.Embedding;

public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService() {
        embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("text-embedding-ada-002")
                .build();
    }

    public Embedding embed(String text) {
        return embeddingModel.embed(text).content();
    }
}
