package com.example.contextholder.api;

import com.example.contextholder.context.CustomContextHolder;
import com.example.contextholder.context.CustomContextHolderImpl;
import com.example.contextholder.filter.ContextFilter;
import com.example.contextholder.filter.CustomContextFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {WebController.class, CustomContextHolderImpl.class})
class WebControllerTest {
    @Autowired
    CustomContextHolder customContextHolder;
    @Autowired
    WebController webController;
    MockMvc mockMvc;

    @Test
    void test() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(webController)
                .addFilters(new CustomContextFilter(customContextHolder))
                .build();

        mockMvc.perform(get("/test").header("Authorization", "token"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("token|props"));
    }

    @Test
    void test2() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(webController)
                .addFilters(new ContextFilter())
                .build();

        mockMvc.perform(get("/test2").header("Authorization", "token"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("token|props"));
    }
}
