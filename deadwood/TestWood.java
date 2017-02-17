package deadwood;
import deadwood.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;


public class TestWood
{
	public static ArrayList<Players> players = new ArrayList<Players>();
	
    public static void main(String [] args)
    {
    	for(int i=0; i<6; i++)
    	{
    		players.add(new Players());
    	}	
    	UI ui = new UI();
    }
}
