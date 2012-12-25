import greenfoot.*;
import java.util.ArrayList ;

/**
 * Inventar.
 * Objekte von Unterklassen von Item können im Inventar abgelegt werden, bis das Inventar gefüllt ist. 
 * Sie werden dann am rechten Bildschirmrand dargestellt.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 18-11-2012
 */
public class Inventory extends Actor
{
    /**
     * Hier werden die Objekte von Itemunterklassen gespeichert. 
     */
    private ArrayList<Scrollable> items = new ArrayList<Scrollable>();
    
    /**
     * Definiert, wo das Item auf das Bild sich befinden soll.
     */
    private int posItem_y = 55;
    
    /**
     * Definiert, wieviele Items das Inventar enthälten darf.
     */
    final private int ITEM_MAX = 2;
      
    /**
     * Diese Methode ist noch zu machen. Momentan tut sie nichts
     */
    public void storeActor(Actor actor)
    {
        
    }
    
    /**
     * Speichert ein Item in das Inventar, wenn es nicht voll ist.
     */
    public void storeItem(Item item)
    {
        System.out.println("how many items in Inventory? : " + items.size());
        if (items.size() < ITEM_MAX)
        {
            storeScrble(item);
        }
    }
    
    /**
     * Speicehrt ein Scrollable-Object im inventar. 
     */
    public void storeScrble(Scrollable scrble)
    {
        //System.out.println("add new Item : " + scrble);
        items.add(scrble);
        World world = scrble.getWorld();
        scrble.getScrWorld().removeObject(scrble);
        world.addObject(scrble, 955, posItem_y);
        // Das nächste Item wird weiter nach unten gezeigt.
        posItem_y += 100;
    }
    
    /**
     * Entfernt ein Objekt aus dem Inventar und fügt es der eigentlichen Welt hinzu.
     * @param x X-Koordinate wo das Objekt hinzugefügt wird.
     * @param y Y-Koordinate wo das Objekt hinzugefügt wird (wir von 150 incrementiert).
     */
    public void removeScrble(int x, int y)
    {
        get().getWorld().removeObject(get());
        get().getScrWorld().initObj(get(), x, y + 150);
        items.clear();
        posItem_y = 55;
    }
    
    /**
     * Kurze Schreibweise von get(0).
     */
    public Scrollable get()
    {
        return get(0);
    }
    
    /**
     * Gibt das Objekt mit Index i aus dem Inventar. Der Index beginnt mit 0.
     * Wenn kein Objekt im Inentar ist, wird null zurückgegeben.
     * 
     * @param i Index des Objektes im Inventar.
     */
    public Scrollable get(int i)
    {
        if (i < 0 || i >= items.size())
        {
            return null;
        }
        else
        {
            return items.get(i);
        }
    }
    
    /**
     * Diese Funktion gibt zurück, ob das Inventar leer oder nicht ist.
     */
    public boolean isEmpty()
    {
        if(items.size()==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
