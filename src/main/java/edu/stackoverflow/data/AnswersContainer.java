package edu.stackoverflow.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Holder for all the answers coming from the API call.
 * @author tarek-nawara
 * @version 1.0
 */
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

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AnswersContainer that = (AnswersContainer) o;
        return Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }

    @Override
    public String toString() {
        return "AnswersContainer{"
                + "answers="
                + answers
                + '}';
    }
}
