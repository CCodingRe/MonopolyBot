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

	public static void turn(){
		for(it = players.iterator(); it.hasNext(); ) { // iterator iterates through the players arrayList. This avoids a ConcurrentModificationException when declaring bankruptcy and removing elements
			Players currPlayer = it.next();
			if(playGame) {
				Info_Panel.UserInput("\n" + currPlayer.getName() + "'s turn");
				roll = true;
				cond = true;
				rent = true;
				cmdCheck(currPlayer);
			}
		}
		if(playGame) {
			turn();
		}
	}

	private static void cmdCheck(Players currPlayer){
		while(cond){
			Info_Panel.UserInput("Enter Command: ");
			Locations loc;
			String[] s = Cmd_panel.getCommand().split(" "); //input commands are split into words and stored in array
			String command = s[0];
			switch(command){

			case "roll" :
				if(roll && rent){ // ensures player pays rent on current property if necessary
					roll = false;
					movePlayer(currPlayer);
					loc = locations.get(currPlayer.getLocation());
					if(loc instanceof Propertys){
						if((((Propertys) loc).getOwner() != currPlayer) && (((Propertys)loc).getOwner() != null) && (((Propertys)loc).isMortgaged() != true)) { // checks if the property is owned by another player and if it's not mortgaged
							rent = false;
							Info_Panel.UserInput("You must pay rent");
						}
					}
						if(loc instanceof Services){
							if((((Services) loc).getOwner() != currPlayer) && (((Services)loc).getOwner() != null) && (((Services)loc).isMortgaged() != true)) { // checks if the property is owned by another player and if it's not mortgaged
								rent = false;
							}
						}
				}
				else if(!roll) Info_Panel.UserInput("Error: Player has already rolled");
				else if(!rent) Info_Panel.UserInput("Error: You must pay outstanding rent");

				break;


			case "buy" :
				loc = locations.get(currPlayer.getLocation());
				if(loc instanceof Propertys){ //checks if player is on property
					if(((Propertys) loc).getOwner() == null){
						if(currPlayer.deductBalance(((Propertys) loc).getValue()) == true) { //takes money away from players balance if it doesn't leave balance below 0, if so the rest will run
							((Propertys) loc).setOwner(currPlayer); //sets owner of property
							currPlayer.propertyBought((Propertys) loc); // adds property name to propertyNames array in Players which will be use for querying owned property
							Info_Panel.UserInput(currPlayer.getName() + " bought " + loc.getName() + " for $" + ((Propertys) loc).getValue());
						}

					}	 else {
						Info_Panel.UserInput("Error: Property already bought");
					}
				}

				else if(loc instanceof Services){ //checks if player is on property
					if(((Services) loc).getOwner() == null){
						if(currPlayer.deductBalance(((Services) loc).getValue()) == true) { //takes money away from players balance if it doesn't leave balance below 0, if so the rest will run
							((Services) loc).setOwner(currPlayer); //sets owner of property
							currPlayer.servicesBought((Services) loc); // adds property name to propertyNames array in Players which will be use for querying owned property
							Info_Panel.UserInput(currPlayer.getName() + " bought " + loc.getName() + " for $" + ((Services) loc).getValue());

						}

					}	 else {
						Info_Panel.UserInput("Error: Property already bought");
					}
				} else {
					Info_Panel.UserInput("Error: Invalid Command");
				}
				break;


			case "balance" :
				Info_Panel.UserInput(Integer.toString(currPlayer.getBalance()));
				break;


			case "property" :
				Info_Panel.UserInput(currPlayer.getPropertiesOwned()); //return list of players property
				break;


			case "pay" :
				if(!(s.length == 2)){
					Info_Panel.UserInput("Error: Invalid command");
					break;
				}
				if(s[1].equalsIgnoreCase("rent")) {
					loc = locations.get(currPlayer.getLocation());
					if(loc instanceof Propertys){ //checks that player is on a property
						if((((Propertys) loc).getOwner() != currPlayer) && (((Propertys) loc).getOwner() != null) ){
							if(currPlayer.deductBalance(((Propertys) loc).getRent()) == true) { //take rent from player if it doesn't leave balance below 0, if so the rest will run
								((Propertys) loc).getOwner().addBalance(((Propertys) loc).getRent());//give rent to property owner
								rent = true;
								Info_Panel.UserInput(currPlayer.getName() + " paid $" + ((Propertys) loc).getRent() + " to " + ((Propertys) loc).getOwnerName());
							}
						}else{
							Info_Panel.UserInput("Error: Can't pay rent here");
						}
					}else if(loc instanceof Services){ //checks that player is on a property
						if((((Services) loc).getOwner() != currPlayer) && (((Services) loc).getOwner() != null) ){
							if(currPlayer.deductBalance(((Services) loc).getRent()) == true) { //take rent from player if it doesn't leave balance below 0, if so the rest will run
								((Services) loc).getOwner().addBalance(((Services) loc).getRent());//give rent to property owner
								rent = true;
								Info_Panel.UserInput(currPlayer.getName() + " paid $" + ((Services) loc).getRent() + " to " + ((Services) loc).getOwnerName());
							}
						}
					} else {
						Info_Panel.UserInput("Error: Invalid command");
					}
				}

				break;


			case "mortgage" :
				if(!(s.length == 2)){
					Info_Panel.UserInput("type 'mortgage <propertyname>' to mortgage a property");
					break;
				}

				String propertyName = s[1];
				Propertys prop = propertyFinder(propertyName);
				Services service = serviceFinder(propertyName);
				if(prop==null ) {
					if(service==null){
						Info_Panel.UserInput("Error: Invalid input name");
						break;
					} else {
						if(propertyName.equalsIgnoreCase(service.getInputName()) && currPlayer==service.getOwner() && service.isMortgaged()==false) { // if the input name is correct, the player owns the property and it is not already mortgaged
							service.mortgage();
							currPlayer.addBalance(service.getMortgageValue());
							Info_Panel.UserInput(currPlayer.getName() + " mortgaged " + service.getName() + " for $" + service.getMortgageValue());
						}
						else if(propertyName.equalsIgnoreCase(service.getInputName()) && currPlayer!=service.getOwner()) {
							Info_Panel.UserInput("Error: You don't own this property");
						}
						else if(propertyName.equalsIgnoreCase(service.getInputName()) && currPlayer==service.getOwner() && service.isMortgaged()) {
							Info_Panel.UserInput("Error: Property is already mortgaged");
						}
					}
				} else {
					if(propertyName.equalsIgnoreCase(prop.getInputName()) && currPlayer==prop.getOwner() && prop.isMortgaged()==false && prop.getUnits()==0) { // if the input name is correct, the player owns the property and it is not already mortgaged
						prop.mortgage();
						currPlayer.addBalance(prop.getMortgageValue());
						Info_Panel.UserInput(currPlayer.getName() + " mortgaged " + prop.getName() + " for $" + prop.getMortgageValue());
					}
					else if(propertyName.equalsIgnoreCase(prop.getInputName()) && currPlayer!=prop.getOwner()) {
						Info_Panel.UserInput("Error: You don't own this property");
					}
					else if(propertyName.equalsIgnoreCase(prop.getInputName()) && currPlayer==prop.getOwner() && prop.isMortgaged()) {
						Info_Panel.UserInput("Error: Property is already mortgaged");
					}
					else if(propertyName.equalsIgnoreCase(prop.getInputName()) && currPlayer==prop.getOwner() && prop.isMortgaged()==false && prop.getUnits()>=0) {
						Info_Panel.UserInput("Error: Please sell all houses/hotel before mortgaging");
					}
				}
				break;


			case "redeem" :
				if(!(s.length == 2)){
					Info_Panel.UserInput("type 'redeem <propertyname>' to redeem a mortgaged property");
					break;
				}

				propertyName = s[1];
				prop = propertyFinder(propertyName);
				service = serviceFinder(propertyName);
        if(prop==null){
					if(service == null){
						Info_Panel.UserInput("Error: Invalid input name");
						break;
					}
          if(propertyName.equalsIgnoreCase(service.getInputName()) && currPlayer==service.getOwner() && service.isMortgaged()) { // if the input name is correct, the player owns the property and it is already mortgaged
            if(currPlayer.deductBalance(service.getRedeemValue()) == true) {
              service.redeem();
              Info_Panel.UserInput(currPlayer.getName() + " redeemed " + service.getName() + " for $" + service.getRedeemValue());
            }
          }
          else if(propertyName.equalsIgnoreCase(service.getInputName()) && currPlayer!=service.getOwner()) {
            Info_Panel.UserInput("Error: You don't own this property");
          }
          else if(propertyName.equalsIgnoreCase(service.getInputName()) && currPlayer==service.getOwner() && service.isMortgaged()==false) {
            Info_Panel.UserInput("Error: Property is not already mortgaged");
          }
        } else {
          if(propertyName.equalsIgnoreCase(prop.getInputName()) && currPlayer==prop.getOwner() && prop.isMortgaged()) { // if the input name is correct, the player owns the property and it is already mortgaged
            if(currPlayer.deductBalance(prop.getRedeemValue()) == true) {
              prop.redeem();
              Info_Panel.UserInput(currPlayer.getName() + " redeemed " + prop.getName() + " for $" + prop.getRedeemValue());
            }
          }
          else if(propertyName.equalsIgnoreCase(prop.getInputName()) && currPlayer!=prop.getOwner()) {
            Info_Panel.UserInput("Error: You don't own this property");
          }
          else if(propertyName.equalsIgnoreCase(prop.getInputName()) && currPlayer==prop.getOwner() && prop.isMortgaged()==false) {
            Info_Panel.UserInput("Error: Property is not already mortgaged");
          }
				}
				break;


			case "input" :
				if(!(s.length == 2)){
					Info_Panel.UserInput("Error: Invalid command");
					break;
				}
				if(s[1].equalsIgnoreCase("names")){
					Info_Panel.UserInput("");
					for(Locations property : locations) {
						if(property instanceof Propertys) { //checks that the property object is a property
							Info_Panel.UserInput(property.getName() + " - " + ((Propertys) property).getInputName());
						}
					}
					Info_Panel.UserInput("");
				} else {
					Info_Panel.UserInput("Error: Invalid command");
				}
				break;


			case "bankrupt" :
				Info_Panel.UserInput(currPlayer.getName() + " has declared bankrupty");
				for(Locations property : locations) {
					if(property instanceof Propertys) { //checks that the property object is a property
						if(currPlayer==((Propertys) property).getOwner()) { // if the player owns the property
							((Propertys) property).setOwner(null);
							((Propertys) property).redeem();
							((Propertys) property).removeUnits(((Propertys) property).getUnits());
						}
					}
					if(property instanceof Services) { //checks that the property object is a property
						if(currPlayer==((Services) property).getOwner()) { // if the player owns the property
							((Services) property).setOwner(null);
							((Services) property).redeem();
						}
					}
				}

				it.remove();
				Board.refresh();
				cond = false;
				playGame = checkWinner();
				break;


			case "build" :
				if(!(s.length == 3)){
					Info_Panel.UserInput("type 'build <propertyname> <number of units>' to buy houses for property");
					Info_Panel.UserInput("type 'build <propertyname> hotel' to buy hotel for property");
					break;
				}

				propertyName = s[1];
				prop = propertyFinder(propertyName);

				if(prop==null){
					Info_Panel.UserInput("Error: Invalid property input name");
					break;
				}

				boolean monopoly = groupCheck(currPlayer, prop.getGroup());

				if(currPlayer==prop.getOwner() && !(prop.isMortgaged()) && monopoly){
					try{
						if(s[2].equalsIgnoreCase("hotel")){
							if(prop.getUnits() == 4){
								prop.addUnits(1);
								Info_Panel.UserInput("Building Hotel on " + prop.getName());
							} else if (prop.getUnits() == 5){
								Info_Panel.UserInput("Error: Hotel already built here");
							} else {
								Info_Panel.UserInput("Error: Need 4 houses before building hotel");
							}
						} else {
							int units = Integer.parseInt(s[2]);
							if((prop.getUnits() + units) <= 4){
								prop.addUnits(units);
								Info_Panel.UserInput("Building " + units + " houses on " + prop.getName());
							} else {
								Info_Panel.UserInput("Error: Max of 4 houses per property");
							}
						}

					} catch(NumberFormatException e) {
						Info_Panel.UserInput("Error: ensure correct inputs");
					}
				} else {
					Info_Panel.UserInput("Error: can't build here");
				}
				break;

			case "demolish"	:
				if(!(s.length == 3)){
					Info_Panel.UserInput("type 'demolish <propertyname> <number of units>' to buy houses for property");
					Info_Panel.UserInput("type 'demolish <propertyname> hotel' to buy houses for property");
				}

				prop = propertyFinder(s[1]);
				if(prop==null){ Info_Panel.UserInput("Error: Incorrect Property Name"); break;}

				if(currPlayer==prop.getOwner()){
					try{
						if(s[2].equalsIgnoreCase("hotel")){
							if(prop.getUnits() == 5){
								prop.removeUnits(1);
								Info_Panel.UserInput("Demolishing Hotel on " + prop.getName());
							} else {
								Info_Panel.UserInput("Error: No Hotel to demolish");
							}
						} else {
							int units = Integer.parseInt(s[2]);
							if(prop.getUnits() >= units){
								prop.removeUnits(units);
								Info_Panel.UserInput("Demolishing " + units + " houses on " + prop.getName());
							} else {
								Info_Panel.UserInput("Error: You dont't have that many houses");
							}
						}

					} catch(NumberFormatException e) {
						Info_Panel.UserInput("Error: ensure correct inputs");
					}
				} else {
					Info_Panel.UserInput("Error: can't build here");
				}
				break;


			case "move" : //for easier testing, move <number of spaces>
				int n = Integer.parseInt(s[1]);
				currPlayer.move(n);
				loc = locations.get(currPlayer.getLocation());
				squareInfo(loc);
				break;


			case "help" :
				Info_Panel.UserInput("\ntype 'roll' to move player");
				Info_Panel.UserInput("type 'buy' to buy property");
				Info_Panel.UserInput("type 'pay rent' to pay rent");
				Info_Panel.UserInput("type 'build <propertyname> <number of units>' to buy houses for property");
				Info_Panel.UserInput("type 'build <propertyname> hotel' to buy hotel for property");
				Info_Panel.UserInput("type 'demolish <propertyname> <number of units>' to buy houses for property");
				Info_Panel.UserInput("type 'demolish <propertyname> hotel' to buy houses for property");
				Info_Panel.UserInput("type 'balance' to get bank balance");
				Info_Panel.UserInput("type 'property' to query owned property");
				Info_Panel.UserInput("type 'mortgage <propertyname>' to mortgage a property");
				Info_Panel.UserInput("type 'redeem <propertyname>' to redeem a mortgaged property");
				Info_Panel.UserInput("type 'input names' to get a list of input names for the properties");
				Info_Panel.UserInput("type 'done' when you are finished your turn");
				Info_Panel.UserInput("type 'bankrupt' to declare bankruptcy");
				Info_Panel.UserInput("type 'quit' to end game\n");

				break;


			case "done" :
				if(roll==true){
					Info_Panel.UserInput("Error: You must finish rolling");
				}else if(rent==false){
					Info_Panel.UserInput("Error: You must pay outstanding rent");
				}
				else{
					cond = false;
				}
				playGame = checkWinner();

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

			case "cheat" :
				prop = propertyFinder("Mediterranean");
				prop.setOwner(currPlayer);
				prop = propertyFinder("Baltic");
				prop.setOwner(currPlayer);
				break;

			default :
				Info_Panel.UserInput("Error: Invalid command");
			}
		}
	}

	private static int rollNum;
	public static int roll(Players element) { //returns dice roll
		int dice1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		int dice2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		Info_Panel.UserInput(element.getName() + " rolled a " + dice1 + " and a " + dice2);
		if(dice1 == dice2 && element.getFirstRoll() != 0) { // if double is rolled you can roll again
			Info_Panel.UserInput("You Rolled a Double, Roll Again");
			roll = true;
		}
		rollNum = dice1 + dice2;
		return rollNum;
	}
	public static int getRoll(){
		return rollNum;
	}


	private static void movePlayer(Players currPlayer) {

		currPlayer.move();
		Locations loc = locations.get(currPlayer.getLocation());
		squareInfo(loc);

	}

	private static void squareInfo(Locations loc) {
		//info on square
		Info_Panel.UserInput("\n" + loc.getName());
		if(loc instanceof Propertys) {
			Info_Panel.UserInput("Cost: " + Integer.toString(((Propertys) loc).getValue()));
			if(((Propertys) loc).getOwner() != null){
				Info_Panel.UserInput("Owner: " + ((Propertys) loc).getOwnerName());
				Info_Panel.UserInput("Rent: " + ((Propertys) loc).getRent());
				Info_Panel.UserInput("Mortgaged: " + ((Propertys) loc).isMortgaged());
				if(((Propertys) loc).getUnits()<=4) {
					Info_Panel.UserInput("Houses: " + ((Propertys) loc).getUnits());
				}
				else Info_Panel.UserInput("Hotel");
				if(!((Propertys) loc).isMortgaged()) Info_Panel.UserInput("You must pay rent on this property.");
				else Info_Panel.UserInput("You don't need to pay rent on this property.");
			} else {
				Info_Panel.UserInput("No Owner");
				Info_Panel.UserInput("Property is for sale!");
			}
		} 
		if(loc instanceof Services) {
			Info_Panel.UserInput("Cost: " + Integer.toString(((Services) loc).getValue()));
			if(((Services) loc).getOwner() != null){
				Info_Panel.UserInput("Owner: " + ((Services) loc).getOwnerName());
				Info_Panel.UserInput("Rent: " + ((Services) loc).getRent());
				Info_Panel.UserInput("Mortgaged: " + ((Services) loc).isMortgaged());
				if(!((Services) loc).isMortgaged()) Info_Panel.UserInput("You must pay rent on this property.");
				else Info_Panel.UserInput("You don't need to pay rent on this property.");
			} else {
				Info_Panel.UserInput("No Owner");
				Info_Panel.UserInput("Property is for sale!");
			}
		}
	}

	private static Boolean checkWinner() {
		if(players.size() > 1) return true;
		else {
			Info_Panel.UserInput("\nWinner, Winner, Chicken Dinner");
			Info_Panel.UserInput(players.get(0).getName() + " is the Winner");
			return false;
		}
	}

	private static Propertys propertyFinder(String propName){
		for(Locations property : locations){
			if(property instanceof Propertys){
				if( (((Propertys) property).getInputName()).equalsIgnoreCase(propName)){
					return (Propertys) property;
				}
			}
		}
		return null;
	}

	private static Services serviceFinder(String serviceName){
		for(Locations service : locations){
			if(service instanceof Services){
				if( (((Services) service).getInputName()).equalsIgnoreCase(serviceName)){
					return (Services) service;
				}
			}
		}
		return null;
	}

	public static boolean groupCheck(Players player, String group){
		for (Locations property : locations){
			if(property instanceof Propertys){
				if ( ( (Propertys) property).getGroup().equals(group) && ((Propertys) property).getOwner() != player) return false;
			}
		}
		return true;
	}


}
