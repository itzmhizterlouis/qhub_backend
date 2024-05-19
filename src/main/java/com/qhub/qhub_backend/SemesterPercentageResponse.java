package com.qhub.qhub_backend;

import com.qhub.qhub_backend.models.Semester;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class SemesterPercentageResponse {

    private String percent;
    private Semester semester;
}
