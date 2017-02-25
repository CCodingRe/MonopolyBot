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
    while(cond){
      Info_Panel.UserInput("Enter Command: ");
      switch(Cmd_panel.getCommand()){
        case "move" :
            Info_Panel.UserInput("How Many Spaces? (Hit Enter for default roll)");
            String temp1 = Cmd_panel.getCommand();
            int num;

              if(temp1.equals("")){
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
              }
              Info_Panel.UserInput(SetUp.getLocationsList().get(element.getLocation()).getName());
              if(SetUp.getLocationsList().get(element.getLocation()) instanceof Propertys){
                Info_Panel.UserInput("Cost: " + Integer.toString(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getValue()));
                if(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() != null){
                  Info_Panel.UserInput(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwnerName());
                } else {
                  Info_Panel.UserInput("No Owner");
                }
              }
              break;

        case "buy" :
            if(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getOwner() == null){
              element.buy(((Propertys) SetUp.getLocationsList().get(element.getLocation())).getValue(); //takes money away from players balance
              ((Propertys) SetUp.getLocationsList().get(element.getLocation())).setOwner(element); //sets owner of property
            } else {
              Info_Panel.UserInput("Property already bought");
            }
            break;
        case "balance" :
            Info_Panel.UserInput(Integer.toString(element.getBalance()));

        case "help" :
            Info_Panel.UserInput("type move to move player");
            Info_Panel.UserInput("type buy to buy property");
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
