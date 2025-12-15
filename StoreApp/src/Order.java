static class Order {
    private static int nextOrderId = 1;  // 订单自增ID
    private int orderId;                 // 订单ID
    private Goods goods;                 // 关联的商品对象（核心改造点）
    private int quantity;                // 购买数量
    private double totalPrice;           // 购买总价
    private double remainingBudget;      // 剩余预算

   
    public Order(Goods goods, int quantity, double totalPrice, double remainingBudget) {
        this.orderId = nextOrderId++;
        this.goods = goods;             
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.remainingBudget = remainingBudget;
    }

   
    public int getOrderId() {
        return orderId;
    }

    public Goods getGoods() {           
        return goods;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getRemainingBudget() {
        return remainingBudget;
    }

   
    public int getGoodsId() {
        return goods.getId();
    }

    
    public String getGoodsName() {
        return goods.getName();
    }
}
