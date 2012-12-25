import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList ;

/**
 * Die von Spieler gesteuerte Rakete. Objekte dieser Klasse können sich mit der Maus bewegen lassen und 
 * Kugeln schießen in der Richtung eines linkes Mauses Klick. Das Ziehl eine Rackete ist Kosmonauten zu
 * retten; das passiert wenn die Rackette eine Kollision mit ein Astronaut hat. Die Rackette kann von
 * Asteroiden vernichtet werden falls sie zu viel mals von eine berührt wird; deshalb muss man die
 * Asteroiden abschießen.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 04-11-2012
 */
public class Rocket extends Scrollable
{
     /**
     * Definiert die Mausinfos, wie z.B. wo man Klick, welchte Maustaste gedrückt wird usw.
     */
    private MouseInfo mouse= null;

    /**
     * Definiert Wie heil die Rackette ist.
     */ 
    private int hp = 10;
    
    /**
     * Definiert wie viele Kollision die Rackete mit Asteroiden hatte.
     */
    private int collisions = 0;
    
    /**
     * Definiert die Geschwindigkeit der Rackete
     */
    private int speed = 1;
    
    /**
     * 
     */
    private GreenfootImage img = null;
    
    /**
     * Enthält alle Sounds, die abgespielen werden sollen wenn die Rackette eine Asteroid
     * berührt
     */
    private ArrayList<GreenfootSound> sounds = new ArrayList<GreenfootSound>();
    
    /**
     * Speichert die Position falls die Rakete auf ein Hindernis trifft
     */
    private Pose pose;
    
    /**
     * Objekt, das alle gesamellte items speichert.
     */
    private Inventory inventory;
    
    /**
     * Definiert, ob man gerade auf eine Num Taste drückt, um ein Item zu nehmen
     * Dieser Attribut hilft, ein Item nur einmal benutzt zu werden (erstes druckt
     * auf eine Taste) und nicht so lange die Taste gedrückt wird.
     */
    private boolean takingItem = false;
    
    /**
     * 
     */
    private int itemBeingUsed = 1;
    /**
     * Speichert ob man schon etwas abgeworfen hat
     */
    private boolean dropped = false;
    /**
     * Sound wenn etwas abegeworfen wird
     */
    private GreenfootSound drop = new GreenfootSound("biglaser.wav");
    
    /**
     * Ringbuffer für Koordiaten und drehung
     */
    private RingBuffer ringX , ringY , ringRot;
    
    /**
     * Diese Konstante enthält die Grösse der 3 Ringpuffers von der Rackette.
     */
    private final int CAPACITY_RINGBUFFER = 1000;
    Alien alien = new Alien();

    
    public Rocket()
    {
        //init(getX(),getY());
        super(false);
        makePlaylist("bomb-1.wav,bomb-2.mp3,");
        pose = new Pose(this);
        inventory = new Inventory();
        
        ringX =  new RingBuffer(CAPACITY_RINGBUFFER);
        ringY =  new RingBuffer(CAPACITY_RINGBUFFER);
        ringRot =  new RingBuffer(CAPACITY_RINGBUFFER);
    }

    /**
     * Konstruktor. Herstellt ein Objekt mit eine benutzergewünschte Playliste.
     */
    public Rocket(String playlist)
    {
        super(false);
        makePlaylist(playlist);
        pose = new Pose(this);
        inventory = new Inventory();
        
        ringX =  new RingBuffer(CAPACITY_RINGBUFFER);
        ringY =  new RingBuffer(CAPACITY_RINGBUFFER);
        ringRot =  new RingBuffer(CAPACITY_RINGBUFFER);
    }
    /**
     * Diese Methode herstellt eine Playliste für ein Objekt "Rocket"
     * 
     * @param playlist : ein String mit Dateiennahme, die mit "," getrennen sind. Dabei muss playlist auch mit einem "," enden.
     */
    public void makePlaylist(String playlist)
    {
        int i = 0,j = 0;
        while(i < playlist.length())
        {
            j = playlist.indexOf(",", i+1);
            sounds.add(new GreenfootSound(playlist.substring(i, j)));
            i = j + 1;
        }
    }
    
    /**
     * Diese Methode wird immer angerufen, wenn die Taste 'Act' oder 'Run' gedrückt ist.
     * Hier wird auf Mausklicks reagiert und eine Object der Bulet Klasse der Welt hinzugefügt.
     */
    public void act() 
    {
       pose.update();
       

       //System.out.println(getPose()+" getpose");
       //System.out.println(pose+"  pose");

       mouse = Greenfoot.getMouseInfo();
        
       moveAndTurn();
       //System.out.println(getX()+":"+ getRealX()+"        "+getY()+":"+getRealY());
        
        if(Greenfoot.mousePressed(null))
        {
           //turnTowards(mouse.getX(),mouse.getY()); 
           Greenfoot.playSound("biglaser.wav");
           //space.initObj(new Bullet(getX(),getY(),mouse.getX(),mouse.getY()),getRealX(),getRealY());
           space.initObj(new Bullet(getX(),getY(),mouse.getX(),mouse.getY()),getX(),getY());
           //System.out.println(getRealX()+":"+getRealY()+"     "+getX()+":"+getY());
        }
        if(!Greenfoot.isKeyDown("r"))
        {
          ringX.push(getRealX());
          //System.out.println("push "+ getX() + " real : " + getRealX());
          ringY.push(getRealY());
          ringRot.push(getRotation());
        }
        else if(!ringX.isEmpty())
        {
            setLocation(ringX.pop() + getScrWorld().getShiftX(), ringY.pop() + getScrWorld().getShiftY());
            setRotation(ringRot.pop());
        }
    }
    
    /**
     * Dem Raumschiff 10 Schaden zufügen. siehe auch receiveDamege(int damege)
     */
    public  void receiveDamage()
    {
        receiveDamage(2);
    }
    
    /**
     * Der Rakete damage Schaden zufügen. Wenn die HP der Rakete alle sind, passiert irgendwas.
     */
    public void receiveDamage(int damage)
    {
        hp = hp - damage;
        if(!sounds.get(collisions%sounds.size()).isPlaying())
        {
            sounds.get(collisions%sounds.size()).play();
        }
        if(hp <= 0)
        {
            System.out.println("Rackete kaputt. Das Spiel ist aus.");
            Greenfoot.stop();
        }
    }
    
    /**
     * Steuerung des Raumschiffs durch den Spieler und allg. Bewegung.
     * Steuerung via Pfeiltasten oder WASD.
     */
    private void moveAndTurn()
    {
            move(speed);
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
        {
            turn(-3);
        }
        else if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
        {
            turn(3);
        }
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
        {
            move(3);
            speed = 1;
        }
        else if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))
        {
            move(-3);
            speed = -1;
        }
        
        
        if(Greenfoot.isKeyDown("space"))
        {
            if(!inventory.isEmpty()&&!dropped)
            {
                inventory.removeScrble(getX()+10,getY()+10);
                dropped = true;
            }
            else if(!drop.isPlaying())
            {
                Greenfoot.playSound("biglaser.wav");
                dropped = false;
            }
        }
        if(isColliding(Obstacle.class) && !(inventory.get(itemBeingUsed -1) instanceof Shield))//&& Greenfoot.isKeyDown("space"))
        {
            //System.out.println("pose rest"+getOneIntersectingObject(Obstacle.class));
            pose.resetActor();
        }
        
    }

    /**
     * Fügt ein Objekt in das Rackettes Inventar.
     */
    public void addItem(Item new_item)
    {
        inventory.storeItem(new_item);
    }
    
    /**
     * Getter für Pose
     */
    public Pose getPose()
    {
        return new Pose(getRealX(), getRealY(), getRotation());
    }
    /**
     * Getter für Pose
     */
    public String toString()
    {
        return getPose() + "";
    }
}
