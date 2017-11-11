package edu.stackoverflow.data

import edu.stackoverflow.data.utils.JsonTestingUtils
import spock.lang.Specification

class AnswersContainerSpec extends Specification {
    def "Constructing answers container from json should work"() {
        given:
        final jsonTestingUtils = new JsonTestingUtils()
        final def (answersContainer, json) = jsonTestingUtils.readJsonFromFile("answers-container.json", AnswersContainer.class)

        expect:
        answersContainer.getAnswers().size() == json.items.size()
    }
}
