/*
 * Olga Lashko
 * Programming II
 * CITC-1311-C01
 * 11-08-23
 * Cafe Management System GUI
 */

/*
Requirements: 
    *Create an application that will allow users to make order and show invoice. 
    *All menus will take the form of Graphis User Interface. 
    *The application will allow the user to input data. 
    *The application will validate the data and provide appropriate error messages. 
    *The application will retrieve and store data to and from external sources. 
    *The application will allow the user to edit and display the menu. 
    *The application will display an order based on the input data. 
    *The application will calculate taxes and total. 
    *The application will ask if the user wants to make another order or exit the program after the invoice was shown. 
    *At least one of the menus shall contain an image.

Menus
    *Main Menu
    *Order Menu
    *Show Menu
    *Manage Menu

Data Storage 
    *Data for the Cafe management system application will be held in txt files. 
    *The data will be read from those files and held after the application is exited. 
    *The arrays are listed below: 
        *menu_drink.txt – this file holds all drinks with prices on the menu. 
        *menu_food.txt – this file holds all the food with prices on the menu. 
        *last_order.txt - this file holds chosen food, drinks, total, and time the last order was made. 

Java Classes: 
    *CafeMSApp 
    *MainMenu 
    *OrderMenu
    *ManageMenu 
    *ShowMenu
    *Item
    *IO 
    *Console 

    CafeMSApp class methods:
        main()
        showNameDrink()
        showNameFood()
        showNames (ArrayList <Item> itemList)
        showItems(String fileName, ArrayList<Item> menuList)
        exit()
        showMenu()
        showWarningMessage(String message)
        showInformationMessage(String message)
        addTime()
    
    CafeMSApp class variables:
        String MENUDRINK
        String MENUFOOD
        String LAST_ORDER
        ArrayList <Item> drinkMenuList
        ArrayList <Item> foodMenuList
        
    MainMenu class methods:
        MainMenu()
        exitButActionPerformed()
        manageMenuButActionPerformed()
        makeOrderButActionPerformed()
        showMenuButActionPerformed()
        main()

    OrderMenu class methods:
        OrderMenu()
        updateQuantityAndPrice() 
        updatePrice() 
        findItem()
        updateComboBoxModel()
        addItemToOrder()
        updateOrderTable()
        updateTotal()
        clearLastOrder()
        findItemInOrder()
        addOrderBtnActionPerformed()
        exitBtnActionPerformed()
        receiptBtnActionPerformed()
        newOrderBtnActionPerformed()
        delOrderBtnActionPerformed()
        mainMenuBtnActionPerformed()
        main()

    OrderMenu class variables:
        String formattedTotal
        String formattedTax
        String formattedGrandTotal
        ArrayList<String> currentItems
        ButtonGroup orderTypeButtonGroup
        ArrayList<Item> order

    ManageMenu class methods:
        ManageMenu()
        setActionListeners()
        addItem()
        refreshComboBoxes()
        getNameCmbBx()
        changeItem()
        getNameCmbBxForDelete()
        getNameCmbBxForChange()
        refreshDelItemComboBox()
        refreshChangeItemComboBox()
        deleteItem()
        makeOrderBtnActionPerformed()
        deleteBtnActionPerformed()
        changeBtnActionPerformed()
        addBtnActionPerformed()
        exitButActionPerformed()
        mainMenuButActionPerformed()
        showMenuButActionPerformed()
        
    ManageMenu class variables:
        private ButtonGroup addBtnGroup;
        private ButtonGroup changeBtnGroup;
        private ButtonGroup delBtnGroup;

    ShowMenu class methods:
        ShowMenu()
        exitButActionPerformed()
        backButActionPerformed()
        makeOrderBtnActionPerformed()
        manageBtnActionPerformed()
        main()

    Item class methods:
        Item()
        Item(String item, int quantity, double price)
        Item(String item, double price)
        setName()
        getName()
        setQuantity()
        getQuantity()
        setPrice()
        getPrice()
        getPriceFormatted()
        getTotalFormatted()
        getTotal()

    Item class variables:
        String name
        int quantity
        double price
        double total

    IO class methods:
        saveMenu()
        saveOrder()
        checkFile()
        getMenu()
        getOrder()
        addTotalAndTime()
        getStrings()

    Console class methods:
        getString()
        getInt()
        getDouble()

    Console class variables:
        Scanner sc

Data validation methods:
    *If there is not enought information or information is in wrong format, 
     it will be shown window with message using showWarningMessage() method

    *When infornation is succcesfully changed 
     it will be shown notification message using showInformationMessage() method

*/
package CafeMSApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class CafeMSApp {
    
    public static final String MENUDRINK = "menu_drink.txt";
    public static final String MENUFOOD = "menu_food.txt";
    public static final String LAST_ORDER = "last_order.txt";
    
    public static ArrayList <Item> drinkMenuList = IO.getMenu(MENUDRINK);
    public static ArrayList <Item> foodMenuList = IO.getMenu(MENUFOOD);
    
    
    static JFrame mainMenu;

    public static void main(String[] args) { 
        mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        
    }
    
    public static ArrayList<String> showNameDrink(){ 
        ArrayList <String> drinkNameList = new ArrayList<>();
        for(Item item:drinkMenuList){
            drinkNameList.add(item.getName());
        }
        return drinkNameList;
    }
    
    public static ArrayList<String> showNameFood(){  
        ArrayList <String> foodNameList = new ArrayList<>();
        for(Item item:foodMenuList){
            foodNameList.add(item.getName());
        }
        return foodNameList;
    }
    
    public static ArrayList<String> showNames (ArrayList <Item> itemList){  
        ArrayList <String> nameList = new ArrayList<>();
        for(Item item:itemList){
            nameList.add(item.getName());
        }
        return nameList;
    }
    
    
    public static DefaultListModel<String> showItems(String fileName, ArrayList<Item> menuList) {
        
        menuList = IO.getMenu(fileName);
        DefaultListModel<String> formattedList = new DefaultListModel<>();

        for (Item menuItem : menuList) {
            String str = String.format("%s - %s%n", menuItem.getName(),menuItem.getPriceFormatted());     
            formattedList.addElement(str);
        }
        System.out.println("Items Names:\n " + formattedList); // Add this line for debugging
        return formattedList;
    }

         
    public static void exit(){
        System.exit(0);
    }
    
    
    //ShowMenu screen
    public static void showMenu(){
        ShowMenu showMenu = new ShowMenu();
    
        DefaultListModel<String> drinkListModel = showItems(MENUDRINK, drinkMenuList);
        DefaultListModel<String> foodListModel = showItems(MENUFOOD, foodMenuList);

        showMenu.drinkList.setModel((ListModel<String>) drinkListModel);
        showMenu.foodList.setModel((ListModel<String>) foodListModel);

        showMenu.setVisible(true);
    }
    
     
    public static void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information requaired", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void showInformationMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Just a little information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static String addTime (){
            // Get the current date and time
            LocalDateTime currentTime = LocalDateTime.now();

            // Format the date and time as a string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            
            StringBuilder str = new StringBuilder();
            str.append(formattedTime);
            str.append("\n------------------\n");
            return str.toString();
    }
}
