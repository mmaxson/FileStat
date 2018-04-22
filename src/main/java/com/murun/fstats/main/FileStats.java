/*
 * mark urun 4/21/2018
 *
 * */
package com.murun.fstats.main;

import com.murun.fstats.control.Processor;
import com.murun.fstats.model.Host;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@SpringBootApplication
public class FileStats {



    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(FileStats.class, args);
        Processor reader = ctx.getBean(Processor.class);
        Set<Host> hostStats = null;
        try {
            File file = new File(Processor.class.getClassLoader().getResource("CodingDemoData.txt").getFile());
            hostStats = reader.processFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("======================");
        System.out.println("Results:");
        System.out.println("======================");

        hostStats.forEach(h ->System.out.println(h.getHostName() + ": "
             +  "Average: " +  String.format("%1$.1f", h.getAverage()) + " Max: " + h.getMax() + " Min: " + h.getMin()));

    }
}