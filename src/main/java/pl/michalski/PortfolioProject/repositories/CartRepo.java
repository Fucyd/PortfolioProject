package pl.michalski.PortfolioProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalski.PortfolioProject.Entities.Cart;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(String username);
}
