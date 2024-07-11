/*
 * Olga Lashko
 * Programming II
 * CITC-1311-C01
 * 11-13-23
 * Cafe Management System Semester Progect
 */
package CafeMSApp;

import static CafeMSApp.OrderMenu.formattedGrandTotal;
import static CafeMSApp.OrderMenu.formattedTax;
import static CafeMSApp.OrderMenu.formattedTotal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


public class IO {
     private File translateFile = null;
 
    
    public static void saveMenu(ArrayList<Item> menuList, String filename) {
        IO file = new IO();
        file.checkFile(filename);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (int i = 0; i < menuList.size(); i++) {
                Item item = menuList.get(i);
                // Use the format <name> - $<price>
                writer.write(item.getName() + " - $" + item.getPrice() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static void saveOrder(ArrayList<Item> menuList, String filename) {
        IO file = new IO();
        file.checkFile(filename);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (int i = 0; i < menuList.size(); i++) {
                Item item = menuList.get(i);
                // Use the format <name> - $<price>
                writer.write(item.getName() + " - $" + item.getPrice()+ " - " + item.getQuantity() + " - $" + item.getTotal() + "\n");
                
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void checkFile(String filename){
        try{
            translateFile = new File(filename);
            
            if(!translateFile.exists())
                translateFile.createNewFile();
        }catch(IOException e){
            System.out.println("Error :" + e);
        }
    }
        
    
    public static ArrayList<Item> getMenu(String filename) {
        IO file = new IO();
        file.checkFile(filename);

        ArrayList<Item> itemList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

            String line = in.readLine();
            while (line != null) {
                // Split the line by "-"
                String[] parts = line.split(" - ");

                // Check if the line has at least 2 parts
                if (parts.length >= 2) {
                    // Join the parts after the first "-" to get the full item name
                    String itemName = String.join(" - ", Arrays.copyOfRange(parts, 0, parts.length - 1));
                    double itemPrice = Double.parseDouble(parts[parts.length - 1].replace("$", ""));

                    // Add menu object and add object to the array list
                    Item item = new Item(itemName, itemPrice);
                    itemList.add(item);
                } 
                line = in.readLine();
            }
            return itemList;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static ArrayList<Item> getOrder(String filename) {
        IO file = new IO();
        file.checkFile(filename);

        ArrayList<Item> itemList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

            String line = in.readLine();

            while (line != null) {
                // Split the line by "-"
                String[] parts = line.split(" - ");

                // Check if the line has at least 4 parts
                if (parts.length >= 4) {
                    // Join the parts after the first "-" to get the full item name
                    String itemName = String.join(" - ", Arrays.copyOfRange(parts, 0, parts.length - 3));
                    double price = Double.parseDouble(parts[parts.length - 3].replace("$", ""));
                    int quantity = Integer.parseInt(parts[parts.length - 2]);
                    double total = Double.parseDouble(parts[parts.length - 1].replace("$", ""));

                    // Add menu object and add object to the array list
                    Item item = new Item(itemName, quantity, price);
                    itemList.add(item);
                } else {
                    System.out.println("Invalid line format: " + line);
                }

                line = in.readLine();
            }
        return itemList;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public static void addTotalAndTime(String filename) {
        IO file = new IO();
        //file.checkFile(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Get the current date and time
            LocalDateTime currentTime = LocalDateTime.now();

            // Format the date and time as a string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);

            // Append the formatted time to the file
            writer.write("---------------------------------------------------------------------\n");
            writer.write("Subtotal: " + formattedTotal + "\n");
            writer.write("Tax: " + formattedTax + "\n");
            writer.write("---------------------------------------------------------------------\n");
            writer.write("Total: " + formattedGrandTotal +"\n");
            writer.write("------------------\n");
            writer.write(formattedTime);
            writer.write("\n------------------\n");
            
            System.out.println("Current time order added to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static ArrayList<String> getStrings(String filename){
        IO file = new IO();
        file.checkFile(filename);
        
        var translates = new ArrayList<String>();
        try (BufferedReader in = new BufferedReader(
                                 new FileReader(filename))) {

            String line = in.readLine();
            while (line != null) {
                translates.add(line);
                line = in.readLine();
            }
            return translates;
        }
        catch (FileNotFoundException e) {
            System.out.println(filename + " doesn't exist.");
            return null;            
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

}

