package Command.Restaurant;

public class PizzaOrder implements Order{
    private Cook cook;
    private String type;

    @Override
    public void prepare(){
        cook.preparePizza(type);
    }

    public PizzaOrder(Cook cook, String type) {
        this.cook = cook;
        this.type = type;
    }
}
