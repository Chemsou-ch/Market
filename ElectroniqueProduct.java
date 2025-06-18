package Market;

public class ElectroniqueProduct extends Product {
	private String warrentyPeriod;
	public ElectroniqueProduct(String id, String name, double price, int quantity, String category, String speacial) throws NegativeException, InvalidNameException{
		super(id , name , price, quantity , category);
		this.warrentyPeriod = speacial;
	}

	public void displayInfo() {
		super.displayInfo();
		System.out.println("WarrentyPeriod : " + warrentyPeriod);
	}
	public void displayInfo(String Currency) {
		super.displayInfo(Currency);
		System.out.println("WarrentyPeriod : " + warrentyPeriod);
	}
	public void displayInfo(boolean detaile) {
		super.displayInfo(detaile);
		System.out.println("WarrentyPeriod : " + warrentyPeriod);
	}
	public void setWarrentyPeriod(String warrentyPeriod) {
		this.warrentyPeriod = warrentyPeriod ;
	}
	public String getWarrentyPeriod() {
		return warrentyPeriod;
		
	}
	@Override
	public void speicificIinfo() {
		System.out.println("speicificI info : warrenty Period " + warrentyPeriod);
	}
	public void displayDiscount() {
		super.displayDiscount();
	}
	public String toString() {
		return this.getId()+ " " +this.getName()+ " " + this.getPrice() + " " + this.getQuantity() + " " + this.getCategory() + " " + this.getWarrentyPeriod(); 
	}
}
