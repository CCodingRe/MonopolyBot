import java.util.ArrayList;

public class DeadWood implements Bot {

	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects

	private boolean rollDone = false;
	private boolean wasInJail = false;
	private int turnsInJail = 0;
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
		String command = "done";

		if(!wasInJail) isReroll();
		if(command=="done") command = checkForJail();
		if(command=="done") command = tryToBuyProperty();
		if(command=="done") command = checkBalance();
		if(command=="done") command = tryToRoll();

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
		Property prop = null;
		//square = board.getSquare(player.getPosition());
		if(boardBot.isProperty(playerBot.getPosition())) {
			prop = boardBot.getProperty(playerBot.getPosition());
		}
		if(prop!=null && prop.getOwner()==null && prop!=boardBot.getProperty(12) && prop!=boardBot.getProperty(28) ) {
			if(playerBot.getBalance() < prop.getPrice()) {
				return mortgageCheapestProperty();
			}
			return "buy";
		}
		return "done";
	}

	private String checkForJail() {
		if(playerBot.isInJail()) {
			int ownedProp = 0;

			if(turnsInJail > 0 && !rollDone) {
				for(int checkProperties = 0; checkProperties<40; checkProperties++)
				{
					if(boardBot.isProperty(checkProperties))
					{
						if(boardBot.getProperty(checkProperties).getOwner() != null)
						{
							ownedProp += 1;
						}
					}
				}

				if(ownedProp>=24 && !rollDone)
				{
					wasInJail = true;
					rollDone = true;
					return "roll";
				} else if(ownedProp<24 && playerBot.hasGetOutOfJailCard ()==true) {
					wasInJail = true;
					return "card";
				}else{
					if(playerBot.getBalance() > 50) {
						wasInJail = true;
						return "pay";
					}
					else return mortgageCheapestProperty();
				}
			}
			else rollDone = true;
			turnsInJail += 1;
		}
		else {
			turnsInJail=0;
			wasInJail=false;
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

	private String checkBalance() {
		ownedProperty = playerBot.getProperties();
		String command;
		if(playerBot.getBalance() < 0) {
			command = mortgageCheapestProperty();
			if(command=="done") command = "bankrupt";
			return command;
		}
		else if(playerBot.getBalance() > 600) {
			for(Property currProp : ownedProperty){
				if(playerBot.isGroupOwner((Site) currProp)) {
					return "done";//TODO add build houses thing
				}
			}
			return redeemMostExpensiveProperty();
		}
		return "done";
	}



	private String mortgageCheapestProperty() {
		ownedProperty = playerBot.getProperties();
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
					if(cheapestProp.getRent() > currProp.getRent() && !currProp.isMortgaged()) {
						cheapestProp = currProp;
					}
				}
				if(((Site) cheapestProp).getNumHouses()>0){
					return "demolish" + cheapestProp.getShortName() + " 1";//TODO check building is ready
				}
				else	return "mortgage " + cheapestProp.getShortName();
			}
		}
		return "done";
	}

	private String redeemMostExpensiveProperty() {
		ownedProperty = playerBot.getProperties();
		if(ownedProperty != null) {
			Property expensiveProp = null;
			int i=0;
			while(expensiveProp==null && i<ownedProperty.size()) {
				if(ownedProperty.get(i).isMortgaged()) {
					expensiveProp = ownedProperty.get(i);
				}
				i++;
			}
			if(expensiveProp!=null) {
				for(Property currProp : ownedProperty) {
					if(expensiveProp.getPrice() < currProp.getPrice() && currProp.isMortgaged()) {
						expensiveProp = currProp;
					}
				}
				return "redeem " + expensiveProp.getShortName();
			}
		}
		return "done";
	}

}
