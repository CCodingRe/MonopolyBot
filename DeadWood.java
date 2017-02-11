/* DeadWood Monopoly game - Sean Grant 15347791
                            Aonghus Condren 15348051
                            Conall Doherty 15468482 */

import javax.swing.*;
import deadwood.*;
import java.awt.event.*;
import java.awt.*;


public class DeadWood extends JFrame
{
    private static Info_Panel info = new Info_Panel();
    private static Cmd_panel cmd = new Cmd_panel();
    JFrame frame = new JFrame();

    public DeadWood()
    {
      frame.setSize(900, 900);
      frame.setLayout(new BorderLayout());
      frame.add(info.getInfoPanel(), BorderLayout.EAST);
      frame.add(cmd.getCMDPanel(), BorderLayout.PAGE_END);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static void main(String [] args)
    {

      DeadWood monopoly = new DeadWood();
      Board.LoadBoard();

    }
}
