package deadwood;
import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class cmd_panel  extends JFrame{

    private JFrame frame;
    private JLabel Label;
    private JPanel controlPanel;
    private Info_Panel MyInfoPanel;
    private static int i = 1;
    List<player> users = new ArrayList<player>();

    public cmd_panel(){ //gui constructor
      prepareGUI();
      input_Cmd();
      MyInfoPanel = new Info_Panel();
      }

    private void prepareGUI(){
      frame = new JFrame("Command pannel");
      frame.setSize(200,100);
      frame.setLayout(new GridLayout(3, 1));

      frame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              System.exit(0);
           }
        });

      Label = new JLabel("", JLabel.CENTER);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      frame.add(Label);
      frame.add(controlPanel);
      frame.setVisible(true);

    }

    private void input_Cmd(){

      JLabel  namelabel= new JLabel("Command: ", JLabel.RIGHT);
      final JTextField userText = new JTextField(7);

      userText.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String str = userText.getText();
            getMethod(str);
            userText.setText("");
         }
      });

      controlPanel.add(namelabel);
      controlPanel.add(userText);
      frame.setVisible(true);

    }

    public int roll(){ //returns dice roll
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
    }

    public void getMethod(String method){
        MyInfoPanel.UserInput(method);
        if(method.equals("move")){

        }
    }


}
