package edu.stackoverflow.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.stackoverflow.config.StackoverflowServiceParameters;
import edu.stackoverflow.data.AnswersContainer;
import edu.stackoverflow.service.api.StackoverflowService;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * Implementation for {@code Stackoverflow service}.
 * @author tarek-nawara
 * @version 1.0
 */
@Singleton
public final class StackoverflowServiceImpl implements StackoverflowService {
    private static final CloseableHttpClient HTTP_CLIENT =
            HttpClients.createDefault();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(StackoverflowServiceImpl.class);

    private final StackoverflowServiceParameters parameters;

    /**
     * Constructor.
     *
     * @param parameters holder for all API URLs
     */
    @Inject
    public StackoverflowServiceImpl(final StackoverflowServiceParameters parameters) {
        this.parameters = parameters;
    }

    /** {@inheritDoc} */
    public Optional<AnswersContainer> getAnswers() {
        try {
            HttpGet httpGet = new HttpGet(parameters.getAnswersURL());
            final CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet);
            final HttpEntity entity = response.getEntity();
            final AnswersContainer answersContainer =
                    MAPPER.readValue(IOUtils.toString(entity.getContent(), Charset.defaultCharset()), AnswersContainer.class);
            return Optional.of(answersContainer);
        } catch (final IOException e) {
            LOGGER.warn("Get answers API call failed with exception={}", e.getMessage());
            return Optional.empty();
        }
    }
}
