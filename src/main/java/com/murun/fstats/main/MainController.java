/*
 * mark urun 4/21/2018
 *
 * */
package com.murun.fstats.main;

import com.murun.fstats.control.Processor;
import com.murun.fstats.control.UserInput;
import com.murun.fstats.model.Host;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Component
public class MainController {

    @Resource
    private Processor processor;

    @Resource
    private UserInput userInput;

    public void run() {

        Set<Host> hostStats = null;
        try {

            File file = userInput.getFileFromUser();
            if ( file != null) {
                hostStats = processor.processFile(file);
                System.out.println("======================");
                System.out.println("Results:");
                System.out.println("======================");
                hostStats.forEach(h ->System.out.println(h.getHostName() + ": "
                        +  "Average: " +  String.format("%1$.1f", h.getAverage()) + " Max: " + h.getMax() + " Min: " + h.getMin()));
            }

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }
}
