package ir.maktab.jdbc.manager.menu;

import ir.maktab.jdbc.entity.Major;
import ir.maktab.jdbc.entity.Student;
import ir.maktab.jdbc.exception.WrongInputException;

import java.util.Scanner;

public class StudentCS implements ConsoleScanner<Student>{
    @Override
    public Student getSaveInf() {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Please Enter Your Id : ");
            int id = scan.nextInt();
            System.out.println("Please Enter Your Name : ");
            String name = scan.next();
            System.out.println("Please Enter Your Family Name : ");
            String familyName = scan.next();
            System.out.println("Please Enter Your Major ID : ");
            int majorId = scan.nextInt();
            return Student.builder()
                    .id(id)
                    .name(name)
                    .familyName(familyName)
                    .major(new Major(majorId))
                    .build();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new WrongInputException("Wrong input. please try again...");
        }
    }
}
