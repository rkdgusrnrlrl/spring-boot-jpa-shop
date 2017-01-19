package me.dakbutfly.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by khk on 2017-01-16.
 */
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORD_ID")
    private Long id;
    private int totalPrice;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEM_ID")
    private Member member;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORL_ID")
    private OrderLine orderLine;
    @Column(name = "ORD_ADDRESS")
    private String address;

}
