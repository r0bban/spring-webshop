package springWebshop.application.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
