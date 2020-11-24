package springWebshop.application.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
/**
 * Product type is the highest level of product segmentation.
 * Such as Clothes, Home appliance, Tools etc.
 */
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;

    public ProductType(String name) {
        this.name = name;
    }
}
