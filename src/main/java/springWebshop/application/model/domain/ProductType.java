package springWebshop.application.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.GeneratorType;


@Data
@Entity
@NoArgsConstructor
/**
 * Product type is the highest level of product segmentation.
 * Such as Clothes, Home appliance, Tools etc.
 */
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    
    String name;

    public ProductType(String name) {
        this.name = name;
    }
}
