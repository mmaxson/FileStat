package com.murun.fstats.control;


import com.murun.fstats.main.ApplicationConfiguration;
import com.murun.fstats.model.Host;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ApplicationConfiguration.class})


public class ProcessorTest {


    @Resource
    Processor processor;





    @Test
    public void lineCalculationsShouldBeCorrect()  {

        String line = "n32,1366829460,1366831260,60|None,None,None,None,None,100.0,50.0,20.0,10.0";

        Host resultHost = processor.processLine(line);
        assertEquals("n32", resultHost.getHostName());
        assertEquals(10.0, resultHost.getMin(), 0.0);
        assertEquals(100.0, resultHost.getMax(), 0.0);
        assertEquals(45, resultHost.getAverage(), 0.0);

    }


    @Test(expected = InputMismatchException.class)
    public void threeIntsExpected1()  {
        String line = "n20,1366829460.0,1366831260,60| 100.0,50.0,20.0,10.0";
        Host resultHost = processor.processLine(line);
    }

    @Test(expected = InputMismatchException.class)
    public void threeIntsExpected2()  {
        String line = "n20,13668294600,abc,60| 100.0,50.0,20.0,10.0";
        Host resultHost = processor.processLine(line);
    }

    @Test(expected = InputMismatchException.class)
    public void threeIntsExpected3()  {
        String line = "n20,13668294600,1366831260,60.0| 100.0,50.0,20.0,10.0";
        Host resultHost = processor.processLine(line);
    }


    @Test(expected = NoSuchElementException.class)
    public void pipeSeperatorRequired()  {
        String line = "n20,13668294600,1366831260,60,100.0,50.0,20.0,10.0";
        Host resultHost = processor.processLine(line);
    }

    @Test(expected = NoSuchElementException.class)
    public void missingData()  {
        String line = "n20,";
        Host resultHost = processor.processLine(line);
    }

    @Test
    public void processTestFile() throws IOException {

        File testFile = new File(this.getClass().getClassLoader().getResource("TestData.txt").getFile());
        Set<Host> hostStats = processor.processFile(testFile);

        Iterator<Host> hostStatsIterator = hostStats.iterator();

        Host topHost = hostStatsIterator.next();
        assertEquals("n11", topHost.getHostName());
        assertEquals(35.0, topHost.getMin(), 0.0);
        assertEquals(100.0, topHost.getMax(), 0.0);
        assertEquals(72.6, topHost.getAverage(), 0.0);


        Host bottomHost = hostStatsIterator.next();
        assertEquals("n10", bottomHost.getHostName());
        assertEquals(37.0, bottomHost.getMin(), 0.0);
        assertEquals(100.0, bottomHost.getMax(), 0.0);
        assertEquals(66, bottomHost.getAverage(), 0.0);

    }

    @Test
    public void processTestFileWithLineSkip() throws IOException {

        File testFile = new File(this.getClass().getClassLoader().getResource("TestData2.txt").getFile());
        Set<Host> hostStats = processor.processFile(testFile);

        Iterator<Host> hostStatsIterator = hostStats.iterator();

        Host topHost = hostStatsIterator.next();
        assertEquals("n11", topHost.getHostName());
        assertEquals(35.0, topHost.getMin(), 0.0);
        assertEquals(100.0, topHost.getMax(), 0.0);
        assertEquals(72.6, topHost.getAverage(), 0.0);

    }

}

