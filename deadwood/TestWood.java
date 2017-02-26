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
    	UI ui = new UI();
        SetUp set = new SetUp();
        set.playerCheck(); //gets amount of players and sets names
        TurnControl.turn();
    }
}
