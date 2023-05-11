package com.example.contextholder.api;

import com.example.contextholder.context.CustomContextHolder;
import com.example.contextholder.context.CustomContextHolderImpl;
import com.example.contextholder.filter.ContextFilter;
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

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(webController)
                .addFilters(new ContextFilter(customContextHolder))
                .build();
    }

    @Test
    void test() throws Exception {
        mockMvc.perform(get("/test").header("Authorization", "token"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("token|props"));
    }
}
