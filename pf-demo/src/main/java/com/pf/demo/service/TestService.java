package com.pf.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Author: Ru He
 * Date: Created in 2019/1/29.
 * Description:
 */
@Service
public class TestService {

    @Async
    public void testAsync(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---> ok");
    }

}
