package com.example.expass5.service;
import org.springframework.stereotype.Service;
import redis.clients.jedis.UnifiedJedis;

import java.util.Set;


@Service
public class loggedInService {

    private final UnifiedJedis jedis;
    private static final String SET_KEY = "loggedIn";

    public loggedInService(UnifiedJedis jedis) {
        this.jedis = jedis;
    }

    public void login(String username) {
        jedis.sadd(SET_KEY, username);
    }

    public void logout(String username) {
        jedis.srem(SET_KEY, username);
    }

    public Set<String> getLoggedInUsers() {
        return jedis.smembers(SET_KEY);
    }
}
