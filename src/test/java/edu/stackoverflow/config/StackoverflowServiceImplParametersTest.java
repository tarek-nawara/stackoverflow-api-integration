package edu.stackoverflow.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.stackoverflow.guice.StackoverflowModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackoverflowServiceImplParametersTest {

    @Test
    public void readStackOverflowServiceParametersSanityTest() throws Exception {
        final Injector injector = Guice.createInjector(new StackoverflowModule());
        final StackoverflowServiceParameters parameters = injector.getInstance(StackoverflowServiceParameters.class);
        assertEquals("https://api.stackexchange.com/2.2/answers?order=desc&sort=activity&site=stackoverflow",
                parameters.getAnswersURL());
    }
}
