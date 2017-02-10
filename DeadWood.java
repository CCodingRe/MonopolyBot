import javax.swing.*;
import deadwood.*;
import java.awt.event.*;
import java.awt.*;


public class DeadWood extends JFrame{

    public static void main(String [] args){

      cmd_panel cmd = new cmd_panel(); //conalls command panel.

      cmd.createPlayer();  //create players
      cmd.createPlayer();
      cmd.createPlayer();

    }
}
