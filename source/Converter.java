public class Converter {

    // constants used for conversion
    private double KILOGRAMSINANOUNCE = 0.0283495;
    private int OUNCESINAPOUND = 16;
    private int POUNDSINASTONE = 14;

    // variables used to store number of kilograms, ounces, pounds, and stones
    private int numberOfKilograms = 0;
    private int numberOfOunces = 0;
    private int numberOfPounds = 0;
    private int numberOfStone = 0;

    // method to convert a given input in kilograms to stones, pounds and ounces
    public void convert(int inputInKilograms) {

        // set the numberOfKilograms so it can be used in the printValues method
        numberOfKilograms = inputInKilograms;

        // declare variables to store a more exact version of the total number of each unit
        double totalOunces = kilogramsToOunces(inputInKilograms);
        double totalPounds = ouncesToPounds(totalOunces);
        double totalStone = poundsToStone(totalPounds);

        // now calculate the displayed values as ints
        numberOfStone = (int)totalStone;
        numberOfPounds = (int)(totalPounds - numberOfStone*POUNDSINASTONE);
        // note: ounces needs to be rounded instead of floored
        numberOfOunces = (int)(Math.round(totalOunces - numberOfPounds*OUNCESINAPOUND - numberOfStone*POUNDSINASTONE*OUNCESINAPOUND));

    }

    // format and print the output
    public void printValues() {

        // print error if value entered was <= 0
        if (numberOfKilograms <= 0) {
            System.out.println("Invalid input!");
        }
        else {
            if (numberOfKilograms == 1) {
                System.out.println("1 kilogram in stones, pounds and ounces is: ");
            }
            else {
                System.out.println(numberOfKilograms + " kilograms in stones, pounds and ounces is: ");
            }

            // create an empty line to append to.
            String outputLine = "";

            // do a check to see if plurality is needed for stone
            if(numberOfStone == 1) outputLine += "1 stone";
            else if(numberOfStone > 1) outputLine += numberOfStone + " stones";
            // first check if an "and" is needed
            if(numberOfStone > 0 && (numberOfPounds > 0 ^ numberOfOunces > 0)) outputLine += " and ";
            // next check if a "," is needed
            else if(numberOfStone > 0 && numberOfPounds > 0 && numberOfOunces > 0) outputLine += ", ";

            // do a check to see if plurality is needed for pounds
            if(numberOfPounds == 1) outputLine += "1 pound";
            else if(numberOfPounds > 1) outputLine += numberOfPounds + " pounds";
            // check if an "and" is needed
            if(numberOfPounds > 0 && numberOfOunces > 0) outputLine += " and ";

            // do a check to see if plurality is needed for ounces
            if(numberOfOunces == 1) outputLine += "1 ounce";
            else if(numberOfOunces > 1) outputLine += numberOfOunces + " ounces";

            // print formatted line
            System.out.println(outputLine);
        }

    }

    // method to convert kilograms to ounces
    private double kilogramsToOunces(double input) {
        return input/KILOGRAMSINANOUNCE;
    }
    // method to convert ounces to pounds
    private double ouncesToPounds(double input) {
        return input/OUNCESINAPOUND;
    }
    // method to convert pounds to stone
    private double poundsToStone(double input) {
        return input/POUNDSINASTONE;
    }

    // methods to get the variables from outside of class
    public int getNumberOfOunces() {
        return numberOfOunces;
    }
    public int getNumberOfPounds() {
        return numberOfPounds;
    }
    public int getNumberOfStone() {
        return numberOfStone;
    }
}
