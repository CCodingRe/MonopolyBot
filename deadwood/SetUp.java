package deadwood;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;


public class SetUp{

	private static ArrayList<Players> users = new ArrayList<Players>();
	private static List<Locations> LocationList = new ArrayList<Locations>();
	public static ArrayList<Players> sameRollUsers = new ArrayList<Players>();
	Propertys p;

	public SetUp(){
		//set up board
		LocationList.add(new Locations("Go", 0));
		LocationList.add(new Propertys("Mediterranean Avenue", "Mediterranean", 1, 60, 30, 2, 10, 30, 90, 160, 250, "Brown", 50));
		LocationList.add(new Locations("Community Chest", 2));
		LocationList.add(new Propertys("Baltic Avenue", "Baltic", 3, 60, 30, 4,20,60,180,320,450, "Brown", 50));
		LocationList.add(new Locations("Income Tax", 4));
		LocationList.add(new Services("Reading Railroad","Reading", 5, 200, 100, 25, 50, 100, 200, 200, 200, "railroad"));
		LocationList.add(new Propertys("Oriental Avenue", "Oriental", 6, 100, 50, 6,30,90,270,400,550, "Turquoise", 50));
		LocationList.add(new Locations("Chance", 7));
		LocationList.add(new Propertys("Vermont Avenue", "Vermont", 8, 100, 50, 6,30,90,270,400,550, "Turquoise", 50));
		LocationList.add(new Propertys("Connecticut Avenue", "Connecticut", 9, 120, 60, 8,40,100,300,450,600, "Turquoise", 50 ));
		LocationList.add(new Locations("Jail", 10));
		LocationList.add(new Propertys("St. Charles Place", "Charles", 11, 140, 50, 10,50,150,450,625,750, "Pink", 100 ));
		LocationList.add(new Services("Electric Company","Electic", 12, 150, 75, 4, 10, 10, 10, 10, 10, "utilities"));
		LocationList.add(new Propertys("States Avenue", "States", 13, 140 , 70, 10,50,150,450,625,750, "Pink", 100 ));
		LocationList.add(new Propertys("Virginia Avenue", "Virginia", 14, 160, 80, 12,60,180,500,700,900, "Pink", 100 ));
		LocationList.add(new Services("Pennsylvania Railroad","Pennsylvania", 15, 200, 100, 25, 50, 100, 200, 200, 200, "railroad"));
		LocationList.add(new Propertys("St. James Place", "James", 16, 180, 90, 14,70,200,550,750,950, "Orange",100));
		LocationList.add(new Locations("Community Chest", 17));
		LocationList.add(new Propertys("Tennessee Avenue", "Tennessee", 18, 180, 90, 14, 70,200,550,750,950, "Orange",100 ));
		LocationList.add(new Propertys("New York Avenue", "York", 19, 200, 100, 16,80,220,600,800,1000 , "Orange",100));
		LocationList.add(new Locations("Free Parking", 20));
		LocationList.add(new Propertys("Kentucky Avenue", "Kentucky", 21, 220, 110, 18,90,250,700,875,1050, "Red", 150 ));
		LocationList.add(new Locations("Chance", 22));
		LocationList.add(new Propertys("Indiana Avenue", "Indiana", 23, 220, 110, 18,90,250,700,875,1050, "Red", 150 ));
		LocationList.add(new Propertys("Illinois Avenue", "Illinois", 24, 240, 120, 20,100,300,750,925,1100 , "Red", 150));
		LocationList.add(new Services("B & O Railroad","B&O", 25, 200, 100, 25, 50, 100, 200, 200, 200, "railroad"));
		LocationList.add(new Propertys("Atlantic Avenue", "Atlantic", 26, 260, 130, 22,110,330,800,975,1150, "Yellow", 150));
		LocationList.add(new Propertys("Ventnor Avenue", "Ventnor", 27, 260, 130, 22,110,330,800,975,1150, "Yellow", 150));
		LocationList.add(new Services("Water Works","Water", 28, 150, 75, 4, 10, 10, 10, 10, 10, "utilities"));
		LocationList.add(new Propertys("Marvin Gardens", "Marvin", 29, 280, 140, 24,120,360,850,1025,1200, "Yellow", 150));
		LocationList.add(new Locations("Go To Jail", 30));
		LocationList.add(new Propertys("Pacific Avenue", "Pacific", 31, 300, 150, 26,130,390,900,1100,1275, "Green", 200));
		LocationList.add(new Propertys("North Carolina Avenue", "Carolina", 32, 300, 150, 26,130,390,900,1100,1275, "Green", 200));
		LocationList.add(new Locations("Community Chest", 33));
		LocationList.add(new Propertys("Pennsylvania Avenue", "Pennsylvania", 34, 320, 160, 28,150,450,1000,1200,1400, "Green", 200));
		LocationList.add(new Services("Short Line Railroad","Short", 35, 200, 100, 25, 50, 100, 200, 200, 200, "railroad"));
		LocationList.add(new Locations("Chance", 36));
		LocationList.add(new Propertys("Park Place", "Park", 37, 350, 175, 35,175,500,1100,1300,1500, "Blue", 200 ));
		LocationList.add(new Locations("Luxery Tax", 38));
		LocationList.add(new Propertys("Boardwalk", "Boardwalk", 39, 400, 200, 50,200,600,1400,1700,2000, "Blue", 200));

	}

	public void playerCheck() {
		addPlayers();
		Board.refresh();
		addPlayerNames();
		doFirstRoll();
		sortPlayers();
		Board.refresh();
	}

	private static void addPlayers() {
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
				addPlayers();
				return;
			}
		}
		catch (NumberFormatException e) {
			Info_Panel.UserInput("Error: Please enter a valid number");
			addPlayers();
			return;
		}
	}

	private static void addPlayerNames() {
		//get player names
		int k = 1;
		for(Players element : users ) {
			Info_Panel.UserInput("Enter name for player " + k);
			element.setName(Cmd_panel.getCommand());
			k++;
		}
	}

	private static void doFirstRoll() {
		int highestTotal = 0;
		for(Players element : users){
			int total = TurnControl.initialRoll(element);
			element.setFirstRoll(total);
			if(highestTotal < element.getFirstRoll()){
				highestTotal = element.getFirstRoll();
			}
		}



	for(int possDuplicate=0;possDuplicate<users.size();possDuplicate++){
		if(users.get(possDuplicate).getFirstRoll()==highestTotal){
			sameRollUsers.add(users.get(possDuplicate));
			users.remove(users.get(possDuplicate));
		}
	}
			if(sameRollUsers.size()<2){
				users.add(sameRollUsers.get(0));
					}
					else if(sameRollUsers.size()>=2){
						Info_Panel.UserInput("The following players rolled the same and must roll again!");
							for(int Duplicate=0;Duplicate<sameRollUsers.size();Duplicate++){
									Info_Panel.UserInput(sameRollUsers.get(Duplicate).getName() + " rolled the same! ");
								}

								for(int newRoll=0; newRoll<sameRollUsers.size();newRoll++)
								{
									int aTotal = sameRollUsers.get(newRoll).getFirstRoll() + TurnControl.initialRoll(sameRollUsers.get(newRoll));
									sameRollUsers.get(newRoll).setFirstRoll(aTotal);
									users.add(sameRollUsers.get(newRoll));
								}
							}
	}

	private static void sortPlayers() {
		Collections.sort(users, new Comparator<Players>(){
			@Override public int compare(Players p1, Players p2){
				return p2.getFirstRoll() - p1.getFirstRoll(); // Ascending
			}
		});
	}


	public static ArrayList<Players> getPlayers(){
		return users;
	}
	public static List<Locations> getLocationsList(){
		return LocationList;
	}

}
