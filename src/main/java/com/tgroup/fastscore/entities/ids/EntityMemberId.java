package com.tgroup.fastscore.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EntityMemberId implements Serializable {
    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "player_id")
    private UUID playerId;
}
