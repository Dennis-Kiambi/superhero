package DAO;

import models.Hero;

import java.util.List;

public interface HeroDAO {
    List<Hero> getAllHeroes();

    void addHero(Hero hero);

    Hero getHeroById(int id);

    void deleteHeroById(int id);

    void updateHero(int id, String name, String power, String weakness, int age, int squadId);
}
