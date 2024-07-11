/*
 * Olga Lashko
 * Programming II
 * CITC-1311-C01
 * 11-13-23
 * Cafe Management System Semester Progect
 */
package CafeMSApp;

import static CafeMSApp.CafeMSApp.showInformationMessage;
import static CafeMSApp.CafeMSApp.showWarningMessage;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

public class ManageMenu extends javax.swing.JFrame {
    
    private ButtonGroup addBtnGroup;
    private ButtonGroup changeBtnGroup;
    private ButtonGroup delBtnGroup;
    
    
    public ManageMenu() {
        initComponents();
        
        // Initialize add button group
        addBtnGroup = new ButtonGroup();
        addBtnGroup.add(addDrinkRB);
        addBtnGroup.add(addFoodRB);
        
        // Initialize change button group
        changeBtnGroup = new ButtonGroup();
        changeBtnGroup.add(changeDrinkRB);
        changeBtnGroup.add(changeFoodRB);
        
        // Initialize delete button group
        delBtnGroup = new ButtonGroup();
        delBtnGroup.add(deleteDrinkRB);
        delBtnGroup.add(deleteFoodRB);
        
        setActionListeners();
    }
    
    
    private void setActionListeners() {
    // Set the action listeners for delete radio buttons
    deleteDrinkRB.addActionListener(evt -> refreshDelItemComboBox());
    deleteFoodRB.addActionListener(evt -> refreshDelItemComboBox());
    
    changeDrinkRB.addActionListener(evt -> refreshChangeItemComboBox());
    changeFoodRB.addActionListener(evt -> refreshChangeItemComboBox());
}
    
    
    private void addItem(ArrayList<Item> menuList,Item newItem, String menuFileName) {
        if (menuList == null) {
            System.out.println("Menu list is null");
            return; // Exit the method if menuList is null
        }
        menuList.add (newItem);
        IO.saveMenu(menuList, menuFileName);
        refreshComboBoxes(); // Refresh the combo boxes to reflect the changes
    }

    private void refreshComboBoxes() {
        DefaultComboBoxModel<String> comboBoxModel = getNameCmbBx();

        if (comboBoxModel != null) {
            DefaultComboBoxModel<String> existingModel = (DefaultComboBoxModel<String>) chooseItemCBox.getModel();
            existingModel.removeAllElements(); // Clear existing elements

            // Iterate over the elements of comboBoxModel and add them to existingModel
            for (int i = 0; i < comboBoxModel.getSize(); i++) {
                existingModel.addElement(comboBoxModel.getElementAt(i));
            }
        } else {
            // Handle the case when getNameCmbBx() returns null
            System.err.println("Error: ComboBoxModel is null");
        }
    }   
    
    
    private DefaultComboBoxModel<String> getNameCmbBx() {
    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    if (changeDrinkRB.isSelected()) {
        for (String itemName : CafeMSApp.showNameDrink()) {
            comboBoxModel.addElement(itemName);
        }
    } else if (changeFoodRB.isSelected()) {
        for (String itemName : CafeMSApp.showNameFood()) {
            comboBoxModel.addElement(itemName);
        }
    }
    return comboBoxModel;
}

    
    private void changeItem(String name, double newPrice, ArrayList<Item> menuList, String menuFileName) {
        if (menuList == null) {
            System.out.println("Menu list is null");
            return; // Exit the method if menuList is null
        }

        for (Item item : menuList) {
            if (item.getName().equals(name)) {
                item.setPrice(newPrice);
                IO.saveMenu(menuList, menuFileName);
                return;
            }
        }
    }
    
    
    private DefaultComboBoxModel<String> getNameCmbBxForDelete() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Use the showNames method to get names based on the selected radio button
        if (deleteDrinkRB.isSelected()) {
            comboBoxModel.addAll(CafeMSApp.showNames(CafeMSApp.drinkMenuList));
        } else if (deleteFoodRB.isSelected()) {
            comboBoxModel.addAll(CafeMSApp.showNames(CafeMSApp.foodMenuList));
        }
        return comboBoxModel;
    }
    
    
    private DefaultComboBoxModel<String> getNameCmbBxForChange() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Use the showNames method to get names based on the selected radio button
        if (changeDrinkRB.isSelected()) {
            comboBoxModel.addAll(CafeMSApp.showNames(CafeMSApp.drinkMenuList));
        } else if (changeFoodRB.isSelected()) {
            comboBoxModel.addAll(CafeMSApp.showNames(CafeMSApp.foodMenuList));
        }
        return comboBoxModel;
    }

    
    private void refreshDelItemComboBox() {
        DefaultComboBoxModel<String> comboBoxModel = getNameCmbBxForDelete();
        delItemCBox.setModel(comboBoxModel);
    }
    
    private void refreshChangeItemComboBox() {
        DefaultComboBoxModel<String> comboBoxModel = getNameCmbBxForChange();
        chooseItemCBox.setModel(comboBoxModel);
    }
    
    
    private void deleteItem(String name, ArrayList<Item> menuList, String menuFileName) {
        // Remove the item with the specified name from the list
        menuList.removeIf(item -> item.getName().equals(name));
        IO.saveMenu(menuList, menuFileName);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        showMenuBut = new javax.swing.JButton();
        manageMenuLabel = new javax.swing.JLabel();
        mainMenuBut = new javax.swing.JButton();
        cafeMSLabel = new javax.swing.JLabel();
        exitBut = new javax.swing.JButton();
        addLabel = new javax.swing.JLabel();
        addDrinkRB = new javax.swing.JRadioButton();
        addFoodRB = new javax.swing.JRadioButton();
        addItemLabel = new javax.swing.JLabel();
        addPriceLabel = new javax.swing.JLabel();
        addItemField = new javax.swing.JTextField();
        addPriceField = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        changeLabel = new javax.swing.JLabel();
        oldItemLabel = new javax.swing.JLabel();
        chooseItemCBox = new javax.swing.JComboBox<>();
        newPriceLabel = new javax.swing.JLabel();
        newPriceField = new javax.swing.JTextField();
        changeBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        deleteDrinkRB = new javax.swing.JRadioButton();
        deleteFoodRB = new javax.swing.JRadioButton();
        delItemLabel = new javax.swing.JLabel();
        delItemCBox = new javax.swing.JComboBox<>();
        deleteBtn = new javax.swing.JButton();
        changeDrinkRB = new javax.swing.JRadioButton();
        changeFoodRB = new javax.swing.JRadioButton();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        makeOrderBtn = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(625, 425));

        showMenuBut.setBackground(new java.awt.Color(225, 216, 202));
        showMenuBut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        showMenuBut.setForeground(new java.awt.Color(94, 56, 24));
        showMenuBut.setText("SHOW MENU");
        showMenuBut.setActionCommand("SHOW NEW MENU ");
        showMenuBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMenuButActionPerformed(evt);
            }
        });

        manageMenuLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        manageMenuLabel.setForeground(new java.awt.Color(94, 56, 24));
        manageMenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manageMenuLabel.setText("Manage Menu");

        mainMenuBut.setBackground(new java.awt.Color(225, 216, 202));
        mainMenuBut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mainMenuBut.setForeground(new java.awt.Color(94, 56, 24));
        mainMenuBut.setText("BACK TO MAIN MENU");
        mainMenuBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuButActionPerformed(evt);
            }
        });

        cafeMSLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cafeMSLabel.setForeground(new java.awt.Color(255, 153, 102));
        cafeMSLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cafeMSLabel.setText("cafe management system");

        exitBut.setBackground(new java.awt.Color(255, 153, 102));
        exitBut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitBut.setForeground(new java.awt.Color(94, 56, 24));
        exitBut.setText("EXIT");
        exitBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButActionPerformed(evt);
            }
        });

        addLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addLabel.setForeground(new java.awt.Color(94, 56, 24));
        addLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addLabel.setText("Add new food or drink to menu:");

        addDrinkRB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addDrinkRB.setForeground(new java.awt.Color(94, 56, 24));
        addDrinkRB.setText("Drinks");

        addFoodRB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addFoodRB.setForeground(new java.awt.Color(94, 56, 24));
        addFoodRB.setText("Pastries");
        addFoodRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFoodRBActionPerformed(evt);
            }
        });

        addItemLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addItemLabel.setText("Enter new item: ");

        addPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addPriceLabel.setText("Enter price:");

        addItemField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemFieldActionPerformed(evt);
            }
        });

        addPriceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPriceFieldActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(152, 206, 129));
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(94, 56, 24));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        changeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        changeLabel.setForeground(new java.awt.Color(94, 56, 24));
        changeLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        changeLabel.setText("Change food or drink price:");

        oldItemLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        oldItemLabel.setText("Choose item:");

        newPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newPriceLabel.setText("Enter price:");

        changeBtn.setBackground(new java.awt.Color(152, 206, 129));
        changeBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        changeBtn.setForeground(new java.awt.Color(94, 56, 24));
        changeBtn.setText("CHANGE");
        changeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeBtnActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(94, 56, 24));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Delete food or drink from menu:");

        deleteDrinkRB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteDrinkRB.setForeground(new java.awt.Color(94, 56, 24));
        deleteDrinkRB.setText("Drinks");

        deleteFoodRB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteFoodRB.setForeground(new java.awt.Color(94, 56, 24));
        deleteFoodRB.setText("Pastries");
        deleteFoodRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFoodRBActionPerformed(evt);
            }
        });

        delItemLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delItemLabel.setText("Choose item:");

        deleteBtn.setBackground(new java.awt.Color(255, 153, 102));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(94, 56, 24));
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        changeDrinkRB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        changeDrinkRB.setForeground(new java.awt.Color(94, 56, 24));
        changeDrinkRB.setText("Drinks");

        changeFoodRB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        changeFoodRB.setForeground(new java.awt.Color(94, 56, 24));
        changeFoodRB.setText("Pastries");
        changeFoodRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFoodRBActionPerformed(evt);
            }
        });

        makeOrderBtn.setBackground(new java.awt.Color(225, 216, 202));
        makeOrderBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        makeOrderBtn.setForeground(new java.awt.Color(94, 56, 24));
        makeOrderBtn.setText("MAKE AN ORDER");
        makeOrderBtn.setActionCommand("SHOW NEW MENU ");
        makeOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeOrderBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator15)
                    .addComponent(jSeparator14)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(cafeMSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(manageMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addItemLabel)
                                    .addComponent(addPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(addPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(addItemField)))
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(delItemLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(delItemCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(newPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(oldItemLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(newPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(changeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(chooseItemCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(showMenuBut, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(makeOrderBtn))
                                    .addComponent(jSeparator13, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(mainMenuBut, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(exitBut))
                                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel15Layout.createSequentialGroup()
                                                    .addComponent(changeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(changeDrinkRB, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(changeFoodRB))
                                                .addGroup(jPanel15Layout.createSequentialGroup()
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(deleteDrinkRB, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(deleteFoodRB))
                                                .addGroup(jPanel15Layout.createSequentialGroup()
                                                    .addGap(425, 425, 425)
                                                    .addComponent(deleteBtn))))
                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                            .addComponent(addLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(addDrinkRB, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(addFoodRB))))))
                        .addGap(6, 6, 6)))
                .addGap(27, 27, 27))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cafeMSLabel)
                    .addComponent(manageMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addFoodRB)
                        .addComponent(addDrinkRB)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addItemLabel)
                    .addComponent(addItemField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addPriceLabel)
                    .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addPriceField, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeLabel)
                    .addComponent(changeDrinkRB)
                    .addComponent(changeFoodRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oldItemLabel)
                    .addComponent(chooseItemCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(changeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newPriceField, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(newPriceLabel)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteDrinkRB)
                    .addComponent(jLabel14)
                    .addComponent(deleteFoodRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delItemLabel)
                    .addComponent(delItemCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitBut, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showMenuBut, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainMenuBut, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(makeOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void makeOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeOrderBtnActionPerformed
        dispose();
        JFrame orderMenu = new OrderMenu();
        orderMenu.setVisible(true);
    }//GEN-LAST:event_makeOrderBtnActionPerformed

    private void changeFoodRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFoodRBActionPerformed
        //  refreshComboBoxes();
    }//GEN-LAST:event_changeFoodRBActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed

     String selectedName = (String) delItemCBox.getSelectedItem();

    // Check if selectedName is not null before using it
    if (selectedName != null) {
        // Call the deleteItem method based on the selected radio button
        if (deleteDrinkRB.isSelected() && !selectedName.equals("")) {
            deleteItem(selectedName, CafeMSApp.drinkMenuList, CafeMSApp.MENUDRINK);
            showInformationMessage("Item was deleted from Drink Menu");
        } else if (deleteFoodRB.isSelected() && !selectedName.equals("")) {
            deleteItem(selectedName, CafeMSApp.foodMenuList, CafeMSApp.MENUFOOD);
            showInformationMessage( "Item was deleted from Food Menu");
        } else {
            showWarningMessage("Choose menu you want to delete an item: food or drink");
        }

        // Refresh the combo box after deletion
        refreshDelItemComboBox();
    } else {
        // Handle the case where selectedName is null
        showWarningMessage("Please select an item to delete.");
    }

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void deleteFoodRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFoodRBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteFoodRBActionPerformed

    private void changeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeBtnActionPerformed
        if (chooseItemCBox.getSelectedIndex() == -1) {
            showWarningMessage( "Select an item to change");
            return;
        }

        String selectedName = (String) chooseItemCBox.getSelectedItem();
        double newPrice;

        try {
            // Parse the new price as a double
            newPrice = Double.parseDouble(newPriceField.getText());
        } catch (NumberFormatException e) {
            showWarningMessage("New price should be a valid number");
            return;
        }

        // Call the changeItem method based on the selected radio button
        if (changeDrinkRB.isSelected()) {
            changeItem(selectedName, newPrice, CafeMSApp.drinkMenuList, CafeMSApp.MENUDRINK);
            showInformationMessage( "Item was changed in Drink Menu");
            //  refreshComboBoxes(); // Refresh the combo boxes to reflect the changes
        } else if (changeFoodRB.isSelected()) {
            changeItem(selectedName, newPrice, CafeMSApp.foodMenuList, CafeMSApp.MENUFOOD);
            showInformationMessage( "Item was changed in Food Menu");
            //  refreshComboBoxes(); // Refresh the combo boxes to reflect the changes
        } else {
            showWarningMessage("Choose menu you want to change an item: food or drink");
        }
        // Clear newItemField and newPriceField after the change
        //newItemField.setText("");
        newPriceField.setText("");

    }//GEN-LAST:event_changeBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

        if (addItemField.getText().isEmpty() || addPriceField.getText().isEmpty()) {
            showWarningMessage("Enter name of Item and Price");
        } else {
            try {
                // Parse the price as a double
                double price = Double.parseDouble(addPriceField.getText());

                Item item = new Item();
                item.setName(addItemField.getText());
                item.setPrice(price);

                // Call the addItem method based on the selected radio button
                if (addDrinkRB.isSelected()) {
                    addItem(CafeMSApp.drinkMenuList, item, CafeMSApp.MENUDRINK);
                    showInformationMessage( "New item was added to Drink Menu");
                    addItemField.setText("");
                    addPriceField.setText("");
                } else if (addFoodRB.isSelected()) {
                    addItem(CafeMSApp.foodMenuList, item, CafeMSApp.MENUFOOD);
                    showInformationMessage( "New item was added to Food Menu");
                    addItemField.setText("");
                    addPriceField.setText("");
                } else {
                    showWarningMessage("Choose menu you want to add an item: food or drink");
                }

            } catch (NumberFormatException e) {
                showWarningMessage("Price should be a valid number");
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void addPriceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPriceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addPriceFieldActionPerformed

    private void addItemFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addItemFieldActionPerformed

    private void addFoodRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFoodRBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addFoodRBActionPerformed

    private void exitButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButActionPerformed
        CafeMSApp.exit();
    }//GEN-LAST:event_exitButActionPerformed

    private void mainMenuButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuButActionPerformed
        dispose();
        JFrame mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }//GEN-LAST:event_mainMenuButActionPerformed

    private void showMenuButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMenuButActionPerformed
        dispose();
        CafeMSApp.showMenu();
    }//GEN-LAST:event_showMenuButActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ManageMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JRadioButton addDrinkRB;
    private javax.swing.JRadioButton addFoodRB;
    private javax.swing.JTextField addItemField;
    private javax.swing.JLabel addItemLabel;
    private javax.swing.JLabel addLabel;
    private javax.swing.JTextField addPriceField;
    private javax.swing.JLabel addPriceLabel;
    private javax.swing.JLabel cafeMSLabel;
    private javax.swing.JButton changeBtn;
    private javax.swing.JRadioButton changeDrinkRB;
    private javax.swing.JRadioButton changeFoodRB;
    private javax.swing.JLabel changeLabel;
    private javax.swing.JComboBox<String> chooseItemCBox;
    private javax.swing.JComboBox<String> delItemCBox;
    private javax.swing.JLabel delItemLabel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JRadioButton deleteDrinkRB;
    private javax.swing.JRadioButton deleteFoodRB;
    private javax.swing.JButton exitBut;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JButton mainMenuBut;
    private javax.swing.JButton makeOrderBtn;
    private javax.swing.JLabel manageMenuLabel;
    private javax.swing.JTextField newPriceField;
    private javax.swing.JLabel newPriceLabel;
    private javax.swing.JLabel oldItemLabel;
    private javax.swing.JButton showMenuBut;
    // End of variables declaration//GEN-END:variables
}
