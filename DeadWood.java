
public class DeadWood implements Bot {
	
	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects
	
	private boolean rollDone = false;
//	private boolean isOnUnownedProperty = false;
//	private Square square;
	private Property prop;
	
	DeadWood (BoardAPI board, PlayerAPI player, DiceAPI dice) {
		if(dice.isDouble()) {
			rollDone = false;
		}
		//square = board.getSquare(player.getPosition());
		if(board.isProperty(player.getPosition())) {
			prop = board.getProperty(player.getPosition());
		}
		return;
	}
	
	public String getName() {
		return "DeadWood";
	}
	
	public String getCommand() {
		if(!rollDone) {
			rollDone = true;
			return "roll";
		}
		if(prop!=null) {
			if(prop.getOwner()==null) {
				return "buy";
			}
		}
		return "done";
	}
	
	public String getDecision() {
		// Add your code here
		return "pay";
	}
	
}
