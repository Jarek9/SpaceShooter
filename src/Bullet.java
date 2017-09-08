import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject implements EntityA{
	
	
	
	private Color color1;
	private Game game;
	private Controller c;
	private Enemy e;
	public Bullet(double x, double y, Game game)
	{
		super(x, y);
		this.game = game;
		
		
		color1 = Color.YELLOW;				
	}
	
	
	
	public void tick()
	{
		y-=10;
//		if(Physics.Collision(this, game.eb))
//		{
//			c.removeEntity(this);
//			
//		}
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(color1);
//		g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		g.fillRect((int) (x), (int) (y), 3, 20);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 3, 20);
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}


		
	

}
