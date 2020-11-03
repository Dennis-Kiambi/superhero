package DAO;

import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquadDAO  implements  SquadDAO{
    private final Sql2o sql2o;

    public Sql2oSquadDAO(Sql2o sql2o) {

        this.sql2o = sql2o;
    }

    @Override
    public List<Squad> getAllSquads() {
        String sql = "SELECT * FROM squads";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Squad.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addSquad(Squad squad) {
        String sql = "INSERT INTO squads (squadName, squadPurpose, squadNumber, squadGroup) VALUES (:squadName, :squadPurpose, :squadNumber, :squadGroup)";
        try (Connection con = sql2o.open()){
            con.createQuery(sql, false)
                    .bind(squad)
                    .executeUpdate();
            int id = (int) con.createQuery(sql, true)
                    .bind(squad)
                    .executeUpdate()
                    .getKey();
            squad.setId(id);
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Squad getSquadById(int id) {
        String sql = "SELECT * FROM squads WHERE id=:id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Squad.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public List<Hero> getSquadsHeroesById(int id) {
        String sql = "SELECT * FROM heroes WHERE squadId=:id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Hero.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void deleteSquadById(int id) {
        String sql = "DELETE FROM squads WHERE id=:id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void updateSquad(int id, String name, String purpose, int number, String group) {
      String sql = "UPDATE squads SET squadName = :name, squadPurpose = :purpose, squadNumber = :number, squadGroup = :group WHERE id=:id";
      try (Connection con = sql2o.open()){
          con.createQuery(sql)
                  .addParameter("id", id)
                  .addParameter("name", name)
                  .addParameter("purpose", purpose)
                  .addParameter("number", number)
                  .addParameter("group", group)
                  .executeUpdate();
      }catch (Sql2oException ex){
          System.out.println(ex);
      }
    }
}
