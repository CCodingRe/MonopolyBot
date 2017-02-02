package deadwood;

import java.util.concurrent.ThreadLocalRandom;


public class cmd_panel {

    public void move(player obj){ //moves player according to roll()
      int n = roll();
      obj.location += n;
    }
    public void move(player obj, int n){ //moves player manually n spaces
      obj.location += n;

    }
    public int roll(){ //returns dice roll
      int randomNum = ThreadLocalRandom.current().nextInt(2, 12 + 1);
      return randomNum;
    }

    public class player{ //creates player
      int location = 0;
      public int getLocation(){ //returns players location
          return location;
      }
    }
}
