package deadwood;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.util.concurrent.ThreadLocalRandom;


public class cmd_panel  extends JFrame{

    private JFrame frame;
    private JLabel Label;
    private JPanel controlPanel;
    private Info_Panel MyInfoPanel;

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

    public void move(player obj){ //moves player according to roll()
      int n = roll();
      obj.location += n;
    }
    public void move(player obj, int n){ //moves player manually n spaces
      obj.location += n;

    }
    public int roll(){ //returns dice roll
      int randomNum = ThreadLocalRandom.current().nextInt(2, 12 + 1);
      return randomNum;
    }

    public class player{ //creates player
      int location = 0;
      public int getLocation(){ //returns players location
          return location;
      }
    }
    public void getMethod(String method){
        MyInfoPanel.UserInput(method);
        if(method.equals("move")){
          MyInfoPanel.UserInput("Moved one space forward");
        }

    }


}
