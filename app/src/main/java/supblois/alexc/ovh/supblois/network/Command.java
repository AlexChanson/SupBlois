package supblois.alexc.ovh.supblois.network;
public class Command{
    String type;
    Object[] parameters;
    Class expected;

    public Command(String type, Object[] parameters, Class expected) {
        this.type = type;
        this.parameters = parameters;
        this.expected = expected;
    }

    public Command(String type, Object[] parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public String getType() {

        return type;
    }

    public void setExpected(Class expected) {
        this.expected = expected;
    }

    public Class getExpected() {
        return expected;
    }
}
