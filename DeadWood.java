import java.util.ArrayList;

public class DeadWood implements Bot {

	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects

	private boolean rollDone = false;
	private Property prop;
	//private Square square;
	//private ArrayList<Property> ownedProperty;
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
		String command = "done";
		
		isReroll();
		command = tryToBuyProperty();
		if(command=="done") command = checkForJail();
		if(command=="done") command = tryToRoll();
		if(command=="done") command = checkForNegativeBal();
		
		if(command=="done") rollDone = false;
		return command;
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
	
	private String tryToBuyProperty() {
		//square = board.getSquare(player.getPosition());
		if(boardBot.isProperty(playerBot.getPosition())) {
			prop = boardBot.getProperty(playerBot.getPosition());
		}
		if(prop!=null && prop.getOwner()==null) {
				if(playerBot.getBalance() < prop.getPrice()) {
					return mortgageCheapestProperty();
				}
				return "buy";
		}
		return "done";
	}
	
	private String checkForJail() {
		if(playerBot.isInJail()) {
			rollDone = true;
			return getDecision();
		}
		return "done";
	}
	
	private String tryToRoll() {
		if(!rollDone) {
			rollDone = true;
			return "roll";
		}
		return "done";
	}
	
	private String checkForNegativeBal() {
		if(playerBot.getBalance() < 0) {
			return mortgageCheapestProperty();
		}
		return "done";
	}
	
	private String mortgageCheapestProperty() {
		ArrayList<Property> ownedProperty = playerBot.getProperties();
		if(ownedProperty != null) {
			Property cheapestProp = null;
			int i=0;
			while(cheapestProp==null && i<ownedProperty.size()) {
				if(!ownedProperty.get(i).isMortgaged()) {
					cheapestProp = ownedProperty.get(i);
				}
				i++;
			}
			if(cheapestProp!=null) {
				for(Property currProp : ownedProperty) {
					if(cheapestProp.getPrice() > currProp.getPrice() && !currProp.isMortgaged()) {
						cheapestProp = currProp;
					}
				}
				return "mortgage " + cheapestProp.getShortName();
			}
		}
		return "done";
	}

}
