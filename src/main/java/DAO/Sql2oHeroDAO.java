package DAO;

import models.Hero;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHeroDAO implements HeroDAO {

    private final Sql2o sql2o;

    public Sql2oHeroDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Hero> getAllHeroes() {
        String sql = "SELECT * FROM heroes";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Hero.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addHero(Hero hero) {
        String sql = "INSERT INTO heroes (heroName, heroPower, heroWeakness, age, squadId) VALUES (:heroName, :heroPower, :heroWeakness, :age, :squadId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(hero)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Hero findByIdHero(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM heroes WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Hero.class); //fetch an individual item
        }

    }

    @Override
    public void updateHero(int id, String heroName, int squadId) {
        String sql = "UPDATE heroes SET heroName = :heroName WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("heroName", heroName)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from heroes WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllHeroes() {
        String sql = "DELETE from heroes";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}