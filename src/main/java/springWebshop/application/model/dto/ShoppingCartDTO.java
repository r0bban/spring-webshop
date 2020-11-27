package springWebshop.application.model.dto;

import springWebshop.application.model.domain.Currency;
import springWebshop.application.model.domain.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCartDTO {
    private Currency currency;
    private Map<ProductDTO, Integer> productMap;

    public ShoppingCartDTO() {
        this.currency = Currency.SEK;
        this.productMap = new LinkedHashMap<>();
    }


    public double getTotalSum(){
        final double[] totalSum = {0.0};
        productMap.forEach((item, quantity) -> {
            totalSum[0] += item.getBasePrice()*quantity;
        });
        return totalSum[0];
    }

    public double getItemSum(ProductDTO item){
        return productMap.get(item)*item.getBasePrice();
    }

}
