import java.awt.image.BufferedImage;
import java.util.Random;

public class Textures {
	
	public BufferedImage player, enemy;
	
	private SpriteSheet ss;
	
	Random r = new Random();
	
	public Textures(Game game)
	{
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures()
	{
		player = ss.grabImage(2, 1, 64, 64);
		enemy = ss.grabImage(r.nextInt(6) + 1, 3, 64, 64);
	}
	
	public void getTextures2()
	{
		
		enemy = ss.grabImage(r.nextInt(6) + 1, 3, 64, 64);
	}

}
