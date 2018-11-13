package ua.kiev.prog.jsonToDb;

import javax.persistence.*;

@Entity
@Table(name = "jtable")
@NamedQuery(name = "JTable.getAll", query = "SELECT c from JTable c")
public class JTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //private Integer id;
    //@SerializedName("geo")
    //private List<String> geoHistory = new ArrayList<>();
    @Column(name = "key_id", length = 32)
    private Integer key;      //Название авто

    @Column(name = "datasetKey", length = 64)
    private String datasetKey;      //Название авто

    @Column(name = "publishingCountry", length = 32)
    private String publishingCountry;      //Название авто

    @Column(name = "protocol", length = 32)
    private String protocol;      //Название авто
    @Column(name = "lifeStage", length = 32)
    private String lifeStage;      //Название авто

    public JTable(Integer id, Integer key, String datasetKey, String publishingCountry, String protocol, String lifeStage) {
        this.id = id;
        this.key = key;
        this.datasetKey = datasetKey;
        this.publishingCountry = publishingCountry;
        this.protocol = protocol;
        this.lifeStage = lifeStage;
    }

    public JTable() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDatasetKey() {
        return datasetKey;
    }

    public void setDatasetKey(String datasetKey) {
        this.datasetKey = datasetKey;
    }

    public String getPublishingCountry() {
        return publishingCountry;
    }

    public void setPublishingCountry(String publishingCountry) {
        this.publishingCountry = publishingCountry;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getLifeStage() {
        return lifeStage;
    }

    public void setLifeStage(String lifeStage) {
        this.lifeStage = lifeStage;
    }

    @Override
    public String toString() {
        return "JsonTable{" +
                "id=" + id +
                ", key=" + key +
                ", datasetKey='" + datasetKey + '\'' +
                ", publishingCountry='" + publishingCountry + '\'' +
                ", protocol='" + protocol + '\'' +
                ", lifeStage='" + lifeStage + '\'' +
                '}';
    }
}


