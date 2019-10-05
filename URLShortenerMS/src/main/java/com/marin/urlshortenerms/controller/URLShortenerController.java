package com.marin.urlshortenerms.controller;

import com.marin.urlshortenerms.shared.IDConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLShortenerController {

    private static int id;

    IDConverter idConverter;

    @Autowired
    public URLShortenerController(IDConverter idConverter) {
        this.idConverter = idConverter;
    }

    @GetMapping("/url")
    public String getShortenedURL() {
        id +=1;
        return id + "";
    }

}
