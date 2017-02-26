package deadwood;

public class TurnControl{

    private static String temp;
    private static Boolean cond, roll, rent;

    public static void turn(){
    for(Players element : SetUp.getPlayers()){
      Info_Panel.UserInput(element.getName() + "'s turn");
      roll = true;
      cond = true;
      rent = false;
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
              movePlayer(element);
              roll = false;
            } else {
              Info_Panel.UserInput("Player has already rolled");
            }

              break;

        case "buy" :
          if(SetUp.getLocationsList().get(element.getLocation()) instanceof Propertys){
            if(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() == null){
              element.deductBalance(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getValue()); //takes money away from players balance
              ((Propertys) SetUp.getLocationsList().get(element.getLocation())).setOwner(element); //sets owner of property
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

        case "help" :
            Info_Panel.UserInput("type \"roll\" to move player");
            Info_Panel.UserInput("type \"buy\" to buy property");
            Info_Panel.UserInput("type \"balance\" to get bank balance");
            Info_Panel.UserInput("type \"done\" when you are finished your turn");
            Info_Panel.UserInput("type \"quit\" to end game");
            Info_Panel.UserInput("type \"pay rent\" to pay rent");
            break;

        case "done" :
            if(!roll){
              cond = false;
            } else {
              Info_Panel.UserInput("Player hasn't finished rolling");
            }
            break;

        default :
            Info_Panel.UserInput("Invalid commad");
        }
      }
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
