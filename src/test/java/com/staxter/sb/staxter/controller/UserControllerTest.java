package com.staxter.sb.staxter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.staxter.sb.staxter.service.IUserService;
import com.staxter.sb.staxter.to.UserInformation;
import com.staxter.sb.staxter.to.UserInformationWithPWD;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    IUserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showUser() throws Exception {

        // prepare mock result
        List<UserInformation> userInformations = new ArrayList<UserInformation>() {{
            add(new UserInformation(1, "Google", "World", "google_World"));
            add(new UserInformation(2, "Microsoft", "World", "microsoft_World"));

        }};
        // mock service
        when(userService.getAllUsers()).thenReturn(userInformations);

        //execute and validate
        MvcResult mvcResult = mockMvc.perform(get(("/userservice/users"))).andReturn();

        //validate
        assert (mvcResult.getResponse().getStatus() == 200);
        assert (mvcResult.getResponse().getContentAsString().contains("google_World") && mvcResult.getResponse().getContentAsString().contains("microsoft_World"));

    }

    // TODO write more negative scenarios
    @Test
    void createUser() throws Exception {

        // prepare mock result
        UserInformationWithPWD userInformationWithPWD = new UserInformationWithPWD(1, "Google", "World", "google_World", "Mypassword");
        when(userService.createUser(userInformationWithPWD)).thenReturn(new UserInformation(1, "Google", "World", "google_World"));


        String inputJson = mapToJson(userInformationWithPWD);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/userservice/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
        assert (mvcResult.getResponse().getContentAsString().contains("google_World") && !mvcResult.getResponse().getContentAsString().contains("Mypassword"));

    }

    // TODO this need to be moved to utility
    private String mapToJson(UserInformationWithPWD userInformationWithPWD) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(userInformationWithPWD);

    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}