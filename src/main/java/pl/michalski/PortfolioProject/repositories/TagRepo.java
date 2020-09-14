package pl.michalski.PortfolioProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michalski.PortfolioProject.Entities.Tag;

import java.util.List;


@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {

}
