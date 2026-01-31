package com.tgroup.fastscore.model.read;

import lombok.Builder;

import java.util.List;

@Builder
public record RoundTreeDto(int roundNumber, String roundName, List<MatchNodeDto> matches) {
}
