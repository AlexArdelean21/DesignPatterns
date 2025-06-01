package Command.Restaurant;

public class Main {
    public static void main(String[] args){
        Cook cook = new Cook("Gigel");

        int tableNo = 8;

        Waiter waiter = new Waiter(8);
        waiter.takeOrder(new PizzaOrder(cook,"peperoni"));
        waiter.takeOrder(new BurgerOrder(cook,"cheeseburger"));

        waiter.sendOrder();
    }
}
