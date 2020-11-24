package springWebshop.application.model.dto;

public class ProductFormModel {

	private long id;
	
	private double basePrice;
	
	private String name;
	
	private String description;
	
	private String fullImageUrl;
	
	private String thumbnailUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullImageUrl() {
		return fullImageUrl;
	}

	public void setFullImageUrl(String fullImageUrl) {
		this.fullImageUrl = fullImageUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "ProductFormModel [id=" + id + ", basePrice=" + basePrice + ", name=" + name + ", description="
				+ description + "]";
	}
	
	
	
}
