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
	private static final int MAX_PLAYERS = 6;
	static ArrayList<Players> players = SetUp.getPlayers();


	public Board()
	{
		try
		{
			img = ImageIO.read(new File("monopoly-board.jpg")); // loads in the image of the board
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

		for(int i=0; i<players.size(); i++) { // adds player tokens to the board. Loops for however many objects are in the players arraylist
			{
				super.paintComponents(g);
				g.drawOval(players.get(i).getX()+PLAYER_OFFSET[i][0], players.get(i).getY()+PLAYER_OFFSET[i][1], 12, 12); // each player position is slightly offset to ensure they arent on top of each other
				g.setColor(PLAYER_COLOURS[i]);
				g.fillOval(players.get(i).getX()+PLAYER_OFFSET[i][0], players.get(i).getY()+PLAYER_OFFSET[i][1], 12, 12);
			}

		}
	}

	public static void loadBoard()
	{
		panel.add(new Board());
	}


	public static void moveTokens(int player, int spaces)
	{
		while(spaces>0)
		{
			try {
				Thread.sleep(300); // suspends execution for a time to show each move
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if(players.get(player).getX()>455 && players.get(player).getY()>450) // bottom of board
			{
				players.get(player).changeX(-55); // move player this many pixels on the board (55 because the corner squares are bigger)
			}
			else if(players.get(player).getX()<455 && players.get(player).getX()>111 && players.get(player).getY()>450)
			{
				players.get(player).changeX(-43); // move player 43 pixels on the board
			}
			else if(players.get(player).getX()<110 && players.get(player).getX()>70 && players.get(player).getY()>450)
			{
				players.get(player).changeX(-55);
			}

			else if(players.get(player).getY()>455 && players.get(player).getX()<70) // left of board
			{
				players.get(player).changeY(-55);
			}
			else if(players.get(player).getY()<455 && players.get(player).getY()>110 && players.get(player).getX()<70)
			{
				players.get(player).changeY(-43);
			}
			else if(players.get(player).getY()<110 && players.get(player).getY()>70 && players.get(player).getX()<70)
			{
				players.get(player).changeY(-55);
			}

			else if(players.get(player).getX()<70 && players.get(player).getY()<70) // top of board
			{
				players.get(player).changeX(55);
			}
			else if(players.get(player).getX()>70 && players.get(player).getX()<410 && players.get(player).getY()<70)
			{
				players.get(player).changeX(43);
			}
			else if(players.get(player).getX()>410 && players.get(player).getX()<455 && players.get(player).getY()<70)
			{
				players.get(player).changeX(55);
			}

			else if(players.get(player).getY()<70 && players.get(player).getX()>455) // right of board
			{
				players.get(player).changeY(55);
			}
			else if(players.get(player).getY()>70 && players.get(player).getY()<410 && players.get(player).getX()>455)
			{
				players.get(player).changeY(43);
			}
			else if(players.get(player).getY()>410 && players.get(player).getY()<455 && players.get(player).getX()>455)
			{
				players.get(player).changeY(55);
			}

			panel.repaint(); // repaints each loop to show tokens new position
			spaces--;
			players.get(player).changeLocation(); // moves 1 location with each loop
			if(players.get(player).getLocation() == 0) { // if the player passes go
				players.get(player).addBalance(200);
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
