package com.danylostasenko.hibernatedemo.example2WithEntityGraph.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore // to fix StackOverflow Exception while serializing to JSON response
    private UserDetails userDetails;



//    Item (Address) { << Owning site. But what is the difference?
//    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=false)
//    private Cart cart;

//    Cart (UserDetails) {
//    @OneToMany(mappedBy="cart")
//    private Set<Item> items;

//    It’s a good practice to mark the many-to-one side as the owning side.
//    In other words, Item (Address) would be the owning side and Cart (UserDetails) the inverse side, which is exactly what we did earlier.



// 6.3. Cart as the Owning Side (BAD)
//    class ItemOIO (Address) {
//    @ManyToOne
//    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
//    private CartOIO cart;

//    class CartOIO (UserDetails) {
//    @OneToMany
//    @JoinColumn(name = "cart_id") // we need to duplicate the physical information << Marker 2: BAD
//    private Set<ItemOIO> items;


//    Conclusion:
// GOOD:
//    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=false)
//    +
//    @OneToMany (mappedBy="cart") <<< difference
//    private Set<Item> items;

// BAD:
//    @ManyToOne
//    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
//    +
//    @OneToMany <<< difference
//    @JoinColumn(name = "cart_id") <<< difference


//    In 2 words: @OneToMany + @JoinColumn = BAD.
//    @OneToMany (mappedBy="cart") --> must have mappedBy config


}