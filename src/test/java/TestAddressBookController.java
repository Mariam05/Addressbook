import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import AddressBook.AddressBook;
import AddressBook.AddressBookController;
import AddressBook.ApplicationLauncher;
import AddressBook.BuddyInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = {ApplicationLauncher.class})
@AutoConfigureMockMvc
public class TestAddressBookController {

    @Autowired
    private AddressBookController controller;

    @Autowired
    private MockMvc mvc;

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testAddressBookAddition() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                        .post("http://localhost:8080/book/new")
                        .content("Imaginary Friends")
                        .contentType( MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuddyInfoAddition() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                        .post("http://localhost:8080/bud/save")
                        .param("name", "Arthur")
                        .param("phone", "123")
                        .param("book", "1")
                        .contentType( MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/buddies/1"))
                .andExpect(status().isOk());

    }





}