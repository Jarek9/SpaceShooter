import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	Menu menu = new Menu();
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		
		//Play Button
		if(mx >= menu.playButton.x && mx <= menu.playButton.x + 250 && my >= menu.playButton.y && my <= menu.playButton.y + 70)
		{			
			Sound.playCollect();
			Game.State = Game.STATE.GAME;
			
		}
		
		//Quit Button
		if(mx >= menu.quitButton.x && mx <= menu.quitButton.x + 250 && my >= menu.quitButton.y && my <= menu.quitButton.y + 70)
		{			
			Sound.playCollect();
			System.exit(1);
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
//	public void mouseMoved(MouseEvent e)
//	{
//		int mx = e.getX();
//		int my = e.getY();
//		if(mx >= menu.playButton.x && mx <= menu.playButton.x + 250 && my >= menu.playButton.y && my <= menu.playButton.y + 70)
//		{
//			Sound.playCollect();
//		}
//		if	(mx >= menu.quitButton.x && mx <= menu.quitButton.x + 250 && my >= menu.quitButton.y && my <= menu.quitButton.y + 70);
//		
//		Sound.playCollect();
//		
//		if (mx >= menu.helpButton.x && mx <= menu.helpButton.x + 250 && my >= menu.helpButton.y && my <= menu.helpButton.y + 70);
//		Sound.playCollect();
//		
//	}

}
