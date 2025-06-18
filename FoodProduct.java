package Market;

public class FoodProduct extends Product  {
	private String expirationDate ; 
	
	public FoodProduct(String id , String name , double price , int quantity , String category , String speacial) throws NegativeException, InvalidNameException{
		super(id, name, price, quantity , category);
		this.expirationDate = speacial;
	}
	
	public void displayInfo() {
		super.displayInfo();
		System.out.println("ExpirationDate : " + expirationDate);
	}
	public void displayInfo(String Currency) {
		super.displayInfo(Currency);
		System.out.println("ExpirationDate : " + expirationDate);	
	}
	public void displayInfo(boolean detaile) {
		super.displayInfo(detaile);
		System.out.println("ExpirationDate : " + expirationDate);
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate ; 
	}
	public String getExpirationDate() {
		return expirationDate ;
	}
	@Override 
	public void speicificIinfo() {
		System.out.println("speicificI info : Expiration Date " + expirationDate);
	}
	public String toString() {
		return this.getId()+ " " +this.getName()+ " " + this.getPrice() + " " + this.getQuantity() + " " + this.getCategory() + " " + this.getExpirationDate(); 
	}
	public void displayDiscount() {
		super.displayDiscount();
	}


}
