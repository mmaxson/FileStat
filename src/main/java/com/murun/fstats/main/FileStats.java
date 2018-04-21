package com.murun.fstats.main;

import com.murun.fstats.control.DataReader;
import com.murun.fstats.model.Host;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class FileStats {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(FileStats.class, args);
        DataReader reader = ctx.getBean(DataReader.class);
        Set<Host> hostStats =null;
        try {
            hostStats = reader.readFile("CodingDemoData.txt");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    //    hostStats.sort(Comparator.comparing(Host::getAverage).reversed());
        hostStats.forEach(h ->System.out.println(h.getHostName() + ": "
             +  "Average: " +  String.format("%1$.1f", h.getAverage()) + " Max: " + h.getMax() + " Min: " + h.getMin()));

//<Host>: Average: <average value> Max: <maxium value> Min: <minimum value>


    }
}