package nz.ac.aut.ense701.gamemodel;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 * The test class PositionTest.
 *
 * @author AS
 * @version 2011
 */
public class PositionTest extends junit.framework.TestCase
{
    private Position onIsland;
    private Position notOnIsland;
    private Island island;
    /**
     * Default constructor for test class PositionTest
     */
    public PositionTest()
    {
        //Default constructor for the test class
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp()
    {
        island = new Island(5,5);
        onIsland = new Position(island, 1,2) ;
        notOnIsland = Position.NOT_ON_ISLAND;
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        island = null;
        onIsland = null;
        notOnIsland = null;       
    }

    @Test
    public void testPositionValidParametersOnIsland()
    {
        assertTrue(onIsland.isOnIsland());
    }

    @Test
    public void testPositionValidParameterNotOnIsland()
    {
        assertFalse(notOnIsland.isOnIsland());
    }

    @Test
    public void testIllegalArgumentNoIsland() throws Exception {
        try 
        {
            new Position(null,0,0);
            fail("No exception thrown when island null.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message", expected.getMessage().contains("Island"));
            Logger.getLogger(PositionTest.class.getName()).log(Level.FINE, null, expected);    
        }
        
    }

    @Test
    public void testIllegalArgumentRowNegative() throws Exception {
        try 
        {
            new Position(island,-1,0);
            fail("No exception thrown when row negative.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message", expected.getMessage().contains("Invalid (row"));
            Logger.getLogger(PositionTest.class.getName()).log(Level.FINE, null, expected);    
        }
        
    }
    
    @Test
    public void testIllegalArgumentRowTooLarge() throws Exception {
        try 
        {
            new Position(island,5,0);
            fail("No exception thrown when row too large.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message", expected.getMessage().contains("Invalid (row"));
            Logger.getLogger(PositionTest.class.getName()).log(Level.FINE, null, expected);    
        }
        
    } 
    
    @Test
    public void testIllegalArgumentColumnNegative() throws Exception {
        try 
        {
            new Position(island,1,-1);
            fail("No exception thrown when column negative.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message", expected.getMessage().contains("Invalid (row,column"));
            Logger.getLogger(PositionTest.class.getName()).log(Level.FINE, null, expected);    
        }
        
    }
    
    @Test
    public void testIllegalArgumentColumnTooLarge() throws Exception {
        try 
        {
            new Position(island,0,5);
            fail("No exception thrown when column too large.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message", expected.getMessage().contains("Invalid (row,column"));
            Logger.getLogger(PositionTest.class.getName()).log(Level.FINE, null, expected);    
        }
        
    }
    
    @Test
    public void testGetColumn()
    {
        assertEquals(2, onIsland.getColumn());
    }    

    @Test
    public void testGetRow()
    {
        assertEquals(1, onIsland.getRow());
    } 

    @Test
    public void testRemoveFromIsland()
    {
        onIsland.removeFromIsland();
        assertFalse(onIsland.isOnIsland());
    }
    
    @Test
    public void testGetNewPositionNull()throws Exception {
        try 
        {
            onIsland.getNewPosition(null);
            fail("No exception thrown when direction null.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message", expected.getMessage().contains("Direction parameter"));
            Logger.getLogger(PositionTest.class.getName()).log(Level.FINE, null, expected);    
        }    
    }
    
    @Test
    public void testGetNewPositionNotOnIsland() {
        assertEquals(notOnIsland.getNewPosition(MoveDirection.NORTH), null);
    }
    
    @Test
    public void testGetNewPositionValidDirection() {
        Position newPosition = onIsland.getNewPosition(MoveDirection.WEST);
        assertEquals(newPosition.getRow(), 1);
        assertEquals(newPosition.getColumn(), 1);
    }
    
    @Test
    public void testIsValidPositionNullIsland() {
        try 
        {
            Position.isValidPosition(null, 1, 1);
            fail("No exception thrown when direction null.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message", expected.getMessage().contains("Island parameter cannot be null"));
            Logger.getLogger(PositionTest.class.getName()).log(Level.FINE, null, expected);    
        } 
    }
    
    @Test
    public void testIsValidPositionInvalidRow() {
        assertFalse(Position.isValidPosition(island, -1 , 1));
        assertFalse(Position.isValidPosition(island, 100 , 1));
    }
    
    @Test
    public void testIsValidPositionInvalidColumn() {
        assertFalse(Position.isValidPosition(island, 1 , -1));
        assertFalse(Position.isValidPosition(island, 1 , 100));
    }

}
