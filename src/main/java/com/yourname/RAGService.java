package com.yourname;

import dev.langchain4j.data.embedding.Embedding;
import java.util.*;

public class RAGService {

    private final PDFLoader loader = new PDFLoader();
    private final EmbeddingService embed = new EmbeddingService();
    private final MilvusConnector milvus = new MilvusConnector();
    private final LLMService llm = new LLMService();
    private final String collection = "pdf_collection";

    public RAGService() throws Exception {
        milvus.createCollection(collection, 768);
        String text = loader.load("C:\\Users\\ELTANANY 01062856027\\Edu_rag_project\\src\\main\\resoures\\document (2).pdf");
        List<String> chunks = Arrays.asList(text.split("\\.\\s+")); // تقسيم الجمل
        List<List<Float>> v = new ArrayList<>();
        for (String chunk : chunks) {
            Embedding e = embed.embed(chunk);
            v.add(e.values());
        }
        milvus.insertVectors(collection, v);
    }

    public String ask(String question) {
        Embedding q = embed.embed(question);
        List<Long> ids = milvus.search(collection, q.values(), 3);
        StringBuilder context = new StringBuilder();
        for (Long id : ids) {
            context.append("Chunk id: ").append(id).append("\n");
        }
        return llm.ask(question, context.toString());
    }
}
