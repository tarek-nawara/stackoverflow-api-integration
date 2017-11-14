package edu.stackoverflow.service.impl

import com.google.inject.Guice
import com.google.inject.Key
import com.google.inject.name.Names
import edu.stackoverflow.guice.StackoverflowModule
import edu.stackoverflow.guice.StackoverflowTestingModule
import edu.stackoverflow.service.api.StackoverflowService
import spock.lang.Specification

class StackoverflowServiceImplSpec extends Specification {
    def "Calling answers API should work"() {
        given:
        final stackoverflowService = getStackoverflowService()

        expect:
        stackoverflowService.getAnswers() != null
    }

    def "Calling answers with id API should work"() {
        given:
        final stackoverflowService = getStackoverflowService()

        expect:
        stackoverflowService.getAnswerWithId(1234) != null
    }

    def getStackoverflowService() {
        final injector = Guice.createInjector(new StackoverflowTestingModule())
        return injector.getInstance(Key.get(StackoverflowService.class, Names.named("WithoutCaching")))
    }
}
