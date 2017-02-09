package deadwood;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends Container
{
	
  private static JFrame frame = new JFrame();
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
      img = ImageIO.read(new File("monopoly-board.jpg"));
      frameWidth = img.getWidth() + 15;
      frameHeight = img.getHeight() + 40;
    }
    catch (IOException e) {
    }
  }
  
  /*public void paint(Graphics g)
  {
	  super.paint(g);
	  g.drawOval(playerX, playerY, 40, 40);
  }*/
  
  public void paint(Graphics g)
  {
	g.drawImage(img, 0, 0, null);
	if(addPlayers[0] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[0], playerY[0], 15, 15);
		g.setColor(Color.BLUE);
		g.fillOval(playerX[0], playerY[0], 15, 15);
	}
	if(addPlayers[1] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[1]+15, playerY[1]+10, 15, 15);
		g.setColor(Color.RED);
		g.fillOval(playerX[1]+15, playerY[1]+10, 15, 15);
	}
	if(addPlayers[2] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[2]-15, playerY[2]-10, 15, 15);
		g.setColor(Color.GREEN);
		g.fillOval(playerX[2]-15, playerY[2]-10, 15, 15);
	}
	if(addPlayers[3] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[3]-15, playerY[3]+10, 15, 15);
		g.setColor(Color.YELLOW);
		g.fillOval(playerX[3]-15, playerY[3]+10, 15, 15);
	}
	if(addPlayers[4] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[4]+15, playerY[4]-10, 15, 15);
		g.setColor(Color.BLACK);
		g.fillOval(playerX[4]+15, playerY[4]-10, 15, 15);
	}
	if(addPlayers[5] == true)
	{
		super.paintComponents(g);
		g.drawOval(playerX[5], playerY[5]-15, 15, 15);
		g.setColor(Color.ORANGE);
		g.fillOval(playerX[5], playerY[5]-15, 15, 15);
	}
  }

  
  public static void LoadBoard()
  {
	//frame.setLayout(new GridLayout(11, 11));
    frame.add(new Board());
    //frame.add(panel);
    frame.setSize(frameWidth, frameHeight);
    frame.setTitle("Game Board");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    
    AddTokens(3);
    MoveTokens(2, 42);
  }
  
  public static void AddTokens(int players)
  {
	for(int i=0; i<players; i++)
	{
		addPlayers[i] = true;
		playerX[i] = 480;
		playerY[i] = 480;
	}
  }
  
  public static void MoveTokens(int player, int spaces)
  {
	while(spaces>0)
	{  	
	   	try {
			Thread.sleep(300); // suspends execution for a time to ensure the button is held and not just clicked
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   	
		if(playerX[player]>455 && playerY[player]>450)
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

		else if(playerY[player]>455 && playerX[player]<70)
		{
			playerY[player] -= 55;
		}
		else if(playerY[player]<110 && playerY[player]>70 && playerX[player]<70)
		{
			playerY[player] -= 55;
		}
		else if(playerY[player]<455 && playerY[player]>110 && playerX[player]<70)
		{
			playerY[player] -= 43;
		}
		
		else if(playerX[player]<70 && playerY[player]<70)
		{
			playerX[player] += 55;
		}
		else if(playerX[player]>410 && playerX[player]<455 && playerY[player]<70)
		{
			playerX[player] += 55;
		}
		else if(playerX[player]>70 && playerX[player]<410 && playerY[player]<70)
		{
			playerX[player] += 43;
		}
		
		else if(playerY[player]<70 && playerX[player]>455)
		{
			playerY[player] += 55;
		}
		else if(playerY[player]>410 && playerY[player]<455 && playerX[player]>455)
		{
			playerY[player] += 55;
		}
		else if(playerY[player]>70 && playerY[player]<410 && playerX[player]>455)
		{
			playerY[player] += 43;
		}
		
		frame.repaint();
		spaces--;
	}
	  
	
  }
 }
