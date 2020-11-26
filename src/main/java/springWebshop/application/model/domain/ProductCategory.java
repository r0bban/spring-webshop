package springWebshop.application.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE,
    mappedBy = "productCategory")
    private Set<ProductSubCategory> subCategories;

    public ProductCategory() {
        this.subCategories = new HashSet<>();
    }

    public ProductCategory(String name) {
        this.subCategories = new HashSet<>();
        this.name = name;
    }

    public void addSubcategory(ProductSubCategory productSubCategory) {
        subCategories.add(productSubCategory);
    }

    public void removeSubcategory(ProductSubCategory productSubCategory) {
        subCategories.remove(productSubCategory);
    }

}
