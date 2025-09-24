package com.example.expass5.service;
import org.springframework.stereotype.Service;
import redis.clients.jedis.UnifiedJedis;
import java.util.Map;


@Service
public class pollService {
    private final UnifiedJedis jedis;

    public pollService(UnifiedJedis jedis) {
        this.jedis = jedis;
    }

    public void createPoll(String pollId, String title, Map<String, Integer> options) {
        String key = "poll:" + pollId;

        jedis.del(key);

        jedis.hset(key, "title", title);

        int i = 1;
        for (Map.Entry<String, Integer> entry : options.entrySet()) {
            jedis.hset(key, "option:" + i, entry.getKey());
            jedis.hset(key, "votes:" + i, entry.getValue().toString());
            i++;
        }
    }

    public void vote(String pollId, int optionNumber) {
        String key = "poll:" + pollId;
        jedis.hincrBy(key, "votes:" + optionNumber, 1 );
    }

    public Map<String, String> getPoll(String pollId) {
        String key = "poll:" + pollId;
        return jedis.hgetAll(key);
    }
}
