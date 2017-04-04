package deadwood;

public class Services extends Locations{

	String inputName;
	int value;
	int mortgageValue;
  int[] rent = new int[6];
	Players owner = null;
  int units = 0;
	String group;
	Boolean isMortgaged;

	public Services(String n, String iN, int loc, int v, int mV, int r, int r1, int r2, int r3, int r4, int r5, String g){
    super(n, loc);
    setName(n);
		setInputName(iN); // input name will be used to compare with user inputs when mortgaging, etc.
		setLocation(loc);
		value = v;
		mortgageValue = mV;
    rent[0] = r;
		rent[1] = r1;
		rent[2] = r2;
		rent[3] = r3;
		rent[4] = r4;
		rent[5] = r5;
		group = g;
		isMortgaged = false;
	}

	public void setInputName(String name) {
		inputName = name;
	}
	public String getInputName() {
		return inputName;
	}
	public void setOwner(Players obj){
		owner = obj;
	}
	public int getValue(){
		return value;
	}
	public int getMortgageValue() {
		return mortgageValue;
	}
	public int getRedeemValue() {
		return (int) (mortgageValue + mortgageValue*.1);
	}
	public int getRent(){

		int rentToPay = 0;
		if(group.equals("utilities") && TurnControl.groupCheck(owner, "utilities")){
			rentToPay = 10 * TurnControl.getRoll();
		} else if(group.equals("utilities")){
			rentToPay = 4 * TurnControl.getRoll();
		}
		else if(group.equals("railroad")){
			int payme = owner.getNumStationsOwned();
			rentToPay = rent[payme-1];
		}
		return rentToPay;

	}
	public String getOwnerName(){
		return owner.getName();
	}
	public Players getOwner(){
		return owner;
	}
	public void mortgage() {
		isMortgaged = true;
	}
  public int getServices(){

		return units;
	}
	public void changeUnits(int x){
		units += x;
	}
	public void redeem() {
		isMortgaged = false;
	}
	public Boolean isMortgaged() {
		return isMortgaged;
	}

	public String getGroup(){
		return group;
	}

	}
