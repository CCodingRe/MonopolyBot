package deadwood;


import javax.swing.*;
import java.awt.Font;


public class Info_Panel
{

	private static JTextArea MyTextArea;

	public Info_Panel()
	{
		LoadInfoPanel();
	}

 public static void LoadInfoPanel()
 {

   MyTextArea = new JTextArea(60, 40);
   MyTextArea.setFont(new Font("Serif", Font.BOLD, 21));
   MyTextArea.setLineWrap(true);
	 MyTextArea.setBounds(0, 0, 500, 800);
   MyTextArea.setWrapStyleWord(true);
   MyTextArea.setEditable(false);

 }
 public JTextArea getInfoPanel(){
	 return MyTextArea;
 }


 /*public String DiceRollCounter(String DiceRoll)
 {

	 String Roll = ("Congratulations, you rolled a " + DiceRoll+"!\n");

	 return Roll;
 }*/

 public void UserInput(String UserText)
 {
	 MyTextArea.append(UserText+"\n");
 }


}
