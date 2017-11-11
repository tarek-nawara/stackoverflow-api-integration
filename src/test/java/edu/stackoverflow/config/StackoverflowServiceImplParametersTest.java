package edu.stackoverflow.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackoverflowServiceImplParametersTest {

    @Test
    public void readStackOverflowServiceParametersSanityTest() throws Exception {
        final StackoverflowServiceParameters parameters = StackoverflowServiceParameters.getInstance();
        assertEquals("https://api.stackexchange.com/2.2/answers?order=desc&sort=activity&site=stackoverflow",
                parameters.getAnswersURL());
    }
}
