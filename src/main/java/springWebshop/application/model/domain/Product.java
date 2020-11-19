package springWebshop.application.model.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductType> productTypes;

    public Product() {
        this.productTypes = new ArrayList<>();
    }
}
