import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 4;
	private JFrame frame;
	public static String title = "2D Space Game";
	private boolean running = false;
	private Thread thread;
	private int updates2;
	private int frames2;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private boolean is_shooting = false;
	
	private int enemy_count = 5 ;
	private int enemy_killed = 0;
	
	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	public static int HEALTH = 100 * 2;
	
	public static enum STATE
	{
		MENU, GAME
	};
	
	public static STATE State = STATE.MENU;
		
	public void init()
	{
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		frame = new JFrame();
		try {
			spriteSheet = loader.loadImage("/spritesheet.png");
			background = loader.loadImage("/space.png");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player(getWidth() / 2 - 32 , getHeight() - 64, tex, this, c);
		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		c.createEnemy(enemy_count);
		
	}
	
	private synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	@Override
	public void run() 
	{  
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 100.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >=1)
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer+=1000;
				updates2 = updates;
				frames2 = frames;
//				frame.setTitle(title + " | " + updates + " updates, " + frames + " fps");
//				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
	}
	
	private void tick()
	{
		if(State == STATE.GAME)
		{
			p.tick();
			c.tick();
		}
		
		if(enemy_killed >= enemy_count)
		{
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
		
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background,0, 0, null);
		
		if(State == STATE.GAME)
		{
			
			p.render(g);
			c.render(g);
			g.setFont(new Font("Century Gothic", Font.BOLD, 16));
			g.setColor(Color.WHITE);
			g.drawString(updates2 + " updates, " + frames2 + " fps", getWidth() - 170, getHeight() - 20);
			g.drawString(enemy_count + " enemies in wave", getWidth() - 170, getHeight() - 40);
			g.drawString((enemy_count - enemy_killed) + " enemies on screen", getWidth() - 170, getHeight() - 60);
			
			g.setColor(Color.gray);
			g.fillRect(5, 5, 200, 50);
			
			g.setColor(Color.green);
			g.fillRect(5, 5, HEALTH, 50);
			
			g.setColor(Color.white);
			g.drawRect(5, 5, 200, 50);
			
		}else if(State == STATE.MENU)
		{
			menu.render(g);
		}
				
		
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		
		if(State == STATE.GAME) {
		if(keyCode == KeyEvent.VK_LEFT)
		{
			p.setVelX(-5);
		}
		
		else if(keyCode == KeyEvent.VK_RIGHT)
		{
			p.setVelX(5);
		}
		
		if(keyCode == KeyEvent.VK_UP)
		{
			p.setVelY(-5);
		}
		
		if(keyCode == KeyEvent.VK_DOWN)
		{
			p.setVelY(5);
		}
		
		if(keyCode == KeyEvent.VK_Z && !is_shooting)
		{
			is_shooting = true;
			c.addEntity(new Bullet(p.getX() + 32,  p.getY(), this));
//			c.addEntity(new Bullet(p.getX(),  p.getY()+32, this));
//			c.addEntity(new Bullet(p.getX() + 64,  p.getY()+32, this));
			Sound.playFiring();
		}
		if(keyCode == KeyEvent.VK_Q)
		{
			State = STATE.MENU;
		}
		
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT)
		{
			p.setVelX(0);
		}
		
		else if(keyCode == KeyEvent.VK_RIGHT)
		{
			p.setVelX(0);
		}
		
		if(keyCode == KeyEvent.VK_UP)
		{
			p.setVelY(0);
		}
		
		if(keyCode == KeyEvent.VK_DOWN)
		{
			p.setVelY(0);
		}
		
		if(keyCode == KeyEvent.VK_Z)
		{
			is_shooting = false;
		}
	}
	
	public static void main(String args[])
	{
		Game game= new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame();
		
		
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}	
	
	public BufferedImage getSpriteSheet()
{
	return spriteSheet;
}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	
	
	
}


