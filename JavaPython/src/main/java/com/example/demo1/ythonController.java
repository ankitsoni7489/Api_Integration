package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ythonController {

    @Autowired
    private PythonService pythonService;

    @GetMapping("/send-data")
    public String sendData() {
        pythonService.sendDataToFlask();  // Call service method
        return "Data sent to Flask and modified data received! Check console.";
    }
}
