package edu.stackoverflow.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Holder for all the API URLs.
 * @author tarek-nawara
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StackoverflowServiceParameters {
    private static final StackoverflowServiceParameters INSTANCE = readStackoverflowServiceParameters();
    private static final String CONFIG_FILE = "api.json";

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

    public static StackoverflowServiceParameters getInstance() {
        return INSTANCE;
    }

    /**
     * Read the API URLs from the {@code CONFIG_FILE}.
     *
     * @return API URLs.
     * @throws RuntimeException if failed to read
     */
    private static StackoverflowServiceParameters readStackoverflowServiceParameters() {
        try {
            final InputStream stream =
                    StackoverflowServiceParameters.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
            final String configFile = IOUtils.toString(stream, Charset.defaultCharset());
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(configFile, StackoverflowServiceParameters.class);
        } catch (final Exception e) {
            throw new RuntimeException("Failed to read stackoverflow service parameters");
        }
    }
}
