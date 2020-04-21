import java.util.*;

public class TestList {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        MyList<Integer> testList = new MyList<>();
        String command = "";
        while (!command.equals("quit")) {
            System.out.print("Enter an inteface method for the list: ");
            command = keyboard.nextLine();
            int i;
            int v;
            switch(command) {
                case "get":
                    i = getIndex();
                    System.out.println("Got " + testList.get(i));
                    break;
                case "set":
                    i = getIndex();
                    v = getValue();
                    testList.set(i,v);
                    System.out.println("Set " + i + " to " + v);
                    break;
                case "add":
                    i = getIndex();
                    v = getValue();
                    testList.add(i, v);
                    System.out.println("Added " + v + " at " + i);
                    break;
                case "remove":
                    i = getIndex();
                    testList.remove(i);
                    System.out.println("Removed index " + i);
                    break;
                case "append":
                    v = getValue();
                    testList.append(v);
                    System.out.println("Appended " + v);
                    break;
                case "pop":
                    i = getIndex();
                    System.out.println("Got " + testList.pop(i) + " from popping index "+ i);
                    break;
            }
            System.out.println(testList);
        }
    }

    public static int getIndex() {
        System.out.print("Enter the index which you'd like to operate on: ");
        int i = keyboard.nextInt();
        return i;
    }

    public static int getValue() {
        System.out.print("Enter the value you'd like to add, append, or insert: ");
        int v = keyboard.nextInt();
        return v;
    }

}