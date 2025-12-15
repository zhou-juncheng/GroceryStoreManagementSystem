public class Goods {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Goods(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        if (!name.isEmpty() && name.length() <= 50) {
            this.name = name;
        } else {
            System.out.println("The product name cannot be empty and its length should not exceed 50！");
        }
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("The price must be greater than 0！");
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("cannot be negative！");
        }
    }
}

