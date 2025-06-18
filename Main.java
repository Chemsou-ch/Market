package Market;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;





public class Main extends dataBaseConnecting{

    Main() { 
    	
    	/*Main frame*/
        JFrame frame = new JFrame("Market manager");
        frame.setSize(1550 , 820);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTable productTable = new JTable(); 
        JTable serviceTable = new JTable(); 
        
        
        /*add new Product*/
        JPanel panelTwo = new JPanel();
        JButton addProd = new JButton("ADD new product");
        addProd.setBackground(Color.DARK_GRAY);
        addProd.setForeground(Color.white);
        addProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	JDialog dialog = new JDialog();       	
            	dialog.setTitle("Enter product information");
            	dialog.setSize(600 , 600);
            	
            	JPanel InfoPanel = new JPanel(new GridLayout(6, 2, 5, 5)); 
            	
            	JPanel idTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            	JTextField idField = new JTextField(); 
            	idField.setPreferredSize(new Dimension(250,30));
            	JLabel idLabel = new JLabel("ID : ",FlowLayout.RIGHT);
            	idTextPanel.add(idLabel);
            	idTextPanel.add(idField);
            	
            	
            	
            	
            	JPanel nameTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)) ;
            	JTextField nameField = new JTextField(); 
            	nameField.setPreferredSize(new Dimension(250,30));;
            	JLabel nameLabel = new JLabel("Name : ", FlowLayout.RIGHT);
            	nameLabel.setLabelFor(nameTextPanel);
            	nameTextPanel.add(nameLabel);
            	nameTextPanel.add(nameField);
            	
            	
            	JPanel priceTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            	JTextField priceField = new JTextField(); 
            	priceField.setPreferredSize(new Dimension(250,30));
            	JLabel priceLabel = new JLabel("Price : ", FlowLayout.RIGHT);
            	priceTextPanel.add(priceLabel);
            	priceTextPanel.add(priceField);
            

            	
            	JPanel quantityTextField = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
            	JTextField quanityField = new JTextField(); 
            	quanityField.setPreferredSize(new Dimension(250,30));
            	JLabel quantityLabel = new JLabel("Quantity :", FlowLayout.RIGHT);
            	quantityTextField.add(quantityLabel);
            	quantityTextField.add(quanityField); 
            	

            	
            	JPanel categoryTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            	JComboBox<String> categoryStock = new JComboBox<>(new String[]{"Food", "Electronique"});     
            	JLabel categoryLabel = new JLabel("Category :", FlowLayout.RIGHT);

            	
            	JPanel speacialTextField = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
            	JTextField spacialField = new JTextField(); 
            	spacialField.setPreferredSize(new Dimension(250,30));
            	JLabel speacialLabel = new JLabel("Speacial Info :", FlowLayout.RIGHT); 
            	speacialTextField.add(speacialLabel);
            	speacialTextField.add(spacialField);	

            	
            	
            	InfoPanel.add(idTextPanel);        
            	InfoPanel.add(nameTextPanel);           	
            	InfoPanel.add(priceTextPanel);            
            	InfoPanel.add(quantityTextField);          	
            	InfoPanel.add(categoryStock);            	
            	InfoPanel.add(speacialTextField);  
            	
            	
            	JPanel buttonPanel = new JPanel(); 
            	JButton saveButton = new JButton("Save");          	
                saveButton.setBackground(Color.DARK_GRAY);
                saveButton.setForeground(Color.white);
            	buttonPanel.add(saveButton);           	
            	saveButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String id = idField.getText();
						String name = nameField.getText(); 
						double price = Double.parseDouble( priceField.getText());
						int quantity = Integer.parseInt(quanityField.getText()) ;
						String category = (String) categoryStock.getSelectedItem() ;
						String speacial = spacialField.getText();
						
						Product p = null ; 
						
	                    if (category.equals("food")) {
	                        try {
								p = new FoodProduct(id, name, price, quantity , category , speacial);
							} catch (NegativeException e1) {							
								e1.printStackTrace();
							} catch (InvalidNameException e1) {							
								e1.printStackTrace();
							}
	                    } else {                    	
	                        try {
								p = new ElectroniqueProduct(id, name, price, quantity ,  category , speacial);
							} catch (NegativeException e1) {								
								e1.printStackTrace();
							} catch (InvalidNameException e1) {								
								e1.printStackTrace();
							}
	                    }
                  
						insertProduct(p);	
						
						
	                    JOptionPane msgAddProd = new JOptionPane(); 
	                    msgAddProd.showMessageDialog(InfoPanel , "Product Add " , "" , JOptionPane.INFORMATION_MESSAGE ); 
					}
				});
            	
                dialog.setLayout(new BorderLayout());
                dialog.add(InfoPanel, BorderLayout.CENTER);
                dialog.add(buttonPanel, BorderLayout.SOUTH);
                dialog.setVisible(true);       
              
            }
        });
        
        /*add new service*/
        JButton addSer= new JButton("Add service ");
        addSer.setBackground(Color.DARK_GRAY);
        addSer.setForeground(Color.white);
        addSer.addActionListener(new ActionListener() {
        
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialogTwo = new JDialog();	 
				dialogTwo.setTitle("add Service information") ;
				dialogTwo.setSize(500 , 500);
				
				JPanel serInfoPanel = new JPanel(new GridLayout(5,2,5,5));
				
				JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JPanel idFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
				JLabel idLabel = new JLabel("Id" , FlowLayout.RIGHT); 
				JTextField idField = new JTextField(FlowLayout.LEFT);
				idField.setPreferredSize(new Dimension(80,30));
				idPanel.add(idLabel);
				idFieldPanel.add(idField); 
				
				
				JPanel descPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
				JPanel descFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
				JLabel descLabel = new JLabel("Description : ", FlowLayout.RIGHT);
				JTextField descField = new JTextField(FlowLayout.LEFT); 
				descField.setPreferredSize(new Dimension(80,30));
				descPanel.add(descLabel);
				descFieldPanel.add(descField); 
				
				
	
				JPanel bpPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JPanel bpFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel bpLabel = new JLabel("Base Price " , FlowLayout.RIGHT); 
				JTextField bpField = new JTextField(FlowLayout.LEFT);
				bpField.setPreferredSize(new Dimension(80,30));
				bpPanel.add(bpLabel);
				bpFieldPanel.add(bpField); 
				
				
				JButton saveButton = new JButton("Save");
		        saveButton.setBackground(Color.DARK_GRAY);
		        saveButton.setForeground(Color.white);
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(saveButton);
				
				serInfoPanel.add(idPanel);
				serInfoPanel.add(idFieldPanel);
				serInfoPanel.add(descPanel);
				serInfoPanel.add(descFieldPanel); 
				serInfoPanel.add(bpPanel); 
				serInfoPanel.add(bpFieldPanel);
				
				
				
			
				saveButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(idField.getText());
						
						String discreption = descField.getText();
						double basePrice = Double.parseDouble(bpField.getText());
						
						Service s = new Service(id, discreption, basePrice) ; 
	
	
						insertService(s);

	                    JOptionPane msgAddProd = new JOptionPane(); 
	                    msgAddProd.showMessageDialog(serInfoPanel , "Product Add " , "" , JOptionPane.INFORMATION_MESSAGE ); 
	                    
					}
					
				});
				
				

				dialogTwo.setLayout(new BorderLayout());
				dialogTwo.add(serInfoPanel,BorderLayout.CENTER);
				dialogTwo.add(buttonPanel, BorderLayout.SOUTH);
				dialogTwo.setVisible(true);
				
				
				
				
			}
        	
        });
        
        /*modify option*/
        
        JPanel modifyPanel= new JPanel() ; 
        JButton modifyButton = new JButton("Modify") ; 
        modifyButton.setBackground(Color.DARK_GRAY);
        modifyButton.setForeground(Color.white);
        modifyPanel.add(modifyButton);
        
        modifyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/*delete product*/
				JDialog infoDialog = new JDialog(); 
				infoDialog.setTitle("Delete Product");
				infoDialog.setSize(500,500);
				infoDialog.setLayout(new BorderLayout());
				
				JPanel infoPanel = new JPanel(new GridLayout(5,2,5,5));
				
				
				
				JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JPanel idFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel idLabel = new JLabel("ID : ",FlowLayout.RIGHT);
				JTextField idField = new JTextField(FlowLayout.LEFT);
				idField.setPreferredSize(new Dimension(80,30));
				idLabel.setLabelFor(idField);
				idPanel.add(idLabel);
				idFieldPanel.add(idField); 
				
				
				JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JPanel nameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel nameLabel = new JLabel("Name : ",FlowLayout.RIGHT);
				JTextField nameField = new JTextField(FlowLayout.LEFT);
				nameField.setPreferredSize(new Dimension(80,30));
				nameLabel.setLabelFor(nameField);
				namePanel.add(nameLabel);
				nameFieldPanel.add(nameField); 
				
				JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JPanel priceFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel priceLabel = new JLabel("Price: ",FlowLayout.RIGHT);
				JTextField priceField = new JTextField(FlowLayout.LEFT);
				priceField.setPreferredSize(new Dimension(80,30));
				priceLabel.setLabelFor(priceField);
				pricePanel.add(priceLabel);
				priceFieldPanel.add(priceField);
				
				JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JPanel quantityFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JLabel quantityLabel = new JLabel("Quantity : ",FlowLayout.RIGHT);
				JTextField quantityField = new JTextField(FlowLayout.LEFT);
				quantityField.setPreferredSize(new Dimension(80,30));
				quantityLabel.setLabelFor(quantityField);
				quantityPanel.add(quantityLabel);
				quantityFieldPanel.add(quantityField);
				
				JPanel categoryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JLabel categoryLabel = new JLabel("Category : ",FlowLayout.RIGHT);
				JTextField categoryField = new JTextField(FlowLayout.LEFT);
				categoryField.setPreferredSize(new Dimension(80,30));
				categoryLabel.setLabelFor(categoryField);
				categoryPanel.add(categoryLabel);
				categoryFieldPanel.add(categoryField);
				
				
				JPanel speacialFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JPanel speacialPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				JLabel speacialLabel = new JLabel("Speacial info : ",FlowLayout.RIGHT);
				JTextField speacialField = new JTextField(FlowLayout.LEFT);
				speacialField.setPreferredSize(new Dimension(80,30));
				speacialLabel.setLabelFor(speacialField);
				speacialPanel.add(speacialLabel);
				speacialFieldPanel.add(speacialField);
				
				
				infoPanel.add(idPanel); 
				infoPanel.add(idFieldPanel);
				
				infoPanel.add(namePanel); 
				infoPanel.add(nameFieldPanel);
				
				infoPanel.add(pricePanel); 
				infoPanel.add(priceFieldPanel); 
				
				
				infoPanel.add(quantityPanel); 
				infoPanel.add(quantityFieldPanel); 
				
				infoPanel.add(speacialPanel);
				infoPanel.add(speacialFieldPanel); 
				
				
				JPanel checkPanel = new JPanel();
				JButton checkButton = new JButton("Check Product"); 
		        checkButton.setBackground(Color.DARK_GRAY);
		        checkButton.setForeground(Color.white);
				checkPanel.add(checkButton);
				
				checkButton.addActionListener(new ActionListener () {
					
				
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        String id = JOptionPane.showInputDialog(null, "Enter the product id:"); 
				        if (id == null ) return;  
				        
				        try {
				            String category = getCategoryById(id);
				            if (category == null) {
				                JOptionPane.showMessageDialog(null, "Product not found", "Error", JOptionPane.ERROR_MESSAGE);
				                return;
				            }
				            
				           
				            if (category.equalsIgnoreCase("food")) {
				                FoodProduct p = (FoodProduct) displaySpeacialProduct(id);
				                if (p != null) {
				                    idField.setText(p.getId());
				                    nameField.setText(p.getName()); 
				                    priceField.setText(String.valueOf(p.getPrice()));
				                    quantityField.setText(String.valueOf(p.getQuantity())); 
				                    categoryField.setText(category);                     
				                    speacialField.setText(p.getExpirationDate());
				                    
				                }
				            } else if(category.equalsIgnoreCase("electronique")) {
				            	ElectroniqueProduct p = (ElectroniqueProduct) displaySpeacialProduct(id);
				                if (p != null) {
				                    idField.setText(p.getId());
				                    nameField.setText(p.getName()); 
				                    priceField.setText(String.valueOf(p.getPrice()));
				                    quantityField.setText(String.valueOf(p.getQuantity())); 
				                    categoryField.setText(category);  
				                    speacialField.setText(p.getWarrentyPeriod());
				                }
				                
				            }
				        } catch (SQLException | NegativeException | InvalidNameException e1) {
				            JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				        }
				    }
				});
				
				JPanel deletePanel = new JPanel(); 
				JButton deleteButton= new JButton("Delete");
				deletePanel.add(deleteButton); 
		        deleteButton.setBackground(Color.DARK_GRAY);
		        deleteButton.setForeground(Color.white);
				deleteButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String id = JOptionPane.showInputDialog(null , "Enter the product id ") ;
						
						try {
							deleteProduct(id);
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						
					}
					
				});
				
				JPanel buttonPanel = new JPanel(new GridLayout(1,2,5,5));
				buttonPanel.add(checkButton); 
				buttonPanel.add(deleteButton);
				infoDialog.add(buttonPanel,BorderLayout.SOUTH);
				infoDialog.add(infoPanel,BorderLayout.NORTH);
				infoDialog.setVisible(true);
		
			}
		});
        	JPanel displayAllProductPanel = new JPanel(); 
        	JButton displayAllProductButton = new JButton("Display Products"); 
            displayAllProductButton.setBackground(Color.DARK_GRAY);
            displayAllProductButton.setForeground(Color.white);
        	displayAllProductPanel.add(displayAllProductButton); 
        	displayAllProductButton.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        try {
        	            List<Product> products = displayAllProduct();

        	          
        	            String[] columnNames = {"ID", "Name", "Price", "Quantity", "Category", "Special Info"};
        	            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        	            // Add product rows
        	            for (Product p : products) {
        	                String id = p.getId();
        	                String name = p.getName();
        	                double price = p.getPrice();
        	                int quantity = p.getQuantity();
        	                String category = p.getCategory();
        	                String specialInfo;

        	                if (p instanceof FoodProduct) {
        	                    specialInfo = ((FoodProduct) p).getExpirationDate();
        	                } else if (p instanceof ElectroniqueProduct) {
        	                    specialInfo = ((ElectroniqueProduct) p).getWarrentyPeriod();
        	                } else {
        	                    specialInfo = "";
        	                }

        	                Object[] rowData = {id, name, price, quantity, category, specialInfo};
        	                
        	                tableModel.addRow(columnNames);
        	                tableModel.addRow(rowData);
        	            }

        	         
        	            productTable.setModel(tableModel);
        	            productTable.setBackground(Color.lightGray);
        	           
        	            productTable.getTableHeader().setBackground(Color.DARK_GRAY);
        	            productTable.getTableHeader().setForeground(Color.WHITE);
        	        } catch (Exception ex) {
        	            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        	        }
        	    }
        	});

        	
        	JPanel displayService = new JPanel(); 
        	JButton displayServiceButton  =new  JButton ("display service");
            displayServiceButton.setBackground(Color.DARK_GRAY);
            displayServiceButton.setForeground(Color.white);
        	displayService.add(displayServiceButton);
        	displayServiceButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					List<Service> serviceList;
					try {
						serviceList = displayService();
						String[] columnNames = {
								"ID" , "Description" , "Base Prie"
						};
						DefaultTableModel tableModel = new DefaultTableModel(columnNames , 0);
						JTable productTable = new JTable(tableModel);
						for (Service s : serviceList) {
							int id = s.getId(); 
							String description = s.getDiscreption(); 
							double basePrice = s.getBasePrice();
						
							Object[] rowData = { 
									id , description , basePrice
							};
							tableModel.addRow(rowData); 
						}
						serviceTable.setModel(tableModel);
						serviceTable.setBackground(Color.lightGray);
						serviceTable.getTableHeader().setBackground(Color.DARK_GRAY);
						serviceTable.getTableHeader().setForeground(Color.WHITE);
						
					} catch (SQLException e1) {
						 JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
					} 


				}
        		
        	});
        JScrollPane scrollPaneProduct = new JScrollPane(productTable);
        JScrollPane scrollPaneService = new JScrollPane(serviceTable); 
        JPanel tableAreaPanel = new JPanel() ;
        tableAreaPanel.setLayout(new GridBagLayout());
        
        tableAreaPanel.add(scrollPaneProduct);
        tableAreaPanel.add(scrollPaneService);
   
        panelTwo.add(addSer);
        panelTwo.add(displayService);
        panelTwo.add(addProd);
        panelTwo.add(modifyPanel);
        panelTwo.add(displayAllProductButton);
        

        
        frame.add(tableAreaPanel , BorderLayout.NORTH);    
        frame.add(panelTwo, BorderLayout.SOUTH);
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {
        new Main();
    }
}

