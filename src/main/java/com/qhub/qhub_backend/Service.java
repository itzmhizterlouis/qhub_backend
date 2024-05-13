package com.qhub.qhub_backend;


import com.qhub.qhub_backend.exceptions.QuoteNotFoundException;
import com.qhub.qhub_backend.models.Quote;
import com.qhub.qhub_backend.models.responses.RandomQuoteResponse;
import com.qhub.qhub_backend.models.responses.YearPercentageResponse;
import com.qhub.qhub_backend.repositories.QuoteRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {

    private final QuoteRepository quoteRepository;

    public YearPercentageResponse getYearPercentage() {

        LocalDate currentDate = LocalDate.now();

        LocalDate startOfYear = LocalDate.of(currentDate.getYear(), 1, 1);
        LocalDate endOfYear = LocalDate.of(currentDate.getYear(), 12, 31);

        long daysPassed = ChronoUnit.DAYS.between(startOfYear, currentDate);

        long totalDaysInYear = ChronoUnit.DAYS.between(startOfYear, endOfYear) + 1;

        double percentageLeft = 100.0 - (1 - (double) daysPassed / totalDaysInYear) * 100;

        String percentage = String.format("%.2f", percentageLeft);

        return YearPercentageResponse.builder()
                .percent(percentage)
                .build();
    }

    public RandomQuoteResponse getRandomQuote() {
        Quote quote;

        do {

            quote = findRandomQuote().orElseThrow();
        } while (quote == null);

        return quote.toDto();
    }

    private Optional<Quote> findRandomQuote() {

        Random random = new Random();

        long index = random.nextInt(1, 401);

        return quoteRepository.findById(index);

    }
}
