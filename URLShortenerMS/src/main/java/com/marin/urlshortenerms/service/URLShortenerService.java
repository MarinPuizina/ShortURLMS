package com.marin.urlshortenerms.service;

import com.marin.urlshortenerms.shared.IDConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Optional;

@Service
@Slf4j
public class URLShortenerService {

    private final String IDTRACKER = "*IDTRACKER*";

    @Autowired
    IDConverter idConverter;

    @Autowired
    Jedis jedis;

    public URLShortenerService(IDConverter idConverter, Jedis jedis) {
        this.idConverter = idConverter;
        this.jedis = jedis;
    }

    public void createIDTrackerRedis() {
        if(jedis.get(IDTRACKER) == null || jedis.get(IDTRACKER).isEmpty()) {
            jedis.set(IDTRACKER, "0");
        }
    }

    public void storeIncrementedIDTracker() {

        Long idTracker = getStoredIDTracker() + 1L;

        jedis.set(IDTRACKER, String.valueOf(idTracker));
        log.info("Storing IDTracker on key: {}, with value: {}", IDTRACKER, idTracker);

    }

    public Long getStoredIDTracker() {
        return Long.parseLong(jedis.get(IDTRACKER));
    }

    public void storeUniqueIDAndSourceURL(String uniqueID, Optional<String> sourceURL) {

        //log.info("Storing uniqueID: {} , sourceURL: {}", uniqueID, sourceURL.get());

        if (!sourceURL.orElse("0").equals("0") ) {
            jedis.set(uniqueID, sourceURL.get());
        }

    }

    public String createShortenURL(String uniqueID) {

        //log.info("Creating shorten URL: www.shorty.com/{}", uniqueID);

        return "www.shorty.com/" + uniqueID;
    }

}
