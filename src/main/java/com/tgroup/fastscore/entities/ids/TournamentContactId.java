package com.tgroup.fastscore.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TournamentContactId implements Serializable {

    @Column(name = "tournament_id")
    private UUID tournamentId;

    @Column(name = "user_id")
    private UUID userId;
}
