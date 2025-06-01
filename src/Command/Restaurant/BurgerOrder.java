package Command.Restaurant;

public class BurgerOrder implements Order{

    private String type;
    private Cook cook;
    @Override
    public void prepare() {
        cook.prepareBurger(this.type);
    }

    public BurgerOrder(Cook cook, String type) {
        this.type = type;
        this.cook = cook;
    }
}
