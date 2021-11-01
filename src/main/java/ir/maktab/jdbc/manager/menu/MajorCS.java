package ir.maktab.jdbc.manager.menu;

import ir.maktab.jdbc.entity.Major;
import ir.maktab.jdbc.exception.WrongInputException;

import java.util.Scanner;

public class MajorCS implements ConsoleScanner<Major>{
    @Override
    public Major getSaveInf() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please Enter Major Name : ");
            String majorName = scan.next();
            System.out.println("Please Enter Major Id : ");
            int majorId = scan.nextInt();
            return new Major(majorId, majorName);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new WrongInputException("Wrong input. please try again...");
        }
    }
}
