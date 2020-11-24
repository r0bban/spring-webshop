package springWebshop.application.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
/**
 * Product category is the second level of product segmentation.
 * Such as Couch, Tables, Chairs etc.
 */
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
}
