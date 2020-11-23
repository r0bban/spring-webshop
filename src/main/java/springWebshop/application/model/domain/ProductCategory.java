package springWebshop.application.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
}
