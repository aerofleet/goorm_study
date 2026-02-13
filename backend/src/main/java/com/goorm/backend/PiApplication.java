package com.goorm.backend;


import com.goorm.backend.service.PiService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PiApplication implements ApplicationRunner {
    private final PiService piService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("PI with 10,000 points = "+ piService.calculate(10000));
        System.out.println("PI with 100,000 points = "+ piService.calculate(100000));
        System.out.println("PI with 1,000,000 points = "+ piService.calculate(1000000));
        System.out.println("PI with 10,000,000 points = "+ piService.calculate(10000000));
        System.out.println("PI with 100,000,000 points = "+ piService.calculate(100000000));
    }
}
