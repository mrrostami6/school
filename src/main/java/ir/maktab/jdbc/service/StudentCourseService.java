package ir.maktab.jdbc.service;

import ir.maktab.jdbc.dao.StudentCourseDao;
import ir.maktab.jdbc.dao.core.BaseDao;
import ir.maktab.jdbc.entity.Course;
import ir.maktab.jdbc.entity.StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseService extends AbstractCrudService<StudentCourse, Integer> {
    public StudentCourseService(){
        setBaseDao(new StudentCourseDao());
    }

    public List<Course> getStudentCourses(Integer studentId){
        List<StudentCourse> studentCourses = loadAll();
        List<Course> courses = new ArrayList<>();
        for (StudentCourse sc : studentCourses) {
            courses.add(new Course(sc.getCourseId()));
        }
        return courses;
    }

}
