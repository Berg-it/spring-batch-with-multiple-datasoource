package com.example.demo;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class WriterString2 implements ItemWriter<String>
{

    private CsvRepo csvRepo;

    public WriterString2(CsvRepo csvRepo) {
        this.csvRepo = csvRepo;
    }



    @Override
    public void write(List<? extends String> list) throws Exception {

        for(String item : list){
            System.out.println("AAAAAA"+item);
        }

    }
}
