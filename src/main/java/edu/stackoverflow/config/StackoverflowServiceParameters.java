package edu.stackoverflow.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.inject.Singleton;

/**
 * Holder for all the API URLs.
 * @author tarek-nawara
 * @version 1.0
 */
@Singleton
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StackoverflowServiceParameters {
    private final String answersURL;

    /**
     * Constructor.
     *
     * @param answersURL URL of answer API
     */
    @JsonCreator
    public StackoverflowServiceParameters(@JsonProperty("answers_url") final String answersURL) {
        this.answersURL = answersURL;
    }

    public String getAnswersURL() {
        return answersURL;
    }
}
