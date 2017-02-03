/* DeadWood Monopoly game - Seán Grant 15347791
                            Aonghus Condren 15348051
                            Conall Doherty 15468482 */

  import javax.swing.*;
  import java.awt.*;
  import deadwood.*;

  public class DeadWood
  {
    public DeadWood()
    {
      JFrame frame = new JFrame();
      frame.add(new Board());
      frame.setSize(540,560);
      frame.setTitle("Game Board");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }

    public static void main(String[] args)
    {
      DeadWood board = new DeadWood();
    }
  }
