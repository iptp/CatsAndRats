import greenfoot.*;
import java.util.List;

public class Rat extends Animal
{
    public void act() 
    {
        // If there are cats nearby
        if(getNeighbours(3, false, Cat.class).size() != 0)
        {
            // Flee from the cats
            pathFinding(Cat.class, true);
        }
        else
        {
            // Seek cheese
            pathFinding(Cheese.class, false);
        } 
        
        // If not stuck on a trap
        if(getOneIntersectingObject(Mousetrap.class) == null)
            walk();
            
        // Eat cheese
        List<Cheese> cheeses = getIntersectingObjects(Cheese.class);
        getWorld().removeObjects(cheeses);
    }
}
