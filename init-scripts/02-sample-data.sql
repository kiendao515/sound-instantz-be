-- Insert Categories
INSERT INTO categories (title, slug, created_at, updated_at)
VALUES
    ('Humor & Comedy', 'humor-comedy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Music & Sound Effects', 'music-sfx', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Gaming', 'gaming', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Memes', 'memes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Animals', 'animals', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Sounds
INSERT INTO sounds (name, category_id, description, file_url, color, play_count, download_count, viewed, tags, created_at, updated_at)
VALUES
    ('Epic Laugh', 1, 'A contagious laughing sound effect', 'https://storage.soundinstantz.com/sounds/epic-laugh.mp3', '#FF5733', 1200, 500, 2000, 'laugh,funny,comedy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Victory Fanfare', 2, 'Triumphant victory music', 'https://storage.soundinstantz.com/sounds/victory.mp3', '#33FF57', 800, 300, 1500, 'victory,music,game', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Cat Meow', 5, 'Cute cat meowing sound', 'https://storage.soundinstantz.com/sounds/cat-meow.mp3', '#5733FF', 1500, 700, 2500, 'cat,animal,cute', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Rage Quit', 3, 'Funny rage quit sound effect', 'https://storage.soundinstantz.com/sounds/rage-quit.mp3', '#FF3333', 2000, 1000, 3000, 'game,rage,funny', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Meme Oh No', 4, 'Classic "Oh No" meme sound', 'https://storage.soundinstantz.com/sounds/oh-no.mp3', '#33FFFF', 3000, 1500, 5000, 'meme,viral,funny', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Sound Events (Likes)
INSERT INTO sound_events (sound_id, event_type, created_at)
SELECT s.id, 'LIKE', CURRENT_TIMESTAMP
FROM sounds s
CROSS JOIN generate_series(1, 5)
WHERE s.id IN (1, 2, 3, 4, 5);
