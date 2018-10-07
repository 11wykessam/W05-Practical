public class W05Practical {

    // main method, where program executes from
    public static void main(String[] args) {

        // create EasyIn2 object to take input from user
        EasyIn2 easyIn2 = new EasyIn2();

        // create converter object
        Converter converter = new Converter();

        // prompt user for input
        System.out.println("Enter weight in kilograms");
        // get input using easyin2
        int input = easyIn2.getInt();

        // use converter object to convert to imperial and then print it in a user friendly way
        converter.convert(input);
        converter.printValues();

    }

}
