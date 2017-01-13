package me.dakbutfly.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by rkdgusrnrlrl on 17. 1. 13.
 */
@Data @NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private Integer price;
}
