package pl.michalski.PortfolioProject.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(mappedBy = "cart")
    private User user;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "product_cart",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")})
    private List<Product> products;


}
