package com.tgroup.fastscore.model;

import jakarta.validation.constraints.Min;

public record ScoreReportDto (
        @Min(0)
        short score1,
        @Min(0)
        short score2
) {}
