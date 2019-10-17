package com.marin.urlshortenerms.ui.controller;

import com.marin.urlshortenerms.service.URLShortenerService;
import com.marin.urlshortenerms.shared.IDConverter;
import com.marin.urlshortenerms.ui.model.request.CreateShortURLRequestModel;
import com.marin.urlshortenerms.ui.model.response.CreateShortURLResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("/url") //localhost:8080/url
public class URLShortenerController {

    @Autowired
    IDConverter idConverter;

    @Autowired
    URLShortenerService urlShortenerService;

    @GetMapping("/status")
    public String getStatus() {
        return "URL shortener microservice is working!";
    }

    /**
     * POST REQUEST: localhost:8080/url/shorten
     * Zuul Gateway: localhost:8011/urlshortener-ms/url/shorten
     */
    @PostMapping(value = "/shorten",
                consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateShortURLResponseModel> createShortURL(@RequestBody CreateShortURLRequestModel requestModel) {

        urlShortenerService.createIDTrackerRedis();
        urlShortenerService.storeIncrementedIDTracker();

        String uniqueID = idConverter.createUniqueID(Optional.of(urlShortenerService.getStoredIDTracker()));
        urlShortenerService.storeUniqueIDAndSourceURL(uniqueID, Optional.of( requestModel.getSourceURL()));

        CreateShortURLResponseModel responseModel = new CreateShortURLResponseModel();
        responseModel.setShortenURL(urlShortenerService.createShortenURL(uniqueID));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    @GetMapping("/redirect/{uniqueId}")
    public RedirectView redirectUrl(@PathVariable String uniqueId) {

        String originalURL = urlShortenerService.getOriginalURL(uniqueId);

        if (originalURL!= null){

            RedirectView redirectView = new RedirectView(originalURL);

            return redirectView;
        }

        return new RedirectView("https://longeatonroundtable.files.wordpress.com/2017/09/file-not-found.jpg");
    }

}
