package edu.stackoverflow.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnswerTest {

    @Test
    public void answerConstructionSanityTest() throws Exception {
        final String answerJsonRepresentation =
                "{\"owner\":{\"reputation\":902,\"user_id\":1738214,\"user_type\":\"registered\"," +
                        "\"profile_image\":\"https://i.stack.imgur.com/oz3ot.jpg?s=128&g=1\"," +
                        "\"display_name\":\"gcucurull\",\"link\":\"https://stackoverflow.com/users/1738214/gcucurull\"}," +
                        "\"is_accepted\":false,\"score\":1,\"last_activity_date\":1510303398,\"last_edit_date\":1510303398," +
                        "\"creation_date\":1495187265,\"answer_id\":44066729,\"question_id\":35789500},";

        final ObjectMapper mapper = new ObjectMapper();
        final Answer answer = mapper.readValue(answerJsonRepresentation, Answer.class);
        assertEquals( 44066729, answer.getAnswerId());
        assertTrue(answer.getOwner() != null);
    }
}
