package com.tgroup.fastscore.entities;

import com.tgroup.fastscore.entities.ids.EntityMemberId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entity_members")
public class EntityMember {
    @EmbeddedId
    private EntityMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("entityId")
    @JoinColumn(name = "entity_id")
    private ParticipatingEntity entity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "rank_at_registration")
    private String rankAtRegistration;
}
