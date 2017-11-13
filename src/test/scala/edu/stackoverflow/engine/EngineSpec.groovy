package edu.stackoverflow.engine

import edu.stackoverflow.data.AnswersContainer
import edu.stackoverflow.data.utils.JsonTestingUtils
import spock.lang.Specification

class EngineSpec extends Specification {
    def "Calling rank owners with score should work"() {
        given:
        final jsonTestUtils = new JsonTestingUtils()
        def (answersContainer, _) = jsonTestUtils.readJsonFromFile("engine-test.json", AnswersContainer.class)
        final engine = new Engine()

        expect:
        engine.rankOwnersWithScore(answersContainer) != null
    }

}
