package deadwood;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class TurnControl{

	private static Boolean cond, roll, rent;
	private static Boolean playGame = true;
	private static ArrayList<Locations> locations = (ArrayList<Locations>) SetUp.getLocationsList();
	private static ArrayList<Players> players = SetUp.getPlayers();
	private static Iterator<Players> it;
	private static String propName;

	public static void turn(){
		for(it = players.iterator(); it.hasNext(); ) { // iterator iterates through the players arrayList. This avoids a ConcurrentModificationException when declaring bankruptcy and removing elements
			Players element = it.next();
			if(playGame) {
				Info_Panel.UserInput("\n" + element.getName() + "'s turn");
				roll = true;
				cond = true;
				rent = true;
				cmdCheck(element);
			}
		}
		if(playGame) {
			turn();
		}
	}

	private static void cmdCheck(Players element){
		while(cond){
			Info_Panel.UserInput("Enter Command: ");
			switch(Cmd_panel.getCommand()){

			case "roll" :
				if(roll){
					roll = false;
					movePlayer(element);
				} else {
					Info_Panel.UserInput("Error: Player has already rolled");
				}
				if(locations.get(element.getLocation()) instanceof Propertys){
					if((((Propertys) locations.get(element.getLocation())).getOwner() != element) && (((Propertys)locations.get(element.getLocation())).getOwner() != null) && (((Propertys)locations.get(element.getLocation())).isMortgaged() != true)) { // checks if the property is owned by another player and if it's not mortgaged
						rent = false;
					}
				}

				break;

			case "buy" :
				if(locations.get(element.getLocation()) instanceof Propertys){ //checks if player is on property
					if(((Propertys) locations.get(element.getLocation())).getOwner() == null){
						element.deductBalance(((Propertys) locations.get(element.getLocation())).getValue()); //takes money away from players balance
						((Propertys) locations.get(element.getLocation())).setOwner(element); //sets owner of property
						element.propertyBought((Propertys) locations.get(element.getLocation())); // adds property name to propertyNames array in Players which will be use for querying owned property
						Info_Panel.UserInput(element.getName() + " bought " + locations.get(element.getLocation()).getName() + " for $" + ((Propertys) locations.get(element.getLocation())).getValue());
					} else {
						Info_Panel.UserInput("Error: Property already bought");
					}
				} else {
					Info_Panel.UserInput("Error: Invalid Command");
				}
				break;

			case "balance" :
				Info_Panel.UserInput(Integer.toString(element.getBalance()));
				break;

			case "property" :
				Info_Panel.UserInput(element.getPropertiesOwned()); //return list of players property
				break;

			case "pay rent" :
				if(locations.get(element.getLocation()) instanceof Propertys){ //checks that player is on a property
					if((((Propertys) locations.get(element.getLocation())).getOwner() != element) && (((Propertys) locations.get(element.getLocation())).getOwner() != null) ){
						element.deductBalance(((Propertys) locations.get(element.getLocation())).getRent()); //take rent from player
						( (Propertys) locations.get(element.getLocation()) ).getOwner().addBalance(((Propertys) locations.get(element.getLocation())).getRent());//give rent to property owner
						rent = true;
						Info_Panel.UserInput(element.getName() + " paid $" + ((Propertys) locations.get(element.getLocation())).getRent());
					}else{
						Info_Panel.UserInput("Error: Can't pay rent here");
					}
				} else {
					Info_Panel.UserInput("Error: Invalid command");
				}
				break;
				
			case "mortgage" :
				Info_Panel.UserInput("What property would you like to mortgage?");
				propName = Cmd_panel.getCommand();
				Boolean isProperty = false; // checks that the user input is a property
				for(Locations property : locations) {
					if(property instanceof Propertys) { //checks that the property object is a property
						if(propName.equalsIgnoreCase(((Propertys) property).getInputName()) && element==((Propertys) property).getOwner() && ((Propertys) property).isMortgaged()==false) { // if the input name is correct, the player owns the property and it is not already mortgaged
							((Propertys) property).mortgage();
							element.addBalance(((Propertys) property).getMortgageValue());
							Info_Panel.UserInput(element.getName() + " mortgaged " + property.getName() + " for $" + ((Propertys) property).getMortgageValue());
							isProperty = true;
						}
						else if(propName.equalsIgnoreCase(((Propertys) property).getInputName()) && element != ((Propertys) property).getOwner()) {
							Info_Panel.UserInput("Error: You don't own this property");
							isProperty = true;
						}
						else if(propName.equalsIgnoreCase(((Propertys) property).getInputName()) && element==((Propertys) property).getOwner() && ((Propertys) property).isMortgaged()) {
							Info_Panel.UserInput("Error: Property is already mortgaged");
							isProperty = true;
						}
					}
				}
				if(isProperty==false) Info_Panel.UserInput("Error: Invalid property input name");
				break;
				
			case "redeem" :
				Info_Panel.UserInput("What mortgaged property would you like to redeem?");
				propName = Cmd_panel.getCommand();
				isProperty = false;
				for(Locations property : locations) {
					if(property instanceof Propertys) { //checks that the property object is a property
						if(propName.equalsIgnoreCase(((Propertys) property).getInputName()) && element==((Propertys) property).getOwner() && ((Propertys) property).isMortgaged()) { // if the input name is correct, the player owns the property and it is mortgaged
							((Propertys) property).redeem();
							element.deductBalance(((Propertys) property).getRedeemValue());
							Info_Panel.UserInput(element.getName() + " redeemed " + property.getName() + " for $" + ((Propertys) property).getRedeemValue());
							isProperty = true;
						}
						else if(propName.equalsIgnoreCase(((Propertys) property).getInputName()) && element != ((Propertys) property).getOwner()) {
							Info_Panel.UserInput("Error: You don't own this property");
							isProperty = true;
						}
						else if(propName.equalsIgnoreCase(((Propertys) property).getInputName()) && element==((Propertys) property).getOwner() && ((Propertys) property).isMortgaged()==false) {
							Info_Panel.UserInput("Error: Property is not already mortgaged");
							isProperty = true;
						}
					}
				}
				if(isProperty==false) Info_Panel.UserInput("Error: Invalid property input name");
				break;
				
			case "input names" :
				Info_Panel.UserInput("");
				for(Locations property : locations) {
					if(property instanceof Propertys) { //checks that the property object is a property
						Info_Panel.UserInput(property.getName() + " - " + ((Propertys) property).getInputName());
					}
				}
				Info_Panel.UserInput("");
				break;
				
			case "bankrupt" :
				Info_Panel.UserInput(element.getName() + " has declared bankrupty");
				for(Locations property : locations) {
					if(property instanceof Propertys) { //checks that the property object is a property
						if(element==((Propertys) property).getOwner()) { // if the player owns the property
							((Propertys) property).setOwner(null);
							((Propertys) property).redeem();
						}
					}
				}
				it.remove();
				Board.refresh();
				cond = false;
				playGame = Check(element);
				break;
				
			case "help" :
				Info_Panel.UserInput("type 'roll' to move player");
				Info_Panel.UserInput("type 'buy' to buy property");
				Info_Panel.UserInput("type 'pay rent' to pay rent");
				Info_Panel.UserInput("type 'balance' to get bank balance");
				Info_Panel.UserInput("type 'property' to query owned property");
				Info_Panel.UserInput("type 'mortgage' to mortgage an owned property");
				Info_Panel.UserInput("type 'redeem' to redeem a mortgaged property");
				Info_Panel.UserInput("type 'input names' to get a list of input names for the properties");
				Info_Panel.UserInput("type 'done' when you are finished your turn");
				Info_Panel.UserInput("type 'bankrupt' to declare bankruptcy");
				Info_Panel.UserInput("type 'quit' to end game");
				break;

			case "done" :
				if(roll==true){
					Info_Panel.UserInput("Error: You must finish rolling");
				}else if(rent == false){
					Info_Panel.UserInput("Error: You must pay outstanding rent");
				}
				else{
					cond = false;
				}
				playGame = Check(element);

				break;

			case "quit" :
				ArrayList<Players> players = SetUp.getPlayers();
				Info_Panel.UserInput("Game Over!");
				Players winner = players.get(0); // sets the initial winner to be the first player in the arrayList
				String winnerName = winner.getName();
				StringBuilder winningNames = new StringBuilder(winnerName);

				for(int i=0; i<players.size(); i++) {
					Info_Panel.UserInput(players.get(i).getName() + "'s total assets are valued at: " + players.get(i).calculateAssets());
				}
				for(int i=0; i<players.size()-1; i++) {
					int j = i+1; // to compare each element with the next
					if(winner.calculateAssets() < players.get(j).calculateAssets()) { // checks to see if the next players assets are more than the current winner
						winner = players.get(j); // sets new winner
						winnerName = winner.getName();
						winningNames = new StringBuilder(winnerName); // string builder for multiple winners created with current winner name
					}
					else if(winner.calculateAssets() == players.get(j).calculateAssets() && winner != players.get(j)) { // if player assets are equal and it ensures it doesn't compare a player with itself
						winner = players.get(j);
						winnerName = null; // sets winnerName to null so it will print all winners at the end
						winningNames.append(" and " + winner.getName()); // appends string builder adding tied winners name
					}
				}
				if(winnerName != null) {
					Info_Panel.UserInput("The winner is " + winnerName);
				}
				else Info_Panel.UserInput("It's a tie! The winners are " + winningNames.toString());

				cond = false;
				playGame = false; // prevents further commands from being entered
				break;


			default :
				Info_Panel.UserInput("Error: Invalid commad");
			}
		}
	}


	public static int roll(Players element) { //returns dice roll
		int dice1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		int dice2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		if(dice1 == dice2) { // if double is rolled you can roll again
			roll = true;
		}
		Info_Panel.UserInput(element.getName() + " rolled a " + dice1 + " and a " + dice2);
		int rollNum = dice1 + dice2;
		return rollNum;
	}


	private static void movePlayer(Players element){

		element.move();

		//info on square
		Info_Panel.UserInput("\n" + locations.get(element.getLocation()).getName());
		if(locations.get(element.getLocation()) instanceof Propertys){
			Info_Panel.UserInput("Cost: " + Integer.toString(((Propertys) locations.get(element.getLocation())).getValue()));
			if(((Propertys) locations.get(element.getLocation())).getOwner() != null){
				Info_Panel.UserInput("Owner: " + ((Propertys) locations.get(element.getLocation())).getOwnerName());
			} else {
				Info_Panel.UserInput("No Owner");
			}
			Info_Panel.UserInput("Mortgaged: " + ((Propertys) locations.get(element.getLocation())).isMortgaged());
		}
	}

	private static Boolean Check(Players e){
		if(e.getBalance() > 0){
			for(Players element : SetUp.getPlayers()){
				if((element.getBalance() > 0) && (element != e)){
					return true;
				}
			}
			Info_Panel.UserInput("Winner, Winner, Chicken Dinner");
			Info_Panel.UserInput(e.getName() + " is the Winner");
			return false;
		}
		return true;
	}
}
