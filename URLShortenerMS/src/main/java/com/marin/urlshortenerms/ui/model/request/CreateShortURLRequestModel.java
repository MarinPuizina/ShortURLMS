package com.marin.urlshortenerms.ui.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateShortURLRequestModel {

    private String sourceURL;

}
