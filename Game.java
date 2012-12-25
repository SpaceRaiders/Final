import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.ArrayList;

/**
 * Das Spiel. Erzeugt alle Welten und bietet eine Klassenmethode, 
 * die zu einer Klasse die passende Welt heraussucht. 
 * Speichert außerdem die klassen der zu übertragenden Objekte
 * 
 * @author Thomas Röfer , Yorick Netzer
 * @version 01.11.2012
 */
public class Game extends World
{
    /** Alle Welten. */
    private static ArrayList<World> worlds;
    
    /** Die Klassen der zu übertragenden Objekte */
    private static Class[] transferables;
    
    /**
     * Der Konstruktor fügt alle Objekte der Welt hinzu.
     */
    public Game()
    {    
        super(1000, 600, 1); // Passend zur Größe der angezeigten Welten
        worlds = new ArrayList<World>();
        
        worlds.add(new Space());
        worlds.add(new Andromeda());
        
        transferables = new Class[]{Rocket.class,Inventory.class};
        
        Greenfoot.setWorld(worlds.get(0));
    }
    
    /**
     * Sucht die Instanz einer bestimmten Weltenklasse heraus und liefert diese zurück.
     * Falls sie nicht gefunden wird, ist das Ergebnis null.
     * @param worldClass Die Klasse der Welt, nach deren Instanz gesucht wird.
     */
    public static World getWorld(Class worldClass)
    {
        for (World world : worlds)
        {
            if (world.getClass() == worldClass)
            {
                return world;
            }
        }
        return null;
    }
    public static Class[] gettransferCls()
    {
        return transferables;
    }
}
