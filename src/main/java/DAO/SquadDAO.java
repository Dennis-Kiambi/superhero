package DAO;

import models.Squad;

import java.util.List;

public interface SquadDAO {

    List<Squad> getAllSquads();

    void addSquad(Squad squad);

    Squad findById(int id);

    //UPDATE
    void update(int id, String name);

    //DELETE
    void deleteById(int id);
    void clearAllSquads();
}
