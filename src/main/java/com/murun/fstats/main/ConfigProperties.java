package com.murun.fstats.main;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Configuration
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "app")
public class ConfigProperties {


    @Min(1)
    @Max(32767)
    private short maxDataLines;

    public short getMaxDataLines() {
        return maxDataLines;
    }

    public void setMaxDataLines(short maxDataLines) {
        this.maxDataLines = maxDataLines;
    }



}
