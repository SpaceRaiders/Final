import greenfoot.*;

/**
 * Klasse Shield : Dieses Item erlaubt, eine Rackette durch ein schwarze loch zu gehen.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 18-11-2012
 */
public class Shield extends Item
{
    /**
     * Diese Methode wird immer angerufen, wenn die Taste 'Act' oder 'Run' gedrückt ist.
     * Hier wird auf Mausklicks reagiert und eine Object der Bulet Klasse der Welt hinzugefügt.
     */
    public void act() 
    {
        checkCollision();
    }
    
    /**
     * Diese Methode überprüft, ob das Shield-Objekt eine Kollision mit eine Rakete-Objekt hat.
     */
    private void checkCollision()
    {
        if(isColliding(Rocket.class))
        {
            Rocket rocket = (Rocket) getCollidingObject(Rocket.class);
            // Das Item Shiel wird in der Inventar der Rackette hinzugefügt.
            rocket.addItem(this);
        }
    }
}
