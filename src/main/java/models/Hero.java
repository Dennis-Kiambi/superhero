package models;

public class Hero {
    private String heroName;
    private String heroPower;
    private String heroWeakness;
    private int heroAge;
    private int squadId;
    private int id;

    public Hero(String heroName, String heroPower, String heroWeakness, int heroAge, int squadId) {
        this.heroName = heroName;
        this.heroPower = heroPower;
        this.heroWeakness = heroWeakness;
        this.heroAge = heroAge;
        this.squadId = squadId;
    }

    public String getHeroName() {
        return heroName;
    }

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

    public int getHeroAge() {
        return heroAge;
    }

    public void setHeroAge(int heroAge) {
        this.heroAge = heroAge;
    }

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

