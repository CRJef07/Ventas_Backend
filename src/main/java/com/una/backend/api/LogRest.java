package com.una.backend.api;

import com.una.backend.entity.Log;
import com.una.backend.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/log")
public class LogRest {
    @Autowired
    private LogRepository logRepository;

    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseStatus(HttpStatus.OK)
    public List<Log> findAll(){
        return (List<Log>) logRepository.findAll();
    }
}
