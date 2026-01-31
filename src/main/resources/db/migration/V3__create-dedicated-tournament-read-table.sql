drop table if exists tournament_current_snapshot;

CREATE TABLE tournament_current_snapshot (
    tournament_id UUID PRIMARY KEY REFERENCES tournaments(id) ON DELETE CASCADE,
    tournament_data JSONB NOT NULL,
    update_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);