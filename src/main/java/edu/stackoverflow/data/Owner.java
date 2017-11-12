package edu.stackoverflow.data;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Representation of the answer owner.
 *
 * @author tarek-nawara
 * @version 1.0
 */
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

    public int getReputation() {
        return reputation;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Owner owner = (Owner) o;
        return reputation == owner.reputation
                && userId == owner.userId
                && Objects.equals(userType, owner.userType)
                && Objects.equals(profileImage, owner.profileImage)
                && Objects.equals(displayName, owner.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reputation, userId, userType, profileImage, displayName);
    }

    @Override
    public String toString() {
        return "Owner{"
                + "reputation="
                + reputation
                + ", userId="
                + userId
                + ", userType='"
                + userType + '\''
                + ", profileImage='"
                + profileImage + '\''
                + ", displayName='"
                + displayName + '\''
                + '}';
    }
}
