package deadwood;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.JTextField;

public class Cmd_panel extends JPanel{

    private JTextField userText = new JTextField(10);;
    private static LinkedList<String> cmdBuff = new LinkedList<String>();


    public Cmd_panel(){
      setLayout(new FlowLayout());
      Info_Panel.UserInput("Welcome");
      Info_Panel.UserInput("How Many Players?");
        class AddActionListener implements ActionListener {
          public void actionPerformed(ActionEvent evt) {  //runs code when action event runs
            synchronized (cmdBuff){
              cmdBuff.add(userText.getText());
              Info_Panel.UserInput(userText.getText());
              userText.setText(""); //empty text field
              cmdBuff.notify();
            }
            return;
          }
        }
        ActionListener listener = new AddActionListener();
        userText.addActionListener(listener);
        JLabel  namelabel = new JLabel("Command: ", JLabel.RIGHT);
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        userText.setFont(font1);
        add(namelabel);
        add(userText);
        return;
      }

      public static String getCommand(){
        String command;
        synchronized (cmdBuff){
          while(cmdBuff.isEmpty()){
            try {
              cmdBuff.wait();
            } catch (InterruptedException e){
              e.printStackTrace();
            }
          }
          command = cmdBuff.pop();
        }
          return command;
      }




}
