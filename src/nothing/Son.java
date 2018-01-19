package nothing;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class Son
{
  public static void jouerSon(String fichierSon)
  {

    AudioInputStream audioInputStream = null;
    try {
      audioInputStream = AudioSystem.getAudioInputStream(DDD.class.getResource(fichierSon));
    } catch (UnsupportedAudioFileException e1) {
      e1.printStackTrace();
      return;
    } catch (IOException e1) {
      e1.printStackTrace();
      return;
    }

    AudioFormat format = audioInputStream.getFormat();
    SourceDataLine auline = null;
    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
    try
    {
      auline = (SourceDataLine)AudioSystem.getLine(info);
      auline.open(format);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
      return;
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    auline.start();
    int nBytesRead = 0;
    byte[] abData = new byte[65536];
    try
    {
      while (nBytesRead != -1) {
        nBytesRead = audioInputStream.read(abData, 0, abData.length);
        if (nBytesRead >= 0)
          auline.write(abData, 0, nBytesRead);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
      return;
    } finally {
      auline.drain();
      auline.close();
    }
  }


}