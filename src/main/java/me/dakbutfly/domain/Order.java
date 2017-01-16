package me.dakbutfly.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by khk on 2017-01-16.
 */
@Data @NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORD_ID")
    private Long id;
    private int totalPrice;
    @OneToOne
    @JoinColumn(name = "MEM_ID")
    private Member member;
    @OneToOne
    @JoinColumn(name = "ORL_ID")
    private OrderLine orderLine;
    @Column(name = "ORD_ADDRESS")
    private String address;

}
