package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "participantdataset")
public class ParticipantDataSet implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "partisipant_name", updatable = false)
    private String partisipant_name;

    @Column(name = "age", updatable = false)
    private int age;

    @Column(name = "city", updatable = false)
    private String city;

    public ParticipantDataSet(long id, String partisipant_name, int age, String city) {
        this.partisipant_name = partisipant_name;
        this.age=age;
        this.city=city;
        this.id=id;

    }

    public ParticipantDataSet() {
    }

    public ParticipantDataSet(String partisipant_name, int age, String city) {
        this.partisipant_name = partisipant_name;
        this.age=age;
        this.city=city;

    }

    public String getPartisipant_name() {
        return partisipant_name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}
