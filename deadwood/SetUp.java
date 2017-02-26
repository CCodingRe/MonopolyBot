package deadwood;

import java.util.ArrayList;
import java.util.List;


public class SetUp{

  private static ArrayList<Players> users = new ArrayList<Players>();
  private static List<Locations> LocationList = new ArrayList<Locations>();
  Propertys p;

  public SetUp(){
    //set up board

    LocationList.add(new Locations("Go", 0));
    LocationList.add(new Propertys("Mediterranean Avenue", 1, 60, 5));
    LocationList.add(new Locations("Community Chest", 2));
    LocationList.add(new Propertys("Baltic Avenue", 3, 60, 5));
    LocationList.add(new Locations("Income Tax", 4));
    LocationList.add(new Locations("Reading Railroad", 5));
    LocationList.add(new Propertys("Oriental Avenue", 6, 100, 5));
    LocationList.add(new Locations("Chance", 7));
    LocationList.add(new Propertys("Vemont Avenue",8, 100, 5 ));
    LocationList.add(new Propertys("Connecticut Avenue", 9, 120, 5 ));
    LocationList.add(new Locations("Jail", 10));
    LocationList.add(new Propertys("St.Charles Place",11, 140, 5 ));
    LocationList.add(new Locations("Electric Company", 12));
    LocationList.add(new Propertys("States Avenue",13, 140 , 5 ));
    LocationList.add(new Propertys("Virginia Avenue", 14, 160, 5 ));
    LocationList.add(new Locations("Pennsylvania Railroad", 15));
    LocationList.add(new Propertys("St.James Place",16, 180, 5));
    LocationList.add(new Locations("Community Chest", 17));
    LocationList.add(new Propertys("Tennessee Avenue", 18, 180, 5 ));
    LocationList.add(new Propertys("Ney York Avenue", 19, 200, 5 ));
    LocationList.add(new Locations("Free Parking", 20));
    LocationList.add(new Propertys("Kentuck Avenue",21, 220, 5 ));
    LocationList.add(new Locations("Chance", 22));
    LocationList.add(new Propertys("Inidanna Avenue", 23, 220, 5 ));
    LocationList.add(new Propertys("Illinois Avenue", 24, 240, 5 ));
    LocationList.add(new Locations("B & O Raiload", 25));
    LocationList.add(new Propertys("Atlantic Avenue", 26, 260, 5));
    LocationList.add(new Propertys("Ventnor Avenue", 27, 260, 5));
    LocationList.add(new Locations("Water Works", 28));
    LocationList.add(new Propertys("Marvin Gardens",29, 280, 5));
    LocationList.add(new Locations("Go To Jail", 30));
    LocationList.add(new Propertys("Pacific Avenue",31, 300, 5));
    LocationList.add(new Propertys("North Carolina Avenue",32, 300, 5));
    LocationList.add(new Locations("Community Chest", 33));
    LocationList.add(new Propertys("Pennsylvania Avenue",34, 320, 5));
    LocationList.add(new Locations("Short Line", 35));
    LocationList.add(new Locations("Chance", 36));
    LocationList.add(new Propertys("Park Place", 37, 350, 5 ));
    LocationList.add(new Locations("Luxery Tax", 38));
    LocationList.add(new Propertys("Boardwalk", 39, 400, 5));

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

  }

  public static ArrayList<Players> getPlayers(){
    return users;
  }
  public static List<Locations> getLocationsList(){
    return LocationList;
  }

}
