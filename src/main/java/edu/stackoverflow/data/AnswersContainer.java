package edu.stackoverflow.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Holder for all the answers coming from the API call.
 *
 * @author tarek-nawara
 * @version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AnswersContainer {
    private final List<Answer> answers;

    /**
     * Constructor.
     *
     * @param answers list of answers coming from the API call.
     */
    @JsonCreator
    public AnswersContainer(@JsonProperty("items") final List<Answer> answers) {
        this.answers = answers;
    }

}
