package pl.michalski.PortfolioProject.dto;



import pl.michalski.PortfolioProject.Entities.Tag;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class NewProductDto {

    @NotEmpty(message = "Pole nie może być puste")
    @Size(min = 3, message = "Wpisz minimum 3 znaki")
    private String name;
    @NotNull(message = "Pole nie może być puste")
    private Double price;
    @NotEmpty(message = "Pole nie może być puste")
    private String description;
    private String imageUrl;
//    private List<Tag> tagList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    public List<Tag> getTagList() {
//        return tagList;
//    }
//
//    public void setTagList(List<Tag> tagList) {
//        this.tagList = tagList;
//    }
}
