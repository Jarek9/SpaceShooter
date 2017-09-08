import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton =  new Rectangle(Game.WIDTH * Game.SCALE / 3 + 120, 250, 250, 70);
	public Rectangle helpButton =  new Rectangle(Game.WIDTH * Game.SCALE / 3 + 120, 400, 250, 70);
	public Rectangle quitButton =  new Rectangle(Game.WIDTH * Game.SCALE / 3 + 120, 550, 250, 70);

	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 80);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("SPACE SHOOTER", Game.WIDTH * Game.SCALE / 4 , 150);
		
		Font fnt1 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt1);
		g.drawString("PLAY", playButton.x + 60, playButton.y + 53);
		g.drawString("HELP", helpButton.x + 60, helpButton.y + 53);
		g.drawString("QUIT", quitButton.x + 60, quitButton.y + 53);
		
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		
	}
}
