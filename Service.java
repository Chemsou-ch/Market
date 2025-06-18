package Market;

public class Service implements Discount{
	private int id ;
	private String description ; 
	private double basePrice; 
	
	public Service (int id ,String description ,double basePrice) {
		this.id = id ;
		this.description= description;
		this.basePrice = basePrice ; 
	}
	public void setId(int id ) {
		this.id = id ; 
	}
	public int getId() {
		return id ; 
		
	}
	public void setDiscription(String description) {
		this.description= description;
	}
	public String getDiscreption() {
		return description;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getBasePrice() {
		return basePrice; 
	}
	@Override
	public void displayDiscount() {
		double discount = 0.05 ;
		double newPrice ; 		
		newPrice = basePrice - basePrice * discount ;		
		System.out.println("price befor discount : " + basePrice);
		
		System.out.println("price after discount : " + newPrice);
	}
	public void displayInfo() {
		System.out.println("Servise descreption : " + description);
		System.out.println("base Price : " + basePrice + "Da");
		
	}
	
	public String toString() {
		return this.getId()+ " " + this.getDiscreption() + " " + this.getBasePrice() ;
	}
	
}
