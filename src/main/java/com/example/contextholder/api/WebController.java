package com.example.contextholder.api;

import com.example.contextholder.context.CustomContext;
import com.example.contextholder.context.CustomContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

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

    @GetMapping("/test2")
    public String testController2() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        CustomContext customContext = (CustomContext) Objects.requireNonNull(attributes)
                .getAttribute("context", RequestAttributes.SCOPE_REQUEST);
        return Objects.requireNonNull(customContext).getAuthToken() + "|" + customContext.getTenantProps();
    }
}
