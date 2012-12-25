import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Testklasse RingBufferTest.
 *
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 27-11-2012
 */
public class RingBufferTest
{
    private RingBuffer ring;
    int laenge;
    
    /**
     * Default constructor for test class RingBufferTest
     */
    public RingBufferTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        laenge = 6;
        ring = new RingBuffer(laenge);
    }
    /**
     * Test1 
     * Die zu Beginn sollten keine Elemente in dem gerade erzeugten Ringbuffer drin sein
     */
    @Test
    public void test1()
    {
        assertEquals(0,ring.size());
    }
    /**
     * Test2 
     * Fügt ein Element dem Ringbuffer hinzu und überprüft dann die Größe des Ringbuffers. Diese sollte nun 1.
     */
    @Test
    public void test2()
    {
        ring.push(1);
        assertEquals(1,ring.size());
    }
    /**
     * Test3
     * Fügt ein neues Element dem Ringbuffer hinzu. 
     * Da der Ringbuffer gerade erzuegt wurde ist dieses zugleich auch das älteste Ele  ment und wird mittels ring.pop() entnommen.
     */
    @Test
    public void test3()
    {
        ring.push(2);
        assertEquals(2,ring.pop());
    }
    /**
     * Test4 
     * Füllt den Ringbuffer mit Zahlen in aufsteigender Reihenfolge.
     * Danach wird einmal die toString Methode bei der Ausgabe in der Konsole getestet ( nicht automatisiert)
     * und es wird geschaut ob das älteste Element 0 ist ( da die 0 als erstes hinzugefügt wurde).
     */
    @Test
    public void test4()
    {
        //ring= new RingBuffer(laenge);
        for(int i=0;i<laenge;i++){
            ring.push(i);
        }
        System.out.println(ring);
        assertEquals(0,ring.peek());
        
    }
    /**
     * Test5 
     * Füllt den Ringbuffer mit Zahlen in aufsteigender Reihenfolge und fügt danach noch "11" und "12" hinzu.
     * Der Ringbuffer sollte jetzt immernoch die länge laenge haben. 
     */
    @Test
    public void test5()
    {
        for(int i=0;i<laenge;i++){
            ring.push(i);
        }
        ring.push(11);
        ring.push(12);
        //System.out.println(ring);
        assertEquals(laenge,ring.size());
    }
    /**
     * Test 6
     * Diesmal wird ein Ringbuffer mit der Länge 25 verwendet.
     * Diesem Ringbuffer werden nacheinander alle Zahlen von 0 bis 99 hinzugefügt.
     * Hier sollte der Ringbuffer "überlaufen" und nur noch die letzten 25 Elemente enthalten.
     * Dies wird in der zweiten for-Schleife auch überprüft, indem bei jedem Schleifendurchlauf geschaut wird, ob die Länge 
     * des Ringbuffers mit jedem Schleifendurchlauf abnimmt ( man nutzt bei der 2ten Anweisung in der Schleife pop(), wodurch 
     * das Element aus der Schleife entfernt werden sollte) und überprüft ob tatsächlich die 75 bis einschließlich 99 enthält.
     * 
     * Zusätzlich wird zwischen den beiden Schleifen die toString() Methode getestet. Außerdem kann man so schauen ob tatsächlich 
     * die Elemente im Ringbuffer sind, die da auch sein sollen.
     */
    @Test
    public void test6()
    {
        ring = new RingBuffer(25);
        for(int i = 0; i<100;i++)
        {
            ring.push(i);
        }
        
        System.out.println(ring);
        for(int i=25;i>0;i--)
        {
            assertEquals(i,ring.size());
            assertEquals(100-i,ring.pop());
            
        }
        //System.out.println(ring.pop());
        /*
         * Hier gibs n Problem, weil der Wert noch da ist bei ring . pop , aber das kann man theoretisch ignorieren 
         * oder man baut bei pop und peek ein if ein das da wegen der länge aufpasst
         */
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
