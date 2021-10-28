package com.ravi.springbatch.batchjob;

import com.ravi.springbatch.domain.NameList;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

public class APIReader implements ItemReader<NameList> {

    @Autowired
    private final WebClient webClient = WebClient.builder().build();

    int count = 0;

    @Override
    public NameList read() {
        System.out.println("Inside reader..");
        return this.webClient.get()
                .uri("http://localhost:8080/getNames")
                .retrieve()
                .bodyToMono(NameList.class)
                .block();
    }

}
