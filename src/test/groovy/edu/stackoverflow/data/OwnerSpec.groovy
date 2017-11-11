package edu.stackoverflow.data

import edu.stackoverflow.data.utils.JsonTestingUtils
import spock.lang.Specification

class OwnerSpec extends Specification {
    def "Constructing owner from json should work"() {
        given:
        final jsonTestingUtils = new JsonTestingUtils()
        final def (owner, json) = jsonTestingUtils.readJsonFromFile("owner.json", Owner.class)

        expect:
        owner.getReputation() == json.reputation
    }
}
