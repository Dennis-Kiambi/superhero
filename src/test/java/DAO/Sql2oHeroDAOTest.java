//package DAO;
//
////import models.Hero;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class Sql2oHeroDAOTest {
//
//    @Test
//    public void getAllHeroes() {
//    }
//
//    @Test
//    public void addHero() throws Exception{
//        Hero hero = setupNewHero();
//        int originalHeroId = hero.getId();
////        heroDao.add(hero);
//        assertNotEquals(originalHeroId, hero.getId());
//    }
//    private Hero setupNewHero() {
//        return new Hero("", "", "", 0, 0);
//    }
//
//    @Test
//    public void findByIdHero() {
//    }
//
//    @Test
//    public void updateChangesHeroContent() throws Exception {
//        String initialDescription = "Superman";
//        Hero hero = new Hero ("", "", "", 0, 0);// or use the helper method for easier refactoring
//        Hero.add(hero);
//        Hero.update("newContent" );
//        Hero updatedHero = Hero.findById(hero.getId()); //why do I need to refind this?
//        assert updatedHero != null;
//        assertNotEquals(initialDescription, updatedHero.getHeroName());
//    }
//}