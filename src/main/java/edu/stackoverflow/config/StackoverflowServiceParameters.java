package edu.stackoverflow.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.inject.Singleton;

/**
 * Holder for all the API URLs.
 *
 * @author tarek-nawara
 * @version 1.0
 */
@Singleton
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StackoverflowServiceParameters {
    private final String answersURL;
    private final String answersWithIdURL;

    /**
     * Constructor.
     *
     * @param answersURL       URL of answer API
     * @param answersWithIdURL URL of answer with id API
     */
    @JsonCreator
    public StackoverflowServiceParameters(
            @JsonProperty("answers_url") final String answersURL,
            @JsonProperty("answers_with_id_url") final String answersWithIdURL) {
        this.answersURL = answersURL;
        this.answersWithIdURL = answersWithIdURL;
    }

    public String getAnswersURL() {
        return answersURL;
    }

    public String getAnswersWithIdURL() {
        return answersWithIdURL;
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
