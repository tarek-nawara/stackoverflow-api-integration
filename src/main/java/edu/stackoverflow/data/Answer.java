package edu.stackoverflow.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Representation of single answer returned from the API call.
 *
 * @author tarek-nawara
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Answer {
    private final Owner owner;
    private final boolean isAccepted;
    private final int score;
    private final long lastActivityDate;
    private final long creationDate;
    private final long answerId;
    private final long questionId;

    /**
     * Constructor.
     *
     * @param owner            answer owner
     * @param isAccepted       if answer is accepted or not
     * @param score            answer score
     * @param lastActivityDate last updated time
     * @param creationDate     time of answer creation
     * @param answerId         id of answer
     * @param questionId       question associated with this answer
     */
    @JsonCreator
    public Answer(@JsonProperty("owner") final Owner owner,
                  @JsonProperty("is_accepted") final boolean isAccepted,
                  @JsonProperty("score") final int score,
                  @JsonProperty("last_activity_date") final long lastActivityDate,
                  @JsonProperty("creation_date") final long creationDate,
                  @JsonProperty("answer_id") final long answerId,
                  @JsonProperty("question_id") final long questionId) {
        this.owner = owner;
        this.isAccepted = isAccepted;
        this.score = score;
        this.lastActivityDate = lastActivityDate;
        this.creationDate = creationDate;
        this.answerId = answerId;
        this.questionId = questionId;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public int getScore() {
        return score;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public long getAnswerId() {
        return answerId;
    }

    public long getQuestionId() {
        return questionId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Answer answer = (Answer) o;
        return isAccepted == answer.isAccepted
                && score == answer.score
                && lastActivityDate == answer.lastActivityDate
                && creationDate == answer.creationDate
                && answerId == answer.answerId
                && questionId == answer.questionId
                && Objects.equals(owner, answer.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, isAccepted, score, lastActivityDate, creationDate, answerId, questionId);
    }

    @Override
    public String toString() {
        return "Answer{"
                + "owner="
                + owner
                + ", isAccepted="
                + isAccepted
                + ", score="
                + score
                + ", lastActivityDate="
                + lastActivityDate
                + ", creationDate="
                + creationDate
                + ", answerId="
                + answerId
                + ", questionId="
                + questionId
                + '}';
    }
}
