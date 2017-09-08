import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA{
	
	
	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player;
	
	private Textures tex;
	
	Game game;
	Controller controller;
	
	public Player(double x , double y, Textures tex, Game game, Controller controller)
	{
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public void tick()
	{
		x+= velX;
		y+= velY;
		
		if (x < 0)
		{
			x = 0;
		}
		
		if (x >= Game.WIDTH * Game.SCALE - 64)
		{
			x = Game.WIDTH * Game.SCALE - 64;
		}
		
		if (y < 0)
		{
			y = 0;
		}
		
		if (y >= Game.HEIGHT * Game.SCALE - 64)
		{
			y = Game.HEIGHT * Game.SCALE - 64;
		}
		
		for(int i = 0; i < game.eb.size(); i++)
		{
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt))
			{
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 10;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				Sound.playHit();
			}
		}
		

	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.player, (int)x, (int)y, null);
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	
	
	

}
