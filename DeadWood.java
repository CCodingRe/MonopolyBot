import java.util.ArrayList;

public class DeadWood implements Bot {

	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects

	private boolean rollDone = false;
	private Property prop;
	//private Square square;
	private ArrayList<Property> ownedProperty;
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
		isReroll();
		
		//square = board.getSquare(player.getPosition());
		if(boardBot.isProperty(playerBot.getPosition())) {
			prop = boardBot.getProperty(playerBot.getPosition());
		}
		if(prop!=null) {
			if(prop.getOwner()==null) {
				if(playerBot.getBalance() < prop.getPrice()) {
					return mortgageCheapestProperty();
				}
				return "buy";
			}
		}
		
		if(playerBot.isInJail()) {
			rollDone = true;
			return getDecision();
		}
		
		if(!rollDone) {
			rollDone = true;
			return "roll";
		}
		
		if(playerBot.getBalance() < 0) {
			return mortgageCheapestProperty();
		}
		
		rollDone = false;
		return "done";
	}

	public String getDecision() {
		// Add your code here
		return "pay";
	}
	
	private void isReroll() {
		if(diceBot.isDouble()) {
			rollDone = false;
			if(playerBot.isInJail()) rollDone=true;
		}
	}
	
	private String mortgageCheapestProperty() {
		ownedProperty = playerBot.getProperties();
		Property cheapestProp = ownedProperty.get(0);
		for(Property currProp : ownedProperty) {
			if(cheapestProp.getPrice() > currProp.getPrice()) {
				cheapestProp = currProp;
			}
		}
		return "mortgage " + cheapestProp.getShortName();
	}

}
