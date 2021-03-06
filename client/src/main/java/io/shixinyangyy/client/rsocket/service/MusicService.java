package io.shixinyangyy.client.rsocket.service;

import io.shixinyangyy.client.rsocket.model.MusicData;
import io.shixinyangyy.client.rsocket.repository.MusicRepository;
import io.shixinyangyy.client.rsocket.server.MusicDataRepository;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author Shixinyangyy
 */
// @Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final MusicDataRepository musicDataRepository;

    public MusicService(MusicRepository musicRepository, MusicDataRepository musicDataRepository) {
        this.musicRepository = musicRepository;
        this.musicDataRepository = musicDataRepository;
    }

    public Flux<MusicData> getByAuthor(String author) {

        return Flux
                .interval(Duration.ZERO,
                        Duration.ofSeconds(1))
                .flatMap(i -> musicRepository.getMusicData(author));
    }


    public Flux<MusicData> getAllMusicData(String author){
        return
                Flux.interval(Duration.ofMillis(100))
                .map(l-> {

                    MusicData musicData = new MusicData();
                    musicData.setSongInfo("happy song");
                    musicData.setAuthor(author);
                    return musicData;
                }).take(2);
    }

}
