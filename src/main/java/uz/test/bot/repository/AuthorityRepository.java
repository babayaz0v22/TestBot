package uz.test.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.bot.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
