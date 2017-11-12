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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Implementation for {@code Stackoverflow service}.
 *
 * @author tarek-nawara
 * @version 1.0
 */
@Singleton
public final class StackoverflowServiceImpl implements StackoverflowService {
    private static final CloseableHttpClient HTTP_CLIENT =
            HttpClients.createDefault();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(StackoverflowServiceImpl.class);
    private static final int GET_ANSWER_TIMEOUT = 60;
    private static final int GET_ANSWER_WITH_ID_TIMEOUT = 10;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AnswersContainer> getAnswers() {
        return executeRequest(parameters.getAnswersURL(), AnswersContainer.class, GET_ANSWER_TIMEOUT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AnswersContainer> getAnswerWithId(final long id) {
        return executeRequest(parameters.getAnswersWithIdURL(id), AnswersContainer.class, GET_ANSWER_WITH_ID_TIMEOUT);
    }

    /**
     * Execute the API request, but limiting the execution
     * timeout with {@code timeout} parameter.
     *
     * @param url       request url
     * @param valueType class to marshall the response to
     * @param timeOut   limiting execution time
     * @param <T>       return type
     * @return optional of {@code T}
     */
    private static <T> Optional<T> executeRequest(final String url, final Class<T> valueType, final int timeOut) {
        try {
            return CompletableFuture
                    .supplyAsync(() -> executeRequest(url, valueType))
                    .get(timeOut, TimeUnit.SECONDS);
        } catch (final Exception e) {
            LOGGER.error("API call timed out");
            return Optional.empty();
        }
    }

    /**
     * Execute the API request, then marshall the API response
     * to the given class type.
     *
     * @param url       request url
     * @param valueType class to marshall the response to
     * @param <T>       return type
     * @return optional of {@code T}
     */
    private static <T> Optional<T> executeRequest(final String url, final Class<T> valueType) {
        try {
            final HttpGet httpGet = new HttpGet(url);
            final CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet);
            final HttpEntity entity = response.getEntity();
            final T answersContainer =
                    MAPPER.readValue(IOUtils.toString(entity.getContent(), Charset.defaultCharset()), valueType);
            return Optional.of(answersContainer);
        } catch (final IOException e) {
            LOGGER.warn("API call failed with exception={}", e.getMessage());
            return Optional.empty();
        }
    }
}
