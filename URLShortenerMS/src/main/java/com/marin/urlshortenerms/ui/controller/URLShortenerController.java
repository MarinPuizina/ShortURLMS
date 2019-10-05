package com.marin.urlshortenerms.ui.controller;

import com.marin.urlshortenerms.service.URLShortenerService;
import com.marin.urlshortenerms.ui.model.request.CreateShortURLRequestModel;
import com.marin.urlshortenerms.shared.IDConverter;
import com.marin.urlshortenerms.ui.model.response.CreateShortURLResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController //localhost:8080
public class URLShortenerController {

    private static int id;

    @Autowired
    IDConverter idConverter;

    @Autowired
    URLShortenerService urlShortenerService;


    @GetMapping("/url/shorten") //localhost:8080/url/shorten
    public String getShortenedURL() {



        return "ID is -> " + id + " , Unique ID is -> " + idConverter.createUniqueID(java.util.Optional.of(id));
    }

    /**
     * POST REQUEST: localhost:8080/url/shorten
     */
    @PostMapping(value = "/url/shorten",
                consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateShortURLResponseModel> createShortURL(@RequestBody CreateShortURLRequestModel requestModel) {

        String uniqueID = idConverter.createUniqueID(Optional.of(id += 1));

        urlShortenerService.storeUniqueIDAndSourceURL(uniqueID, Optional.of( requestModel.getSourceURL()));

        CreateShortURLResponseModel responseModel = new CreateShortURLResponseModel();
        responseModel.setShortenURL(urlShortenerService.createShortenURL(uniqueID));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

}
