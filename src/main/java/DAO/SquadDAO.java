package DAO;

import models.Hero;
import models.Squad;
import java.util.List;

public interface SquadDAO {
    List<Squad> getAllSquads();
    void addSquad(Squad squad);
    Squad getSquadById(int id);
    List<Hero> getSquadsHeroesById(int id);
    void deleteSquadById(int id);
    void updateSquad(int id, String name, String purpose, int number, String group);

}
