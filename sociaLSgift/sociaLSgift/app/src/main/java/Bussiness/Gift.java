package Bussiness;

public class Gift {
    private int id;
    private int wishlist_id;
    private String url;
    private int priority;
    private int booked;
    public Gift(int id, int wishlist_id, String url, int priority, int booked) {
        this.id = id;
        this.wishlist_id = wishlist_id;
        this.url = url;
        this.priority = priority;
        this.booked = booked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Integer getBooked() {
        return booked;
    }

    public void setBooked(Integer booked) {
        this.booked = booked;
    }
}
