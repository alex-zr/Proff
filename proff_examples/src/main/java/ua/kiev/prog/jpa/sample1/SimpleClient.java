package ua.kiev.prog.jpa.sample1;

import ua.kiev.prog.jpa.sample2.Group;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class SimpleClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    private Integer age;

    @ManyToOne
    private Group group;

    public SimpleClient() {
    }

    public SimpleClient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SimpleClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
