package AppStarter.Controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import AppStarter.JpaMemoryDatabase.BuddyInfo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AddressBookControllerTest {
    @Autowired
    private AddressBookController controller;

    @Autowired
    private MockMvc mockMvc;

    public AddressBookControllerTest() {
    }

    @Test
    public void testMakeAddressBook() throws Exception {

        JSONObject buddy = new JSONObject();
        buddy.put("phoneNumber", 123L);
        buddy.put("name", "WHYWHYWHY");
        buddy.put("id", 123);

        this.mockMvc.perform(post("/createAddressBook")
                .param("id", "555")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(containsString("id: 555")))
                .andReturn();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
