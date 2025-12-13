# GroceryStoreManagementSystem
team project
import java.util.Scanner;

public class StoreApp {

    // ===== Product Class Inside Same File =====
    static class Goods {
        int id;
        String name;
        double price;
        int stock;

        Goods(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    // ===== Global Variables =====
    static Scanner sc = new Scanner(System.in);//Used to read data input by the user from the console
    static Goods[] goods = new Goods[100];//An array of Goods objects with a capacity of 100, used to store all product information
    static int goodsCount = 0;//Record the number of products added so far
    static int nextId = 1;//Used to assign a unique ID number to newly added products
    static final String ADMIN_PWD = "123456";//Admin password, used to log in to admin mode


    public static void main(String[] args) {
        System.out.println("===== Small Grocery Store =====");//Print welcome message

        while (true) {
            System.out.println("1. Admin Mode  2. Customer Mode  0. Exit");//Display main menu
            System.out.print("Choose (0-2): ");//Prompt user to enter selection
            int choice = sc.nextInt();//Read the number entered by the user as the selection
            sc.nextLine();//Read the enter key

            switch (choice) {
                case 1 -> adminMode();//Enter admin mode
                case 2 -> customerMode();//Enter customer mode
                case 0 -> {
                    System.out.println("Goodbye!");//Exit the program
                    sc.close();//Close the scanner object
                    return;
                }
                default -> System.out.println("Invalid Input! Choose 0-2.");//Prompt user to re-enter when input is invalid
            }
        }
    }

    // ===================== ADMIN MODE =====================
    static void adminMode() {
        System.out.println("===== Admin Mode =====");//Print admin mode title
        System.out.print("Enter Password: ");//Prompt user to enter admin password
        String pwd = sc.nextLine();//Read the password entered by the user

        if (!pwd.equals(ADMIN_PWD)) {
            System.out.println("Wrong Password!");//Prompt user to re-enter when password is wrong
            return;//Return to main menu
        }

        System.out.println("Login Successful!");//Prompt user when login is successful

        while (true) {
            System.out.println("1. Add Product 2，Delete Product 0. Back");//Display admin mode menu
            System.out.print("Choose: ");//Prompt user to enter selection
            int adminChoice = sc.nextInt();//Read the number entered by the user as the selection
            sc.nextLine();//Read the enter key

            switch (adminChoice) {
                case 1 -> addGoods();//Add product
                case 2 ->deleteGoods();//Delete product
                case 0 -> {
                    System.out.println("Returning to Main Menu...");//Return to main menu
                    return;
                }
                default -> System.out.println("Invalid Input!");//Prompt user to re-enter when input is invalid
            }
        }
    }

    // Add new product
    static void addGoods() {
        System.out.println("===== Add New Product =====");//Print add product title

        //Read the product name entered by the user
        String name;
        while (true) {
            System.out.print("Product Name: ");
            name = sc.nextLine().trim(); // Use trim() to remove leading and trailing spaces Learn From AI
            if (name.isEmpty()) {
                System.out.println("Error: Product name cannot be empty! Please re-enter.");
            }
            else if (name.length() > 50) { // Optional: add length limit
                System.out.println("Error: Product name exceeds the 50-character limit.");
            }
            else {
                break; // Input is valid, exit the loop
            }
        }

        double price;
        while (true) {
            System.out.print("Price: ");//Prompt user to enter product price
            price = sc.nextDouble();//Read the product price entered by the user
            if (price > 0) break;//Exit the loop if the price is greater than 0
            System.out.println("Price must be greater than 0!");//Prompt user to re-enter when the entered price is less than or equal to 0
        }

        int stock;
        while (true) {
            System.out.print("Stock: ");//Prompt user to enter product stock
            stock = sc.nextInt();
            if (stock >= 0) break;
            System.out.println("Stock cannot be negative!");
        }
        sc.nextLine();

        goods[goodsCount] = new Goods(nextId++, name, price, stock);//Create a new Goods object and add it to the goods array
        goodsCount++;

        System.out.println("Product Added Successfully!");
    }
    static void deleteGoods() {
        System.out.println("===== Delete Product =====");

        // Check if there are any products
        if (goodsCount == 0) {
            System.out.println("No products available to delete!");
            return;
        }

        // Enter the ID of the product to be deleted
        System.out.print("Enter Product ID to delete: ");
        int deleteId = sc.nextInt();
        sc.nextLine();

        // Find the product index
        int deleteIndex = -1;
        for (int i = 0; i < goodsCount; i++) {
            if (goods[i].id == deleteId) {
                deleteIndex = i;
                break;
            }
        }

        // Check if the product is found
        if (deleteIndex == -1) {
            System.out.println("Product ID not found!");
            return;
        }

        // Perform deletion: shift array elements forward
        for (int i = deleteIndex; i < goodsCount - 1; i++) {
            goods[i] = goods[i + 1];
        }
        goods[goodsCount - 1] = null;
        goodsCount--;

        System.out.println("Product deleted successfully!");

    }

    // ===================== CUSTOMER MODE =====================
    static void customerMode() {         // Customer mode
        System.out.println("===== Customer Mode =====");

        double budget;
        while (true) {
            System.out.print("Enter your budget: ");//Prompt user to enter budget
            budget = sc.nextDouble();//Read the budget entered by the user
            if (budget > 0) break;//Exit the loop if the budget is greater than 0
            System.out.println("Budget must be greater than 0!");//Prompt user to re-enter when the entered budget is less than or equal to 0
        }
        sc.nextLine();

        while (true) {
            System.out.println("1. Buy Product  0. Back");//Display customer mode menu
            System.out.print("Choose: ");
            int customerChoice = sc.nextInt();
            sc.nextLine();

            switch (customerChoice) {
                case 1 -> budget = buyGoods(budget);//Buy product
                case 0 -> {
                    System.out.println("Thanks for visiting!");//Prompt user when leaving customer mode
                    return;
                }
                default -> System.out.println("Invalid Input!");//Prompt user to re-enter when input is invalid
            }
        }
    }

    // Customer buys items
    static double buyGoods(double budget) {     // Customer buys products
        System.out.println("===== Buy Products =====");// Print product list

        if (goodsCount == 0) {       // When there are no products
            System.out.println("No products available!");// Prompt user that there are no products to buy
            return budget;//Return to previous menu
        }
        System.out.println("----- Product List -----");// Print product list
        for (int i = 0; i < goodsCount; i++) {
            System.out.println("ID: " + goods[i].id +
                    " | Name: " + goods[i].name +
                    " | Price: " + goods[i].price +
                    " | Stock: " + goods[i].stock);// Print product information
        }

        System.out.print("Enter Product ID: ");//Prompt user to enter product ID
        int id = sc.nextInt();//Read the ID entered by the user
        sc.nextLine();

        Goods selected = null;// Used to store the selected product
        for (int i = 0; i < goodsCount; i++) {
            if (goods[i].id == id) {// Match product ID
                selected = goods[i];// Store the selected product in the selected variable
                break;
            }
        }

        if (selected == null) {  // When product ID is not found
            System.out.println("Product ID not found!");// Prompt user that the product ID is not found
            return budget;//Return to previous menu
        }

        System.out.print("Enter quantity: ");//Prompt user to enter quantity
        int quantity = sc.nextInt();//Read the quantity entered by the user
        sc.nextLine();

        if (quantity <= 0) { // When quantity is less than or equal to 0
            System.out.println("Quantity must be > 0!");// Prompt user that quantity is less than or equal to 0
            return budget;//Return to previous menu
        }

        if (quantity > selected.stock) {    // When quantity is greater than stock
            System.out.println("Not enough stock! Available: " + selected.stock);// Prompt user that quantity is greater than stock
            return budget;
        }

        double total = selected.price * quantity;  // Calculate total price

        if (budget < total) {
            System.out.println("Not enough budget! Required: " + total + " | You have: " + budget);// Prompt user that budget is insufficient
            return budget;
        }

        selected.stock -= quantity;//Subtract the purchased quantity from the stock of the selected product
        budget -= total;//Deduct the total purchase cost from the customer's budget
        System.out.println("Purchase successful! Remaining Budget: " + budget);//Display purchase success message and remaining budget to the user

        return budget;
    }
}
