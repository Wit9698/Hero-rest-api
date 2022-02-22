package pl.coderslab.hero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.hero.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
