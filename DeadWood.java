/* DeadWood Monopoly game - Se�n Grant 15347791
                            Aonghus Condren 15348051
                            Conall Doherty 15468482 */
                            
import javax.swing.*;
import deadwood.*;
import java.awt.event.*;
import java.awt.*;


public class DeadWood extends JFrame{

    public static void main(String [] args){

      Board.LoadBoard();
      cmd_panel cmd = new cmd_panel(); //conalls command panel.

      cmd.createPlayer();  //create players
      cmd.createPlayer();
      cmd.createPlayer();

    }
}

/* DeadWood Monopoly game - Se�n Grant 15347791
                            Aonghus Condren 15348051
                            Conall Doherty 15468482 */
