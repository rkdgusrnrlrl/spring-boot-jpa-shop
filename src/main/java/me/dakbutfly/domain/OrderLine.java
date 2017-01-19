package me.dakbutfly.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by khk on 2017-01-16.
 */
@Data
@NoArgsConstructor
@Entity
public class OrderLine {
     @Id @GeneratedValue
     @Column(name = "ORL_ID")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}
