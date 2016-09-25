import java.util.Scanner;

public class NumberTrackerTester {

    public static void main(String[] args) {

        NumberTracker numberTracker = new NumberTracker();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter a number: ");
            String input = scan.next();

            if (input.startsWith("q")) {
                System.out.println("Goodbye.");
                System.exit(0); // a break would have the same effect, but this is slightly clearer
            }

            int inputAsInt = Integer.parseInt(input);
            if (!numberTracker.add(inputAsInt)) {
                System.out.println("Sorry, only positive numbers.");
                continue; // remember, this will skip all the code below and go back to the while statement
            }

            System.out.printf("Current max = %d, Current min = %d\n", numberTracker.getMax(), numberTracker.getMin());
            System.out.println(numberTracker.longestRun());
        }

    }

}
