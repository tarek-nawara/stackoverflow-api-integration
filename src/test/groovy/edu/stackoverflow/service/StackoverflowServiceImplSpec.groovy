package edu.stackoverflow.service

import com.google.inject.Guice
import edu.stackoverflow.guice.StackoverflowModule
import edu.stackoverflow.service.api.StackoverflowService
import spock.lang.Specification

class StackoverflowServiceImplSpec extends Specification {
    def "Calling answers API should work"() {
        given:
        final injector = Guice.createInjector(new StackoverflowModule())
        final stackoverflowService = injector.getInstance(StackoverflowService.class)

        expect:
        stackoverflowService.getAnswers().isPresent()
    }
}
