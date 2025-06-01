package Flyweight.Forest;

public class ConcreateTreeType  implements TreeType{

    private String name;
    private String color;
    private String texture;

    public ConcreateTreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void display(int x, int y) {
        System.out.println("Displaying " + name + " tree at (" + x + ", " + y + ")");
    }

}
