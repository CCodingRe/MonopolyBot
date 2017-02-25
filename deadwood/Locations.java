package deadwood;

public class Locations{

  private String name;
  private int location;

  public Locations(String n, int loc){
    setName(n);
    setLocation(loc);
  }

   public void setName(String n){
    name = n;
  }

   public void setLocation(int loc){
    location = loc;
  }
  public String getName(){
    return name;
  }
  public int getLocation(){
    return location;
  }


}
