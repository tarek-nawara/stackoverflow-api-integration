package edu.stackoverflow.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Representation of single answer returned from the API call.
 *
 * @author tarek-nawara
 * @version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Answer {
    private static final int DATE_SCALE = 1000;

    private final Owner owner;
    private final boolean isAccepted;
    private final int score;
    private final Date lastActivityDate;
    private final Date creationDate;
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
        this.lastActivityDate = new Date(lastActivityDate * DATE_SCALE);
        this.creationDate = new Date(creationDate * DATE_SCALE);
        this.answerId = answerId;
        this.questionId = questionId;
    }
}
