package deviceasking.model;

import java.util.Objects;
import java.util.UUID;
/**
 * This class contains required fields for all device types
 */
public abstract class AbstractDevice implements Device {
    final String type = this.getClass().getSimpleName();
    final String name;
    final String id;

    public AbstractDevice(String name) {
        this.name = name;
        id = UUID.randomUUID().toString();
    }

    public AbstractDevice(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDevice that = (AbstractDevice) o;
        return Objects.equals(name, that.name) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
