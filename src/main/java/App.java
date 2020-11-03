import DAO.Sql2oHeroDAO;
import DAO.Sql2oSquadDAO;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;



public class App {
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
//        String connectionString = "jdbc:postgresql://localhost:5432/heroes"; //connect to heroes, not heroes_test!
//        Sql2o sql2o = new Sql2o(connectionString, "postgres", "Tempott1");       // Connection con;
//        String connectionString = "jdbc:postgresql://ec2-34-231-56-78.compute-1.amazonaws.com:5432/dd4tbspeggkt8u"; //!
//        Sql2o sql2o = new Sql2o(connectionString, "fbxwoczftbcplu", "5bd2f22417e768febf89383a55e82db9ee192aa216e7677998c972de46ef759d"); //!        Sql2o sql2o = new Sql2o(connectionString, "fbxwoczftbcplu", "5bd2f22417e768febf89383a55e82db9ee192aa216e7677998c972de46ef759d"); //!


        String connectionString = "jdbc:h2:~/heroes.db;INIT=RUNSCRIPT from 'classpath:db/createtables.sql'";
       // Connection con;
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "Tempott1");


        Sql2oSquadDAO squadDAO = new Sql2oSquadDAO(sql2o);
        Sql2oHeroDAO heroDAO = new Sql2oHeroDAO(sql2o);
        Map<String, Object> model = new HashMap<>();
        List<String> myStrings = new ArrayList<>();


        get("/", (req, res) -> {
//            Map<String, Object> squad = new HashMap<>();
            model.put("squads", squadDAO.getAllSquads());
//            List<Hero> hero = heroDAO.getAllHeroes();
//            model.put("heroes", heroDAO.getAllHeroes());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/squads", (req, res) -> {
            List<Squad> squad = squadDAO.getAllSquads();
            model.put("squads", squad);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            model.put("heroes", squadDAO.getSquadsHeroesById(id));
            model.put("squad", squadDAO.getSquadById(id));
            return new ModelAndView(model, "squad-information.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes", (req, res) -> {
            List<Hero> hero = heroDAO.getAllHeroes();
            model.put("hero", hero);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Hero hero = heroDAO.getHeroById(id);
            Squad squad = squadDAO.getSquadById(hero.getSquadId());
            model.put("hero", heroDAO.getHeroById(id));
            model.put("squad", squad);
            return new ModelAndView(model, "hero-information.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addsquad", (req, res) -> new ModelAndView(model, "form.hbs"), new HandlebarsTemplateEngine());

        post("/addsquad", (req, res) -> {
            String name = req.queryParams("name");
            String cause = req.queryParams("cause");
            int size = Integer.parseInt(req.queryParams("number"));
            String group = req.queryParams("group");
            Squad newSquad = new Squad(name, cause, size, group);
            squadDAO.addSquad(newSquad);
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addhero", (req, res) -> {
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addhero", (req, res) -> {
            String name = req.queryParams("name");
            String power = req.queryParams("power");
            String weakness = req.queryParams("weakness");
            int age = Integer.parseInt(req.queryParams("age"));
            int squadId = Integer.parseInt(req.queryParams("squad"));
            Hero newHero = new Hero(name, power, weakness, age, squadId);
            heroDAO.addHero(newHero);
            model.put("squads", squadDAO.getAllSquads());
            model.put("heroes", heroDAO.getAllHeroes());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deletehero/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            heroDAO.deleteHeroById(id);
            model.put("heroes", heroDAO.getAllHeroes());
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deletesquad/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            squadDAO.deleteSquadById(id);
            model.put("heroes", heroDAO.getAllHeroes());
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/edithero/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            model.put("editHero", true);
            model.put("hero", heroDAO.getHeroById(id));
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "update-hero.hbs");
        }, new HandlebarsTemplateEngine());

        post("/edithero/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            String name = req.queryParams("name");
            String power= req.queryParams("power");
            String weakness= req.queryParams("weakness");
            int age = Integer.parseInt(req.queryParams("age"));
            int squadId = Integer.parseInt(req.queryParams("squad"));
            heroDAO.updateHero(id, name, power, weakness, age, squadId);
            model.put("hero", heroDAO.getHeroById(id));
            return new ModelAndView(model, "hero-information.hbs");
        }, new HandlebarsTemplateEngine());

        get("/editsquad/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            model.put("editSquad", true);
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "update-squad.hbs");
        }, new HandlebarsTemplateEngine());

        post("/editsquad/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            String name = req.queryParams("name");
            String purpose= req.queryParams("purpose");
            int number = Integer.parseInt(req.queryParams("number"));
            String group= req.queryParams("group");
            squadDAO.updateSquad(id, name, purpose, number, group);
            model.put("squad", squadDAO.getSquadById(id));
            return new ModelAndView(model, "squad-information.hbs");
        }, new HandlebarsTemplateEngine());
    }
}


