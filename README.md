# 📚 Java RAG System using LangChain4j, OpenAI, and Milvus

This project is a simple **RAG (Retrieval-Augmented Generation)** application built with **Java 21**. It allows you to upload a PDF file, convert its content into embeddings using OpenAI, store them in **Milvus**, and ask questions to get answers based on the document.

---

## 🎯 What This Project Does

- Extracts text from a PDF file
- Splits the text into chunks
- Converts each chunk into vector embeddings using OpenAI
- Stores embeddings in Milvus (vector DB)
- Embeds your question, searches Milvus for similar content
- Sends results to GPT (OpenAI) to answer your question

---

## 🧰 Technologies Used

- **Java 21**
- **LangChain4j** (OpenAI + Embedding integration)
- **OpenAI API**
- **Milvus (v2.4.3)**
- **Apache PDFBox**
- **Docker + Docker Compose**
- **Maven**

---

## 🚀 How to Run

1. **Clone the repo**

```bash
git clone https://github.com/karima-mahmoud/Edu_rag_project.git
cd Edu_rag_project
```

2. **Add your OpenAI API key**

Create a `.env` file in the root:

```
OPENAI_API_KEY=your_openai_key_here
```

3. **Add your PDF file**

Put your PDF in the root folder and name it:

```
document.pdf
```

4. **Start Milvus using Docker**

```bash
docker-compose up -d
```

5. **Build the project**

```bash
mvn clean install
```

6. **Run the project**

```bash
mvn exec:java -Dexec.mainClass="com.yourname.App"
```

---

## 📁 Project Files

- `App.java`: Runs the whole process
- `RAGService.java`: Main logic
- `PDFLoader.java`: Loads PDF content
- `EmbeddingService.java`: Sends text to OpenAI for embeddings
- `LLMService.java`: Sends questions + context to GPT
- `MilvusConnector.java`: Manages Milvus collection + search

---

## 🧪 Example Output

```
Answer: The origin of artificial intelligence can be traced back to...
```

---

## ✍️ Author

**Karima Mahmoud Salem**  
GitHub: [karima-mahmoud](https://github.com/karima-mahmoud)

---

## 🛑 License

Free to use for educational purposes.
