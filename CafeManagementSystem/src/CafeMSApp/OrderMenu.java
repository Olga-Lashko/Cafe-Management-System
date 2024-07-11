/*
 * Olga Lashko
 * Programming II
 * CITC-1311-C01
 * 11-13-23
 * Cafe Management System Semester Progect
 */
package CafeMSApp;


import static CafeMSApp.CafeMSApp.LAST_ORDER;
import static CafeMSApp.CafeMSApp.showWarningMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class OrderMenu extends javax.swing.JFrame {
    

    public static String formattedTotal;
    public static String formattedTax;
    public static String formattedGrandTotal;
    
    private ArrayList<String> currentItems; //list name food or drink depends on radio button choice
    private ButtonGroup orderTypeButtonGroup;
    private ArrayList<Item> order = new ArrayList<>();
 
    public OrderMenu() {
        
       // order = new ArrayList<>();
        initComponents();

        // Initialize the combo box based on the default selection
        if (orderDrinkRBtn.isSelected()) {
            currentItems = CafeMSApp.showNameDrink();
        } else if(orderFoodRBtn.isSelected()){
            currentItems = CafeMSApp.showNameFood();
        } else {
            currentItems = new ArrayList<>(); 
        }

        // Set the combo box model
        updateComboBoxModel();

        // Initialize the button group
        orderTypeButtonGroup = new ButtonGroup();
        orderTypeButtonGroup.add(orderDrinkRBtn);
        orderTypeButtonGroup.add(orderFoodRBtn);

        // Add ItemListener to radio buttons
        orderDrinkRBtn.addItemListener((ItemEvent e) -> {
            if (orderDrinkRBtn.isSelected()) {
                currentItems = CafeMSApp.showNameDrink();
            } else {
                currentItems = CafeMSApp.showNameFood();
            }
            updateComboBoxModel();
        });

        orderFoodRBtn.addItemListener((ItemEvent e) -> {
            if (orderFoodRBtn.isSelected()) {
                currentItems = CafeMSApp.showNameFood();
            } else {
                currentItems = CafeMSApp.showNameDrink();
            }
            updateComboBoxModel();
        });

        // Add listener to combo box for item selection
        itemNameCmbBx.addActionListener((ActionEvent e) -> {
            updatePrice();
            updateQuantityAndPrice();
            itemNameCmbBx.hidePopup();
        });

        // Add listener to quantity field for text changes
        quantTxtFld.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePrice();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePrice();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePrice();
            }
        });
    }

    private void updateQuantityAndPrice() {
        // Get the selected item from combo box
        String selectedItem = (String) itemNameCmbBx.getSelectedItem();

        // Find the corresponding item in the menu lists
        Item selectedMenuItem = findItem(selectedItem);

        // Update the quantity field with the default value of 1
        quantTxtFld.setText("1");

        // If the selected item is found, update the price field
        if (selectedMenuItem != null) {
            priceTxtFld.setText(String.valueOf(selectedMenuItem.getPrice()));
        } else {
            // Handle the case where the selected item is not found (you can display an error message)
            JOptionPane.showMessageDialog(this, "Error: Selected item not found!");
        }
    }

    private void updatePrice() {
        
        // Get the entered quantity from the quantity field
        String quantityText = quantTxtFld.getText();

        // Check if the quantityText is not empty
        if (!quantityText.isEmpty()) {
            // Parse the quantity as an integer (you may want to add error handling)
            int quantity = Integer.parseInt(quantityText);

            // Get the selected item from combo box
            String selectedItem = (String) itemNameCmbBx.getSelectedItem();

            // Find the corresponding item in the menu lists
            Item selectedMenuItem = findItem(selectedItem);

            // If the selected item is found, update the price field by multiplying the quantity
            if (selectedMenuItem != null) {
                double totalPrice = quantity * selectedMenuItem.getPrice();
                priceTxtFld.setText(String.valueOf(totalPrice));
            }
        }
    }

    private Item findItem(String itemName) {
        // Search for the item in both drink and food menu lists
        for (Item item : CafeMSApp.drinkMenuList) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }

        for (Item item : CafeMSApp.foodMenuList) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null; // Return null if the item is not found
    }

    private void updateComboBoxModel() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(currentItems.toArray(new String[0]));
        itemNameCmbBx.setModel(comboBoxModel);
    }
    
   
    private void addItemToOrder() {
        String selectedItem = (String) itemNameCmbBx.getSelectedItem();
        int quantity = Integer.parseInt(quantTxtFld.getText());
        Item selectedMenuItem = findItem(selectedItem);

        if (selectedMenuItem != null) {
            double price = selectedMenuItem.getPrice();
            //double totalPrice = quantity * price;

            // Create a new Item and add it to the order list
            Item orderedItem = new Item(selectedItem, quantity, price);
            order.add(orderedItem);
            
            //check what order is:
            for(Item i:order){
                System.out.println(i.getName() + "\n");
            }
        }
    }
    
    
    private void updateOrderTable() {
        DefaultTableModel model = (DefaultTableModel) orderTbl.getModel();
        model.setRowCount(0); // Clear existing rows

        // Add items from the order list to the table
        for (Item orderedItem : order) {
            model.addRow(new Object[]{orderedItem.getName(), orderedItem.getQuantity(), orderedItem.getPriceFormatted(), orderedItem.getTotalFormatted()});
        }
    }
    
    
    private void updateTotal() {
       double total = 0;

    // Iterate through the items in the order and sum up the total price
    for (Item orderedItem : order) {
        total += orderedItem.getTotal();
    }

    // Calculate tax (10% of the sub-total)
    double tax = total * 0.1;

    // Calculate total including tax
    double grandTotal = total + tax;

    // Format the values with two decimals and add "$" at the beginning
    formattedTotal = String.format("$%.2f", total);
    formattedTax = String.format("$%.2f", tax);
    formattedGrandTotal = String.format("$%.2f", grandTotal);

    // Display the formatted values in the respective text fields
    subTxtFld.setText(formattedTotal);
    taxTxtFld.setText(formattedTax);
    totalTxtFld.setText(formattedGrandTotal);
    }
    

    private void clearLastOrder() {
        order.clear();
        updateOrderTable();
    }
    
    private Item findItemInOrder(String itemName, int quantity, double price) {
    for (Item orderedItem : order) {
        if (orderedItem.getName().equals(itemName) && orderedItem.getQuantity() == quantity && orderedItem.getPrice() == price) {
            return orderedItem;
        }
    }
    return null;
}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        picLabel = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        newOrderBtn = new javax.swing.JButton();
        MakeOrderLabel = new javax.swing.JLabel();
        receiptBtn = new javax.swing.JButton();
        cafeMSLabel = new javax.swing.JLabel();
        exitBtn = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JSeparator();
        orderItemLabel = new javax.swing.JLabel();
        orderDrinkRBtn = new javax.swing.JRadioButton();
        orderFoodRBtn = new javax.swing.JRadioButton();
        itemLabel = new javax.swing.JLabel();
        quantLabel = new javax.swing.JLabel();
        addOrderBtn = new javax.swing.JButton();
        itemNameCmbBx = new javax.swing.JComboBox<>();
        priceTxtFld = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        delOrderBtn = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();
        totalTxtFld = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderTbl = new javax.swing.JTable();
        mainMenuBtn = new javax.swing.JButton();
        subLbl = new javax.swing.JLabel();
        subTxtFld = new javax.swing.JTextField();
        taxLbl = new javax.swing.JLabel();
        taxTxtFld = new javax.swing.JTextField();
        quantTxtFld = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(625, 425));

        picLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coffee.png"))); // NOI18N
        picLabel.setText("jLabel4");

        newOrderBtn.setBackground(new java.awt.Color(225, 216, 202));
        newOrderBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        newOrderBtn.setForeground(new java.awt.Color(94, 56, 24));
        newOrderBtn.setText("NEW ORDER");
        newOrderBtn.setActionCommand("SHOW NEW MENU ");
        newOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderBtnActionPerformed(evt);
            }
        });

        MakeOrderLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MakeOrderLabel.setForeground(new java.awt.Color(94, 56, 24));
        MakeOrderLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MakeOrderLabel.setText("Make an Order");

        receiptBtn.setBackground(new java.awt.Color(225, 216, 202));
        receiptBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        receiptBtn.setForeground(new java.awt.Color(94, 56, 24));
        receiptBtn.setText("SHOW RECEIPT");
        receiptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptBtnActionPerformed(evt);
            }
        });

        cafeMSLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cafeMSLabel.setForeground(new java.awt.Color(255, 153, 102));
        cafeMSLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cafeMSLabel.setText("cafe management system");

        exitBtn.setBackground(new java.awt.Color(255, 153, 102));
        exitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(94, 56, 24));
        exitBtn.setText("EXIT");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        orderItemLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderItemLabel.setForeground(new java.awt.Color(94, 56, 24));
        orderItemLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        orderItemLabel.setText("Order Item:");

        orderDrinkRBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        orderDrinkRBtn.setForeground(new java.awt.Color(94, 56, 24));
        orderDrinkRBtn.setText("Drinks");

        orderFoodRBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        orderFoodRBtn.setForeground(new java.awt.Color(94, 56, 24));
        orderFoodRBtn.setText("Pastries");
        orderFoodRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderFoodRBtnActionPerformed(evt);
            }
        });

        itemLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        itemLabel.setText("Choose item: ");

        quantLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quantLabel.setText("Enter quantity:");

        addOrderBtn.setBackground(new java.awt.Color(152, 206, 129));
        addOrderBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addOrderBtn.setForeground(new java.awt.Color(94, 56, 24));
        addOrderBtn.setText("ADD");
        addOrderBtn.setPreferredSize(new java.awt.Dimension(72, 23));
        addOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderBtnActionPerformed(evt);
            }
        });

        itemNameCmbBx.setToolTipText("choose item");

        priceTxtFld.setEditable(false);
        priceTxtFld.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        priceTxtFld.setText("$0.00");
        priceTxtFld.setBorder(null);
        priceTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTxtFldActionPerformed(evt);
            }
        });

        priceLabel.setText("Price: ");

        delOrderBtn.setBackground(new java.awt.Color(255, 153, 102));
        delOrderBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delOrderBtn.setForeground(new java.awt.Color(94, 56, 24));
        delOrderBtn.setText("DELETE");
        delOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delOrderBtnActionPerformed(evt);
            }
        });

        totalLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalLabel.setForeground(new java.awt.Color(94, 56, 24));
        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalLabel.setText("Total:");

        totalTxtFld.setEditable(false);
        totalTxtFld.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        totalTxtFld.setForeground(new java.awt.Color(94, 56, 24));
        totalTxtFld.setText("$0.00");
        totalTxtFld.setBorder(null);
        totalTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTxtFldActionPerformed(evt);
            }
        });

        orderTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item", "quantity", "price", "price total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(orderTbl);
        if (orderTbl.getColumnModel().getColumnCount() > 0) {
            orderTbl.getColumnModel().getColumn(0).setPreferredWidth(150);
            orderTbl.getColumnModel().getColumn(1).setPreferredWidth(30);
            orderTbl.getColumnModel().getColumn(2).setPreferredWidth(30);
            orderTbl.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        mainMenuBtn.setBackground(new java.awt.Color(225, 216, 202));
        mainMenuBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mainMenuBtn.setForeground(new java.awt.Color(94, 56, 24));
        mainMenuBtn.setText("BACK TO MAIN MENU");
        mainMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuBtnActionPerformed(evt);
            }
        });

        subLbl.setText("Subtotal:");

        subTxtFld.setEditable(false);
        subTxtFld.setText("$0.00");
        subTxtFld.setBorder(null);
        subTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subTxtFldActionPerformed(evt);
            }
        });

        taxLbl.setText("Taxes:");

        taxTxtFld.setEditable(false);
        taxTxtFld.setText("$0.00");
        taxTxtFld.setBorder(null);
        taxTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxTxtFldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 50, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(newOrderBtn)
                                .addGap(12, 12, 12)
                                .addComponent(receiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mainMenuBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exitBtn))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(quantLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                    .addComponent(itemLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(quantTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(priceTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(addOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(itemNameCmbBx, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(orderItemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(orderDrinkRBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(orderFoodRBtn))))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(MakeOrderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(cafeMSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(delOrderBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(totalLabel))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(subLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(taxLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(subTxtFld)
                                            .addComponent(taxTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(totalTxtFld))))))
                .addGap(27, 27, 27))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cafeMSLabel)
                    .addComponent(MakeOrderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderFoodRBtn)
                    .addComponent(orderDrinkRBtn)
                    .addComponent(orderItemLabel))
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemNameCmbBx, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(quantLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(priceTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(priceLabel))
                    .addComponent(quantTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subLbl)
                            .addComponent(subTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(taxLbl)
                            .addComponent(taxTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(delOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(receiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainMenuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderBtnActionPerformed
        
        if(currentItems.isEmpty()|| "".equals(quantTxtFld.getText())){
            showWarningMessage("Choose Drinks or Pastries menu\nChoose quantity ");
        } else {
            addItemToOrder();
            updateTotal();
            updateOrderTable();
            IO.saveOrder(order, LAST_ORDER); 
            IO.addTotalAndTime(LAST_ORDER);
        }
    }//GEN-LAST:event_addOrderBtnActionPerformed

    private void orderFoodRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderFoodRBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderFoodRBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        CafeMSApp.exit();  
    }//GEN-LAST:event_exitBtnActionPerformed

    private void receiptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptBtnActionPerformed
      
        StringBuilder str = new StringBuilder();
       
        for (Item i : order) {           
            str.append(String.format("%s - Price: %s  Quantity: %d  Subtotal: %s%n",
                i.getName(),
                i.getPriceFormatted(),
                i.getQuantity(),
                i.getTotalFormatted()));
        }
        String totalStr = "---------------------------------------------------------------------\n"
                + "Subtotal: " + subTxtFld.getText() + "\n"
                + "Tax: " + taxTxtFld.getText() + "\n"
                + "---------------------------------------------------------------------\n"
                + "Total: " + totalTxtFld.getText() +"\n"
                + "------------------\n";
        str.append(totalStr);
        str.append(CafeMSApp.addTime());

        JOptionPane.showMessageDialog(this, str, "YOUR RECEIPT", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_receiptBtnActionPerformed

    private void newOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderBtnActionPerformed
        clearLastOrder();
    }//GEN-LAST:event_newOrderBtnActionPerformed

    private void priceTxtFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTxtFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTxtFldActionPerformed

    private void delOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delOrderBtnActionPerformed
         // Get the selected row index
    int selectedRow = orderTbl.getSelectedRow();

    // Check if a row is selected
    if (selectedRow == -1) {
        showWarningMessage("Select an item to delete from the order.");
        return;
    }

    // Retrieve details from the selected row
    String itemName = (String) orderTbl.getValueAt(selectedRow, 0);
    int quantity = (int) orderTbl.getValueAt(selectedRow, 1);
    String priceString = ((String) orderTbl.getValueAt(selectedRow, 2)).replaceAll("[^\\d.]", "");
    double price = Double.parseDouble(priceString);

    // Find the corresponding item in the order list
    Item itemToRemove = findItemInOrder(itemName, quantity, price);

    // Remove the item from the order list
    order.remove(itemToRemove);

    // Update the order table, total, tax, and grand total
    updateOrderTable();
    updateTotal();
    }//GEN-LAST:event_delOrderBtnActionPerformed

    private void totalTxtFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTxtFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalTxtFldActionPerformed

    private void mainMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuBtnActionPerformed
        dispose();
        JFrame mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }//GEN-LAST:event_mainMenuBtnActionPerformed

    private void subTxtFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subTxtFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subTxtFldActionPerformed

    private void taxTxtFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxTxtFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taxTxtFldActionPerformed

 
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MakeOrderLabel;
    private javax.swing.JButton addOrderBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cafeMSLabel;
    private javax.swing.JButton delOrderBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel itemLabel;
    public javax.swing.JComboBox<String> itemNameCmbBx;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JButton mainMenuBtn;
    private javax.swing.JButton newOrderBtn;
    private javax.swing.JRadioButton orderDrinkRBtn;
    private javax.swing.JRadioButton orderFoodRBtn;
    private javax.swing.JLabel orderItemLabel;
    private javax.swing.JTable orderTbl;
    private javax.swing.JLabel picLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTxtFld;
    private javax.swing.JLabel quantLabel;
    private javax.swing.JTextField quantTxtFld;
    private javax.swing.JButton receiptBtn;
    private javax.swing.JLabel subLbl;
    private javax.swing.JTextField subTxtFld;
    private javax.swing.JLabel taxLbl;
    private javax.swing.JTextField taxTxtFld;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JTextField totalTxtFld;
    // End of variables declaration//GEN-END:variables
}
