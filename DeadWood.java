/* DeadWood Monopoly game - Sean Grant 15347791
                            Aonghus Condren 15348051
                            Conall Doherty 15468482 */

import deadwood.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;


public class DeadWood
{

    public static void main(String [] args)
    {
      SetUp set = new SetUp();
      UI ui = new UI();
      DeadWood d = new DeadWood();
      set.playerCheck(); //gets amount of players and sets names
      TurnControl.turn();
    }
}
