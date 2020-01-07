package pl.michalski.PortfolioProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michalski.PortfolioProject.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
