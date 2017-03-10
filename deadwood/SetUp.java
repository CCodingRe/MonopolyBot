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
		LocationList.add(new Propertys("Mediterranean Avenue", "Mediterranean", 1, 60, 30, 5));
		LocationList.add(new Locations("Community Chest", 2));
		LocationList.add(new Propertys("Baltic Avenue", "Baltic", 3, 60, 30, 5));
		LocationList.add(new Locations("Income Tax", 4));
		LocationList.add(new Locations("Reading Railroad", 5));
		LocationList.add(new Propertys("Oriental Avenue", "Oriental", 6, 100, 50, 5));
		LocationList.add(new Locations("Chance", 7));
		LocationList.add(new Propertys("Vermont Avenue", "Vermont", 8, 100, 50, 5 ));
		LocationList.add(new Propertys("Connecticut Avenue", "Connecticut", 9, 120, 60, 5 ));
		LocationList.add(new Locations("Jail", 10));
		LocationList.add(new Propertys("St. Charles Place", "Charles", 11, 140, 50, 5 ));
		LocationList.add(new Locations("Electric Company", 12));
		LocationList.add(new Propertys("States Avenue", "States", 13, 140 , 70, 5 ));
		LocationList.add(new Propertys("Virginia Avenue", "Virginia", 14, 160, 80, 5 ));
		LocationList.add(new Locations("Pennsylvania Railroad", 15));
		LocationList.add(new Propertys("St. James Place", "James", 16, 180, 90, 5));
		LocationList.add(new Locations("Community Chest", 17));
		LocationList.add(new Propertys("Tennessee Avenue", "Tennessee", 18, 180, 90, 5 ));
		LocationList.add(new Propertys("New York Avenue", "York", 19, 200, 100, 5 ));
		LocationList.add(new Locations("Free Parking", 20));
		LocationList.add(new Propertys("Kentucky Avenue", "Kentucky", 21, 220, 110, 5 ));
		LocationList.add(new Locations("Chance", 22));
		LocationList.add(new Propertys("Indiana Avenue", "Indiana", 23, 220, 110, 5 ));
		LocationList.add(new Propertys("Illinois Avenue", "Illinois", 24, 240, 120, 5 ));
		LocationList.add(new Locations("B & O Raiload", 25));
		LocationList.add(new Propertys("Atlantic Avenue", "Atlantic", 26, 260, 130, 5));
		LocationList.add(new Propertys("Ventnor Avenue", "Ventnor", 27, 260, 130, 5));
		LocationList.add(new Locations("Water Works", 28));
		LocationList.add(new Propertys("Marvin Gardens", "Marvin", 29, 280, 140, 5));
		LocationList.add(new Locations("Go To Jail", 30));
		LocationList.add(new Propertys("Pacific Avenue", "Pacific", 31, 300, 150, 5));
		LocationList.add(new Propertys("North Carolina Avenue", "Carolina", 32, 300, 150, 5));
		LocationList.add(new Locations("Community Chest", 33));
		LocationList.add(new Propertys("Pennsylvania Avenue", "Pennsylvania", 34, 320, 160, 5));
		LocationList.add(new Locations("Short Line", 35));
		LocationList.add(new Locations("Chance", 36));
		LocationList.add(new Propertys("Park Place", "Park", 37, 350, 175, 5 ));
		LocationList.add(new Locations("Luxery Tax", 38));
		LocationList.add(new Propertys("Boardwalk", "Boardwalk", 39, 400, 200, 5));

	}

	public void playerCheck(){
		//set up players
		int j = Integer.parseInt(Cmd_panel.getCommand());

		//get amount of players
		if(j <= 6 && j >= 2){
			for(int i = 1; i <= j; i++){
				users.add(new Players());
			}

			Board.refresh();

			//get player names
			int k = 1;
			for(Players element : users ){
				Info_Panel.UserInput("Enter name for player " + k);
				element.setName(Cmd_panel.getCommand());
				k++;
			}
			Board.refresh();

		} else {
			Info_Panel.UserInput("Invalid amount, 2 to 6 players allowed");
			playerCheck();
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

		int i = 0;
		for(Players element : users) {
			element.setId(i);
			i++;
		}


	}

	public static ArrayList<Players> getPlayers(){
		return users;
	}
	public static List<Locations> getLocationsList(){
		return LocationList;
	}

}