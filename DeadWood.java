/* DeadWood Monopoly game - Sean Grant 15347791
                            Aonghus Condren 15348051
                            Conall Doherty 15468482 */

import deadwood.*;

public class DeadWood
{
	public static void main(String [] args)
	{
		UI ui = new UI();
		SetUp set = new SetUp();
		set.playerCheck(); //gets amount of players and sets names
		TurnControl.turn();
	}
}
