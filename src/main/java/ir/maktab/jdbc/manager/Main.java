
package ir.maktab.jdbc.manager;

import ir.maktab.jdbc.entity.Course;
import ir.maktab.jdbc.entity.Major;
import ir.maktab.jdbc.entity.Student;
import ir.maktab.jdbc.entity.base.BaseEntity;
import ir.maktab.jdbc.manager.menu.*;
import ir.maktab.jdbc.service.*;
import java.util.List;

public class Main {
    private final static StudentService studentService = new StudentService();
    private final static CourseService courseService = new CourseService();
    private final static MajorService majorService = new MajorService();
    private final static StudentCourseService scs = new StudentCourseService();
    private final static StudentCS studentCs = new StudentCS();
    private final static CourseCS courseCs = new CourseCS();
    private final static MajorCS majorCs = new MajorCS();
    private final static StudentCourseCS scsCs = new StudentCourseCS();

    public static void main(String[] args) {
        int menu = MainMenu.printMainMenu();
        while (menu != 6){
            int option = MainMenu.printMainOptions();
            switch (menu){
                case 1 -> getSaveServices(option);
                case 2 -> getUpdateServices(option);
                case 3 -> getDeleteServices(option);
                case 4 -> System.out.println(getLoadByIdServices(option));
                case 5 -> System.out.println(getLoadAllServices(option));
                default -> System.out.println("Please Enter a Valid Number");
            }
            menu = MainMenu.printMainMenu();
        }
        System.out.println("Thank You For Choosing us. see you later !!!!!!!");
    }

    private static List getLoadAllServices(int choice) {
        return switch (choice) {
            case 1 -> studentService.loadAll();
            case 2 -> courseService.loadAll();
            case 3 -> majorService.loadAll();
            case 4 -> scs.loadAll();
            default -> null;
        };
    }

    private static void getSaveServices(int choice) {
        switch (choice) {
            case 1 -> studentService.save(studentCs.getSaveInf());
            case 2 -> courseService.save(courseCs.getSaveInf());
            case 3 -> majorService.save(majorCs.getSaveInf());
            case 4 -> scs.save(scsCs.getSaveInf());
            default -> System.out.println("Please Enter a Valid number...");
        }
    }

    private static BaseEntity<Integer> getLoadByIdServices(int choice) {
        return switch (choice) {
            case 1 -> studentService.loadById(studentCs.getLoadByIdInf());
            case 2 -> courseService.loadById(courseCs.getLoadByIdInf());
            case 3 -> majorService.loadById(majorCs.getLoadByIdInf());
            case 4 -> scs.loadById(scsCs.getLoadByIdInf());
            default -> null;
        };
    }

    private static void getUpdateServices(int choice) {
        int id;
        switch (choice) {
            case 1:
                id = scsCs.getUpdateInf();
                Student student = studentCs.getSaveInf();
                studentService.update(student, id);
                break;
            case 2:
                id = courseCs.getUpdateInf();
                Course course = courseCs.getSaveInf();
                courseService.update(course, id);
                break;
            case 3:
                id = majorCs.getUpdateInf();
                Major major = majorCs.getSaveInf();
                majorService.update(major, id);
            case 4:
                id = scsCs.getUpdateInf();
                var studentCourse = scsCs.getSaveInf();
                scs.update(studentCourse, id);
            default:
                System.out.println("Please Enter a Valid number...");
        }
    }

    private static void getDeleteServices(int choice) {
        switch (choice) {
            case 1 -> studentService.delete(studentCs.getDeleteInf());
            case 2 -> courseService.delete(courseCs.getDeleteInf());
            case 3 -> majorService.delete(majorCs.getDeleteInf());
            case 4 -> scs.delete(scsCs.getDeleteInf());
            default -> System.out.println("Please Enter a Valid number...");
        }
    }


}
