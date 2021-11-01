package ir.maktab.jdbc.manager.menu;

import ir.maktab.jdbc.entity.Course;
import ir.maktab.jdbc.exception.WrongInputException;

import java.util.Scanner;

public class CourseCS implements ConsoleScanner<Course>{
    @Override
    public Course getSaveInf() {
        Scanner scan = new Scanner(System.in);
        try {

            System.out.println("Please Enter Id : ");
            int id = scan.nextInt();
            System.out.println("Please Enter Course Name : ");
            String name = scan.next();
            System.out.println("Please Enter Unit Number : ");
            int unitNumber = scan.nextInt();
            return new Course(id, name, unitNumber);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new WrongInputException("Wrong input. please try again...");
        }
    }
}
