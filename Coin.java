import java.util.*;
import java.lang.*;


public abstract class Coin {
    public abstract double getValue();
    public abstract String getName();


    /**
     * Handles penny/pennies otherwise appends the letter s to the end of coin
     * @return a String representation of the coin name
     */


    public String getPluralName() {
        if(getName().equals("penny"))
            return "pennies";
        else
            return getName() + "s";
    }
}