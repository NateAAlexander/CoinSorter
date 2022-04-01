import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.io.*;

/**
 * Class CoinSorterMachine works with an abstract Coin object and reads in a data file, parsing integers
 * and building a bank of coins that is then separated by type and value.
 * @version 03/31/2022
 * @author Nate Alexander
 */

public class CoinSorterMachine {
    private ArrayList<Coin> coins;
    private ArrayList<Coin> coinMap;
    private int[] typeCounts;


    /**
     * initializes coins ArrayList
     */

    public CoinSorterMachine()  {
        coins = new ArrayList<>();
        coinMap = new ArrayList<Coin>();
        coinMap.add(new Penny());
        coinMap.add(new Nickel());
        coinMap.add(new Dime());
        coinMap.add(new Quarter());
        coinMap.add(new HalfDollar());
        coinMap.add(new Dollar());
        typeCounts = new int[6];

    }

    private Coin makeCoin(double value) {
        for(Coin c : coinMap)   {
            if(c.getValue() == value)
                return c;
        }
        return null;
    }

    /**
     * use one or two Scanner objects, prompting user for the appropriate file
     * name and importing the data from filename – MUST handle diabolic values!
     */
    public void depositCoins() {
        System.out.println("Depositing coins...");
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the name of the data file for coin deposit: ");
            String fileName = sc.nextLine();
            sc.close();
            Scanner in = new Scanner(new File(fileName));
            while (in.hasNext()) {
                int val = in.nextInt();
                Coin c = makeCoin(val / 100.);
                if (c == null)
                    System.out.println("Coin value " + val + " not recognized");
                else {
                    if (c instanceof Penny)
                        typeCounts[0]++;
                    else if (c instanceof Nickel)
                        typeCounts[1]++;
                    else if (c instanceof Dime)
                        typeCounts[2]++;
                    else if (c instanceof Quarter)
                        typeCounts[3]++;
                    else if (c instanceof HalfDollar)
                        typeCounts[4]++;
                    else if (c instanceof Dollar)
                        typeCounts[5]++;
                    coins.add(c);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Prints deposit summary using a DecimalFormat object (see output section)
     */
    public void printDepositSummary()   {
        DecimalFormat df = new DecimalFormat("$.00");
        System.out.println("Summary of deposit:");
        for(int i = 0; i < typeCounts.length; i++)   {
            if(typeCounts[i] == 1)
                System.out.println("\t" + typeCounts[i] + " " + coinMap.get(i).getName() + " " + df.format(typeCounts[i]*coinMap.get(i).getValue()));
            else
                System.out.println("\t" + typeCounts[i] + " " + coinMap.get(i).getPluralName() + " " + df.format(typeCounts[i]*coinMap.get(i).getValue()));
        }
        System.out.println("TOTAL DEPOSIT: " + df.format(getTotalValue()));

    }

    /**
     *
     * @return the total value of all Coin objects stored in coins as a double
     */
    public double getTotalValue() {
        double total = 0;
        for (Coin c : coins) {
            total += c.getValue();
        }
        return total;
    }

    /**
     * main method for the class should use these exact three lines of code
     * @param args
     */
    public static void main(String[] args){
        CoinSorterMachine app = new CoinSorterMachine();
        app.depositCoins();
        app.printDepositSummary();

    }
}