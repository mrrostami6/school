package ir.maktab.jdbc.entity;

import ir.maktab.jdbc.entity.base.BaseEntity;

public class StudentCourse implements BaseEntity<Integer> {
    private int id;
    private int courseId;
    private int studentId;

    public StudentCourse(int studentId, int courseId){
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
