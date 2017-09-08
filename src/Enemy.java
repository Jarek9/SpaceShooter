import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {
	
	Random r = new Random();
	private Textures tex;
	private int speed = r.nextInt(3)+1;
	private Game game;
	private Controller c;
	
	
	public Enemy (double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
	}
	
	public void tick()
	{
		
		y += speed;
		
		
		if (y > (Game.HEIGHT * Game.SCALE)) 
		{
			y = -64;
			x = r.nextInt(Game.WIDTH * Game.SCALE) - 64;
			speed = r.nextInt(3)+1;
		}
		
		for(int i = 0; i < game.ea.size(); i++)
		{
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt))
			{
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				Sound.playExplosion();
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
		
		
		
			
//			tex.getTextures2();
	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.enemy, (int) x, (int) y, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 64, 64);
	}
	
	public double getY()
	{
		return y;
	}
	
	

	public double getX() {
		return x;
	}

}
