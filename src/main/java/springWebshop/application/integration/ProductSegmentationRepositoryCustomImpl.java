package springWebshop.application.integration;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import springWebshop.application.model.domain.Product;
import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.domain.ProductSubCategory;
import springWebshop.application.model.domain.ProductType;

public class ProductSegmentationRepositoryCustomImpl implements ProductSegmentationRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public Optional<List<Product>> findProductsByCategoryId(long categoryId) {
		
		
		 try {
	            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
	            Root<Product> product = criteriaQuery.from(Product.class);
//	            product.fetch("productType", JoinType.INNER);
	            Join<Product, ProductCategory> categories = product.join("productType").join("productSubCategory").join("productCategory");
	            
	            criteriaQuery.select(product)
	            		.where(criteriaBuilder.equal(categories.get("id"), categoryId))
	                    .distinct(true);
	            TypedQuery<Product> typedQuery = em.createQuery(criteriaQuery);
	            
	            
	            return Optional.of(typedQuery.getResultList());
	        } catch (NoResultException e) {
	            return Optional.empty();
	        }
	}


	@Override
	public Optional<List<Product>> findProductsBySubCategoryId(long subCategoryId) {
		 try {
	            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
	            Root<Product> product = criteriaQuery.from(Product.class);
//	            product.fetch("productType", JoinType.INNER);
	            Join<Product, ProductSubCategory> subCategories = product.join("productType").join("productSubCategory");
	            
	            criteriaQuery.select(product)
	            		.where(criteriaBuilder.equal(subCategories.get("id"), subCategoryId))
	                    .distinct(true);
	            TypedQuery<Product> typedQuery = em.createQuery(criteriaQuery);
	            
	            
	            return Optional.of(typedQuery.getResultList());
	        } catch (NoResultException e) {
	            return Optional.empty();
	        }
	}
//
//
	@Override
	public Optional<List<Product>> findProductsByTypeId(long typeId) {
		 try {
	            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
	            Root<Product> product = criteriaQuery.from(Product.class);
//	            product.fetch("productType", JoinType.INNER);
	            Join<Product, ProductType> types = product.join("productType");
	            
	            criteriaQuery.select(product)
	            		.where(criteriaBuilder.equal(types.get("id"), typeId))
	                    .distinct(true);
	            TypedQuery<Product> typedQuery = em.createQuery(criteriaQuery);
	            
	            
	            return Optional.of(typedQuery.getResultList());
	        } catch (NoResultException e) {
	            return Optional.empty();
	        }
	}

}
