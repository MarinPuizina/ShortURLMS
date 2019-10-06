package com.marin.urlshortenerms.service;

import com.marin.urlshortenerms.shared.IDConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Optional;

import static com.marin.urlshortenerms.shared.URLShortenerConstants.ID_TRACKER;
import static com.marin.urlshortenerms.shared.URLShortenerConstants.SHORT_URL;

@Service
@Slf4j
public class URLShortenerService {

    @Autowired
    IDConverter idConverter;

    @Autowired
    Jedis jedis;

    public URLShortenerService(IDConverter idConverter, Jedis jedis) {
        this.idConverter = idConverter;
        this.jedis = jedis;
    }

    /**
     * Creates ID Tracker key with value in database.
     */
    public void createIDTrackerRedis() {
        if(jedis.get(ID_TRACKER) == null || jedis.get(ID_TRACKER).isEmpty()) {
            jedis.set(ID_TRACKER, "0");
        }
    }

    /**
     * Stores incremented value of the ID Tracker.
     */
    public void storeIncrementedIDTracker() {

        Long idTracker = getStoredIDTracker() + 1L;

        jedis.set(ID_TRACKER, String.valueOf(idTracker));
        log.info("Storing IDTracker on key: {}, with value: {}", ID_TRACKER, idTracker);

    }

    /**
     * Returns value of the ID Tracker.
     *
     * @return Value of the ID Tracker.
     */
    public Long getStoredIDTracker() {
        return Long.parseLong(jedis.get(ID_TRACKER));
    }

    /**
     * Stores Unique ID and Source URL in database.
     *
     * @param uniqueID The key for storing the sourceURL.
     * @param sourceURL Original URL which we want to store.
     */
    public void storeUniqueIDAndSourceURL(String uniqueID, Optional<String> sourceURL) {

        //log.info("Storing uniqueID: {} , sourceURL: {}", uniqueID, sourceURL.get());

        if (!sourceURL.orElse("0").equals("0") ) {
            jedis.set(uniqueID, sourceURL.get());
        }

    }

    /**
     * Creates shorten URL.
     *
     * @param uniqueID Used for creating unique short URL.
     * @return Shorten URL.
     */
    public String createShortenURL(String uniqueID) {

        //log.info("Creating shorten URL: www.shorty.com/{}", uniqueID);

        return SHORT_URL + uniqueID;
    }

}
