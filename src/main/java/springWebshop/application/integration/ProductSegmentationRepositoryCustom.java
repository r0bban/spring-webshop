package springWebshop.application.integration;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import springWebshop.application.model.domain.Product;


public interface ProductSegmentationRepositoryCustom {
	
	Optional<List<Product>>  findProductsByCategoryId(long categoryId);
	Optional<List<Product>>  findProductsBySubCategoryId(long subCategoryId);
	Optional<List<Product>>  findProductsByTypeId(long typeId);

}
