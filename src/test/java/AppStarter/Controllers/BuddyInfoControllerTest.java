package AppStarter.Controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import AppStarter.JpaMemoryDatabase.BuddyInfo;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BuddyInfoControllerTest {
    @Autowired
    private BuddyInfoController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMakeBuddy() throws Exception {
        this.mockMvc.perform(post("/buddy")
                .param("id", "444")
                .param("name", "YEET")
                .param("phoneNumber", "999999")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(content().string(containsString("id: 444")))
            .andReturn();
    }
}
