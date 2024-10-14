package com.bnpp.kata.berlinclock.controller;

import com.bnpp.kata.berlinclock.model.BerlinClockRequest;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.bnpp.kata.berlinclock.constants.TestConstants.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BerlinClockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Rest API to calculate Berlin Time should return valid response")
    public void calculateBerlinClockTime_validRequest_shouldReturnBerlinClockResponse() throws Exception {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWENTYTHREE).minutes(FIFTYNINE).seconds(FIFTYNINE).build();
        BerlinClockRequest request = BerlinClockRequest.builder().time(timeComponent).build();

        mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.berlinTime").isNotEmpty())
                .andExpect(result -> assertNotNull(result.getResponse().getContentAsString()));
    }

    @Test
    @DisplayName("Rest API should throw exception when invalid input is passed")
    public void calculateBerlinClockTime_passInvalidInputRequest_shouldReturnBadRequest() throws Exception {

        TimeComponent timeComponent = TimeComponent.builder().hours(EMPTY).minutes(FIFTYNINE).seconds(FIFTYNINE).build();
        BerlinClockRequest request = BerlinClockRequest.builder().time(timeComponent).build();

        mockMvc.perform(post(API_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Rest API should throw exception when request contains null time")
    public void calculateBerlinClockTime_requestContainsNullTime_shouldReturnBadRequest() throws Exception {

        BerlinClockRequest request = BerlinClockRequest.builder().time(null).build();

        mockMvc.perform(post(API_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Rest API should throw internal server error when null request is passed")
    public void calculateBerlinClockTime_passNullRequest_shouldReturnInternalServerError() throws Exception {

        mockMvc.perform(post(API_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isInternalServerError());
    }
}
