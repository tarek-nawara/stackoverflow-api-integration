package edu.stackoverflow.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.inject.Singleton;
import lombok.Data;

/**
 * Holder for all the API URLs.
 *
 * @author tarek-nawara
 * @version 1.0
 */
@Data
@Singleton
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StackoverflowServiceParameters {
    private final String answersURL;
    private final String answersWithIdURL;
    private final int answersTimeout;
    private final int answerWithIdTimeout;

    /**
     * Constructor.
     *
     * @param answersURL          URL of answer API
     * @param answersWithIdURL    URL of answer with id API
     * @param answersTimeout      timeout for calling get all answers API
     * @param answerWithIdTimeout timeout for calling get answer with ID API
     */
    @JsonCreator
    public StackoverflowServiceParameters(
            @JsonProperty("answers_url") final String answersURL,
            @JsonProperty("answers_with_id_url") final String answersWithIdURL,
            @JsonProperty("answers_timeout") final int answersTimeout,
            @JsonProperty("answer_with_id_timeout") final int answerWithIdTimeout) {
        this.answersURL = answersURL;
        this.answersWithIdURL = answersWithIdURL;
        this.answersTimeout = answersTimeout;
        this.answerWithIdTimeout = answerWithIdTimeout;
    }

    /**
     * Build {@code answerWithIdURL} with the given id.
     *
     * @param id target answer id
     * @return URL with the given id
     */
    public String getAnswersWithIdURL(final long id) {
        return String.format(answersWithIdURL, id);
    }
}
