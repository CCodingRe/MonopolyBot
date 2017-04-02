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
	private static final Color[] PLAYER_COLOURS = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.MAGENTA,Color.WHITE};
	private static final int[][] PLAYER_OFFSET = {{0, 0}, {12, 12}, {-12,-12}, {-12, 12}, {12, -12}, {0, 12}};
	static ArrayList<Players> players = SetUp.getPlayers();


	public Board()
	{
		try
		{
			img = ImageIO.read(this.getClass().getResource("monopoly-board.jpg")); // loads in the image of the board
			panelWidth = img.getWidth() + 15;
			panelHeight = img.getHeight() + 40; // +15 and +40 to width and height to adjust for the title at the top of the Jpanel
			setPreferredSize(new Dimension(panelWidth, panelHeight));


		}
		catch (IOException e) {
		}
	}

	public void paint(Graphics g)
	{

		g.drawImage(img, 0, 0, this); // paints the board image
		for(Players player : players) { // adds player tokens to the board. Loops for however many objects are in the players arrayList
			{
				super.paintComponents(g);
				g.drawOval(player.getX()+PLAYER_OFFSET[player.getId()][0], player.getY()+PLAYER_OFFSET[player.getId()][1], 12, 12); // each player position is slightly offset to ensure they arent on top of each other
				g.setColor(PLAYER_COLOURS[player.getId()]);
				g.fillOval(player.getX()+PLAYER_OFFSET[player.getId()][0], player.getY()+PLAYER_OFFSET[player.getId()][1], 12, 12);
			}

		}
	}

	public static void loadBoard()
	{
		panel.add(new Board());
	}


	public static void moveTokens(Players player, int spaces)
	{
		while(spaces>0)
		{
			try {
				Thread.sleep(300); // suspends execution for a time to show each move
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if(player.getX()>455 && player.getY()>450) // bottom of board
			{
				player.changeX(-55); // move player this many pixels on the board (55 because the corner squares are bigger)
			}
			else if(player.getX()<455 && player.getX()>111 && player.getY()>450)
			{
				player.changeX(-43); // move player 43 pixels on the board
			}
			else if(player.getX()<110 && player.getX()>70 && player.getY()>450)
			{
				player.changeX(-55);
			}

			else if(player.getY()>455 && player.getX()<70) // left of board
			{
				player.changeY(-55);
			}
			else if(player.getY()<455 && player.getY()>110 && player.getX()<70)
			{
				player.changeY(-43);
			}
			else if(player.getY()<110 && player.getY()>70 && player.getX()<70)
			{
				player.changeY(-55);
			}

			else if(player.getX()<70 && player.getY()<70) // top of board
			{
				player.changeX(55);
			}
			else if(player.getX()>70 && player.getX()<410 && player.getY()<70)
			{
				player.changeX(43);
			}
			else if(player.getX()>410 && player.getX()<455 && player.getY()<70)
			{
				player.changeX(55);
			}

			else if(player.getY()<70 && player.getX()>455) // right of board
			{
				player.changeY(55);
			}
			else if(player.getY()>70 && player.getY()<410 && player.getX()>455)
			{
				player.changeY(43);
			}
			else if(player.getY()>410 && player.getY()<455 && player.getX()>455)
			{
				player.changeY(55);
			}

			panel.repaint(); // repaints each loop to show tokens new position
			spaces--;
			player.changeLocation(); // moves 1 location with each loop
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
