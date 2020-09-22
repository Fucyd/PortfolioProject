package pl.michalski.PortfolioProject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michalski.PortfolioProject.Entities.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String productName);
    List<Product> findAllByCategory(String category);
}
