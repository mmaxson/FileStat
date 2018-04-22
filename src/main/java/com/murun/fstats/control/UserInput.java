
/*
 * mark urun 4/21/2018
 *
 * */
package com.murun.fstats.control;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class UserInput {

    public File getFileFromUser(){

        File retVal = null;
        try ( Scanner sc = new Scanner(System.in) ) {

            do {
                System.out.println("Enter path or just enter Q to quit:");
                String pathStr = sc.next();

                if (pathStr.equalsIgnoreCase("Q")) return null;
                retVal = getFileForPathStr(pathStr);
                if (retVal == null){
                    System.out.println("File not found.");
                }
            } while (retVal==null);

            return retVal;
        }
    }


    private File getFileForPathStr(String pathStr){
        File f = new File(pathStr);
        if (f.exists() && f.isFile()) {
            return f;
        }
        else{
            return null;
        }
    }


}
