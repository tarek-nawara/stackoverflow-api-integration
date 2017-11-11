package edu.stackoverflow.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OwnerTest {
    @Test
    public void constructOwnerSanityTest() throws Exception {
        final String ownerJsonRepresentation =
               "{\"reputation\":902,\"user_id\":1738214,\"user_type\":\"registered\"," +
                       "\"profile_image\":\"https://i.stack.imgur.com/oz3ot.jpg?s=128&g=1\"," +
                       "\"display_name\":\"gcucurull\",\"link\":\"https://stackoverflow.com/users/1738214/gcucurull\"}";

        ObjectMapper mapper = new ObjectMapper();
        final Owner owner = mapper.readValue(ownerJsonRepresentation, Owner.class);
        assertEquals( 902, owner.getReputation());
        assertEquals( 1738214, owner.getUserId());
    }

    @Test
    public void getOwnerJsonRepresentation() throws Exception {
        final Owner owner = new Owner(1, 2, "registered", "image", "display_name");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(owner));
        assertTrue(true);
    }
}
