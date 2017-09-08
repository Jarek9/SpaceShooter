import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Sound {
	
	
	
	public boolean rodzajDzwiêku;
	public static void playExplosion() {
	    try {
	    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("exploding.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static void playHit() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("hit.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static void playFiring() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("shot.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	  
	}
	
	public static void playCollect() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("collect.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
		
	

}
