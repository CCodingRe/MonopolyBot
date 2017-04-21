
public class YourTeamName implements Bot {
	
	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects

	
	YourTeamName (BoardAPI board, PlayerAPI player, DiceAPI dice) {
		return;
	}
	
	public String getName () {
		return "YourTeamName";
	}
	
	public String getCommand () {
		// Add your code here
		return "help";
	}
	
	public String getDecision () {
		// Add your code here
		return "pay";
	}
	
}
