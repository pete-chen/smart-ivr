package hcc.pete.smartivr.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Data
@Entity(name = "t_block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "properties")
    private String properties;
}
