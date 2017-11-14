package edu.stackoverflow.engine

import edu.stackoverflow.data.AnswersContainer
import edu.stackoverflow.data.utils.JsonTestingUtils
import spock.lang.Specification

class EngineSpec extends Specification {
    def "Calling rank owners with score should work"() {
        given:
        final engine = new Engine(loadAnswersContainer())

        expect:
        engine.rankOwnersWithScore().head()._1().getUserId() == 4984832
    }

    def "Calling rank answers with score should work"() {
        given:
        final engine = new Engine(loadAnswersContainer())

        expect:
        engine.rankAnswersWithScore().head().getAnswerId() == 47272554
    }

    def "Calling find answers after creation date should work"() {
        given:
        final engine = new Engine(loadAnswersContainer())
        final date = new Date(1510603222L * 1000)

        expect:
        engine.findAnswersAfterCreationDate(date).length() == 2
    }


    private static AnswersContainer loadAnswersContainer() {
        final jsonTestUtils = new JsonTestingUtils()
        def (answersContainer, _) = jsonTestUtils.readJsonFromFile("engine-test.json", AnswersContainer.class)
        return answersContainer
    }
}
