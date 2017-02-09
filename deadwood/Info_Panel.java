package deadwood;


import javax.swing.*;
import java.awt.Font;


public class Info_Panel
{
	private static JFrame MyFrame;
	private static JPanel MyPanel;
	private static JTextArea MyTextArea;

	public Info_Panel()
	{
		LoadInfoPanel();
	}

 public static void LoadInfoPanel()
 {

   MyFrame = new JFrame();
   MyFrame.setLocation(1130,0);
   MyFrame.setSize(800,1200);
   MyFrame.setTitle("Latest Moves");


   MyPanel = new JPanel();
   MyFrame.add(MyPanel);

   MyTextArea = new JTextArea();
   MyTextArea.setFont(new Font("Serif", Font.BOLD, 21));


   MyTextArea.setLineWrap(true);
   MyTextArea.setWrapStyleWord(true);
   MyTextArea.setEditable(true);
   MyFrame.add(MyTextArea);
   MyFrame.setVisible(true);


   MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }


 public String DiceRollCounter(String DiceRoll)
 {

	 String Roll = ("Congratulations, you rolled a " + DiceRoll+"!\n");

	 return Roll;
 }

 public void UserInput(String UserText)
 {
	 MyTextArea.append(UserText+"\n");
 }


}
