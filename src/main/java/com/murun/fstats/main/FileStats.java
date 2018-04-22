/*
 * mark urun 4/21/2018
 *
 * */
package com.murun.fstats.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


@SpringBootApplication
public class FileStats implements CommandLineRunner {

    @Resource
    private MainController mainControler;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FileStats.class, args);
    }



    @Override
    public void run(String... args) throws Exception {

        mainControler.run();
    }
}