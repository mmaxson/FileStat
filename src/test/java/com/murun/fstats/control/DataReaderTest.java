package com.murun.fstats.control;


import com.murun.fstats.main.ApplicationConfiguration;
import com.murun.fstats.model.Host;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.;
import static org.mockito.BDDMockito.given;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ApplicationConfiguration.class})


public class DataReaderTest {


    DataReader dataReader;

    @Before
    public void beforeEach() throws Exception {
        dataReader = new DataReader();
    }

    @Test
    public void resultsShouldBeEqual()  {

        String line = "n32,1366829460,1366831260,60|None,None,None,None,None,100.0,50.0,20.0,10.0";

        Host resultHost = dataReader.processLine(line);
        assertEquals("n32", resultHost.getHostName());
        assertEquals(10.0, resultHost.getMin(), 0.0);
        assertEquals(100.0, resultHost.getMax(), 0.0);
        assertEquals(45, resultHost.getAverage(), 0.0);

    }


    @Test(expected = InputMismatchException.class)
    public void threeIntsExpected1()  {
        String line = "n20,1366829460.0,1366831260,60| 100.0,50.0,20.0,10.0";
        Host resultHost = dataReader.processLine(line);
    }

    @Test(expected = InputMismatchException.class)
    public void threeIntsExpected2()  {
        String line = "n20,13668294600,abc,60| 100.0,50.0,20.0,10.0";
        Host resultHost = dataReader.processLine(line);
    }

    @Test(expected = InputMismatchException.class)
    public void threeIntsExpected3()  {
        String line = "n20,13668294600,1366831260,60.0,| 100.0,50.0,20.0,10.0";
        Host resultHost = dataReader.processLine(line);
    }


    @Test(expected = InputMismatchException.class)
    public void pipeSeperatorRequired()  {
        String line = "n20,13668294600,1366831260,60, 100.0,50.0,20.0,10.0";
        Host resultHost = dataReader.processLine(line);
    }
}

