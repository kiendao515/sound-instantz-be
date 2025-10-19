-- Ensure we have the basic categories first
INSERT INTO categories (id, title, slug, created_at, updated_at)
VALUES
    (1, 'Humor & Comedy', 'humor-comedy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Music & Sound Effects', 'music-sfx', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Gaming', 'gaming', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Memes', 'memes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Animals', 'animals', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

-- Insert large amount of sound data
INSERT INTO sounds (name, category_id, description, file_url, color, play_count, download_count, viewed, tags, created_at, updated_at)
VALUES
    -- Humor & Comedy Sounds (category_id = 1)
    ('Epic Laugh Track', 1, 'Perfect for sitcom moments', 'https://storage.soundinstantz.com/sounds/laugh-track-1.mp3', '#FF5733', 15000, 7500, 25000, 'laugh,comedy,sitcom', CURRENT_TIMESTAMP - INTERVAL '100 days', CURRENT_TIMESTAMP),
    ('Cartoon Slip', 1, 'Classic slipping sound effect', 'https://storage.soundinstantz.com/sounds/slip.mp3', '#33FF57', 12000, 6000, 20000, 'cartoon,funny,slip', CURRENT_TIMESTAMP - INTERVAL '99 days', CURRENT_TIMESTAMP),
    ('Rimshot Drum', 1, 'Ba dum tss!', 'https://storage.soundinstantz.com/sounds/rimshot.mp3', '#5733FF', 18000, 9000, 30000, 'drum,comedy,joke', CURRENT_TIMESTAMP - INTERVAL '98 days', CURRENT_TIMESTAMP),
    ('Circus Theme', 1, 'Funny circus music', 'https://storage.soundinstantz.com/sounds/circus.mp3', '#FF3333', 13500, 6750, 22500, 'circus,funny,music', CURRENT_TIMESTAMP - INTERVAL '97 days', CURRENT_TIMESTAMP),
    ('Comedic Boing', 1, 'Springy cartoon sound', 'https://storage.soundinstantz.com/sounds/boing.mp3', '#33FFFF', 14200, 7100, 23500, 'cartoon,boing,funny', CURRENT_TIMESTAMP - INTERVAL '96 days', CURRENT_TIMESTAMP),

    -- Music & Sound Effects (category_id = 2)
    ('Epic Orchestra Hit', 2, 'Dramatic orchestral impact', 'https://storage.soundinstantz.com/sounds/orchestra-hit.mp3', '#FF9933', 25000, 12500, 40000, 'orchestra,dramatic,music', CURRENT_TIMESTAMP - INTERVAL '95 days', CURRENT_TIMESTAMP),
    ('Deep Bass Drop', 2, 'Heavy electronic bass drop', 'https://storage.soundinstantz.com/sounds/bass-drop.mp3', '#9933FF', 28000, 14000, 45000, 'bass,electronic,drop', CURRENT_TIMESTAMP - INTERVAL '94 days', CURRENT_TIMESTAMP),
    ('Cinematic Whoosh', 2, 'Perfect for transitions', 'https://storage.soundinstantz.com/sounds/whoosh.mp3', '#33FF99', 22000, 11000, 35000, 'whoosh,transition,effect', CURRENT_TIMESTAMP - INTERVAL '93 days', CURRENT_TIMESTAMP),
    ('Electric Guitar Riff', 2, 'Rock guitar solo', 'https://storage.soundinstantz.com/sounds/guitar-riff.mp3', '#FF3399', 26000, 13000, 42000, 'guitar,rock,music', CURRENT_TIMESTAMP - INTERVAL '92 days', CURRENT_TIMESTAMP),
    ('Drum Loop', 2, 'Energetic drum pattern', 'https://storage.soundinstantz.com/sounds/drum-loop.mp3', '#3399FF', 24000, 12000, 38000, 'drums,loop,rhythm', CURRENT_TIMESTAMP - INTERVAL '91 days', CURRENT_TIMESTAMP),

    -- Gaming Sounds (category_id = 3)
    ('Level Up Chime', 3, 'RPG level up sound', 'https://storage.soundinstantz.com/sounds/level-up.mp3', '#FFFF33', 35000, 17500, 55000, 'game,rpg,levelup', CURRENT_TIMESTAMP - INTERVAL '90 days', CURRENT_TIMESTAMP),
    ('Game Over', 3, 'Classic arcade game over', 'https://storage.soundinstantz.com/sounds/game-over.mp3', '#FF33FF', 32000, 16000, 50000, 'game,arcade,over', CURRENT_TIMESTAMP - INTERVAL '89 days', CURRENT_TIMESTAMP),
    ('Coin Collect', 3, 'Retro coin pickup sound', 'https://storage.soundinstantz.com/sounds/coin.mp3', '#33FFFF', 38000, 19000, 60000, 'game,coin,retro', CURRENT_TIMESTAMP - INTERVAL '88 days', CURRENT_TIMESTAMP),
    ('Health Pickup', 3, 'HP restoration effect', 'https://storage.soundinstantz.com/sounds/health.mp3', '#FF9933', 30000, 15000, 48000, 'game,health,pickup', CURRENT_TIMESTAMP - INTERVAL '87 days', CURRENT_TIMESTAMP),
    ('Boss Battle Music', 3, '8-bit boss theme', 'https://storage.soundinstantz.com/sounds/boss-battle.mp3', '#9933FF', 36000, 18000, 58000, 'game,boss,music', CURRENT_TIMESTAMP - INTERVAL '86 days', CURRENT_TIMESTAMP),

    -- Memes (category_id = 4)
    ('MLG Airhorn', 4, 'Classic MLG airhorn', 'https://storage.soundinstantz.com/sounds/airhorn.mp3', '#33FF33', 45000, 22500, 70000, 'meme,mlg,airhorn', CURRENT_TIMESTAMP - INTERVAL '85 days', CURRENT_TIMESTAMP),
    ('Sad Violin', 4, 'World smallest violin', 'https://storage.soundinstantz.com/sounds/sad-violin.mp3', '#3333FF', 42000, 21000, 65000, 'meme,sad,violin', CURRENT_TIMESTAMP - INTERVAL '84 days', CURRENT_TIMESTAMP),
    ('Wow Meme', 4, 'Owen Wilson wow', 'https://storage.soundinstantz.com/sounds/wow.mp3', '#FF3333', 48000, 24000, 75000, 'meme,wow,funny', CURRENT_TIMESTAMP - INTERVAL '83 days', CURRENT_TIMESTAMP),
    ('Nope.avi', 4, 'Engineer Nope', 'https://storage.soundinstantz.com/sounds/nope.mp3', '#33FFFF', 40000, 20000, 62000, 'meme,nope,tf2', CURRENT_TIMESTAMP - INTERVAL '82 days', CURRENT_TIMESTAMP),
    ('To Be Continued', 4, 'Roundabout meme', 'https://storage.soundinstantz.com/sounds/to-be-continued.mp3', '#FFFF33', 44000, 22000, 68000, 'meme,jojo,continued', CURRENT_TIMESTAMP - INTERVAL '81 days', CURRENT_TIMESTAMP),

    -- Animals (category_id = 5)
    ('Cat Meow Collection', 5, 'Various cat sounds', 'https://storage.soundinstantz.com/sounds/cat-meow.mp3', '#FF9933', 20000, 10000, 32000, 'cat,animal,meow', CURRENT_TIMESTAMP - INTERVAL '80 days', CURRENT_TIMESTAMP),
    ('Wolf Howl', 5, 'Majestic wolf howling', 'https://storage.soundinstantz.com/sounds/wolf.mp3', '#9933FF', 18000, 9000, 29000, 'wolf,animal,howl', CURRENT_TIMESTAMP - INTERVAL '79 days', CURRENT_TIMESTAMP),
    ('Bird Songs', 5, 'Morning bird chorus', 'https://storage.soundinstantz.com/sounds/birds.mp3', '#33FF99', 22000, 11000, 35000, 'bird,nature,song', CURRENT_TIMESTAMP - INTERVAL '78 days', CURRENT_TIMESTAMP),
    ('Lion Roar', 5, 'Powerful lion roar', 'https://storage.soundinstantz.com/sounds/lion.mp3', '#FF3399', 25000, 12500, 40000, 'lion,animal,roar', CURRENT_TIMESTAMP - INTERVAL '77 days', CURRENT_TIMESTAMP),
    ('Dolphin Calls', 5, 'Playful dolphin sounds', 'https://storage.soundinstantz.com/sounds/dolphin.mp3', '#3399FF', 21000, 10500, 33000, 'dolphin,ocean,animal', CURRENT_TIMESTAMP - INTERVAL '76 days', CURRENT_TIMESTAMP),

    -- More Humor & Comedy
    ('Laugh Track 2', 1, 'Audience laughter', 'https://storage.soundinstantz.com/sounds/laugh-2.mp3', '#FF5733', 16000, 8000, 26000, 'laugh,comedy,audience', CURRENT_TIMESTAMP - INTERVAL '75 days', CURRENT_TIMESTAMP),
    ('Cartoon Spring', 1, 'Bouncy spring effect', 'https://storage.soundinstantz.com/sounds/spring.mp3', '#33FF57', 13000, 6500, 21000, 'cartoon,spring,funny', CURRENT_TIMESTAMP - INTERVAL '74 days', CURRENT_TIMESTAMP),
    ('Comedy Whistle', 1, 'Sliding whistle sound', 'https://storage.soundinstantz.com/sounds/whistle.mp3', '#5733FF', 17000, 8500, 28000, 'whistle,comedy,slide', CURRENT_TIMESTAMP - INTERVAL '73 days', CURRENT_TIMESTAMP),

    -- More Music & Effects
    ('Epic Brass', 2, 'Dramatic brass hit', 'https://storage.soundinstantz.com/sounds/brass.mp3', '#FF9933', 27000, 13500, 43000, 'brass,dramatic,music', CURRENT_TIMESTAMP - INTERVAL '72 days', CURRENT_TIMESTAMP),
    ('Synth Wave', 2, '80s style synth', 'https://storage.soundinstantz.com/sounds/synth.mp3', '#9933FF', 29000, 14500, 46000, 'synth,80s,retro', CURRENT_TIMESTAMP - INTERVAL '71 days', CURRENT_TIMESTAMP),
    ('Orchestra Strings', 2, 'Emotional strings', 'https://storage.soundinstantz.com/sounds/strings.mp3', '#33FF99', 23000, 11500, 37000, 'strings,orchestra,emotional', CURRENT_TIMESTAMP - INTERVAL '70 days', CURRENT_TIMESTAMP),

    -- More Gaming
    ('Power Up', 3, 'Game power up effect', 'https://storage.soundinstantz.com/sounds/power-up.mp3', '#FFFF33', 34000, 17000, 54000, 'game,power,up', CURRENT_TIMESTAMP - INTERVAL '69 days', CURRENT_TIMESTAMP),
    ('Menu Select', 3, 'UI selection sound', 'https://storage.soundinstantz.com/sounds/menu.mp3', '#FF33FF', 31000, 15500, 49000, 'game,ui,menu', CURRENT_TIMESTAMP - INTERVAL '68 days', CURRENT_TIMESTAMP),
    ('Victory Fanfare', 3, 'Win celebration music', 'https://storage.soundinstantz.com/sounds/victory.mp3', '#33FFFF', 37000, 18500, 59000, 'game,victory,win', CURRENT_TIMESTAMP - INTERVAL '67 days', CURRENT_TIMESTAMP),

    -- More Memes
    ('Fart Sound Effects', 4, 'Collection of fart sounds', 'https://storage.soundinstantz.com/sounds/fart-pack.mp3', '#FF3333', 46000, 23000, 72000, 'meme,fart,funny', CURRENT_TIMESTAMP - INTERVAL '66 days', CURRENT_TIMESTAMP),
    ('Mission Failed', 4, 'GTA mission failed', 'https://storage.soundinstantz.com/sounds/mission-failed.mp3', '#33FFFF', 43000, 21500, 67000, 'meme,gta,fail', CURRENT_TIMESTAMP - INTERVAL '65 days', CURRENT_TIMESTAMP),
    ('Illuminati', 4, 'X-Files theme meme', 'https://storage.soundinstantz.com/sounds/illuminati.mp3', '#FFFF33', 47000, 23500, 73000, 'meme,illuminati,xfiles', CURRENT_TIMESTAMP - INTERVAL '64 days', CURRENT_TIMESTAMP),

    -- More Animals
    ('Fox Sounds', 5, 'What does the fox say', 'https://storage.soundinstantz.com/sounds/fox.mp3', '#FF9933', 19000, 9500, 31000, 'fox,animal,funny', CURRENT_TIMESTAMP - INTERVAL '63 days', CURRENT_TIMESTAMP),
    ('Elephant Trumpet', 5, 'Elephant call', 'https://storage.soundinstantz.com/sounds/elephant.mp3', '#9933FF', 17000, 8500, 28000, 'elephant,animal,trumpet', CURRENT_TIMESTAMP - INTERVAL '62 days', CURRENT_TIMESTAMP),
    ('Monkey Sounds', 5, 'Various monkey calls', 'https://storage.soundinstantz.com/sounds/monkey.mp3', '#33FF99', 23000, 11500, 36000, 'monkey,animal,primate', CURRENT_TIMESTAMP - INTERVAL '61 days', CURRENT_TIMESTAMP),

    -- Additional Varied Sounds
    ('Thunder Crack', 2, 'Loud thunder effect', 'https://storage.soundinstantz.com/sounds/thunder.mp3', '#FF5733', 28000, 14000, 45000, 'weather,thunder,nature', CURRENT_TIMESTAMP - INTERVAL '60 days', CURRENT_TIMESTAMP),
    ('Cash Register', 2, 'Money counting sound', 'https://storage.soundinstantz.com/sounds/cash.mp3', '#33FF57', 25000, 12500, 40000, 'money,cash,business', CURRENT_TIMESTAMP - INTERVAL '59 days', CURRENT_TIMESTAMP),
    ('Vinyl Scratch', 2, 'DJ scratch effect', 'https://storage.soundinstantz.com/sounds/scratch.mp3', '#5733FF', 30000, 15000, 48000, 'dj,vinyl,music', CURRENT_TIMESTAMP - INTERVAL '58 days', CURRENT_TIMESTAMP),
    ('Police Siren', 2, 'Emergency siren sound', 'https://storage.soundinstantz.com/sounds/siren.mp3', '#FF9933', 27000, 13500, 43000, 'police,emergency,siren', CURRENT_TIMESTAMP - INTERVAL '57 days', CURRENT_TIMESTAMP),
    ('Crowd Cheering', 2, 'Stadium crowd cheer', 'https://storage.soundinstantz.com/sounds/cheering.mp3', '#9933FF', 32000, 16000, 50000, 'crowd,cheer,sport', CURRENT_TIMESTAMP - INTERVAL '56 days', CURRENT_TIMESTAMP),

    -- More Recent Sounds (within last month)
    ('Epic Saxophone', 2, 'Smooth jazz saxophone', 'https://storage.soundinstantz.com/sounds/saxophone.mp3', '#33FF99', 15000, 7500, 25000, 'jazz,sax,music', CURRENT_TIMESTAMP - INTERVAL '30 days', CURRENT_TIMESTAMP),
    ('Rain Ambient', 2, 'Calming rain sounds', 'https://storage.soundinstantz.com/sounds/rain.mp3', '#FF3399', 18000, 9000, 29000, 'rain,ambient,nature', CURRENT_TIMESTAMP - INTERVAL '29 days', CURRENT_TIMESTAMP),
    ('Horror Scream', 2, 'Scary scream effect', 'https://storage.soundinstantz.com/sounds/scream.mp3', '#3399FF', 20000, 10000, 32000, 'horror,scream,scary', CURRENT_TIMESTAMP - INTERVAL '28 days', CURRENT_TIMESTAMP),
    ('Glass Break', 2, 'Glass shattering', 'https://storage.soundinstantz.com/sounds/glass.mp3', '#FFFF33', 22000, 11000, 35000, 'glass,break,crash', CURRENT_TIMESTAMP - INTERVAL '27 days', CURRENT_TIMESTAMP),
    ('Door Creak', 2, 'Creepy door sound', 'https://storage.soundinstantz.com/sounds/door.mp3', '#FF33FF', 19000, 9500, 31000, 'door,creak,horror', CURRENT_TIMESTAMP - INTERVAL '26 days', CURRENT_TIMESTAMP),
    ('Wind Howl', 2, 'Strong wind effect', 'https://storage.soundinstantz.com/sounds/wind.mp3', '#33FFFF', 21000, 10500, 34000, 'wind,weather,nature', CURRENT_TIMESTAMP - INTERVAL '25 days', CURRENT_TIMESTAMP),
    ('Ocean Waves', 2, 'Peaceful ocean sound', 'https://storage.soundinstantz.com/sounds/ocean.mp3', '#FF3333', 24000, 12000, 38000, 'ocean,waves,nature', CURRENT_TIMESTAMP - INTERVAL '24 days', CURRENT_TIMESTAMP),
    ('Fire Crackling', 2, 'Cozy fireplace sound', 'https://storage.soundinstantz.com/sounds/fire.mp3', '#33FF33', 23000, 11500, 37000, 'fire,cozy,nature', CURRENT_TIMESTAMP - INTERVAL '23 days', CURRENT_TIMESTAMP),
    ('Clock Ticking', 2, 'Analog clock tick', 'https://storage.soundinstantz.com/sounds/clock.mp3', '#3333FF', 17000, 8500, 28000, 'clock,time,tick', CURRENT_TIMESTAMP - INTERVAL '22 days', CURRENT_TIMESTAMP),
    ('Typewriter', 2, 'Old typewriter keys', 'https://storage.soundinstantz.com/sounds/typewriter.mp3', '#FF9933', 16000, 8000, 26000, 'typewriter,vintage,office', CURRENT_TIMESTAMP - INTERVAL '21 days', CURRENT_TIMESTAMP);
