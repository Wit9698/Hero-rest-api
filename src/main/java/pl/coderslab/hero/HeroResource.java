package pl.coderslab.hero;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.hero.model.Hero;
import pl.coderslab.hero.repository.HeroRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class HeroResource {
    
    private final HeroRepository heroRepository;

    @GetMapping("/chuck-fact")
    public String chuckFact(){
        Faker faker = new Faker();
        return faker.chuckNorris().fact();
    }
    @GetMapping("/hero/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable Long id){
        Optional<Hero> hero = heroRepository.findById(id);
        return hero.map(h -> new ResponseEntity<>(h, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/heroes")
    public ResponseEntity<List<Hero>> getAllHeroes(Pageable pageable ){
        Page<Hero> heroes = heroRepository.findAll(pageable);
        return new ResponseEntity<>(heroes.getContent(), HttpStatus.OK);
    }
}
