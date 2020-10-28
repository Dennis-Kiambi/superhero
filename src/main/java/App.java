import DAO.Sql2oHeroDAO;
import DAO.Sql2oSquadDAO;
import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/todolist"; //connect to todolist, not todolist_test!
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "Tempott1");        Connection con;
        Sql2oSquadDAO squadDAO = new Sql2oSquadDAO(sql2o);
        Sql2oHeroDAO heroDAO = new Sql2oHeroDAO(sql2o);
        Map<String, Object> model = new HashMap<>();

        List<String> myStrings = new ArrayList<>();


        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            model.put("squads", squadDAO.getAllSquads());
            List<Hero> hero = heroDAO.getAllHeroes();
            model.put("heroes", heroDAO.getAllHeroes());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addsquad", (req, res) -> {
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addsquad", (req, res) -> {
            String name = req.queryParams("name");
            String cause = req.queryParams("cause");
            int size = Integer.parseInt(req.queryParams("size"));
            Squad newSquad = new Squad(name, cause, size);
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





        //get: delete all heroes
        get("/heroes/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            Hero.clearAllHeroes(); //change
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete an individual hero
        get("/heroes/:id/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("id"));
            Hero deleteHero = Hero.findByIdHero(idOfHeroToDelete); //change
            assert deleteHero != null;
            deleteHero.deleteById();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show all heroes
        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes = Hero.getAllHeroes(); //change
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new hero form
        get("/heroes/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //hero: process new hero form
        post("/heroes", (req, res) -> { //URL to make new hero on POST route
//            Map<String, Object> model = new HashMap<>();
            String description = req.queryParams("description");
            Hero newHero = new Hero("heroName", "heroPower", "heroWeakness", 0, 0); //change
//            HeroDao.add(newHero);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual hero
        get("/heroes/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params("id"));
            Hero foundHero = Hero.findById(idOfHeroToFind); //change
            model.put("hero", foundHero);
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a hero
        get("/heroes/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit); //change
            model.put("editHero", editHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //hero: process a form to update a hero
        post("/heroes/:id", (req, res) -> { //URL to update hero on POST route
//            Map<String, Object> model = new HashMap<>();
            String newContent = req.queryParams("description");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit); //change
            editHero.update(newContent); //change
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //show new squad form
        get("/squads/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll(); //refresh list of links for navbar
            model.put("squads", squads);
            return new ModelAndView(model, "squad-form.hbs"); //new
        }, new HandlebarsTemplateEngine());


//post: process new squad form
        post("/squads", (req, res) -> { //new
//            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Squad newSquad = new Squad(squad);
            squadDao.add(newSquad);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual squad and heroes it contains
        get("/squads/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            int idOfSquadsToFind = Integer.parseInt(req.params("id")); //new
            Squads foundSquads = squadDao.findById(idOfSquadsToFind);
            model.put("squad", foundSquads);
            List<Hero> allHeroesBySquads = squadDao.getAllHeroesBySquads(idOfSquadsToFind);
            model.put("heroes", allHeroesBySquads);
            model.put("squads", squadDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "squad-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //post: process new hero form
        post("/heroes", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads", allSquads);
            String description = req.queryParams("description");
            int squadId = Integer.parseInt(req.queryParams("squadId"));
            Hero newHero = new Hero("", "", "", 0 , 0); //ignore the hardcoded squadId
            heroDao.add(newHero);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a squad
        get("/heroes/:id/edit", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            model.put("editSquad", true);
            Squad squad = squadDao.findById(Integer.parseInt(req.params("id")));
            model.put("squad", squad);
            model.put("heroes", squadDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());


//post: process a form to update a squad
        post("/heroes/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("newSquadName");
            squadDao.update(idOfSquadToEdit, newName);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a hero
        get("/heroes/:id/edit", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("heroes", allSquads);
            Hero hero = heroDao.findById(Integer.parseInt(req.params("id")));
            model.put("hero", hero);
            model.put("editHero", true);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

//post: process a form to update a hero
        post("/heroes/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            int heroToEditId = Integer.parseInt(req.params("id"));
            String newContent = req.queryParams("description");
            int newSquadId = Integer.parseInt(req.queryParams("squadId"));
            heroDao.update(heroToEditId, newContent, newSquadId);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes and all heroes
        get("/heroes/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            squadDao.clearAllHeroes();
            heroDao.clearAllHeroes();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


//get: delete all heroes
        get("/heroes/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
            heroDao.clearAllHeroes();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        
    }

    }

