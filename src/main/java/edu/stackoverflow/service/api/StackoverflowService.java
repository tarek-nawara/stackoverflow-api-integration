package edu.stackoverflow.service.api;

import edu.stackoverflow.data.AnswersContainer;

import java.util.Optional;

/**
 * Interface representing services available by all
 * the {@code Stackoverflow services}.
 *
 * @author tarek-nawara
 * @version 1.0
 */
public interface StackoverflowService {
    /**
     * Get the answers from stackoverflow API.
     *
     * @return {@code AnswersContainer} instance, or
     * {@code Optional.empty} if call failed
     * @see AnswersContainer
     */
    Optional<AnswersContainer> getAnswers();
}
