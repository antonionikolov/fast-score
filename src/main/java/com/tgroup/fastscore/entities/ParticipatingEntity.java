package com.tgroup.fastscore.entities;

import com.tgroup.fastscore.model.ParticipatingEntityStatus;
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
@Table(name = "participating_entities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tournament_id", "name"})
})
public class ParticipatingEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @Column(nullable = false)
    private String name;

    private Integer seed;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ParticipatingEntityStatus status = ParticipatingEntityStatus.REGISTRATION_PENDING;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    private List<EntityMember> members;
}
