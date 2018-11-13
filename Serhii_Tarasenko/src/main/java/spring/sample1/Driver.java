package spring.sample1;

public class Driver {
    private String name;

    public Driver() {
    }

    public Driver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nDriver{" +
                "name='" + name + '\'' +
                '}';
    }
}
