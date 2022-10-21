package com.una.backend.aop;

import com.una.backend.entity.Log;
import com.una.backend.repository.LogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class PersonaAOP {
    @Autowired
    private LogRepository logRepository;

    @Before("execution (* com.una.backend.repository.PersonaRepository.findAll(..)) ")
    public void logBeforeFindAll(JoinPoint joinPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setDescripcion(joinPoint.getSignature().getName() + " de la tabla Persona");
        logRepository.save(log);
    }
    @Before("execution (* com.una.backend.repository.PersonaRepository.findById(..))")
    public void logBeforeFindById(JoinPoint joinPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setDescripcion(joinPoint.getSignature().getName() + " de la tabla Persona");
        logRepository.save(log);
    }

    @Before("execution (* com.una.backend.repository.PersonaRepository.save(..))")
    public void logBeforeSave(JoinPoint joinPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setDescripcion(joinPoint.getSignature().getName() + " de la tabla Persona");
        logRepository.save(log);
    }

    @Before("execution (* com.una.backend.repository.PersonaRepository.delete(..))")
    public void logBeforeDelete(JoinPoint joinPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setDescripcion(joinPoint.getSignature().getName() + " de la tabla Persona");
        logRepository.save(log);
    }
}
