package deviceasking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class Scanner extends AbstractDevice {
    static final int length = 9;

    @JsonIgnore
    private final byte[] reference;

    public Scanner(String name, byte[] reference) {
        super(name);
        this.reference = Arrays.copyOf(reference, length);

    }

    @Override
    public Object ask(byte[] args) {
        return Arrays.equals(args, reference) ? Boolean.TRUE : Boolean.FALSE;
    }
}
