import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList ;
import java.awt.Point ;
/**
 * Das WEltall. Jetzt mit SCrollen . 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Space extends ScrollableWorld
{
    //private ArrayList<greenfoot.Actor> objects = new ArrayList<greenfoot.Actor>();
    private ArrayList<Scrollable> objects = new ArrayList<Scrollable>();
    //private ArrayList<Point> realPos = new ArrayList<Point>();
    private int grenze = 100,width=2400,height=1600,shiftX=0,shiftY=0;
    
    /**
     * Constructor for objects of class Space.
     * 
     */
    public Space()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1,2400,1600); 
        
        Rocket rocket = new Rocket();
        initObj(rocket, getWidth()/2, getHeight()/2);
        prepare();
    }
    public Space(Pose pose)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1,2400,1600); 
        
        Rocket rocket = new Rocket();
        initObj(rocket, pose.getX(), pose.getY());
        System.out.println("rakete soll bei:" +pose.getX()+":"+pose.getY());
        prepare();
    }
    public Space(int lvl)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1,2400,1600); 
        
        Rocket rocket = new Rocket();
        initRocket(rocket, getWidth()/2, getHeight()/2);
        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {

        
        /*Asteroid asteroid = new Asteroid();
        initObj(asteroid, 666, 416);
        Asteroid asteroid2 = new Asteroid();
        initObj(asteroid2, 708, 640);
        Asteroid asteroid3 = new Asteroid();
        initObj(asteroid3, 1055, 636);
        Asteroid asteroid4 = new Asteroid();
        initObj(asteroid4, 904, 493);
        Asteroid asteroid5 = new Asteroid();
        initObj(asteroid5, 1019, 423);*/
        initObj(new Asteroid(),1600, 900);
        SpaceLemon spacelemon = new SpaceLemon();
        initObj(spacelemon, 738, 147);
        SpaceLemon spacelemon2 = new SpaceLemon();
        initObj(spacelemon2, 410, 118);
        Blackhole blackhole = new Blackhole();
        initObj(blackhole, 761, 423);
        Blackhole blackhole2 = new Blackhole();
        initObj(blackhole2, 1285, 255);
        /*
        Shield shield = new Shield();
        initObj(shield, 331, 357);
        Shield shield2 = new Shield();
        initObj(shield2, 454, 1000);
        Shield shield3 = new Shield();
        initObj(shield3, 300, 536);
        Shield shield4 = new Shield();
        initObj(shield4, 2000, 91);
        shield3.setLocation(92, 542);
        shield2.setLocation(989, 595);
        Shield shield5 = new Shield();
        initObj(shield5, 600, 536);
        Shield shield6 = new Shield();
        initObj(shield6, 900, 536);
        Shield shield7 = new Shield();
        initObj(shield7, 1200, 536); 
        */
        Alien alien = new Alien();
        initObj(alien,100,150);
        Portal portal = new Portal(Space.class,700,300);
        initObj(portal, 171, 296);
        Portal portal2 = new Portal(Andromeda.class,200,300);
        initObj(portal2, 1507, 509);
        
        Shield shield = new Shield();
        initObj(shield, 342, 551);
        Shield shield2 = new Shield();
        initObj(shield2, 919, 138);
        /*
        Shield shield3 = new Shield();
        initObj(shield3, 205, 549);
        Shield shield4 = new Shield();
        initObj(shield4, 506, 555);
        Shield shield5 = new Shield();
        initObj(shield5, 633, 564);
        shield5.setLocation(590, 560);
        shield4.setLocation(467, 555);*/
        
        Inventory inventory = new Inventory();
        addObject(inventory, 958, 307);
        inventory.setLocation(950, 300);
    }
}
