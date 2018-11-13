package lessonTask;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="Groups")
public class GroupMy {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<SimpleClientMy> clients = new ArrayList<>();

    public GroupMy() {}

    public GroupMy(String name) {
        this.name = name;
    }

    public void addClient(SimpleClientMy client) {
        client.setGroup(this);
        clients.add(client);
    }

    public List<SimpleClientMy> getClients() {
        return Collections.unmodifiableList(clients);
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
