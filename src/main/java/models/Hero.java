package models;

import java.util.ArrayList;

public class Hero {

    private int id;
    private String heroName;
    private String heroPower;
    private String heroWeakness;
    private int age;
    private int squadId;

    public Hero(String heroName, String heroPower, String heroWeakness, int age, int squadId) {
        this.heroName = heroName;
        this.heroPower = heroPower;
        this.heroWeakness = heroWeakness;
        this.age = age;
        this.squadId = squadId;
    }

    public static void clearAllHeroes() {    }

    public static Hero findByIdHero(int idOfHeroToDelete) {
        return null;
    }

    public static ArrayList<Hero> getAllHeroes() { return null;  }

    public static Hero findById(int idOfHeroToFind) {  return null;  }

    public static void add(Hero hero) {    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeroName() { return heroName;   }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroPower() {
        return heroPower;
    }

    public void setHeroPower(String heroPower) {
        this.heroPower = heroPower;
    }

    public String getHeroWeakness() {
        return heroWeakness;
    }

    public void setHeroWeakness(String heroWeakness) {
        this.heroWeakness = heroWeakness;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }

    public void deleteById(int id) { this.id = id; }

    public static void update(String newContent) {    }

    public void deleteById() {    }
}
