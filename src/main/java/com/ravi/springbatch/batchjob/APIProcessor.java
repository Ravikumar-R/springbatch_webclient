package com.ravi.springbatch.batchjob;


import com.ravi.springbatch.domain.NameList;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;
import java.util.stream.Collectors;

public class APIProcessor implements ItemProcessor<NameList, List<String>> {

    @Override
    public List<String> process(NameList listOfNames) {
        System.out.println("Inside processor");
        return listOfNames.getNames().stream().map(String::toUpperCase).collect(Collectors.toList());
    }
}
