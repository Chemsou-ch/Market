package Market;
import java.util.*;
public abstract class Product implements Discount{
	private String id ; 
	private String name ; 
	private double price ; 
	private int quantity ; 
	private String category ; 	
	private static int  totalProducts = 0;
	// constructe part //
	public Product(String id , String name , double price , int quantity , String category ) throws NegativeException, InvalidNameException {
		this.id = id ; 
		setName(name) ; 
		setPrice(price); 
		setQuantity(quantity); 
		this.category = category ; 	
		totalProducts++ ; 	
	}
	//display inforamation with defference methode ( polymorphisme surcharge ) //
	public void displayInfo() {
		System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Category: " + category);
		}
	
	public void displayInfo(String currency) {
		double newPrice ; 
		if(currency == "euro") {
			newPrice = price / 240 ; 
			System.out.println("ID: " + id);
	        System.out.println("Name: " + name);
	        System.out.println("Price: " + newPrice + "euro");
	        System.out.println("Quantity: " + quantity);
	        System.out.println("Category: " + category);
		}
		else if(currency == "dollar") {
			newPrice = price / 180 ; 
			System.out.println("ID: " + id);
	        System.out.println("Name: " + name);
	        System.out.println("Price: " + newPrice + "dollar");
	        System.out.println("Quantity: " + quantity);
	        System.out.println("Category: " + category);
		}
		else if (currency == "dinar") {
			System.out.println("ID: " + id);
	        System.out.println("Name: " + name);
	        System.out.println("Price: " + price + "dinar");
	        System.out.println("Quantity: " + quantity);
	        System.out.println("Category: " + category);
		}
		
	}
	
	public void displayInfo(boolean detaile) {
		if(detaile == true) {
			System.out.println("ID: " + id);
	        System.out.println("Name: " + name);
	        System.out.println("Price: " + price + "dinar");
	        System.out.println("Quantity: " + quantity);
	        System.out.println("Category: " + category);
		}
		else {
	        System.out.println("Name: " + name);
	        System.out.println("Price: " + price + "dinar");
		}
	}
	// xxxxxxxxx//
	public boolean isAvailable() {
		if (quantity > 0 ) {
			return true ; 
		}
		else {
			return false ; 
		}
	}
	
	//Discount 
	
	@Override
	public void displayDiscount() {
		double discount = 0.10;
		double newPrice ; 		
		newPrice =getPrice() - getPrice() * discount ; 
		System.out.println("Price before discount is : " + getPrice());
		System.out.println("Price after discount is : " +newPrice);		
	}

	
	
	
	//abstruct methode //
	
	public abstract void speicificIinfo() ;
	
	
	
	
	//getter and setter part //
	public static int getStatique() {
		return totalProducts ; 
	}
	public void setId(String id) {
		this.id = id; 
	}
	public String getId() {
        return id;
    }
	public void setName(String name) throws InvalidNameException {
		if(name != null && !name.trim().isEmpty()) {
			this.name = name ; 
		}
		else {
			throw new InvalidNameException("The name cant be Empty");
		}
	}
	public String getName() {	
		return name;
	}
	   public void setPrice(double price) throws NegativeException{
	        if (price < 0) {
	            throw new NegativeException("it can't be negative");
	        }
	        this.price = price;
	    }

	public double getPrice() {
		return price ; 
	}
	public void setQuantity(int quantity) throws NegativeException{
		if(quantity < 0) {
			throw new NegativeException(" it can't be negative") ;
		}
		this.quantity = quantity ; 
	
	}
	public int getQuantity () {
		return quantity ; 
	}
	public void setCategory(String category) {
		this.category= category;
	}
	public String getCategory() {
		return category;
	}
}