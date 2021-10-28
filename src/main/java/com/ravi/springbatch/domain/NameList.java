package com.ravi.springbatch.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NameList{
    private List<String> names = new ArrayList<>();
}
