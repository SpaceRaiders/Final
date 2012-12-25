import greenfoot.*;
import java.util.*;
import java.awt.Point;

/**
 * Klasse Portal : Dieses Item erlaubt, eine Rackette die Welt zu wechseln.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 16-11-2012
 */
public class Portal extends Scrollable
{
    /**
     * Dieser Attribut speichert die Welt, wo das Portal führt.
     */
    private Class nextLvl = Andromeda.class;

    private int x,y;

    /**
     * Konstruktor für Objekt Portalb
     * @parameter next : die Welt wo der Portal
     */
    public Portal(Class next, int x, int y)
    {
        nextLvl = next;
        this.x = x;
        this.y = y;
    }

    /**
     * Diese Methode wird immer angerufen, wenn die Taste 'Act' oder 'Run' gedrückt ist.
     * Hier wird auf Mausklicks reagiert und eine Object der Bulet Klasse der Welt hinzugefügt.
     */
    public void act() 
    {
        Rocket rocket = (Rocket)getCollidingObject(Rocket.class);
        if(isColliding(Rocket.class)== true )
        {
            //System.out.println(rocket.getPose());
            //Greenfoot.setWorld(new MainMenu(nextLvl,rocket.getPose()));
            switchWorld();
        }
    }    

    /**
     * Verzweigt zu einer anderen Welt und nimmt den alle Objekte von Klassen mit, die man durch Game.gettransferCLs()
     * erhält. Die Position der Objekte wird daabei beibehalten, nur der Roboter bekommt eine speziell ihm zugewiesene
     * Position.
     */
    private void switchWorld()
    {
        ScrollableWorld oldWorld = (ScrollableWorld) getWorld();
        ScrollableWorld newWorld = (ScrollableWorld) Game.getWorld(nextLvl);

        List<Actor> actors = getWorld().getObjects(null);//hier sind alle Objekte aus der aktuellen welt drin
        ArrayList<Actor> objects = new ArrayList<Actor>();//hier sollen nur die Objekte rein, die in die andere Welt sollen
        ArrayList<Point> points = new ArrayList<Point>();//hier sollen die Koordinaten der Objekte fr die andere Welt rein
        //ArrayList<Class> classes =  new ArrayList<Class>();
        Class [] classes = Game.gettransferCls(); // Hier sind die Klassen der Objetke die kopiert werden sollen drin

        // zu übertragende Objekte aus der alten Welt bestimmen und koordinaten Speichern
        for(int i=0; i< actors.size();i++){
            for(int j=0;j< classes.length;j++){//hier konnte ich nicht auf classes.lenght() zugreifen
                if(actors.get(i).getClass().getName().equals(classes[j].getName()))//hier sollte eigentlich actors.get(i) instanceof classe[j] stehen
                {
                    objects.add(actors.get(i));
                    points.add(new Point(actors.get(i).getX(),actors.get(i).getY()));
                }
            }
        }

        // Objekte aus der alten Welt entfernen und in der neuen einfügen
        for(int i=0; i < objects.size(); i++)
        {
            if(objects.get(i) instanceof Inventory)
            {
                oldWorld.removeObject(objects.get(i));

                newWorld.addObject(objects.get(i),(int) points.get(i).getX(),(int)points.get(i).getY());

            }
            else 
            {
                oldWorld.removeObject((Scrollable)objects.get(i));
                if(objects.get(i) instanceof Rocket)
                {
                    newWorld.initObj((Scrollable) objects.get(i),x,y);
                    System.out.println("pre:  "+getScrWorld().getShiftX()+":"+getScrWorld().getShiftY()+"\n"+(Rocket)objects.get(i));
                }
                else
                {
                    newWorld.initObj((Scrollable)objects.get(i),(int) points.get(i).getX(),(int)points.get(i).getY());
                }
            }

        }
        System.out.println("post: "+getScrWorld().getShiftX()+":"+getScrWorld().getShiftY()+"\n"+newWorld.getRocket());

        // Die Welt wechseln
        Greenfoot.setWorld(newWorld);
    }
}
