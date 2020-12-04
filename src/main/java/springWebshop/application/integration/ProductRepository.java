package springWebshop.application.integration;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import springWebshop.application.model.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductSegmentationRepositoryCustom {

    //    List <Product> findByProductTypes_Name(final String typeName);
    List<Product> findByName(String name);
    
    default Optional<List<Product>>  filterProductsByCategoryId(long categoryId){
    	return findProductsByCategoryId(categoryId);
    }
    default Optional<List<Product>>  filterProductsBySubCategoryId(long subCategoryId){
    	return findProductsBySubCategoryId(subCategoryId);
    }
    default Optional<List<Product>>  filterProductsByTypeId(long typeId){
    	return findProductsByTypeId(typeId);
    }
}
