package com.murun.fstats.control;

import com.murun.fstats.model.Host;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class DataReader {

    public Set<Host> readFile(String resourceName) throws IOException {

        File file = new File(getClass().getClassLoader().getResource(resourceName).getFile());

        Set<Host> lines = new TreeSet<>(Comparator.comparing(Host::getAverage).reversed().thenComparing(Host::getHostName));
        int lineCount =0;
        try ( Scanner scanner = new Scanner(file) ){
            while ( scanner.hasNext()){
                lineCount++;
                try {
                    lines.add( processLine(scanner.nextLine()) );
                } catch (InputMismatchException e) {
                    System.out.println("Line# " + lineCount + "skipped. Reason: " + e.getMessage());
                }
            }
        }

        return lines;
    }

    protected Host processLine( String line ) throws InputMismatchException{

        System.out.println(line);
        try (  Scanner scanner = new Scanner(line) ) {
            scanner.useDelimiter("[,\\|]");

            String hostName = scanner.next();
            long notUsed1 = scanner.nextLong();
            long notUsed2 = scanner.nextLong();
            int notUsed3 = scanner.nextInt();

            if ( line.indexOf("|") == -1 ){
                throw new InputMismatchException("Missing | delimeter.");
            }

            double count = 0;
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            double sum = 0;
            while (scanner.hasNext()) {

                try {
                    double value = Double.parseDouble(scanner.next());
                    //System.out.println(value);
                    if (value > max) max = value;
                    if (value < min) min = value;
                    sum += value;
                    count++;
                } catch (NumberFormatException e) {
                    // be tolerant. ignore non double, continue processing
                }
            }


            return new Host(hostName, min, max, sum / count);
        }
    }
}


