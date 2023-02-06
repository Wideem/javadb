package entities;

import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.List;
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

    public static void showAll() {
        System.out.println("All questions:");
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            List<Question> questions = session.createQuery("from Question", Question.class).list();
            for (Question q : questions) {
                System.out.println(q);
            }
        }
    }

    public static void create(Scanner sc) {
        System.out.println("Adding a new question");
        Question q = new Question(sc);
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(q);
            session.getTransaction().commit();
        }
    }

    public static void update(Scanner sc) {
        showAll();
        System.out.println("Enter ID to update entity:");
        int id = Integer.parseInt(sc.nextLine());
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            Question questionToUpdate = session.get(Question.class, id);
            questionToUpdate.printFieldsAvailable();
            System.out.println("Enter field id to update:");
            questionToUpdate.updateFieldByFieldId(Integer.parseInt(sc.nextLine()), sc);
            session.merge(questionToUpdate);
            session.getTransaction().commit();
        }
    }

    private void printFieldsAvailable() {
        System.out.println("[0] Question: " + this.question);
        System.out.println("[1] Answer A: " + this.answerA);
        System.out.println("[2] Answer B: " + this.answerB);
        System.out.println("[3] Answer C: " + this.answerC);
        System.out.println("[4] Correct answer index: " + this.answerId);
        System.out.println("[5] Exam. id: " + this.getExam());
    }

    private void updateFieldByFieldId(int fieldId, Scanner sc) {
        switch (fieldId) {
            case 0 -> this.setQuestion(sc.nextLine());
            case 1 -> this.setAnswerA(sc.nextLine());
            case 2 -> this.setAnswerB(sc.nextLine());
            case 3 -> this.setAnswerC(sc.nextLine());
            case 4 -> this.setAnswerId(parseInt(sc.nextLine()));
            default -> System.out.println("Unknown field!");
        }
    }

    @Override
    public String toString() {
        return String.format("|%3s|%15s|%15s|", this.getId(), this.getQuestion(), this.getExam());
    }
}


