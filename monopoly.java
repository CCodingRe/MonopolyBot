import javax.swing.*;
import deadwood.*;

public class monopoly{

    public monopoly(){ //gui constructor
      JFrame frame = new JFrame();
      frame.setSize(300,400);
      frame.setTitle("An empty frame");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }

    public static void main(String [] args){

      monopoly n = new monopoly();  //gui, needs work

      cmd_panel cmd = new cmd_panel(); //conalls command panel.
      cmd_panel.player player1 = cmd.new player();  //create players
      cmd_panel.player player2 = cmd.new player();
      cmd_panel.player player3 = cmd.new player();

      //test code

      //prints starting location
      System.out.println("start");
      System.out.println("player1" + " location: " + player1.getLocation() + "");
      System.out.println("player2" + " location: " + player2.getLocation() + "");
      System.out.println("player3" + " location: " + player3.getLocation() + "");


      //move one space
      cmd.move(player1, 1);
      cmd.move(player2, 1);
      cmd.move(player3, 1);

      System.out.println("player1" + " location: " + player1.getLocation() + "");
      System.out.println("player2" + " location: " + player2.getLocation() + "");
      System.out.println("player3" + " location: " + player3.getLocation() + "");

      //rolls dice and moves players
      cmd.move(player1);
      cmd.move(player2);
      cmd.move(player3);

      System.out.println("player1" + " location: " + player1.getLocation() + "");
      System.out.println("player2" + " location: " + player2.getLocation() + "");
      System.out.println("player3" + " location: " + player3.getLocation() + "");

    }
}
