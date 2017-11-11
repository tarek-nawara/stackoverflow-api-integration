import com.google.inject.Guice
import edu.stackoverflow.config.StackoverflowServiceParameters
import edu.stackoverflow.guice.StackoverflowModule
import groovy.json.JsonSlurper
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class StackoverflowServiceParametersSpec extends Specification {
    def "reading stackoverflow service parameters should work"() {
        given:
        def injector = Guice.createInjector(new StackoverflowModule())
        def stackoverflowServiceParameters = injector.getInstance(StackoverflowServiceParameters.class)
        def jsonSlurper = new JsonSlurper()
        def apiURLs = jsonSlurper.parse(this.getClass().getClassLoader().getResourceAsStream("api.json"))
        expect:
        stackoverflowServiceParameters.getAnswersURL() ==
                apiURLs.answers_url
    }
}