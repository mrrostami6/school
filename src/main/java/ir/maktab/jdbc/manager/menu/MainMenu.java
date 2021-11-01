package ir.maktab.jdbc.manager.menu;

import java.util.Scanner;

public final class MainMenu {
    private static final Scanner scan = new Scanner(System.in);

    private MainMenu(){}

    public static int printMainMenu(){
        System.out.println("""
                Welcome. Please Enter A number :
                1 -> Save
                2 -> Update
                3 -> Delete
                4 -> Show
                5 -> Show All
                6 -> Exit
                """);
        return scan.nextInt();
    }

    public static int printMainOptions() {
        System.out.println("""
                Please Enter A number :
                1 -> Student
                2 -> Course
                3 -> Major
                4 -> Student's Courses
                5 -> Exit""");
        return scan.nextInt();
    }


}
