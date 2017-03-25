package deadwood;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;


public class SetUp{

	private static ArrayList<Players> users = new ArrayList<Players>();
	private static List<Locations> LocationList = new ArrayList<Locations>();
	Propertys p;

	public SetUp(){
		//set up board

		LocationList.add(new Locations("Go", 0));
		LocationList.add(new Propertys("Mediterranean Avenue", "Mediterranean", 1, 60, 30, 2, "Brown", 50));
		LocationList.add(new Locations("Community Chest", 2));
		LocationList.add(new Propertys("Baltic Avenue", "Baltic", 3, 60, 30, 4, "Brown", 50));
		LocationList.add(new Locations("Income Tax", 4));
		LocationList.add(new Services("Reading Railroad","Reading", 5, 200,100,25,"railroad"));
		LocationList.add(new Propertys("Oriental Avenue", "Oriental", 6, 100, 50, 6, "Turquoise", 50));
		LocationList.add(new Locations("Chance", 7));
		LocationList.add(new Propertys("Vermont Avenue", "Vermont", 8, 100, 50, 6, "Turquoise", 50));
		LocationList.add(new Propertys("Connecticut Avenue", "Connecticut", 9, 120, 60, 8, "Turquoise", 50 ));
		LocationList.add(new Locations("Jail", 10));
		LocationList.add(new Propertys("St. Charles Place", "Charles", 11, 140, 50, 10, "Pink", 100 ));
		LocationList.add(new Services("Electric Company","Electic", 12,150,75,10,"utilities"));
		LocationList.add(new Propertys("States Avenue", "States", 13, 140 , 70, 10, "Pink", 100 ));
		LocationList.add(new Propertys("Virginia Avenue", "Virginia", 14, 160, 80, 12, "Pink", 100 ));
		LocationList.add(new Services("Pennsylvania Railroad","Pennsylvania", 15, 200,100,25,"railroad"));
		LocationList.add(new Propertys("St. James Place", "James", 16, 180, 90, 14, "Orange",100));
		LocationList.add(new Locations("Community Chest", 17));
		LocationList.add(new Propertys("Tennessee Avenue", "Tennessee", 18, 180, 90, 14, "Orange",100 ));
		LocationList.add(new Propertys("New York Avenue", "York", 19, 200, 100, 16, "Orange",100));
		LocationList.add(new Locations("Free Parking", 20));
		LocationList.add(new Propertys("Kentucky Avenue", "Kentucky", 21, 220, 110, 18, "Red", 150 ));
		LocationList.add(new Locations("Chance", 22));
		LocationList.add(new Propertys("Indiana Avenue", "Indiana", 23, 220, 110, 18, "Red", 150 ));
		LocationList.add(new Propertys("Illinois Avenue", "Illinois", 24, 240, 120, 20, "Red", 150));
		LocationList.add(new Services("B & O Railroad","B&O", 25,200,100,50,"railroad"));
		LocationList.add(new Propertys("Atlantic Avenue", "Atlantic", 26, 260, 130, 22, "Yellow", 150));
		LocationList.add(new Propertys("Ventnor Avenue", "Ventnor", 27, 260, 130, 22, "Yellow", 150));
		LocationList.add(new Services("Water Works","Water", 28,150,75,10,"utilities"));
		LocationList.add(new Propertys("Marvin Gardens", "Marvin", 29, 280, 140, 24, "Yellow", 150));
		LocationList.add(new Locations("Go To Jail", 30));
		LocationList.add(new Propertys("Pacific Avenue", "Pacific", 31, 300, 150, 26, "Green", 200));
		LocationList.add(new Propertys("North Carolina Avenue", "Carolina", 32, 300, 150, 26, "Green", 200));
		LocationList.add(new Locations("Community Chest", 33));
		LocationList.add(new Propertys("Pennsylvania Avenue", "Pennsylvania", 34, 320, 160, 28, "Green", 200));
		LocationList.add(new Services("Short Line Railroad","Short",35, 200,100,50,"railroad"));
		LocationList.add(new Locations("Chance", 36));
		LocationList.add(new Propertys("Park Place", "Park", 37, 350, 175, 35, "Blue", 200 ));
		LocationList.add(new Locations("Luxery Tax", 38));
		LocationList.add(new Propertys("Boardwalk", "Boardwalk", 39, 400, 200, 50, "Blue", 200));

	}

	public void playerCheck() {
		//set up players
		try { // try/catch ensures an integer value is entered
			int j = Integer.parseInt(Cmd_panel.getCommand());
			//get amount of players
			if(j <= 6 && j >= 2) {
				for(int i = 0; i < j; i++) {
					users.add(new Players());
					users.get(i).setId(i);
				}
			} else {
				Info_Panel.UserInput("Error: Invalid amount, 2 to 6 players allowed");
				playerCheck();
				return;
			}
		}
		catch (NumberFormatException e) {
			Info_Panel.UserInput("Error: Please enter a valid number");
			playerCheck();
			return;
		}

		Board.refresh();

		//get player names
		int k = 1;
		for(Players element : users ){
			Info_Panel.UserInput("Enter name for player " + k);
			element.setName(Cmd_panel.getCommand());
			k++;
		}

		for(Players element : users){
			int total = TurnControl.roll(element);
			element.setFirstRoll(total);
		}

		Collections.sort(users, new Comparator<Players>(){
			@Override public int compare(Players p1, Players p2){
				return p2.getFirstRoll() - p1.getFirstRoll(); // Ascending
			}
		});

		Board.refresh();
	}

	public static ArrayList<Players> getPlayers(){
		return users;
	}
	public static List<Locations> getLocationsList(){
		return LocationList;
	}

}
