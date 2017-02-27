package deadwood;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TurnControl{

	private static String temp;
	private static Boolean cond, roll;
	private static Boolean playGame = true;

	public static void turn(){
		for(Players element : SetUp.getPlayers()){
			if(playGame) {
				Info_Panel.UserInput(element.getName() + "'s turn");
				roll = true;
				cond = true;
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
					Info_Panel.UserInput("Player has already rolled");
				}
				break;

			case "buy" :
				if(SetUp.getLocationsList().get(element.getLocation()) instanceof Propertys){
					if(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() == null){
						element.buy(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getValue()); //takes money away from players balance
						((Propertys) SetUp.getLocationsList().get(element.getLocation())).setOwner(element); //sets owner of property
						element.propertyBought((Propertys) SetUp.getLocationsList().get(element.getLocation())); // adds property name to propertyNames array in Players which will be use for querying owned property
						Info_Panel.UserInput(element.getName() + " bought " + SetUp.getLocationsList().get(element.getLocation()).getName());
					} else {
						Info_Panel.UserInput("Property already bought");
					}
				} else {
					Info_Panel.UserInput("Invalid Command");
				}
				break;

			case "balance" :
				Info_Panel.UserInput(Integer.toString(element.getBalance()));
				break;

			case "property" :
				Info_Panel.UserInput(element.getPropertiesOwned());
				break;

			case "help" :
				Info_Panel.UserInput("type 'roll' to move player");
				Info_Panel.UserInput("type 'buy' to buy property");
				Info_Panel.UserInput("type 'balance' to get bank balance");
				Info_Panel.UserInput("type 'property' to query owned property");
				Info_Panel.UserInput("type 'done' when you are finished your turn");
				Info_Panel.UserInput("type 'quit' to end game");
				break;

			case "done" :
				if(roll==true){
					Info_Panel.UserInput("You must roll first");
				}
				else cond = false;
				break;

			case "test" :
				element.addBalance(500);
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
				Info_Panel.UserInput("Invalid commad");
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
		/*Info_Panel.UserInput("How Many Spaces? (Hit Enter for default roll)");
    String temp1 = Cmd_panel.getCommand();
    int num;

    try {
        num = Integer.parseInt(temp1);
        element.move(num);
      } catch (NumberFormatException e) {
        Info_Panel.UserInput("invalid number");
        cmdCheck(element);
      }
    //allows manually moving and moving based on roll
      /*if(temp1.equals("")){
        element.move();
      }
      else{
        try {
            num = Integer.parseInt(temp1);
            element.move(num);
          } catch (NumberFormatException e) {
            Info_Panel.UserInput("invalid number");
            cmdCheck(element);
          }
      }*/

		element.move();

		//info on square
		Info_Panel.UserInput("\n" + SetUp.getLocationsList().get(element.getLocation()).getName());
		if(SetUp.getLocationsList().get(element.getLocation()) instanceof Propertys){
			Info_Panel.UserInput("Cost: " + Integer.toString(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getValue()));
			if(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() != null){
				Info_Panel.UserInput(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwnerName());
			} else {
				Info_Panel.UserInput("No Owner");
			}
		}
	}
}
