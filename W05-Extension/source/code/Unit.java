package code;

public class Unit {

    // class unit just needs to contain its value in kilograms and whether the program requires it as input
    private double valueInKilograms;
    // inUse stores whether the converter is currently outputting this unit as one of the outputs
    private boolean inUse = true;

    // constructor takes value in kilograms as input
    public Unit(double valueInKilograms) {
        this.valueInKilograms = valueInKilograms;
    }

    // getters and setters
    public double getValueInKilograms() {
        return valueInKilograms;
    }
    public void setValueInKilograms(double valueInKilograms) {
        this.valueInKilograms = valueInKilograms;
    }

    public boolean isInUse() {
        return inUse;
    }
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

}
