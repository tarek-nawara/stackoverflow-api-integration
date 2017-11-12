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

    /**
     * Get the answer with the given id.
     *
     * @param id id of the target answer
     * @return the answer with the given id, or
     * {@code Optional.empty} if API call failed
     * @see AnswersContainer
     */
    Optional<AnswersContainer> getAnswerWithId(long id);
}
