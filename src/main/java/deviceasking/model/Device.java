package deviceasking.model;

/**
 * Main interface for all devices
 */
public interface Device {

    String NOT_SUPPORTED_MESSAGE = "Method ask() is not supported";

    class WithResponce {
        public WithResponce(Device device, Object responce) {
            this.device = device;
            this.responce = responce;
        }

        final Device device;
        final Object responce;
    }

    default Object ask(byte[] args){
        return NOT_SUPPORTED_MESSAGE;
    };

    String getId();
}
