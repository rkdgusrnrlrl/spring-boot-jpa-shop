package me.dakbutfly.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by dakbutfly on 2017-01-04.
 */
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEM_NO")
    private Long no;
    @Column(name="MEM_ID", unique = true)
    private String id;
    @Column(name = "MEM_NM")
    private String name;
}
