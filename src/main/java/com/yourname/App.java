package main.java.com.yourname;
import io.milvus.client.*;
public class App {
    public static void main(String[] args) throws Exception {
        RAGService rag = new RAGService();
        String answer = rag.ask("What is the origin of artificial intelligence?");
        System.out.println("Answer: " + answer);
        rag.milvus.close();
    }
}
