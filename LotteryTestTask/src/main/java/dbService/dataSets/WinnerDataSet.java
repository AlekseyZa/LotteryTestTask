package dbService.dataSets;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "winners")
public class WinnerDataSet implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "winner_name", updatable = false)
    private String winner_name;

    @Column(name = "age", updatable = false)
    private int age;

    @Column(name = "city", updatable = false)
    private String city;

    @Column(name = "reward_value", updatable = false)
    private int reward_value;

    public WinnerDataSet(String winner_name, int age, String city, int reward_value) {
        this.winner_name = winner_name;
        this.age = age;
        this.city = city;
        this.reward_value = reward_value;
    }

    public WinnerDataSet() {
    }

    public String getWinner_name() {
        return winner_name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public int getReward_value() {
        return reward_value;
    }
}
