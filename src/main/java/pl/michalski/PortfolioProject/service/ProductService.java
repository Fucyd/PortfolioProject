package pl.michalski.PortfolioProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michalski.PortfolioProject.Entities.Product;
import pl.michalski.PortfolioProject.dto.ShowProductDto;
import pl.michalski.PortfolioProject.repositories.ProductRepo;
import pl.michalski.PortfolioProject.dto.NewProductDto;


import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getOneProduct(String productName){
        return productRepo.findByName(productName);
    }

    public void addNewProduct(NewProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
//        product.setTagList(productDto.getTagList());
        product.setImageUrl(productDto.getImageUrl());
        productRepo.save(product);
    }

    public List<Product> findProductsByCategory(String category){
        return productRepo.findAllByCategory(category);

    }

    public void deleteProductById(Long id){
        productRepo.deleteById(id);
    }

}
