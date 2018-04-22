/*
* mark urun 4/21/2018
*
* */
package com.murun.fstats.control;

import com.murun.fstats.model.Host;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class Processor {

    public Set<Host> processFile(File dataFile) throws IOException {

        System.out.println("Processing " + dataFile.getPath());
        Set<Host> hosts = new TreeSet<>(Comparator.comparing(Host::getAverage).reversed().thenComparing(Host::getHostName));
        long inputLineCount =0;
        try ( Scanner scanner = new Scanner(dataFile) ){
            while ( scanner.hasNext()){
                inputLineCount++;
                try {
                    hosts.add( processLine(scanner.nextLine()) );
                } catch (NoSuchElementException e) {
                    System.out.println("Line# " + inputLineCount + " skipped. Unexpected data." );
                }
            }
        }

        return hosts;
    }

    protected Host processLine( String line ) {

      //  System.out.println(line);
        try (  Scanner scanner = new Scanner(line) ) {
            scanner.useDelimiter("[,\\|]");

            String hostName = scanner.next();
            // following three lines are used to check the file format. Will throw NoSuchElementException or InputMismatchException
            // if type mismatches.
            long notUsed1 = scanner.nextLong();
            long notUsed2 = scanner.nextLong();
            int notUsed3 = scanner.nextInt();

            if ( line.indexOf("|") == -1 ){
                throw new NoSuchElementException("Missing | delimiter.");
            }

            double count = 0;
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            double sum = 0;
            while (scanner.hasNext()) {

                try {
                    double value = Double.parseDouble(scanner.next());

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


