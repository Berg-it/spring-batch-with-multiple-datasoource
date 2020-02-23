package com.example.demo;

import java.util.List;

import org.springframework.batch.item.ItemWriter;


public class WriterString implements ItemWriter<String> {

    private CsvRepo csvRepo;

    public WriterString(CsvRepo csvRepo) {
        this.csvRepo = csvRepo;
    }

    @Override
    public void write(List<? extends String> messages) throws Exception {
        for (String msg : messages) {
            System.out.println("Writing the data -->" + msg);
            csvRepo.list.add(msg);
        }
    }

}
