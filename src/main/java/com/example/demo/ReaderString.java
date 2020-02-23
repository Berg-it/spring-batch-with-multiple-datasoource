package com.example.demo;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;



public class ReaderString implements ItemReader<String> {

    private String[] messages = {"javainuse.com",
            "Welcome to Spring Batch Example1",
            "Welcome to Spring Batch Example2",
            "Welcome to Spring Batch Example3",
            "Welcome to Spring Batch Example4",
            "Welcome to Spring Batch Example5",
            "Welcome to Spring Batch Example6",
            "Welcome to Spring Batch Example7",
            "Welcome to Spring Batch Example8",
            "Welcome to Spring Batch Example",
            "Welcome to Spring Batch Example",
            "Welcome to Spring Batch Example",
            "Welcome to Spring Batch Example",
            "Welcome to Spring Batch Example",
            "We use H2 Database for this example"
    };

    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {

        if (count < messages.length) {
            return messages[count++];
        } else {
            count = 0;
        }
        return null;
    }



}