package com.tgroup.fastscore.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "starts_at", nullable = false)
    private Instant startsAt;

    private UUID venueId;
    private String venueNameManual;

    @Column(name = "organiser_type", nullable = false)
    private String organiserType;

    @Column(name = "organiser_id", nullable = false)
    private UUID organiserId;

    private String format;
    private Short raceTo;
    private boolean handicap = false;

    @Column(name = "status")
    private String status = "DRAFT";

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<ParticipatingEntity> participants;
}
