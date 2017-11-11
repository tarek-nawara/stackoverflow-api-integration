package edu.stackoverflow.data.utils

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper

class JsonTestingUtils {
    def readJsonFromFile(String filename, Class<?> className) {
        final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filename)
        final jsonSlurper = new JsonSlurper()
        final ownerJsonString = stream.text
        final jsonRepresentation = jsonSlurper.parseText(ownerJsonString)
        final mapper = new ObjectMapper()
        final classRepresentation = mapper.readValue(ownerJsonString, className)
        return [classRepresentation, jsonRepresentation]
    }
}
