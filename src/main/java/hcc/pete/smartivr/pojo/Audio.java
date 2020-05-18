package hcc.pete.smartivr.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Data
@Entity(name = "t_audio")
public class Audio {

    public Audio() {
    }

    public Audio(String combId, String path) {
        this.combId = combId;
        this.path = path;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "block_id")
    private int blockId;
    @Column(name = "comb_id")
    private String combId;
    @Column(name = "path")
    private String path;
    @Column(name = "comment")
    private String comment;
    @Column(name = "file_name")
    private String filename;

}
