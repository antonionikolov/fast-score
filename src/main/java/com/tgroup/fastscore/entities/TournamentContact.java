package com.tgroup.fastscore.entities;

import com.tgroup.fastscore.entities.ids.TournamentContactId;
import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
@Table(name = "tournament_contacts")
public class TournamentContact {

    @EmbeddedId
    private TournamentContactId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tournamentId")
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 30)
    private String role;

    // Getters and Setters
}
