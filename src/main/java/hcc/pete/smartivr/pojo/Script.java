package hcc.pete.smartivr.pojo;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Data
@Entity(name = "t_script")
public class Script {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "blocks")
    private String blocks;
    @Column(name = "user_data")
    private String userData;
    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;
    @Column(name = "modify_timestamp")
    private Timestamp modifyTimestamp;

}
