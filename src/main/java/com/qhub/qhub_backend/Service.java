package com.qhub.qhub_backend;


import com.qhub.qhub_backend.exceptions.QuoteNotFoundException;
import com.qhub.qhub_backend.models.Quote;
import com.qhub.qhub_backend.models.Semester;
import com.qhub.qhub_backend.models.responses.RandomQuoteResponse;
import com.qhub.qhub_backend.models.responses.YearPercentageResponse;
import com.qhub.qhub_backend.repositories.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;

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

        double percentGone = 100.0 - (1 - (double) daysPassed / totalDaysInYear) * 100;

        String percentage = String.format("%.2f", percentGone);

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

    public SemesterPercentageResponse getSemesterPercentage() {

        LocalDate currentDate = LocalDate.now();
        double percentGone;
        Semester semester;

        LocalDate alphaStart = LocalDate.of(currentDate.getYear(), 9, 30);
        LocalDate alphaMiddleEnd = LocalDate.of(currentDate.getYear() + 1, 1, 1);
        LocalDate alphaMiddleStart = LocalDate.of(currentDate.getYear(), 1, 1);
        LocalDate alphaEnd = LocalDate.of(currentDate.getYear(), 3, 2);

        LocalDate omegaStart = LocalDate.of(currentDate.getYear(), 3, 30);
        LocalDate omegaEnd = LocalDate.of(currentDate.getYear(), 7, 27);

        long totalDaysInSemester = ChronoUnit.DAYS.between(alphaStart, alphaMiddleEnd) + ChronoUnit.DAYS.between(alphaMiddleStart, alphaEnd);

        if (currentDate.isAfter(alphaStart) && currentDate.isBefore(alphaMiddleEnd)) {

            long daysPassed = ChronoUnit.DAYS.between(alphaStart, currentDate);

            percentGone = 100 - (1 - (double) daysPassed / totalDaysInSemester) * 100;

            semester = Semester.ALPHA;

            System.out.println(totalDaysInSemester);
            System.out.println(daysPassed);
        }

        else if (currentDate.isAfter(alphaMiddleStart) && currentDate.isBefore(alphaEnd)) {

            long daysPassed = ChronoUnit.DAYS.between(alphaMiddleStart, currentDate) + ChronoUnit.DAYS.between(alphaStart, alphaMiddleEnd);

            percentGone =  100 - (1 - (double) daysPassed / totalDaysInSemester) * 100;

            semester = Semester.ALPHA;

            System.out.println(totalDaysInSemester);
            System.out.println(daysPassed);
        }

        else if (currentDate.isAfter(omegaStart) && currentDate.isBefore(omegaEnd)) {

            long daysPassed = ChronoUnit.DAYS.between(omegaStart, currentDate);

            long totalDaysInYear = ChronoUnit.DAYS.between(omegaStart, omegaEnd) + 1;

            percentGone = 100.0 - (1 - (double) daysPassed / totalDaysInYear) * 100;

            semester = Semester.OMEGA;
        }

        else {
            percentGone = -1;
            semester = Semester.NONE;
        }

        return SemesterPercentageResponse.builder()
                .percent(String.format("%.2f", percentGone))
                .semester(semester)
                .build();
    }
}
