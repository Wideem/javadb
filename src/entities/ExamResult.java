
package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exam_result")
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(name = "result")
    private int result;

    @Column(name = "no_questions")
    private int noQuestions;

    @Column(name = "a_selected")
    private int aSelected;

    @Column(name = "b_selected")
    private int bSelected;

    @Column(name = "c_selected")
    private int cSelected;

    public ExamResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getNoQuestions() {
        return noQuestions;
    }

    public void setNoQuestions(int noQuestions) {
        this.noQuestions = noQuestions;
    }

    public int getaSelected() {
        return aSelected;
    }

    public void setaSelected(int aSelected) {
        this.aSelected = aSelected;
    }

    public int getbSelected() {
        return bSelected;
    }

    public void setbSelected(int bSelected) {
        this.bSelected = bSelected;
    }

    public int getcSelected() {
        return cSelected;
    }

    public void setcSelected(int cSelected) {
        this.cSelected = cSelected;
    }

    @Override
    public String toString() {
        return String.format("|%15s|%15s|%3s", this.getId(), this.getStudent(), this.getResult());
    }
}
