package entities;

import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.Scanner;

import config.SessionFactoryMaker;

import static java.lang.Integer.parseInt;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question", nullable = false)
    private String question;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id")
    private Exam exam;
    @Column(name = "answer_A")
    private String answerA;
    @Column(name = "answer_B")
    private String answerB;
    @Column(name = "answer_C")
    private String answerC;

    @Column(name = "answer_id")
    private int answerId;

    public Question() {
    }

    public Question(Scanner sc) {
        System.out.println("Enter question:");
        this.question = sc.nextLine();
        System.out.println("Enter Answer A:");
        this.answerA = sc.nextLine();
        System.out.println("Enter Answer B:");
        this.answerB = sc.nextLine();
        System.out.println("Enter Answer C:");
        this.answerC = sc.nextLine();
        System.out.println("Enter ID of correct answer:");
        this.answerId = parseInt(sc.nextLine());
        System.out.println("Enter exam_id:");
        int examId = parseInt(sc.nextLine());
        this.exam = new Exam(id=examId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public int getAnswerId() {
        return answerId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public static void add(Scanner sc) {
        System.out.println("Adding a new question");
        Question q = new Question(sc);
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(q);
            session.getTransaction().commit();
        }
    }
}


