package edu.stackoverflow.service;

import edu.stackoverflow.config.StackoverflowServiceParameters;
import edu.stackoverflow.data.AnswersContainer;
import edu.stackoverflow.service.api.StackoverflowService;
import edu.stackoverflow.service.impl.StackoverflowServiceImpl;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class StackoverflowServiceImplTest {
    @Test
    public void requestAnswersSanityTest() throws Exception {
        final StackoverflowService stackoverflowServiceImpl = new StackoverflowServiceImpl(StackoverflowServiceParameters.getInstance());
        final Optional<AnswersContainer> answers = stackoverflowServiceImpl.getAnswers();
        assertTrue(answers != null);
    }
}
