drop table if exists players;
drop table if exists participants;
drop table if exists tournaments;
drop table if exists stages;
drop table if exists matches;
drop table if exists tournament_contacts;

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    is_account_non_locked BOOLEAN DEFAULT TRUE,
    last_login TIMESTAMP WITH TIME ZONE
);

CREATE TABLE players (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    display_name VARCHAR(100) NOT NULL,
    user_id UUID REFERENCES users(id) ON DELETE SET NULL,
    is_claimed BOOLEAN DEFAULT FALSE
);

CREATE TABLE tournaments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(60) NOT NULL,
    starts_at TIMESTAMP NOT NULL, -- Fixed name and type
    venue_id UUID,
    venue_name_manual VARCHAR(100),
    organiser_type VARCHAR(20) NOT NULL,
    organiser_id UUID NOT NULL,
    format VARCHAR(50),
    race_to SMALLINT,
    handicap BOOLEAN DEFAULT FALSE,
    status VARCHAR(20) DEFAULT 'DRAFT'
);

CREATE TABLE tournament_contacts (
    tournament_id UUID REFERENCES tournaments(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users(id),
    role VARCHAR(30),
    PRIMARY KEY (tournament_id, user_id)
);

CREATE TABLE participating_entities (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tournament_id UUID NOT NULL REFERENCES tournaments(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    seed INTEGER,
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(tournament_id, name)
);

CREATE TABLE entity_members (
    entity_id UUID REFERENCES participating_entities(id) ON DELETE CASCADE,
    player_id UUID REFERENCES players(id) ON DELETE CASCADE,
    rank_at_registration VARCHAR(10),

    PRIMARY KEY (entity_id, player_id)
);

CREATE TABLE matches (
    id UUID PRIMARY KEY NOT NULL,
    tournament_id UUID NOT NULL REFERENCES tournaments(id) ON DELETE CASCADE,
    parent_match_id UUID REFERENCES matches(id) ON DELETE CASCADE,

    -- Players
    participant1_id UUID REFERENCES participating_entities(id),
    participant2_id UUID REFERENCES participating_entities(id),
    winner_id UUID REFERENCES participating_entities(id),

    -- Scoring
    score1 SMALLINT DEFAULT 0,
    score2 SMALLINT DEFAULT 0,

    -- Metadata for UI/Logic
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, LIVE, COMPLETED, DISPUTED
    -- the number of the round: quater final, final, etc.
    round_number SMALLINT NOT NULL,
    -- how the matches should be sorted in the bracket
    sort_order SMALLINT,

    -- Progression Logic
    next_match_id UUID REFERENCES matches(id),
    next_match_slot INT CHECK (next_match_slot IN (1, 2))
);