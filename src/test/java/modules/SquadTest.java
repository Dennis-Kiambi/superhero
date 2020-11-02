package modules;

import junit.framework.TestCase;
import models.Squad;
import org.junit.Assert;
import org.junit.Test;

public class SquadTest {

    Squad group = new Squad("avengers", "end bullying", 5, "boys club");

    @Test
    public void testGetSquadGroup() {
        Assert.assertEquals("boys club", group.getSquadGroup());
    }

    @Test
    public void testSetSquadGroup() {
        Assert.assertEquals("boys club", group.getSquadGroup());
    }

    @Test
    public void testGetSquadName() {
        Assert.assertEquals("avengers", group.getSquadName());
    }

    @Test
    public void testSetSquadName() {
        Assert.assertEquals("avengers", group.getSquadName());
    }

    @Test
    public void testGetSquadNumber() {
        Assert.assertEquals(5, group.getSquadNumber());
    }

    @Test
    public void testSetSquadNumber() {
        Assert.assertEquals(5, group.getSquadNumber());
    }

    @Test
    public void testGetSquadPurpose() {
        Assert.assertEquals("end bullying", group.getSquadPurpose());
    }

    @Test
    public void testSetSquadPurpose() {
        Assert.assertEquals("end bullying", group.getSquadPurpose());
    }
}