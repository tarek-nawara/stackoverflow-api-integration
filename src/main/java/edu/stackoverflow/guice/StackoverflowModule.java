package edu.stackoverflow.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import edu.stackoverflow.config.StackoverflowServiceParameters;
import edu.stackoverflow.service.api.StackoverflowService;
import edu.stackoverflow.service.impl.StackoverflowServiceImpl;
import edu.stackoverflow.service.impl.StackoverflowServiceWithCachingImpl;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Guice module.
 *
 * @author tarek-nawara
 * @version 1.0
 */
public final class StackoverflowModule extends AbstractModule {
    private static final String CONFIG_FILE = "api.json";

    @Override
    protected void configure() {
        bind(StackoverflowService.class)
                .annotatedWith(Names.named("WithoutCaching"))
                .to(StackoverflowServiceImpl.class);
        bind(StackoverflowService.class).to(StackoverflowServiceWithCachingImpl.class);
    }

    /**
     * Read the API URLs from the {@code CONFIG_FILE}.
     *
     * @return API URLs.
     * @throws RuntimeException if failed to read
     */
    @Provides
    @Singleton
    StackoverflowServiceParameters provideStackoverflowServiceParameters() {
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
