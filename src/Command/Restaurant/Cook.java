package Command.Restaurant;

public class Cook {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    public void preparePizza(String pizza){
        System.out.println("Prepare a pizza: "  + pizza);
    }
    public void prepareBurger(String burger){
        System.out.println("Prepare a burger: " + burger);
    }
}
