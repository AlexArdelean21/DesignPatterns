package Command.ControlSystem;

public class Main {
    public static void main(String[] args) {
        Light livingRoom = new Light();
        Command lightOn = new LightCommand(livingRoom);
        RemoteControl remote = new RemoteControl();
        remote.pressButton();
        remote.pressUndo();
    }
}
