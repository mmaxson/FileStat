/*
 * mark urun 4/21/2018
 *
 * */
package com.murun.fstats.main;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@ComponentScan(basePackages="com.murun.*")
@Configuration
public class ApplicationConfiguration {
    private static final String MAX_DATA_LINES = "max_data_lines";



    @Resource
    private Environment env;


    @Bean
    public short maxDataLines() {

        short retVal =0;
        try {
            retVal = Short.parseShort(env.getRequiredProperty(MAX_DATA_LINES));
        } catch (NumberFormatException | IllegalStateException  e) {
            System.out.println("Cannot read property value " + MAX_DATA_LINES + ".  Set to max.");
            retVal = Short.MAX_VALUE;
        }

        return retVal ;
    }

}
