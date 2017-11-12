package edu.stackoverflow.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import edu.stackoverflow.data.AnswersContainer;
import edu.stackoverflow.service.api.StackoverflowService;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Wrapper around {@code stackoverflow service} that supports
 * caching API responses.
 *
 * @author tarek-nawara
 * @version 1.0
 */
@Singleton
public final class StackoverflowServiceWithCachingImpl implements StackoverflowService {
    private static final int MAX_CACHE_SIZE = 1000;
    private static final int CACHING_DURATION = 10;

    private final StackoverflowService stackoverflowService;
    private final LoadingCache<Long, Optional<AnswersContainer>> answersCache;

    /**
     * Constructor.
     *
     * @param stackoverflowService concrete implementation of {@code stackoverflow service}
     */
    @Inject
    public StackoverflowServiceWithCachingImpl(
            @Named("WithoutCaching") final StackoverflowService stackoverflowService) {
        this.stackoverflowService = stackoverflowService;
        this.answersCache = buildCache(stackoverflowService::getAnswerWithId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AnswersContainer> getAnswers() {
        return stackoverflowService.getAnswers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AnswersContainer> getAnswerWithId(final long id) {
        return answersCache.getUnchecked(id);
    }

    /**
     * Build a loading cache given a {@code keySupplier}.
     *
     * @param keySupplier supplier for the cache keys
     * @param <K>         type of cache key
     * @param <V>         type of cache value
     * @return {@code LoadingCache} instance
     */
    private static <K, V> LoadingCache<K, V> buildCache(final Function<K, V> keySupplier) {
        return CacheBuilder.newBuilder()
                .expireAfterAccess(CACHING_DURATION, TimeUnit.MINUTES)
                .maximumSize(MAX_CACHE_SIZE)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(@Nonnull final K key) throws Exception {
                        return keySupplier.apply(key);
                    }
                });
    }
}
