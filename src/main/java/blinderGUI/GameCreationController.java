package blinderGUI;

import blinderBackEnd.model.Playlist;

public class GameCreationController {

    private static Playlist chosenPlaylist;

    public void storePlaylistInstance(Playlist playlist) {
        chosenPlaylist = playlist;
    }
}
