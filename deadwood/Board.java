package deadwood;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.JPanel;

public class Board extends JPanel
{

	private static JPanel panel = new JPanel();
	static BufferedImage img;
	static int panelWidth;
	static int panelHeight;
	private static final int TOKEN_RADIUS = 13;
	private static final Color[] PLAYER_COLOURS = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.MAGENTA,Color.WHITE};
	private static final int[][] PLAYER_OFFSET = {{0, 0}, {TOKEN_RADIUS, TOKEN_RADIUS}, {-TOKEN_RADIUS,-TOKEN_RADIUS}, {-TOKEN_RADIUS, TOKEN_RADIUS}, {TOKEN_RADIUS, -TOKEN_RADIUS}, {0, TOKEN_RADIUS}};
	static ArrayList<Players> players = SetUp.getPlayers();


	public Board()
	{
		try
		{
			img = ImageIO.read(this.getClass().getResource("monopoly-board.jpg")); // loads in the image of the board
			panelWidth = img.getWidth();
			panelHeight = img.getHeight(); 
			setPreferredSize(new Dimension(panelWidth, panelHeight));


		}
		catch (IOException e) {
		}
	}

	public void paint(Graphics g)
	{

		g.drawImage(img, 0, 0, panelWidth, panelHeight, this); // paints the board image
		for(Players player : players) { // adds player tokens to the board. Loops for however many objects are in the players arrayList
			{
				super.paintComponents(g);
				g.drawOval(player.getX()+PLAYER_OFFSET[player.getId()][0], player.getY()+PLAYER_OFFSET[player.getId()][1], TOKEN_RADIUS, TOKEN_RADIUS); // each player position is slightly offset to ensure they arent on top of each other
				g.setColor(PLAYER_COLOURS[player.getId()]);
				g.fillOval(player.getX()+PLAYER_OFFSET[player.getId()][0], player.getY()+PLAYER_OFFSET[player.getId()][1], TOKEN_RADIUS, TOKEN_RADIUS);
			}

		}
	}

	public static void loadBoard()
	{
		panel.add(new Board());
	}


	public static void moveTokens(Players player, int spaces, int direction) // direction 1=forward, -1=backwards
	{
		while(spaces>0)
		{
			try {
				Thread.sleep(300); // suspends execution for a time to show each move
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if(player.getX()>595 && player.getY()>595) // bottom of board
			{
				if(direction==1) player.changeX(-75); // move player this many pixels on the board (55 because the corner squares are bigger)
				else player.changeY(-75);
			}
			else if(player.getX()<595 && player.getX()>145 && player.getY()>595)
			{
				if(player.getX()<595 && player.getX()>540 && direction==-1) player.changeX(75); // if player needs to move back onto the corner space
				else player.changeX(-55*direction); // move player 43 pixels on the board
			}
			else if(player.getX()<145 && player.getX()>90 && player.getY()>595)
			{
				if(direction==1) player.changeX(-75);
				else player.changeX(55);
			}

			else if(player.getY()>595 && player.getX()<90) // left of board
			{
				if(direction==1) player.changeY(-75);
				else player.changeX(75);
			}
			else if(player.getY()<595 && player.getY()>145 && player.getX()<90)
			{
				if(player.getY()<595 && player.getY()>540 && direction==-1) player.changeY(75); // if player needs to move back onto the corner space
				else player.changeY(-55*direction);
			}
			else if(player.getY()<145 && player.getY()>90 && player.getX()<90)
			{
				if(direction==1) player.changeY(-75);
				else player.changeY(55);
			}

			else if(player.getX()<90 && player.getY()<90) // top of board
			{
				if(direction==1) player.changeX(75);
				else player.changeY(75);
			}
			else if(player.getX()>90 && player.getX()<540 && player.getY()<90)
			{
				if(player.getX()>90 && player.getX()<145 && direction==-1) player.changeX(-75);
				else player.changeX(55*direction);
			}
			else if(player.getX()>540 && player.getX()<595 && player.getY()<90)
			{
				if(direction==1) player.changeX(75);
				else player.changeX(-55);
			}

			else if(player.getY()<90 && player.getX()>595) // right of board
			{
				if(direction==1) player.changeY(75);
				else player.changeX(-75);
			}
			else if(player.getY()>90 && player.getY()<540 && player.getX()>595)
			{
				if(player.getY()>90 && player.getY()<145 && direction==-1) player.changeY(-75);
				else player.changeY(55*direction);
			}
			else if(player.getY()>540 && player.getY()<595 && player.getX()>595)
			{
				if(direction==1) player.changeY(75);
				else player.changeY(-55);
			}

			panel.repaint(); // repaints each loop to show tokens new position
			spaces--;
			player.changeLocation(direction); // moves 1 location with each loop
			if(player.getLocation() == 0) { // if the player passes go
				player.addBalance(200);
				Info_Panel.UserInput(player.getName() + " recieved $200 for passing Go");
			}
		}

	}

	public JPanel getBoard(){
		return panel;
	}

	public static void refresh() {
		panel.repaint();
	}

}
