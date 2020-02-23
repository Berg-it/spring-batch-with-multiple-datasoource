package com.example.demo;

import com.example.demo.batch1.domain.Batch1;
import com.example.demo.batch1.repo1.BatchRepository;
import com.example.demo.batch2.domain2.Batch2;
import com.example.demo.batch2.repo2.Batch2Repository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@ComponentScan("com.example.demo")
@EnableAutoConfiguration(exclude = { //
        DataSourceAutoConfiguration.class, //
        DataSourceTransactionManagerAutoConfiguration.class
})
//@EntityScan(basePackages={"com.example.demo.batch1.domain","com.example.demo.batch2.domain2"})
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;

    @Autowired
    Batch2Repository batch2Repository;

    @Autowired
    BatchRepository batchRepository;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        add3ToDb();


        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);



        System.out.println("Hi");
    }

    @Transactional
    private void add3ToDb(){
        Batch1 str1 = Batch1.builder().id(1700L).batch("Str1").build();
        batchRepository.save(str1);
        Batch2 str2 = Batch2.builder().id(1700L).batch("Str2").build();
        batch2Repository.save(str2);
    }


}
