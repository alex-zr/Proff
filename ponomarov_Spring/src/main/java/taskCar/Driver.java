package taskCar;

public class Driver {
    private String name;
    private int age;

    public Driver() {
    }

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return ", Driver{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
