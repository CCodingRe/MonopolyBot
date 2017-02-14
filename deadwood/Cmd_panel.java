package deadwood;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.Font;
import java.awt.Color;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Cmd_panel  extends JFrame{

    private static JPanel controlPanel;
    private static Info_Panel MyInfoPanel;
    private static int i = 0;
    private static JTextField userText;
    //List<player> users = new ArrayList<player>();


    public Cmd_panel(){
      prepareGUI();
      i++; // next player
      MyInfoPanel = new Info_Panel();
      MyInfoPanel.UserInput("Type 'move' to move player one step");
      MyInfoPanel.UserInput("players " + i + " turn: ");
      input_Cmd();
      }

    public static void prepareGUI(){
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
    }

    public static void input_Cmd(){
      //create the command prompt
      JLabel  namelabel= new JLabel("Command: ", JLabel.RIGHT);
      userText = new JTextField(10);
      Font font1 = new Font("SansSerif", Font.BOLD, 20);
      userText.setFont(font1);
      //listens for commands
      userText.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {  //runs code when action event runs
          getMethod(userText.getText());
          if(userText.getText().equals("move")){
              turnAction(evt);
         } else {
           MyInfoPanel.UserInput("Invalid Command");
         }
         userText.setText(""); //empty text field
      }});

      controlPanel.add(namelabel);
      controlPanel.add(userText);
    }

    private static void turnAction(ActionEvent evt) {
      i %= 6; // mod 6
      doSomething();
   }

   private static void doSomething() {
      i++; // next player
      MyInfoPanel.UserInput("players " + i + " turn: ");
      Board.moveTokens(i-1, 1);
   }

   public static void getMethod(String method){ //prints to text area
     MyInfoPanel.UserInput(method);
   }

   public JPanel getCMDPanel(){ //returns the panel
     return controlPanel;
   }


    // not needed for sprint one
    /*public int roll(){ //returns dice roll
      int randomNum = ThreadLocalRandom.current().nextInt(2, 12 + 1);
      return randomNum;
    }

    public class player{ //creates player
      private int location = 0;
      private String name;
      public void setName(String username){
        name = username;
      }
      public void move(){ //moves player according to roll()
        int n = roll();
        location += n;
      }
      public void move(int n){ //moves player manually n spaces
        location += n;
      }
      public int getLocation(){ //returns players location
          return location;
      }
      public String getName(){//returns players name
        return name;
      }
    }

    public void createPlayer(){
      users.add(new player());
    }

    public void displayUsers(){
      for(player element : users) {
        MyInfoPanel.UserInput(element.getName()  + " is on " + element.getLocation());
        }
    }*/



}
