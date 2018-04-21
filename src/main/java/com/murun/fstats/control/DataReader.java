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

    public Set<Host> readFile(String resourceName) throws IOException, FileNotFoundException, java.net.URISyntaxException {

        File file = new File(getClass().getClassLoader().getResource(resourceName).getFile());

        Set<Host> lines = new TreeSet<>(Comparator.comparing(Host::getAverage).reversed().thenComparing(Host::getHostName));
        try ( Scanner scanner = new Scanner(file) ){
            while ( scanner.hasNext()){
                lines.add( processLine(scanner.nextLine()) );
            }
        }

        return lines;
    }

    public Host processLine( String line ){

        System.out.println(line);
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter("[,\\|]");
        String hostName = scanner.next();
        String notUsed1 = scanner.next();
        String notUsed2 = scanner.next();
        String notUsed3 = scanner.next();


        double count =0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        double sum = 0;
        while (scanner.hasNext()) {

            try {
                double value = Double.parseDouble(scanner.next());
                System.out.println(value);
                if ( value > max ) max = value;
                if ( value < min ) min = value;
                sum += value;
                count++;
            } catch (NumberFormatException e) {
                // nothing needs to be done
            }
        }

        return new Host( hostName, min, max, sum / count );
    }
}


