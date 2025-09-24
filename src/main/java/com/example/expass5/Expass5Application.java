package com.example.expass5;

import com.example.expass5.service.loggedInService;
import com.example.expass5.service.pollService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Expass5Application {

    public static void main(String[] args) {
        SpringApplication.run(Expass5Application.class, args);
    }

    @Bean
    ApplicationRunner expass5(loggedInService caseUsers, pollService casePoll) {
        return args -> {
            caseUsers.login("alice");
            caseUsers.login("bob");
            caseUsers.logout("alice");
            caseUsers.login("oda");
            System.out.println("Logged in users: " + caseUsers.getLoggedInUsers());


            String pollId = "03ebcb7b-bd69-440b-924e-f5b7d664af7b";
            Map<String, Integer> options = new HashMap<>();
            options.put("Yes, yammy!", 269);
            options.put("Mamma mia, nooooo!", 268);
            options.put("I do not really care ...", 42);

            casePoll.createPoll(pollId, "Pineapple on Pizza?", options);
            casePoll.vote(pollId, 1);
            System.out.println("Poll state: " +  casePoll.getPoll(pollId));
        };
    }
}
