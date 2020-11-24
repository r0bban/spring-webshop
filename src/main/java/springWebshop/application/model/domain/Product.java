package springWebshop.application.model.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double basePrice;
    private String title;
    private String description;
    private String fullImageUrl;
    private String thumbnailUrl;
    @ManyToMany(fetch = FetchType.EAGER)//, cascade = CascadeType.PERSIST)
    private Set<ProductType> productTypes;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ProductCategory> productCategories;

    public Product() {
        this.productTypes = new HashSet<>();
        this.productCategories = new HashSet<>();
    }
}
