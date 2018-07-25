import javax.sound.sampled.*;
import java.io.File;

public class SoundClips {
    Model model;
    Clip[] clips;
    int pos;

    SoundClips(String filename, int copies, Model m) throws Exception {
        model = m;
        clips = new Clip[copies];
        for(int i = 0; i < copies; i++) {
            AudioInputStream inputStream =
                    AudioSystem.getAudioInputStream(new File(filename));
            AudioFormat format = inputStream.getFormat();
            DataLine.Info info =
                    new DataLine.Info(Clip.class, format);
            clips[i] = (Clip)AudioSystem.getLine(info);
            clips[i].open(inputStream);
        }
        pos = 0;
    }

    void play() {
        clips[pos].setFramePosition(0);
        clips[pos].loop(0);
        if(++pos >= clips.length)
            pos = 0;
    }
}