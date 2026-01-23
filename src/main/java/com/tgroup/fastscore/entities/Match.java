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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_match_id")
    private Match parentMatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant1_id")
    private ParticipatingEntity participant1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant2_id")
    private ParticipatingEntity participant2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private ParticipatingEntity winner;

    private short score1 = 0;
    private short score2 = 0;
    private String status = "PENDING";

    @Column(name = "round_number", nullable = false)
    private short roundNumber;

    @Column(name = "sort_order")
    private short sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next_match_id")
    private Match nextMatch;

    @Column(name = "next_match_slot")
    private Integer nextMatchSlot;
}
