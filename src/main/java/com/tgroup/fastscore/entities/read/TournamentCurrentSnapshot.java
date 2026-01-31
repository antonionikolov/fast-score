package com.tgroup.fastscore.entities.read;


import com.tgroup.fastscore.model.read.TournamentTreeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tournament_current_snapshot")
public class TournamentCurrentSnapshot {
    @Id
    private UUID tournamentId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tournament_data", columnDefinition = "jsonb")
    private TournamentTreeDto tournamentData;

    @UpdateTimestamp
    private Instant updateTime;
}
