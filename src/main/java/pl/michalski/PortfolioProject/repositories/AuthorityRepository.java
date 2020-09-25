package pl.michalski.PortfolioProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalski.PortfolioProject.Entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
