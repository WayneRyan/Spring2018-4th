

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.util.Scanner;
public class Main extends JFrame implements Runnable{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
		
	private static BufferedImage offscreen, background;
	private Graphics bg;
	public int myPrime;

		
	public Main(){
		offscreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		bg = offscreen.getGraphics();
		
			
		
		InputStream is = (MainClass.class.getResourceAsStream("/resources/myFont.ttf"));
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(120f);
			bg.setFont(font);
			background = ImageIO.read(MainClass.class
					.getResourceAsStream("/resourses/hacker.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		myPrime=2;
		new Thread(this).start();
	}
	
	public static boolean isPrime(int n){
		if(n<2)return false;
		if(n%2==0)return false;
		
		for(int i=3 ; i*i<=n ; i+=2){
			if(n%i==0)return false;
		}
		return true;
	}
	
	
	
	
	public static void main(String[] args) {
			Main mc = new Main();
			mc.setSize(WIDTH, HEIGHT);
			mc.setResizable(false);
			mc.setVisible(true);
			mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mc.setVisible(true);
	}

			
	public void paint(Graphics g) { // background
		bg.setColor(Color.black);
		bg.fillRect(0, 0, WIDTH, HEIGHT);
		bg.setColor(new Color(0xeb51f));
		bg.setFont(new Font("TrueType", Font.TRUETYPE_FONT, 60));
		bg.drawString("Number:"+myPrime, (WIDTH/2)-200, HEIGHT/2);
		
		g.drawImage(offscreen, 0, 30, null);
	
	}
	

	


	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			myPrime++;
			while(!isPrime(myPrime))myPrime++;
			repaint();
		}
	}
}
