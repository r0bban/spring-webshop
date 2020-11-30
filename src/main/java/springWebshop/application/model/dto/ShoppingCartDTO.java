package springWebshop.application.model.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import springWebshop.application.model.domain.Currency;
import springWebshop.application.model.domain.Product;

public class ShoppingCartDTO {
	private Currency currency;
	private Map<Product, Integer> productMap;

	public ShoppingCartDTO() {
		this.currency = Currency.SEK;
		this.productMap = new LinkedHashMap<>();
	}

	public double getTotalSum() {
		final double[] totalSum = { 0.0 };
		productMap.forEach((item, quantity) -> {
			totalSum[0] += item.getBasePrice() * quantity;
		});
		return totalSum[0];
	}

	public double getItemSum(Product item) {
		return productMap.get(item) * item.getBasePrice();
	}

	public void addItem(Product item) {
		int currentAmount = productMap.getOrDefault(item, 0);
		productMap.put(item, ++currentAmount);
	}

	public void removeItem(Product item) {
		int currentAmount = productMap.getOrDefault(item, 0);
		if (currentAmount > 0)
			productMap.put(item, --currentAmount);
		else
			productMap.remove(item);
	}

	@Override
	public String toString() {
//		System.out.println(productMap);
		return productMap
				.entrySet()
				.stream()
				.map(product->product.getKey().getName() + ":" + product.getValue())
				.collect(Collectors.toSet()).toString();
	}

}
