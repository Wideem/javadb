package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Question> questions;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "exams", fetch = FetchType.EAGER)
    private List<Student> students;


    public Exam() {
    }

    public Exam(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
