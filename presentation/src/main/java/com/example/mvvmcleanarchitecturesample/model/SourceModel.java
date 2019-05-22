package com.example.mvvmcleanarchitecturesample.model;

import java.io.Serializable;

public class SourceModel implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

