package com.murun.fstats.main;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "app")
public class ConfigProperties {



    private short maxDataLines;

    public short getMaxDataLines() {
        return maxDataLines;
    }

    public void setMaxDataLines(short maxDataLines) {
        this.maxDataLines = maxDataLines;
    }



}
