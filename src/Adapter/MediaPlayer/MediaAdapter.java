package Adapter.MediaPlayer;

public class MediaAdapter implements MediaPlayer{
    private AdvancedMediaPlayer advancedMediaPlayer = new AdvancedMediaPlayer();
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer.playVlc(fileName);
        }else{
            advancedMediaPlayer.playM4(fileName);
        }
    }
}
