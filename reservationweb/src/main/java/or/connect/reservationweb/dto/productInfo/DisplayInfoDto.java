package or.connect.reservationweb.dto.productInfo;

import java.time.LocalDateTime;

public class DisplayInfoDto {
	private int productId;
	private int categoryId;
	private int displayInfoId;
	private String categoryName;
	private String productDescription;
	private String productContent;
	private String productEvent;
	private String openingHours;
	private String placeName;
	private String placeLot;
	private String placeStreet;
	private String telephone;
	private String homepage;
	private String email;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductEvent() {
		return productEvent;
	}

	public void setProductEvent(String productEvent) {
		this.productEvent = productEvent;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}
}
