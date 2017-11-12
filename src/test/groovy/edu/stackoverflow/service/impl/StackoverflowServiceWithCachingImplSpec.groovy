package edu.stackoverflow.service.impl

import com.google.inject.Guice
import edu.stackoverflow.guice.StackoverflowModule
import edu.stackoverflow.service.api.StackoverflowService
import spock.lang.Specification

class StackoverflowServiceWithCachingImplSpec extends Specification {

    def "stack overflow service with caching sanity test"() {
        given:
        final injector = Guice.createInjector(new StackoverflowModule())
        final stackoverflowService = injector.getInstance(StackoverflowService.class)

        expect:
        stackoverflowService.getAnswers() != null
        stackoverflowService.getAnswerWithId(123) != null
    }

    def "caching should work correctly"() {
        given:
        StackoverflowService mockedService = Mock()
        final stackoverflowService = new StackoverflowServiceWithCachingImpl(mockedService)

        when:
        100.times {
            stackoverflowService.getAnswerWithId(000000000)
        }

        then:
        1 * mockedService.getAnswerWithId(_) >> Optional.empty()
    }
}
