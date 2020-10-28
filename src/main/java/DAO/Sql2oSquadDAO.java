package DAO;

import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquadDAO implements SquadDAO {


    private final Sql2o sql2o;

    public Sql2oSquadDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Squad> getAllSquads() {
        String sql = "SELECT * FROM squads";
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM heroes WHERE squadId = :squadId")
//                    .addParameter("squadId", squadId)
                    .executeAndFetch(Squad.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addSquad(Squad squad) {
        String sql = "INSERT INTO squads (squadName, squadCause, squadSize) VALUES (:squadName, :squadCause, :squadSize)";
        try(Connection con= sql2o.open()){
            con.createQuery(sql)
                    .bind(squad)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Squad findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAllSquads() {

    }
}
