package hellojpa.domain;

import hellojpa.BaseEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long deliveryId;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
