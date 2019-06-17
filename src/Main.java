
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    private int cCount = 0;


    private static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {

        Main main = new Main();

        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';

        char userAction;

        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";

        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();

        Order order1 = new Order(0);
        Transaction trans1 = new Transaction(1, order1, PaymentType.cash);

        Menu menu1 = new Menu(1, "Regular Cheese" , 10.0);
        Menu menu2 = new Menu(2, "Meat Lover", 15.0);
        Menu menu3 = new Menu(3, "Margarita", 13.0);
        Menu menu4 = new Menu(4, "Garlic", 13.0);
        Menu menu5 = new Menu(5, "Hawaiian", 13.0);
        Menu menu6 = new Menu(6, "White", 12.0);

        mList.add(menu1); //Cheese
        mList.add(menu2); //Meat Lovers
        mList.add(menu3); //Margarita
        mList.add(menu4); //Garlic
        mList.add(menu5); //Hawaiian
        mList.add(menu6); //White

        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {

            switch (userAction) {

                case CUST_CODE:
                    cList.add(main.addCustomer(cList));
                    break;

                case CUST_PRNT:
                    Customer.printCustomer(cList);
                    break;

                case MENU_CODE:
                    Menu.listMenu(mList);
                    System.out.println("");
                    break;

                case ORDE_CODE:

                    System.out.print("ENTER CUSTOMER #: " );

                    int id = scnr.nextInt();

                    ArrayList<Menu> cMenu = selectMenu(mList);
                    Order.addOrders(order1, cList.get(id), cMenu);
                    oList.add(order1);
                    trans1 = payment(order1);
                    tList.add(trans1);
                    break;

                case TRAN_CODE:
                    Transaction.listTransactions(tList);
                    break;
            }

            userAction = getAction(PROMPT_ACTION);

        }

    }

    private static Transaction payment(Order order1) {

        double total = 0;
        double amount;

        //Order printout
        for(Menu menu : order1.getMenuItem()){

            System.out.print(menu.getQuantity());
            System.out.print("    ");
            System.out.print(menu.getmenuItem());
            System.out.print(" $");
            amount = menu.getQuantity()*menu.getitemPrice();
            total = total + amount;
            System.out.println(amount);
        }

        System.out.print("Total: $");
        System.out.println(total);
        System.out.println("");

        //Payment Type
        int option;
        Transaction t;
        while(true){
            System.out.println("[1] CASH      [2] CREDIT");
            option = scnr.nextInt();
            if(option==1){
                t = new Transaction(order1.getorderId(), order1, PaymentType.cash);
                System.out.println("CASH SELECTED\n");
                return t;
            }
            else if(option==2){
                t = new Transaction(order1.getorderId(), order1, PaymentType.credit);
                System.out.println("CREDIT SELECTED\n");
                return t;
            }

        }
    }

    public static ArrayList<Menu> selectMenu(ArrayList<Menu> menus){
        System.out.println("Select order (Press 0 to finish order)");

        for (Menu menu : menus)
            System.out.println("'" + menu.getmenuId() + "' for " + menu.getmenuItem());
        int check;

        ArrayList<Menu> menus1 = new ArrayList<>();
        while(true) {
            check = scnr.nextInt();
            if(check == 0)
                break;
            System.out.print("Add quantity: " );
            int quantity = scnr.nextInt();
            Menu item = menus.get(check-1);
            item.setQuantity(quantity);
            menus1.add(item);
        }
        return menus1;
    }

    public static char getAction(String prompt) {
        //removed scanner object from here
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    public Customer addCustomer(ArrayList<Customer> cList) {
        Customer cust = null;

        cust = new Customer(cCount++);
        System.out.println("Please Enter Customer Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter Customer Buzzer #: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());

        return cust;
    }

}
