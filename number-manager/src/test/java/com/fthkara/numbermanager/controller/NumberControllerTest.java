package com.fthkara.numbermanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fthkara.numbermanager.model.NumberDocument;
import com.fthkara.numbermanager.service.NumberService;
import com.fthkara.numbermanager.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(NumberController.class)
public class NumberControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private NumberService numberService;

    @Test
    public void createNewNumber() throws Exception {

        NumberDocument number = new NumberDocument(56, DateUtil.getDate());

        given(numberService.save(Mockito.any(NumberDocument.class))).willReturn(number);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/numbers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(number)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number", is(number.getNumber())));
    }

    @Test
    public void getNumber() throws Exception {

        NumberDocument number = new NumberDocument(10, DateUtil.getDate());

        given(numberService.findByNumber(anyInt())).willReturn(number);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/numbers/25").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", equalTo(number.getNumber())));
    }

    @Test
    public void getMaximumNumber() throws Exception {
        NumberDocument number = new NumberDocument(10, DateUtil.getDate());

        given(numberService.findMaximumNumber()).willReturn(number.getNumber());


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/numbers/maximum").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(number.getNumber())));
    }

    @Test
    public void getMinumumNumber() throws Exception {

        NumberDocument number = new NumberDocument(10, DateUtil.getDate());

        given(numberService.findMinimumNumber()).willReturn(number.getNumber());


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/numbers/minimum").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(number.getNumber())));
    }

    @Test
    public void deleteNumber() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/numbers/20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllNumbers() throws Exception {

        NumberDocument number1 = new NumberDocument(10, DateUtil.getDate());

        NumberDocument number2 = new NumberDocument(8, DateUtil.getDate());

        NumberDocument number3 = new NumberDocument(6, DateUtil.getDate());

        NumberDocument number4 = new NumberDocument(2, DateUtil.getDate());

        List<NumberDocument> numbers = Arrays.asList(number1, number2, number3, number4);

        given(numberService.findAllByNumber(anyString())).willReturn(numbers);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/numbers")
                        .contentType(MediaType.APPLICATION_JSON)
                .param("sort", "DESC")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[3].number", equalTo(number4.getNumber())));

    }
}