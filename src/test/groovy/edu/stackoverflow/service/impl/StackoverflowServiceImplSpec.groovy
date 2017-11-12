package edu.stackoverflow.service.impl

import com.google.inject.Guice
import com.google.inject.Key
import com.google.inject.name.Names
import edu.stackoverflow.guice.StackoverflowModule
import edu.stackoverflow.service.api.StackoverflowService
import spock.lang.Specification

class StackoverflowServiceImplSpec extends Specification {
    def "Calling answers API should work"() {
        given:
        final injector = Guice.createInjector(new StackoverflowModule())
        final stackoverflowService = injector.getInstance(Key.get(StackoverflowService.class, Names.named("WithoutCaching")))

        expect:
        stackoverflowService.getAnswers() != null
    }
}
