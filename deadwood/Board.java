package deadwood;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;   // Clean this shit up

public class Board extends Component
{
  BufferedImage img;

  public void paint(Graphics g)
  {
    g.drawImage(img, 0, 0, null);
  }

  public Board()
  {
    try
    {
      img = ImageIO.read(new File("monopoly-board.jpg"));
    }
    catch (IOException e) {
    }
  }
}
