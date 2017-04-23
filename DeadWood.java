
public class DeadWood implements Bot {

	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects

	private boolean rollDone = false;
	private Property prop;
	BoardAPI boardBot; 
	PlayerAPI playerBot; 
	DiceAPI diceBot;

	DeadWood (BoardAPI board, PlayerAPI player, DiceAPI dice) {
		boardBot=board;
		playerBot=player;
		diceBot=dice;
		return;
	}

	public String getName() {
		return "DeadWood";
	}

	public String getCommand() {
		if(diceBot.isDouble()) {
			rollDone = false;
		}
		//square = board.getSquare(player.getPosition());
		if(boardBot.isProperty(playerBot.getPosition())) {
			prop = boardBot.getProperty(playerBot.getPosition());
		}

		if(!rollDone) {
			rollDone = true;
			return "roll";
		}
		if(prop!=null) {
			if(prop.getOwner()==null) {
				return "buy";
			}
		}
		
		rollDone = false;
		return "done";
	}

	public String getDecision() {
		// Add your code here
		return "pay";
	}

}
