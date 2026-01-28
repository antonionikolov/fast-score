package com.tgroup.fastscore.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "tournament_id", nullable = false)
    private UUID tournamentId;

    @Column(name = "parent_match_id")
    private UUID parentMatchId;

    @Column(name = "participant1_id")
    private UUID participant1Id;

    @Column(name = "participant2_id")
    private UUID participant2Id;

    @Column(name = "winner_id")
    private UUID winner_id;

    private short score1 = 0;
    private short score2 = 0;
    private String status = "PENDING";

    @Column(name = "round_number", nullable = false)
    private short roundNumber;

    @Column(name = "sort_order")
    private short sortOrder;

    @Column(name = "next_match_id")
    private UUID nextMatchId;

    @Column(name = "next_match_slot")
    private Integer nextMatchSlot;
}
