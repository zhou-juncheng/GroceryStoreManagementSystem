import java.util.Scanner;

public class GroceryStore {
    private static Scanner sc = new Scanner(System.in);
    private static Goods[] goods = new Goods[100];
    private static int goodsCount = 0;
    private static int nextId = 1;
    private static final String ADMIN_PWD = "123456";
    private static Order[] orderHistory = new Order[100]; 
    private static int orderCount = 0; 

    public static void main(String[] args) {
        System.out.println("===== Small Grocery Store =====");

        while (true) {
            System.out.println("1. Admin Mode  2. Customer Mode  0. Exit");
            System.out.print("Choose (0-2): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> adminMode();
                case 2 -> customerMode();
                case 0 -> {
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid Input! Choose 0-2.");
            }
        }
    }

    static void adminMode() {
        System.out.println("===== Admin Mode =====");
        System.out.print("Enter Password: ");
        String pwd = sc.nextLine();

        if (!pwd.equals(ADMIN_PWD)) {
            System.out.println("Wrong Password!");
            return;
        }

        System.out.println("Login Successful!");

        while (true) {
            System.out.println("1. Add Product  2. Delete Product  3. Modify Product  0. Back");
            System.out.print("Choose: ");
            int adminChoice = sc.nextInt();
            sc.nextLine();

            switch (adminChoice) {
                case 1 -> addGoods();
                case 2 -> deleteGoods();
                case 3 -> modifyGoods();
                case 0 -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid Input!");
            }
        }
    }

    static void modifyGoods() {
        System.out.println("===== Modify Product =====");

        if (goodsCount == 0) {
            System.out.println("No products available to modify!");
            return;
        }

        System.out.print("Enter Product ID to modify: ");
        int modifyId = sc.nextInt();
        sc.nextLine();

        Goods targetGoods = null;
        for (int i = 0; i < goodsCount; i++) {
            if (goods[i].getId() == modifyId) {
                targetGoods = goods[i];
                break;
            }
        }

        if (targetGoods == null) {
            System.out.println("Product ID not found!");
            return;
        }

        System.out.println("Current Product Info:");
        System.out.println("ID: " + targetGoods.getId() +
                " | Name: " + targetGoods.getName() +
                " | Price: " + targetGoods.getPrice() +
                " | Stock: " + targetGoods.getStock());

        while (true) {
            System.out.println("\nWhat to modify?");
            System.out.println("1. Name  2. Price  3. Stock  0. Back");
            System.out.print("Choose: ");
            int modifyChoice = sc.nextInt();
            sc.nextLine();

            switch (modifyChoice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine().trim();
                    targetGoods.setName(newName);
                    System.out.println("Name modified successfully!");
                    break;
                case 2:
                    System.out.print("Enter new price: ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();
                    targetGoods.setPrice(newPrice);
                    System.out.println("Price modified successfully!");
                    break;
                case 3:
                    System.out.print("Enter new stock: ");
                    int newStock = sc.nextInt();
                    sc.nextLine();
                    targetGoods.setStock(newStock);
                    System.out.println("Stock modified successfully!");
                    break;
                case 0:
                    System.out.println("Exiting modify mode...");
                    return;
                default:
                    System.out.println("Invalid Input! Choose 0-3.");
            }

            System.out.println("\nUpdated Product Info:");
            System.out.println("ID: " + targetGoods.getId() +
                    " | Name: " + targetGoods.getName() +
                    " | Price: " + targetGoods.getPrice() +
                    " | Stock: " + targetGoods.getStock());
        }
    }

    static void addGoods() {
        System.out.println("===== Add New Product =====");

        String name;
        while (true) {
            System.out.print("Product Name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Error: Product name cannot be empty! Please re-enter.");
            } else if (name.length() > 50) {
                System.out.println("Error: Product name exceeds the 50-character limit.");
            } else {
                break;
            }
        }

        double price;
        while (true) {
            System.out.print("Price: ");
            price = sc.nextDouble();
            if (price > 0) break;
            System.out.println("Price must be greater than 0!");
        }

        int stock;
        while (true) {
            System.out.print("Stock: ");
            stock = sc.nextInt();
            if (stock >= 0) break;
            System.out.println("Stock cannot be negative!");
        }
        sc.nextLine();

        goods[goodsCount] = new Goods(nextId++, name, price, stock);
        goodsCount++;

        System.out.println("Product Added Successfully!");
    }

    static void deleteGoods() {
        System.out.println("===== Delete Product =====");

        if (goodsCount == 0) {
            System.out.println("No products available to delete!");
            return;
        }

        System.out.print("Enter Product ID to delete: ");
        int deleteId = sc.nextInt();
        sc.nextLine();

        int deleteIndex = -1;
        for (int i = 0; i < goodsCount; i++) {
            if (goods[i].getId() == deleteId) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex == -1) {
            System.out.println("Product ID not found!");
            return;
        }

        for (int i = deleteIndex; i < goodsCount - 1; i++) {
            goods[i] = goods[i + 1];
        }
        goods[goodsCount - 1] = null;
        goodsCount--;

        System.out.println("Product deleted successfully!");
    }

    static void customerMode() {
        System.out.println("===== Customer Mode =====");

        double budget;
        while (true) {
            System.out.print("Enter your budget: ");
           String budgetInput = sc.nextLine().trim(); // trim().learn for AI
    try {
        budget = Double.parseDouble(budgetInput); // turn to number
        if (budget > 0) break;
        System.out.println("Budget must be greater than 0!");
    } catch (NumberFormatException e) {
        // warn if it isn't number
        System.out.println("Please enter a valid number (e.g., 100, 99.9)!");
    }
}
        sc.nextLine();

        while (true) {
            System.out.println("1. Buy Product  0. Back");
            System.out.print("Choose: ");
            int customerChoice = sc.nextInt();
            sc.nextLine();

            switch (customerChoice) {
                case 1 -> budget = buyGoods(budget);
                case 2 -> viewOrderHistory();
                case 0 -> {
                    System.out.println("Thanks for visiting!");
                    return;
                }
                default -> System.out.println("Invalid Input!");
            }
        }
    }
    

    static double buyGoods(double budget) {
        System.out.println("===== Buy Products =====");

        if (goodsCount == 0) {
            System.out.println("No products available!");
            return budget;
        }
        System.out.println("----- Product List -----");
        for (int i = 0; i < goodsCount; i++) {
            System.out.println("ID: " + goods[i].getId() +
                    " | Name: " + goods[i].getName() +
                    " | Price: " + goods[i].getPrice() +
                    " | Stock: " + goods[i].getStock());
        }
        System.out.println("----- Price Query -----");
        while (true) {
            System.out.print("Do you want to view the cheapest product? (Y/N): ");
            String viewCheapest = sc.nextLine().trim().toUpperCase();

            if (viewCheapest.equals("Y") || viewCheapest.equals("N")) {
                if (viewCheapest.equals("Y")) {
                    Goods cheapestGoods = findCheapestProduct();
                    System.out.println("----- Cheapest Product -----");
                    System.out.println("ID: " + cheapestGoods.getId() +
                            " | Name: " + cheapestGoods.getName() +
                            " | Price: " + cheapestGoods.getPrice() +
                            " | Stock: " + cheapestGoods.getStock());
                }
                break;
            } else {
                System.out.println("Invalid input! Please enter Y or N.");
            }
        }

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Goods selected = null;
        for (int i = 0; i < goodsCount; i++) {
            if (goods[i].getId() == id) {
                selected = goods[i];
                break;
            }
        }

        if (selected == null) {
            System.out.println("Product ID not found!");
            return budget;
        }

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        if (quantity <= 0) {
            System.out.println("Quantity must be > 0!");
            return budget;
        }

        if (quantity > selected.getStock()) {
            System.out.println("Not enough stock! Available: " + selected.getStock());
            return budget;
        }

        double total = selected.getPrice() * quantity;

        if (budget < total) {
            System.out.println("Not enough budget! Required: " + total + " | You have: " + budget);
            return budget;
        }

        selected.setStock(selected.getStock() - quantity);
        budget -= total;
        System.out.println("Purchase successful! Remaining Budget: " + budget);
         Order newOrder = new Order(selectedGoods, quantity, totalPrice, budget);
        orderHistory[orderCount] = newOrder;
        orderCount++;

        return budget;
    }
     private static void viewOrderHistory() {
        System.out.println("===== Order History =====");
        if (orderCount == 0) {
            System.out.println("No order history available!");
            return;
        }

        System.out.println("----- All Orders -----");
        for (int i = 0; i < orderCount; i++) {
            Order order = orderHistory[i];
            System.out.println("Order ID: " + order.getOrderId() +
                    " | Goods ID: " + order.getGoodsId() +
                    " | Goods Name: " + order.getGoodsName() +
                    " | Quantity: " + order.getQuantity() +
                    " | Total Price: " + order.getTotalPrice() +
                    " | Remaining Budget: " + order.getRemainingBudget());
        }
    }

    static Goods findCheapestProduct() {
        if (goodsCount == 0) {
            return null;
        }

        Goods cheapest = goods[0];
        for (int i = 1; i < goodsCount; i++) {
            if (goods[i].getPrice() < cheapest.getPrice()) {
                cheapest = goods[i];
            }
        }
        return cheapest;
    }
}
