package com.javamsdt.resource.mapping;

import com.javamsdt.resource.model.audio.Mp3Metadata;
import com.javamsdt.resource.util.ValidationUtil;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Component;

@Component
public class Mp3MetadataMapper {

    public Mp3Metadata mapMp3Metadata(Metadata metadata) {
        return Mp3Metadata.builder()
                .composer(metadata.get("xmpDM:composer"))
                .album(metadata.get("xmpDM:album"))
                .releaseDate(ValidationUtil.getIntegerFromString(metadata.get("xmpDM:releaseDate")))
                .artist(metadata.get("xmpDM:artist"))
                .creator(metadata.get("dc:creator"))
                .audioCompressor(metadata.get("xmpDM:audioCompressor"))
                .audioChannelType(metadata.get("xmpDM:audioChannelType"))
                .version(metadata.get("version"))
                .logComment(metadata.get("xmpDM:logComment"))
                .audioSampleRate(ValidationUtil.getIntegerFromString(metadata.get("xmpDM:audioSampleRate")))
                .channels(ValidationUtil.getIntegerFromString(metadata.get("channels")))
                .title(metadata.get("dc:title"))
                .albumArtist(metadata.get("xmpDM:albumArtist"))
                .duration(ValidationUtil.getDoubleFromString(metadata.get("xmpDM:duration")))
                .sampleRate(ValidationUtil.getIntegerFromString(metadata.get("samplerate")))
                .genre(metadata.get("xmpDM:genre"))
                .contentType(metadata.get("Content-Type"))
                .build();
    }
}
