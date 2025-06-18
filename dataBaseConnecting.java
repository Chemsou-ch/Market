package Market;
import java.sql.*;
import java.util.*;

public class dataBaseConnecting {
	
    private static final String DB_URL = "jdbc:mysql://localhost:3306/market";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
	
    public static void insertProduct(Product p) {
    	String sql = "INSERT INTO product (id, name, price, quantity, category, special) VALUES (?, ?, ?, ?, ?, ?)";


        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, p.getId());
            stm.setString(2, p.getName());
            stm.setDouble(3, p.getPrice());
            stm.setInt(4, p.getQuantity());
            stm.setString(5, p.getCategory());
            
            if (p instanceof FoodProduct) {
                stm.setString(6, ((FoodProduct) p).getExpirationDate());
            } else {
                stm.setString(6, ((ElectroniqueProduct) p).getWarrentyPeriod());
            }

            int row = stm.executeUpdate();
            if (row > 0) {
            	System.out.println("Product added. ");    
            }
        } catch (SQLException e) {
            System.err.println("Error inserting product: " + e.getMessage());
        }
    
    }
    public static void insertService(Service s) {
    	
    	String sql = "INSERT INTO service (id , discreption , basePrice) VALUES (? , ? , ?)" ;
    
    try(
    		Connection conn = DriverManager.getConnection(DB_URL , DB_USER , DB_PASSWORD );
    		PreparedStatement stm = conn.prepareStatement(sql)
    		){
    		stm.setInt(1, s.getId());
    		stm.setString(2, s.getDiscreption());
    		stm.setDouble(3, s.getBasePrice());
    		int row = stm.executeUpdate();
    		if(row > 0) {
    			System.out.println("Service added. ");    			
    		}
    }catch(SQLException e) {
    	System.out.println("Error: " + e.getMessage()); 		
   			}
    }
    
    
    public static Product displaySpeacialProduct(String id) throws SQLException, NegativeException, InvalidNameException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        
        String sql = "SELECT * from product WHERE id = ?"; 
        
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, id);
        
        ResultSet rs = stm.executeQuery(); 
        
        if(rs.next()) {
            if(rs.getString("category").equals("Food")) {
                return new FoodProduct(
                    rs.getString("id"), 
                    rs.getString("name"), 
                    rs.getDouble("price"), 
                    rs.getInt("quantity"), 
                    rs.getString("category"), 
                    rs.getString("special"));
                    
                
            }
            else {
                return new ElectroniqueProduct(
                        rs.getString("id"), 
                        rs.getString("name"), 
                        rs.getDouble("price"), 
                        rs.getInt("quantity"), 
                        rs.getString("category"), 
                        rs.getString("special"));
            }
        }
        return null;
    }
    
    public static List<Product> displayAllProduct() throws SQLException, NegativeException, InvalidNameException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); 
        String sql = "SELECT * FROM PRODUCT";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<Product> productList = new ArrayList<>();

        while (rs.next()) {
            String category = rs.getString("category");
            Product product;

            if (category.equals("Food")) {
                product = new FoodProduct(
                    rs.getString("id"), 
                    rs.getString("name"), 
                    rs.getDouble("price"), 
                    rs.getInt("quantity"), 
                    category, 
                    rs.getString("special")
                );
            } else {
                product = new ElectroniqueProduct(
                    rs.getString("id"), 
                    rs.getString("name"), 
                    rs.getDouble("price"), 
                    rs.getInt("quantity"), 
                    category, 
                    rs.getString("special") 
                );
            }

            productList.add(product);
        }

        rs.close();
        stm.close();
        conn.close();

        return productList;
    }
    public static List<Service> displayService() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String sql = "SELECT * FROM service";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<Service> services = new ArrayList<>();

        while (rs.next()) {
            Service service = new Service(
                rs.getInt("id"),               
                rs.getString("discreption"),   
                rs.getInt("basePrice")        
            );

            services.add(service);
        }

        rs.close();
        stm.close();
        conn.close();

        return services;
    }

    
    public String getCategoryById(String id) throws SQLException {
    	
    	Connection conn = DriverManager.getConnection(DB_URL , DB_USER , DB_PASSWORD);
    	
    	String category = null ; 
    	String sql = "SELECT category FROM product WHERE id = ?";
    	
    	
    	PreparedStatement stm = conn.prepareStatement(sql);
    	stm.setString(1, id);
    	
    	ResultSet rs = stm.executeQuery(); 
    	if(rs.next()) {
    		category = rs.getString("category");
    	}
		return category;
    }
    
    public void deleteProduct(String id) throws SQLException {
    	Connection conn = DriverManager.getConnection(DB_URL , DB_USER , DB_PASSWORD); 
    	String sql = "DELETE FROM product WHERE id = ?"; 
    	
    	PreparedStatement stm = conn.prepareStatement(sql);
    	stm.setString(1 , id);
    	int rs = stm.executeUpdate(); 
    	 
    	stm.close();
    	conn.close();
    	
    	
    }
    
    
}

    
   
    
    
    