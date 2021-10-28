package com.ravi.springbatch.batchjob;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class APIWriter implements ItemWriter<List<String>> {

    @Override
    public void write(List<? extends List<String>> list) {
        System.out.println("Inside writer");
        list.parallelStream().forEach(System.out::println);
    }
}
