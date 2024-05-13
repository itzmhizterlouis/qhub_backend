package com.qhub.qhub_backend;

import com.qhub.qhub_backend.models.responses.RandomQuoteResponse;
import com.qhub.qhub_backend.models.responses.YearPercentageResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class Controller {

    private final Service service;

    @GetMapping("year-percentage")
    public YearPercentageResponse getYearPercentage() {

        return service.getYearPercentage();
    }

    @GetMapping("random-quote")
    public RandomQuoteResponse getRandomQuote() {

        return service.getRandomQuote();
    }
}
