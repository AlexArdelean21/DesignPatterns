package Command.ControlSystem;

public class LightCommand implements Command{
    private Light light;

    @Override
    public void execute() {
        light.On();
    }

    @Override
    public void undO() {
        light.Off();
    }

    public LightCommand(Light light) {
        this.light = light;
    }
}
