package DAO;

import models.Hero;
import models.Squad;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.*;

public class Sql2oSquadDAOTest {

    private Hero heroDao;

    @Test
    public void getAllHeroesBySquadReturnsHeroesCorrectly(Queue<Squad> squadDao) throws Exception {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        Hero newHero = new Hero("heroName", "heroPower", "heroWeakness", 0, 0);
        Hero otherHero = new Hero("heroName", "heroPower", "heroWeakness", 0, 0);
        Hero thirdHero = new Hero("heroName", "heroPower", "heroWeakness", 0, 0);
        heroDao.add(newHero);
        heroDao.add(otherHero); //we are not adding hero 3 so we can test things precisely.
//        assertEquals(2, squadDao.getAllHeroesBySquad(squadId));
//        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(newHero));
//        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(otherHero));
//        assertFalse(squadDao.getAllHeroesBySquad(squadId).contains(thirdHero)); //things are accurate!
    }

    private Squad setupNewSquad() {

        return null;
    }


    @Test
    public void addSquad() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAllSquads() {
    }

}