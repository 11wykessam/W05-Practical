package code;

public class Converter {

    // conversion constants
    private double OUNCEINKILOGRAMS = 0.028349523125;
    private double POUNDINKILOGRAMS = 0.45359237;
    private double STONEINKILOGRAMS = 6.35029318;

    // units used in convert method
    private Unit KILOGRAM = new Unit(1);
    private Unit OUNCE = new Unit(OUNCEINKILOGRAMS);
    private Unit POUND = new Unit(POUNDINKILOGRAMS);
    private Unit STONE = new Unit(STONEINKILOGRAMS);

    // variables that store the total number of each unit
    private double totalKilograms = 0;

    // method to set the kilogram variable and convert the imperial variables
    public void changeMetric(double kilogramInput) {
        totalKilograms = kilogramInput;
    }

    // method to set the total imperial variables and total kilogram variable based off a "pounds, stones and ounces" input
    public void changeImperial(double stoneInput, double poundInput, double ounceInput) {
        // if any of the units are disables don't use them to convert
        if (!STONE.isInUse()) stoneInput = 0;
        totalKilograms = calculateNumberOfKilograms(stoneInput, poundInput, ounceInput);
    }

    // method to return an array of doubles of the pounds, stones and ounces with the choice for any one combination of these units
    public int[] getImperialUnits() {
        // create variables to store the returned values for each unit
        int stones = 0;
        int pounds = 0;
        int ounces = 0;

        // a variable to keep count of how many kilograms are left to deal with
        double runningKilogramTotal = totalKilograms;

        if(STONE.isInUse()) {
            stones = (int) convert(runningKilogramTotal, KILOGRAM, STONE);
            runningKilogramTotal -= convert(stones, STONE, KILOGRAM);
        }
        if(POUND.isInUse()) {
            pounds = (int) convert(runningKilogramTotal, KILOGRAM, POUND);
            runningKilogramTotal -= convert(pounds, POUND, KILOGRAM);
        }
        if(OUNCE.isInUse()) {
            ounces = (int)Math.round(convert(runningKilogramTotal, KILOGRAM, OUNCE));
        }

        // put these values into an array to return
        int[] imperialUnits = {stones, pounds, ounces};

        return imperialUnits;

    }

    // method that takes stones, pounds and ounces and outputs the total in kilograms
    private double calculateNumberOfKilograms(double stoneInput, double poundInput, double ounceInput) {
        double total = 0;
        total += convert(stoneInput, STONE, KILOGRAM);
        total += convert(poundInput, POUND, KILOGRAM);
        total += convert(ounceInput, OUNCE, KILOGRAM);
        return total;
    }

    // method to convert value from one unit to another
    private double convert(double input, Unit inputUnit, Unit outputUnit) {
        return (input*inputUnit.getValueInKilograms())/outputUnit.getValueInKilograms();
    }

    // method that sets which imperial units are being returned
    public void setUnitsInUse(boolean stonesInUse, boolean poundsInUse, boolean ouncesInUse) {
        STONE.setInUse(stonesInUse);
        POUND.setInUse(poundsInUse);
        OUNCE.setInUse(ouncesInUse);
    }

    // getters for which units in use
    public boolean isStoneInUse() {
        return STONE.isInUse();
    }
    public boolean isPoundInUse() {
        return POUND.isInUse();
    }
    public boolean isOunceInUse() {
        return OUNCE.isInUse();
    }

// method to get the totalKilograms
    public double getTotalKilograms() {
        return this.totalKilograms;
    }

}
