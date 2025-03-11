DROP TABLE IF EXISTS mp3_metadata;
DROP TABLE IF EXISTS songs;

CREATE TABLE IF NOT EXISTS mp3_metadata (
    id bigserial not null primary key,
    audio_sample_rate integer not null, 
    channels integer not null, 
    duration float(53) not null, 
    release_date integer not null, 
    sample_rate integer not null,  
    resource_id bigint unique, 
    album varchar(255), 
    album_artist varchar(255), 
    artist varchar(255), 
    audio_channel_type varchar(255), 
    audio_compressor varchar(255), 
    composer varchar(255), 
    content_type varchar(255), 
    creator varchar(255), 
    genre varchar(255), 
    log_comment varchar(255), 
    title varchar(255), 
    mp3_version varchar(255)
);

CREATE TABLE IF NOT EXISTS songs (
    id bigserial not null primary key, 
    song oid
);

