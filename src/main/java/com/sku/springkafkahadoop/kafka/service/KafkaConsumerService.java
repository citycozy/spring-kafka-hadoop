package com.sku.springkafkahadoop.kafka.service;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final List<String> messages = new ArrayList<>();
    private final String hdfsOutputPath = "hdfs://localhost:9000/user/ccb/hadoop-3.3.0/output/data.txt";


    @KafkaListener(topics = "parsed-data-topic-test", groupId = "group-id")
    public void listen(String message) {
        synchronized (messages) {
            messages.add(message);
            writeMessageToHDFS(message);
        }
    }

    public List<String> getMessages() {
        synchronized (messages) {
            return new ArrayList<>(messages);
        }
    }

    private void writeMessageToHDFS(String message) {
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://localhost:9000");

            FileSystem fs = FileSystem.get(conf);
            Path outputPath = new Path(hdfsOutputPath);

            try (OutputStream os = fs.create(outputPath, true)) {
                os.write(message.getBytes());
            }

            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
