package deadwood;

import java.util.concurrent.ThreadLocalRandom;

public class TurnControl{

    private static String temp;
    private static Boolean cond, roll, rent;

    public static void turn(){
    for(Players element : SetUp.getPlayers()){
      Info_Panel.UserInput(element.getName() + "'s turn");
      roll = true;
      cond = true;
      cmdCheck(element);
    }
    turn();
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
            if(SetUp.getLocationsList().get(element.getLocation()) instanceof Propertys){
              if((((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() != element) && (((Propertys)SetUp.getLocationsList().get(element.getLocation())).getOwner() != null)){
                rent = false;
              }
            }

              break;

        case "buy" :
          if(SetUp.getLocationsList().get(element.getLocation()) instanceof Propertys){
            if(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() == null){
              element.deductBalance(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getValue()); //takes money away from players balance
              ((Propertys) SetUp.getLocationsList().get(element.getLocation())).setOwner(element); //sets owner of property
              element.propertyBought(SetUp.getLocationsList().get(element.getLocation()).getName()); // adds property name to propertyNames array in Players which will be use for querying owned property
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

        case "pay rent" :
          if(SetUp.getLocationsList().get(element.getLocation()) instanceof Propertys){ //checks that player is on a property
            if((((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() != element) && (((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() != null) ){
              element.deductBalance(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getRent());
              rent = true;
            }else{
              Info_Panel.UserInput("Can't pay rent here");
            }
          } else {
            Info_Panel.UserInput("Invalid command");
          }
            break;

        case "help" :

            Info_Panel.UserInput("type \"roll\" to move player");
            Info_Panel.UserInput("type \"buy\" to buy property");
            Info_Panel.UserInput("type \"balance\" to get bank balance");
            Info_Panel.UserInput("type \"done\" when you are finished your turn");
            Info_Panel.UserInput("type \"quit\" to end game");
            Info_Panel.UserInput("type \"pay rent\" to pay rent");
            break;

        case "done" :
        	if(roll==true){
        		Info_Panel.UserInput("You must finish rolling");
        	}else if(rent == false){
            Info_Panel.UserInput("You must pay outstanding rent");
          }
        	else cond = false;

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

    //allows manually moving and moving based on roll
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
