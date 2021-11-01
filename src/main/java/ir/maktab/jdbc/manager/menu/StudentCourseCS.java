package ir.maktab.jdbc.manager.menu;

import ir.maktab.jdbc.entity.StudentCourse;
import ir.maktab.jdbc.exception.WrongInputException;

import java.util.Scanner;

public class StudentCourseCS implements ConsoleScanner<StudentCourse>{
    @Override
    public StudentCourse getSaveInf() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Please Enter Student ID : ");
            int studentId = scan.nextInt();
            System.out.println("Please Enter Course ID : ");
            int courseId = scan.nextInt();
            return new StudentCourse(studentId, courseId);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new WrongInputException("Wrong input. please try again...");
        }
    }
}
