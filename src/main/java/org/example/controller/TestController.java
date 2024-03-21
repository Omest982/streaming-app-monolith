package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class TestController {
    @Operation(summary = "Test operation")
    @GetMapping
    public String test(){
        return "All works!";
    }
}
