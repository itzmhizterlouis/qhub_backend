package com.qhub.qhub_backend.models.responses;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class YearPercentageResponse {

    private String percent;
}
