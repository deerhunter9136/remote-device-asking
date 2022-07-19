package deviceasking.model;

import java.util.Random;

public class Sensor extends AbstractDevice {

    private static final Random rand = new Random();

    public Sensor(String name) {
        super(name);
    }

    @Override
    public Object ask(byte[] args) {
        String stub = rand.nextBoolean() ? "YES" : "NO";
        return stub;
    }
}
