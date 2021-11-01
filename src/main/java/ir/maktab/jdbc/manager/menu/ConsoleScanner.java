package ir.maktab.jdbc.manager.menu;

import ir.maktab.jdbc.entity.Student;
import ir.maktab.jdbc.entity.base.BaseEntity;
import ir.maktab.jdbc.exception.WrongInputException;

import java.util.Scanner;

public interface ConsoleScanner<E extends BaseEntity<Integer>>{
    E getSaveInf();
    default Integer getDeleteInf() {
        try {
            System.out.println("Please Enter Id you want to delete it's course : ");
            return new Scanner(System.in).nextInt();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new WrongInputException("Wrong input. please try again...");
        }
    }

    default Integer getLoadByIdInf(){
        try {
            System.out.println("Please Enter Id you want to see it's information : ");
            return new Scanner(System.in).nextInt();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new WrongInputException("Wrong input. please try again...");
        }
    }

    default Integer getUpdateInf() {
        try {
            System.out.println("Please Enter Id you want to update it's course : ");
            return new Scanner(System.in).nextInt();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new WrongInputException("Wrong input. please try again...");
        }
    }
}
