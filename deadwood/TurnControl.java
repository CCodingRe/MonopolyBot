package deadwood;

public class TurnControl{

    private static String temp;
    private static Boolean cond;

    public static void turn(){
    for(Players element : SetUp.getPlayers()){
      Info_Panel.UserInput(element.getName() + "'s turn");
      //temp = Cmd_panel.getCommand();
      cond = true;
      cmdCheck(element);
    }
    turn();
  }

  private static void cmdCheck(Players element){
	int remainingRolls = 1; 
    while(cond){
      Info_Panel.UserInput("Enter Command: ");
      switch(Cmd_panel.getCommand()){
        case "move" :
        	if(remainingRolls > 0) {
        		element.move();
        		remainingRolls--;
        	}
        	else Info_Panel.UserInput("No rolls remaining");
            
        break;

        case "info" :
            Info_Panel.UserInput(SetUp.getLocationsList().get(element.getLocation()).getName());
            Info_Panel.UserInput(Integer.toString(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getValue()));
            if(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() != null){
              Info_Panel.UserInput(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwnerName());
            } else {
              Info_Panel.UserInput("No Owner");
            }
            break;

        /*case "buy" :
            if(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() == null){
              element.buy(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getValue(); //takes money away from players balance
              ((Propertys) SetUp.getLocationsList().get(element.getLocation())).setOwner(element); //sets owner of property
            } else {
              Info_Panel.UserInput("Property already bought");
            }
            break;*/
        case "balance" :
            Info_Panel.UserInput(Integer.toString(element.getBalance()));

        case "help" :
            Info_Panel.UserInput("type move to move player");
            Info_Panel.UserInput("type buy to buy property");
            Info_Panel.UserInput("type info for details on current property");
            Info_Panel.UserInput("type balance to get bank balance");
            Info_Panel.UserInput("type done when you are finished your turn");
            Info_Panel.UserInput("type quit to end game");

        case "done" :
            cond = false;
            break;


        default :
            Info_Panel.UserInput("Invalid commad");
        }
      }
  }
}
