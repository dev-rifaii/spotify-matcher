package perosnal.spotifymatcher.model;

import java.util.List;

public record GetSeveralTracksResponse(List<Track> tracks) {


    public record Track(SpotifyTrackAlbum album, String id, String name, SpotifyTrackUrl external_urls) {


        public record SpotifyTrackUrl(String spotify) {
        }

        public record SpotifyTrackAlbum(List<SpotifyAlbumImage> images) {
        }

        public record SpotifyAlbumImage(String url) {
        }

    }
}
