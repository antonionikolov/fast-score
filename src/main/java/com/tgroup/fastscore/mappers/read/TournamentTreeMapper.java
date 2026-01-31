package com.tgroup.fastscore.mappers.read;

import com.tgroup.fastscore.model.*;
import com.tgroup.fastscore.model.read.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TournamentTreeMapper {
    public TournamentTreeDto tournamentToTournamentTree(
            TournamentDto tournamentDto,
            List<MatchDto> matchDtoList,
            List<ParticipatingEntityDto> participatingEntityDtoList
    ) {
        Map<UUID, ParticipatingEntityDto> participatingEntityDtoMap = participatingEntityDtoList.stream()
                .collect(Collectors.toMap(ParticipatingEntityDto::id, participatingEntityDto -> participatingEntityDto));

        return TournamentTreeDto.builder()
                .tournamentId(tournamentDto.id())
                .tournamentName(tournamentDto.name())
                .startsAt(tournamentDto.startsAt())
                .venueNameManual(tournamentDto.venueNameManual())
                .format(tournamentDto.format())
                .raceTo(tournamentDto.raceTo())
                .handicap(tournamentDto.handicap())
                .status(tournamentDto.status())
                .rounds(this.generateRounds(matchDtoList, participatingEntityDtoMap))
                .build();
    }

    private List<RoundTreeDto> generateRounds(List<MatchDto> matchDtoList,
                                              Map<UUID, ParticipatingEntityDto> participatingEntityDtoMap
    ) {
        Map<Short, List<MatchDto>> mapByMatchRound =
                matchDtoList.stream().collect(Collectors.groupingBy(MatchDto::roundNumber));

        return mapByMatchRound.entrySet().stream().map(entry ->
                RoundTreeDto.builder()
                        .roundNumber(entry.getKey())
                        .roundName("Last " + entry.getValue().size())
                        .matches(entry.getValue().stream().map(
                                matchDto -> this.matchDtoToMatchNodeDto(matchDto, participatingEntityDtoMap)).toList())
                        .build()
        ).toList();
    }

    private MatchNodeDto matchDtoToMatchNodeDto(MatchDto matchDto, Map<UUID, ParticipatingEntityDto> participatingEntityDtoMap) {
        return MatchNodeDto.builder()
                .matchId(matchDto.id())
                .participant1(this.participatingEntityDtoToParticipantNodeDto(participatingEntityDtoMap.get(matchDto.participant1Id())))
                .participant2(this.participatingEntityDtoToParticipantNodeDto(participatingEntityDtoMap.get(matchDto.participant2Id())))
                .score1(matchDto.score1())
                .score2(matchDto.score2())
                .winnerId(matchDto.winnerId())
                .status(matchDto.status())
                .nextMatchId(matchDto.nextMatchId())
                .build();

    }

    private ParticipantNodeDto participatingEntityDtoToParticipantNodeDto(ParticipatingEntityDto participatingEntityDto) {
        if (participatingEntityDto == null) {
            return null;
        }

        return ParticipantNodeDto.builder()
                .participantId(participatingEntityDto.id())
                .participantName(participatingEntityDto.name())
                .status(participatingEntityDto.status())
                .members(participatingEntityDto.members().stream().map(entityMemberDto -> this.entityMemberDtoToParticipantNodeMember(entityMemberDto)).toList())
                .build();
    }

    private ParticipantNodeMember entityMemberDtoToParticipantNodeMember(EntityMemberDto entityMemberDto) {
        return ParticipantNodeMember.builder()
                .playerId(entityMemberDto.player().id())
                .name(entityMemberDto.player().displayName())
                .rank(entityMemberDto.rankAtRegistration())
                .build();
    }
}
