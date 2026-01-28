INSERT INTO players (id, display_name, user_id, is_claimed) VALUES
(gen_random_uuid(), 'Alex "The Ace"', NULL, false),
(gen_random_uuid(), 'Billy Brook', NULL, false),
(gen_random_uuid(), 'CueMaster_99', NULL, false),
(gen_random_uuid(), 'Deadpool_Pool', NULL, false),
(gen_random_uuid(), 'Eagle Eye', NULL, false),
(gen_random_uuid(), 'Fast Freddy', NULL, false),
(gen_random_uuid(), 'Ghost Roller', NULL, false),
(gen_random_uuid(), 'Hustle Hard', NULL, false),
(gen_random_uuid(), 'Iron Maiden', NULL, false),
(gen_random_uuid(), 'Jump Shot Jack', NULL, false),
(gen_random_uuid(), 'King Pin', NULL, false),
(gen_random_uuid(), 'Lucky Linda', NULL, false),
(gen_random_uuid(), 'Magic Mike', NULL, false),
(gen_random_uuid(), 'Nitro Nick', NULL, false),
(gen_random_uuid(), 'Orbit Smith', NULL, false),
(gen_random_uuid(), 'Pocket Prophet', NULL, false),
(gen_random_uuid(), 'Quick Draw', NULL, false),
(gen_random_uuid(), 'Rail Runner', NULL, false),
(gen_random_uuid(), 'SideSpin Sam', NULL, false),
(gen_random_uuid(), 'Table Titan', NULL, false),
(gen_random_uuid(), 'Under-cutter', NULL, false),
(gen_random_uuid(), 'Vortex Val', NULL, false),
(gen_random_uuid(), 'Wizard Will', NULL, false),
(gen_random_uuid(), 'X-Factor', NULL, false),
(gen_random_uuid(), 'Yellow Jacket', NULL, false),
(gen_random_uuid(), 'Zig Zag', NULL, false),
(gen_random_uuid(), 'Breakout Ben', NULL, false),
(gen_random_uuid(), 'Combo Chris', NULL, false),
(gen_random_uuid(), 'Draw Shot Dan', NULL, false),
(gen_random_uuid(), 'English Eric', NULL, false),
(gen_random_uuid(), 'Follow Faith', NULL, false),
(gen_random_uuid(), 'Massé Max', NULL, false);

INSERT INTO tournaments (
    id,
    name,
    starts_at,
    venue_id,
    venue_name_manual,
    organiser_type,
    organiser_id,
    format,
    race_to,
    handicap,
    status
) VALUES (
    'a1b2c3d4-e5f6-7890-abcd-111122223333', -- Static UUID for testing convenience
    'World 9-Ball Open 2026',
    '2026-06-01 10:00:00',
    NULL,                  -- No linked venue ID
    'The Grand Billiards Hall',
    'CLUB',
    gen_random_uuid(), -- Assuming a random Club ID for now
    'SINGLE_ELIMINATION',
    7,
    false,
    'REGISTRATION_OPEN'
);

INSERT INTO participating_entities (
    id,
    tournament_id,
    name,
    status
)
SELECT
    gen_random_uuid(),
    'a1b2c3d4-e5f6-7890-abcd-111122223333', -- The Tournament ID from V3
    display_name,                          -- Use the player's name
    'CONFIRMED'
FROM players;

INSERT INTO entity_members (
    entity_id,
    player_id
)
SELECT
    pe.id,
    p.id
FROM participating_entities pe
JOIN players p ON pe.name = p.display_name
WHERE pe.tournament_id = 'a1b2c3d4-e5f6-7890-abcd-111122223333';