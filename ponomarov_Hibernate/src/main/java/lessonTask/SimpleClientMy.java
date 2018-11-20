package lessonTask;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class SimpleClientMy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    private Integer age;

    @ManyToOne
    private GroupMy group;

    public SimpleClientMy() {
    }

    public SimpleClientMy(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public GroupMy getGroup() {
        return group;
    }

    public void setGroup(GroupMy group) {
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
