package com.yourname;
import io.milvus.client.*;
import io.milvus.param.ConnectParam;
import io.milvus.param.collection.*;
import io.milvus.param.dml.InsertParam;
import io.milvus.param.dml.SearchParam;
import io.milvus.grpc.DataType;

import java.util.*;

public class MilvusConnector {

    private final MilvusClient client;

    public MilvusConnector() {
        ConnectParam param = ConnectParam.newBuilder()
                .withHost("localhost")
                .withPort(19530)
                .build();
        client = new MilvusGrpcClient(param);
    }

    public void createCollection(String name, int dim) {
        if (client.hasCollection(name)) {
            client.dropCollection(name);
        }
        FieldType idField = FieldType.newBuilder()
                .withName("id")
                .withDataType(DataType.Int64)
                .withPrimaryKey(true)
                .withAutoID(true)
                .build();
        FieldType vecField = FieldType.newBuilder()
                .withName("embedding")
                .withDataType(DataType.FloatVector)
                .withDimension(dim)
                .build();
        CreateCollectionParam cp = CreateCollectionParam.newBuilder()
                .withCollectionName(name)
                .withDescription("PDF embeddings")
                .withShardNum(1)
                .addFieldType(idField)
                .addFieldType(vecField)
                .build();
        client.createCollection(cp);
    }

    public List<Long> insertVectors(String collection, List<List<Float>> vectors) {
        InsertParam ip = InsertParam.newBuilder()
                .withCollectionName(collection)
                .withFloatVectors(vectors)
                .build();
        InsertResponse resp = client.insert(ip);
        return resp.getInsertIds();
    }

    public List<Long> search(String collection, List<Float> queryVector, int topK) {
        SearchParam sp = SearchParam.newBuilder()
                .withCollectionName(collection)
                .withFloatVectorField("embedding")
                .withTopK(topK)
                .withVectors(Collections.singletonList(queryVector))
                .withMetricType(MetricType.COSINE)
                .build();
        SearchResponse sr = client.search(sp);
        return sr.getResults(0).getIdsList();
    }

    public void close() {
        client.close();
    }
}
