package edu.stackoverflow.data;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Representation of the answer owner.
 *
 * @author tarek-nawara
 * @version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Owner {
    private final int reputation;
    private final long userId;
    private final String userType;
    private final String profileImage;
    private final String displayName;

    /**
     * Constructor.
     *
     * @param reputation   answer owner reputation
     * @param userId       answer owner id
     * @param userType     answer owner type, valid values `registered`
     * @param profileImage image associated with owner id.
     * @param displayName  name of answer owner
     */
    @JsonCreator
    public Owner(@JsonProperty("reputation") final int reputation,
                 @JsonProperty("user_id") final int userId,
                 @JsonProperty("user_type") final String userType,
                 @JsonProperty("profile_image") final String profileImage,
                 @JsonProperty("display_name") final String displayName) {
        this.reputation = reputation;
        this.userId = userId;
        this.userType = userType;
        this.profileImage = profileImage;
        this.displayName = displayName;
    }
}
