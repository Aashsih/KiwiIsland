package nz.ac.aut.ense701.gameModel;

import java.util.ArrayList;
import nz.ac.aut.ense701.gameModel.occupants.Item;
import nz.ac.aut.ense701.gameModel.occupants.Tool;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

/**
 * Player represents the player in the KiwiIsland game.
 * 
 * @author AS
 * @version July 2011
 */
public class Player 
{
    public static final double MOVE_STAMINA = 1.0;
    
    private Position  position;
    private final String    name;
    private final double    maxStamina;
    private double    stamina;
    private boolean   alive;
    private final Set<Item> backpack;
    private final List<String> messages;
    private final double    maxBackpackWeight;
    private final double    maxBackpackSize;   
    private int playerScore;
    private int numberOfSteps;
    
    /**
     * Constructs a new player object.
     * 
     * @param position the initial position of the player
     * @param name the name of the player
     * @param maxStamina the maximum stamina level of the player
     * @param maxBackpackWeight the most weight that can be in a backpack
     * @param maxBackpackSize the maximum size items that will fit in the backpack     
     * @param playerScore the current score of the player
     */    
    public Player(Position position, String name, double maxStamina,
                  double maxBackpackWeight, double maxBackpackSize, int playerScore)
    {
       this.position          = position;
       this.name              = name;
       this.maxStamina        = maxStamina;
       this.stamina = maxStamina;
       this.maxBackpackWeight = maxBackpackWeight;
       this.maxBackpackSize = maxBackpackSize;
       this.playerScore     = playerScore;
       this.alive = true;
       this.backpack = new HashSet<Item>();
       this.messages = new ArrayList<String>();
    }   
    
    /*****************************************************************************************************
     * Accessor methods
     ****************************************************************************************************/
    
    /**
     * Gets the name of the player.
     * @return the name of the player
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the current position of the player.
     * @return the current position of the player
     */
    public Position getPosition()
    {
        return position;
    }
    
    /**
     * Checks if Player is still alive
     * @return true if Player is alive, false if not
     */
    public boolean isAlive()
    {
        return this.alive;
    }   
 
    /**
     * Get the maximum stamina for the player.
     * @return maximum stamina
     */
    public double getMaximumStaminaLevel()
    {
       return this.maxStamina;
    }

    /**
     * Get the current stamina for the player.
     * @return current stamina of the player
     */
    public double getStaminaLevel()
    {
       return this.stamina;
    }


    /**
     * Returns the amount of stamina needed to move.
     * @param terrain the terrain to move in
     * @return the amount of stamina needed for the next move
     */
    public double getStaminaNeededToMove(Terrain terrain)
    {
        double staminaNeeded = MOVE_STAMINA;
        double load = getCurrentBackpackWeight() / maxBackpackWeight;
        // Twice as much is needed when the backpack is full
        staminaNeeded *= (1.0 + load);
        // and even more when the terrain is difficult
        staminaNeeded *= terrain.getDifficulty();
        return staminaNeeded;
    }

    
    /**
     * Checks to see if the player has enough stamina to move.
     * 
     * @param terrain the terrain to move in
     * @return true if player can move, false if not
     */
    public boolean hasStaminaToMove(Terrain terrain)
    {
        return (this.stamina >= getStaminaNeededToMove(terrain));
    }
    
        
    /**
     * Get current size of backpack.
     * 
     * @return currentBackpackSize
     */
    public double getCurrentBackpackSize(){
        double totalSize = 0.0;
        for ( Item item : backpack ) 
        {
            totalSize += item.getSize();
        }
        return totalSize;
    }

    /**
     * Gets the maximum Backpack size.
     * 
     * @return the maximum backpack size
     */
    public double getMaximumBackpackSize()
    {
        return maxBackpackSize;
    }

    
    /**
     * Get current weight of backpack.
     * 
     * @return currentBackpackWeight
     */
    public double getCurrentBackpackWeight()
    {
        double totalWeight = 0.0;
        for ( Item item : backpack ) 
        {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }    

        
    /**
     * Gets the maximum Backpack weight.
     * 
     * @return the maximum backpack weight
     */
    public double getMaximumBackpackWeight()
    {
        return maxBackpackWeight;
    }
    
        
    /**
     * Checks if the player has a specific item.
     * 
     * @param item to check
     * @return true if item in backpack, false if not
     */
    public boolean hasItem(Item item)
    {
        return backpack.contains(item);
    }

    /**
     * Checks if the player has a tool.
     * 
     * @return true if tool in backpack, false if not
     */
    public boolean hasTrap()
    {
        boolean found = false;
        for ( Item item : backpack ) 
        {
            if(item instanceof Tool)
            {
                Tool tool = (Tool) item;
                if (tool.isTrap())
                {
                    found = true;
                }
            }
        }
        return found;
    }
    
    /**
     * get a trap from player's backpack
     * @return trap or null if player has no trap
     */
    public Tool getTrap()
    {
        Tool tool;
        Tool trap = null;
        for ( Item item : backpack ) 
        {
            if(item instanceof Tool)
            {
                tool = (Tool) item;
                if (tool.isTrap())
                {
                    trap = tool;
                }
            }
        }
        return trap;
    }
    
    /**
     * Returns a collection of all items in the player's backpack.
     * 
     * @return the items in the player's backpack
     */
    public Collection<Object> getInventory()
    {
        List<Object> backpackItems = new ArrayList<Object>();
    
       for(Item b : backpack)
       {
           backpackItems.add(b);
       }
       if(!messages.isEmpty())
       {
           backpackItems.add("Messages");
       }

        return Collections.unmodifiableCollection(backpackItems);
    }
    
    /*************************************************************************************************************
     * Mutator methods
     ****************************************************************************************************************/
    
    /**
     * Kills the Player
     */
    public void kill()
    {
        this.alive = false;
    } 
    
    /**
     * Decrease the stamina level by reduction.
     * 
     * @param reduction the amount of stamina to reduce
     */
    public void reduceStamina(double reduction)
    {
       if ( reduction > 0 )
       { 
          this.stamina -= reduction;
          if ( this.stamina < 0.0 )
          {
             this.stamina = 0.0; 
          }
       }    
    }    
    
    /**
     * Increase the stamina level of the player.
     * 
     * @param increase the amount of stamina increase
     */
    public void increaseStamina(double increase)
    {
       if ( increase > 0 && isAlive() )
       {         
          this.stamina += increase;    
       }
       if ( stamina > maxStamina )
       {
           stamina = maxStamina;
       }
    }
    
    /**
     * Collect an item if it will fit in player's backpack.
     * 
     * @param item to collect
     * @return true if item is placed in backpack.
     */
    public boolean collect(Item item)
    {
        boolean success = false;
        if ( item != null && item.isOkToCarry() )
        {
            double  addedSize   = getCurrentBackpackSize() + item.getSize();
            boolean enoughRoom  = (addedSize <= this.maxBackpackSize);
            double  addedWeight = getCurrentBackpackWeight() + item.getWeight();
            //Will weight fit in backpack?
            boolean notTooHeavy = (addedWeight <= this.maxBackpackWeight);
            //Player can only carry one trap at a time.
            //Is this an addtional trap?
            boolean additionalTrap = false;
            if(item instanceof Tool)
            {
                Tool tool = (Tool) item;
                additionalTrap = (tool.isTrap() && this.hasTrap());
            }       
                   
            if ( enoughRoom && notTooHeavy && !additionalTrap)
            {
                success = backpack.add(item);
                // when item is collected, it is no longer "on the island"
                if ( success )
                {
                    // assign it the "not on island" position
                    item.setPosition(Position.NOT_ON_ISLAND);
                }
            }
        }
        return success;
    }
    
    /**
     * Drops an item if player is carrying it.
     * 
     * @param item to drop
     * @return true if item dropped, false if not
     */
    public boolean drop(Item item)
    {
        return backpack.remove(item);
    }
    
    /**
     * Moves the player over terrain to a new position.
     * 
     * @param newPosition the new position of the player
     * @param terrain the terrain to move over
     */
    public void moveToPosition(Position newPosition, Terrain terrain)
    {
        if( (position == null) || (terrain == null) )
        {
            throw new IllegalArgumentException("Null parameters");
        }
        
        if( hasStaminaToMove(terrain) )
        {
            this.position = newPosition;
            reduceStamina(getStaminaNeededToMove(terrain));
        }
    }
     
    /**
     * This method converts all the messages stored in the messages list into a
     * string and returns it
     * 
     * @return string representation of all the facts separated by newline
     */
    public List<String> getPlayerMessages()
    {
        return Collections.unmodifiableList(messages);

    }
    
     /**
     * This method is used by the game class to add a fact to the messages list
     * 
     * @param message 
     */
    public void addMessage(String message)
    {
        messages.add(message);
    }
    
     // A method used to increment the number of steps taken by the user
    //returns the number of steps and is called in the Game class to be used 
    public int incrementSteps(){
        ++numberOfSteps;
        return numberOfSteps;
    }
    //The getters and setters for the incrementSteps method 
    public int getNumberOfSteps() {
        return numberOfSteps;
    }
    
    //Changes the player score and sets the score in Score class
    public void changeScore(int change){
        
        this.playerScore += change;
       
    }
    //Gets the current score of the player
    public Score getPlayerScore(){
        
       return new Score(name, playerScore);
    }
}