package deadwood;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
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


  public Board()
  {
    try
    {
      img = ImageIO.read(new File("monopoly-board.jpg")); // loads in the image of the board
      panelWidth = img.getWidth() + 15;
      panelHeight = img.getHeight() + 40; // +15 and +40 to width and height to adjust for the title at the top of the Jpanel
      setPreferredSize(new Dimension(panelWidth, panelHeight));
      //addTokens(6);

    }
    catch (IOException e) {
    }
  }

  public void paint(Graphics g)
  {

	g.drawImage(img, 0, 0, this); // paints the board image

	for(int i=0; i<TestWood.players.size(); i++) { // adds player tokens to the board. Loops for however many objects are in the players arraylist
		{
			super.paintComponents(g);
			g.drawOval(TestWood.players.get(i).getX()+PLAYER_OFFSET[i][0], TestWood.players.get(i).getY()+PLAYER_OFFSET[i][1], 12, 12); // each player position is slightly offset to ensure they arent on top of each other
			g.setColor(PLAYER_COLOURS[i]);
			g.fillOval(TestWood.players.get(i).getX()+PLAYER_OFFSET[i][0], TestWood.players.get(i).getY()+PLAYER_OFFSET[i][1], 12, 12);
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

		if(TestWood.players.get(player).getX()>455 && TestWood.players.get(player).getY()>450) // bottom of board
		{
			TestWood.players.get(player).changeX(-55); // move player this many pixels on the board (55 because the corner squares are bigger)
		}
		else if(TestWood.players.get(player).getX()<455 && TestWood.players.get(player).getX()>111 && TestWood.players.get(player).getY()>450)
		{
			TestWood.players.get(player).changeX(-43); // move player 43 pixels on the board
		}
		else if(TestWood.players.get(player).getX()<110 && TestWood.players.get(player).getX()>70 && TestWood.players.get(player).getY()>450)
		{
			TestWood.players.get(player).changeX(-55);
		}

		else if(TestWood.players.get(player).getY()>455 && TestWood.players.get(player).getX()<70) // left of board
		{
			TestWood.players.get(player).changeY(-55);
		}
		else if(TestWood.players.get(player).getY()<455 && TestWood.players.get(player).getY()>110 && TestWood.players.get(player).getX()<70)
		{
			TestWood.players.get(player).changeY(-43);
		}
		else if(TestWood.players.get(player).getY()<110 && TestWood.players.get(player).getY()>70 && TestWood.players.get(player).getX()<70)
		{
			TestWood.players.get(player).changeY(-55);
		}

		else if(TestWood.players.get(player).getX()<70 && TestWood.players.get(player).getY()<70) // top of board
		{
			TestWood.players.get(player).changeX(55);
		}
		else if(TestWood.players.get(player).getX()>70 && TestWood.players.get(player).getX()<410 && TestWood.players.get(player).getY()<70)
		{
			TestWood.players.get(player).changeX(43);
		}
		else if(TestWood.players.get(player).getX()>410 && TestWood.players.get(player).getX()<455 && TestWood.players.get(player).getY()<70)
		{
			TestWood.players.get(player).changeX(55);
		}

		else if(TestWood.players.get(player).getY()<70 && TestWood.players.get(player).getX()>455) // right of board
		{
			TestWood.players.get(player).changeY(55);
		}
		else if(TestWood.players.get(player).getY()>70 && TestWood.players.get(player).getY()<410 && TestWood.players.get(player).getX()>455)
		{
			TestWood.players.get(player).changeY(43);
		}
		else if(TestWood.players.get(player).getY()>410 && TestWood.players.get(player).getY()<455 && TestWood.players.get(player).getX()>455)
		{
			TestWood.players.get(player).changeY(55);
		}

		panel.repaint(); // repaints each loop to show tokens new position
		spaces--;
	}

  }
  
  public JPanel getBoard(){
    return panel;
  }



 }
