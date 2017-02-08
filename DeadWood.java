import javax.swing.*;
import deadwood.*;
import java.awt.event.*;
import java.awt.*;


public class DeadWood extends JFrame{

    public static void main(String [] args){

      cmd_panel cmd = new cmd_panel(); //conalls command panel.

        //test code

      cmd_panel.player player1 = cmd.new player();  //create players
      cmd_panel.player player2 = cmd.new player();
      cmd_panel.player player3 = cmd.new player();


    }
}
