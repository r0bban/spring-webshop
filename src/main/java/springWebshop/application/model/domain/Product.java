package springWebshop.application.model.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "product")
    private List<ProductType> productTypes;
    @OneToMany(mappedBy = "product")
    private List<ProductCategory> productCategories = new ArrayList<>();



}
