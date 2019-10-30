package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    private long id;

    private String name;
    private String city;
    private String street;
    private String zipCode;

    @OneToMany(mappedBy = "ORDER")
    @Column(name = "orders")
    private List<Order> orders = new ArrayList<>();
}
