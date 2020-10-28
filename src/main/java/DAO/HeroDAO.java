package DAO;

import models.Hero;

import java.util.List;

public interface HeroDAO {

    List<Hero> getAllHeroes();

    void addHero(Hero hero);

    Hero findByIdHero(int id);

    void updateHero(int id, String heroName, int squadId);

    void deleteById(int id);

    void clearAllHeroes();
}
