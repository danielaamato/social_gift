package Bussiness;

import java.util.List;

public class GiftInfo {
    private int id;
    private String name;
    private String description;
    private String link;
    private String photo;
    private double price;
    private int isActive;
    private List<Integer> categoryIds;

    public GiftInfo(int id, String name, String description, String link, String photo, double price, int isActive, List<Integer> categoryIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.photo = photo;
        this.price = price;
        this.isActive = isActive;
        this.categoryIds = categoryIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
