package tech.getarrays.employeemanager.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private double price;
    private List<String> tags;
    private boolean favorite;
    private double stars;
    private String imageUrl;
    private List<String> origins;
    private String cookTime;

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", favorite=" + favorite +
                ", stars=" + stars +
                ", imageUrl='" + imageUrl + '\'' +
                ", origins=" + origins +
                ", cookTime='" + cookTime + '\'' +
                '}';
    }
}
