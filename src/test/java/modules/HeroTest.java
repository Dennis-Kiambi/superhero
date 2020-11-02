package modules;

import junit.framework.TestCase;
import models.Hero;
import org.junit.Assert;
import org.junit.Test;

public class HeroTest {
    private Hero hero = new Hero("Dude", "invisibility", "darkness", 2, 1);

    @Test
    public void testGetHeroName() {
       Assert.assertEquals("Dude", hero.getHeroName());
    }

    @Test
    public void testSetHeroName() {
        Assert.assertEquals("Dude", hero.getHeroName());
    }

    @Test
    public void testGetHeroPower() {
        Assert.assertEquals("invisibility", hero.getHeroPower());
    }

    @Test
    public void testSetHeroPower() {
        Assert.assertEquals("invisibility", hero.getHeroPower());
    }

    @Test
    public void testGetHeroWeakness() {
        Assert.assertEquals("darkness", hero.getHeroWeakness());
    }

    @Test
    public void testSetHeroWeakness() {
        Assert.assertEquals("darkness", hero.getHeroWeakness());
    }

    @Test
    public void testGetHeroAge() {
        Assert.assertEquals(2, hero.getHeroAge());
    }

    @Test
    public void testSetHeroAge() {
        Assert.assertEquals(2, hero.getHeroAge());
    }

    @Test
    public void testGetSquadId() {
        Assert.assertEquals(1, hero.getSquadId());
    }

    @Test
    public void testSetSquadId() {
        Assert.assertEquals(1, hero.getSquadId());
    }
}