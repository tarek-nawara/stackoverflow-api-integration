package edu.stackoverflow.data

import edu.stackoverflow.data.utils.JsonTestingUtils
import spock.lang.Specification

class AnswerSpec extends Specification {
    def "Constructing answer from json should work"() {
        given:
        final jsonTestingUtils = new JsonTestingUtils()
        final def (answer, json) = jsonTestingUtils.readJsonFromFile("answer.json", Answer.class)

        expect:
        answer.getScore() == json.score
    }
}
