package edu.stackoverflow.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.stackoverflow.data.AnswersContainer;
import edu.stackoverflow.guice.StackoverflowModule;
import edu.stackoverflow.service.api.StackoverflowService;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class StackoverflowServiceImplTest {
    @Test
    public void requestAnswersSanityTest() throws Exception {
        final Injector injector = Guice.createInjector(new StackoverflowModule());
        final StackoverflowService stackoverflowService = injector.getInstance(StackoverflowService.class);
        final Optional<AnswersContainer> answers = stackoverflowService.getAnswers();
        assertTrue(answers != null);
    }
}
