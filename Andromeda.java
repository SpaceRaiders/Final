import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Andromeda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Andromeda extends ScrollableWorld
{

    /**
     * Constructor for objects of class Andromeda.
     * 
     */
    public Andromeda()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1,2400,1600); 
         
        prepare();
    }
    
    public Andromeda(Pose pose)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1,2400,1600); 
        initRocket(new Rocket(),pose.getX(),pose.getY());
        Inventory inventory = new Inventory();
        addObject(inventory, 949, 300);
        prepare();
    }
    public void prepare()
    {
         initObj(new Asteroid(),300,600);
         initObj(new Portal(Space.class,1300,800),600,600); 
         SpaceLemon spacelemon = new SpaceLemon();
         initObj(spacelemon, 738, 147);
         SpaceLemon spacelemon2 = new SpaceLemon();
         initObj(spacelemon2, 410, 118);
    }
}
