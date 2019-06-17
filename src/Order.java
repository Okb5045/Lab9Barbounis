import java.util.*;
public class Order {


    private int orderId;
    private Customer cust;
    private ArrayList<Menu> menuItem;
    private static ArrayList<Order> orderList = new ArrayList<>();


    public Order(int _orderId) {
        this.orderId = _orderId;
    }


    public int getorderId() {
        return orderId;
    }

    public void setorderId(int _orderId) {
        this.orderId = _orderId;
    }

    public static void addOrders(Order order, Customer customer, ArrayList<Menu> mList){
        order.setorderId(order.getorderId());
        order.setCust(customer);
        order.setMenuItem(mList);
        orderList.add(order);

        System.out.println("\nOrder added to customer #: " + order.getorderId());
        System.out.println("\nCustomer Name : " + order.getCust().getCustomerName()
                + ", Customer ID : " + order.getCust().getCustomerId());
    }

    public ArrayList<Menu> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(ArrayList<Menu> menuItem) {
        this.menuItem = menuItem;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

}
