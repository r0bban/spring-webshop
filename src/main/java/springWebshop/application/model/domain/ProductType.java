package springWebshop.application.model.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
/**
 * Product type is the highest level of product segmentation.
 * Such as Clothes, Home appliance, Tools etc.
 */
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;

}
