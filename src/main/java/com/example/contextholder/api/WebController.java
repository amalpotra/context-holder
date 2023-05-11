package com.example.contextholder.api;

import com.example.contextholder.context.CustomContext;
import com.example.contextholder.context.CustomContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    private final CustomContextHolder customContextHolder;

    public WebController(CustomContextHolder customContextHolder) {
        this.customContextHolder = customContextHolder;
    }

    @GetMapping("/test")
    public String testController() {
        CustomContext customContext = customContextHolder.getContext();
        return customContext.getAuthToken() + "|" + customContext.getTenantProps();
    }
}
