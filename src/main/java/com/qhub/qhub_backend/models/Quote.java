package com.qhub.qhub_backend.models;


import com.qhub.qhub_backend.models.responses.RandomQuoteResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    private Long id;
    private String quote;
    private String author;

    public RandomQuoteResponse toDto() {
        return RandomQuoteResponse.builder()
                .author(author)
                .quote(quote)
                .build();
    }
}
