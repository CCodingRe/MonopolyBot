package deadwood;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;

public class Board extends JPanel
{

  private static JPanel frame = new JPanel();
  static BufferedImage img;
  static int frameWidth;
  static int frameHeight;
  static int playerX[] = new int[6];
  static int playerY[] = new int[6];
  static boolean addPlayers[] = new boolean[6];


  public Board()
  {
    try
    {
      img = ImageIO.read(new File("monopoly-board.jpg")); // loads in the image of the board
      frameWidth = img.getWidth() + 15;
      frameHeight = img.getHeight() + 40; // +15 and +40 to width and height to adjust for the title at the top of the JFrame
      setPreferredSize(new Dimension(frameWidth, frameHeight));
      addTokens(6);

    }
    catch (IOException e) {
    }
  }

  public void paint(Graphics g)
  {

	g.drawImage(img, 0, 0, this); // paints the board image

	if(addPlayers[0] == true) // if show is changed to true for each player in the AddTokens method is true it will paint each player on the board
	{
		super.paintComponents(g);
		g.drawOval(playerX[0], playerY[0], 12, 12);
		g.setColor(Color.BLUE);
		g.fillOval(playerX[0], playerY[0], 12, 12);
	}
	if(addPlayers[1] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[1]+12, playerY[1]+10, 12, 12); // +12 and +10 in coordinates so each player is in a slightly different position
		g.setColor(Color.RED);
		g.fillOval(playerX[1]+12, playerY[1]+10, 12, 12);
	}
	if(addPlayers[2] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[2]-12, playerY[2]-10, 12, 12);
		g.setColor(Color.GREEN);
		g.fillOval(playerX[2]-12, playerY[2]-10, 12, 12);
	}
	if(addPlayers[3] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[3]-12, playerY[3]+10, 12, 12);
		g.setColor(Color.YELLOW);
		g.fillOval(playerX[3]-12, playerY[3]+10, 12, 12);
	}
	if(addPlayers[4] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[4]+12, playerY[4]-10, 12, 12);
		g.setColor(Color.WHITE);
		g.fillOval(playerX[4]+12, playerY[4]-10, 12, 12);
	}
	if(addPlayers[5] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[5], playerY[5]-12, 12, 12);
		g.setColor(Color.PINK);
		g.fillOval(playerX[5], playerY[5]-12, 12, 12);
	}
  }

  public static void loadBoard()
  {
    frame.add(new Board());
  }

  public static void addTokens(int players)
  {
	for(int i=0; i<players; i++) // loops i times to put i players into the game
	{
		addPlayers[i] = true;
		playerX[i] = 480;
		playerY[i] = 480;
	}
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

		if(playerX[player]>455 && playerY[player]>450) // bottom of board
		{
			playerX[player] -= 55;
		}
		else if(playerX[player]<455 && playerX[player]>111 && playerY[player]>450)
		{
			playerX[player] -= 43;
		}
		else if(playerX[player]<110 && playerX[player]>70 && playerY[player]>450)
		{
			playerX[player] -= 55;
		}

		else if(playerY[player]>455 && playerX[player]<70) // left of board
		{
			playerY[player] -= 55;
		}
		else if(playerY[player]<455 && playerY[player]>110 && playerX[player]<70)
		{
			playerY[player] -= 43;
		}
		else if(playerY[player]<110 && playerY[player]>70 && playerX[player]<70)
		{
			playerY[player] -= 55;
		}

		else if(playerX[player]<70 && playerY[player]<70) // top of board
		{
			playerX[player] += 55;
		}
		else if(playerX[player]>70 && playerX[player]<410 && playerY[player]<70)
		{
			playerX[player] += 43;
		}
		else if(playerX[player]>410 && playerX[player]<455 && playerY[player]<70)
		{
			playerX[player] += 55;
		}

		else if(playerY[player]<70 && playerX[player]>455) // right of board
		{
			playerY[player] += 55;
		}
		else if(playerY[player]>70 && playerY[player]<410 && playerX[player]>455)
		{
			playerY[player] += 43;
		}
		else if(playerY[player]>410 && playerY[player]<455 && playerX[player]>455)
		{
			playerY[player] += 55;
		}

		frame.repaint(); // repaints each loop to show tokens new position
		spaces--;
	}

  }
  public JPanel getBoard(){
    return frame;
  }



 }
