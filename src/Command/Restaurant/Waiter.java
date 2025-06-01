package Command.Restaurant;

import javax.swing.text.DefaultEditorKit;
import java.util.ArrayList;
import java.util.function.IntUnaryOperator;

public class Waiter {
    public ArrayList<Order> orders;
    public Integer tableNo;

    public Waiter(Integer tableNo) {
        this.orders = new ArrayList<>();
        this.tableNo = tableNo;
    }

    public void takeOrder(Order order){
        this.orders.add(order);
    }

    public void sendOrder(){
        for (Order order : orders){ order.prepare(); }
        this.orders.clear();
    }

}
