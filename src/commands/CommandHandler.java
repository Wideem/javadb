package commands;

import commands.enums.Command;
import config.SessionFactoryMaker;
import entities.Exam;
import entities.ExamResult;
import entities.Question;
import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CommandHandler {


    public static void printCommands() {
        for (Command c : Command.values()) {
            System.out.println(c);
        }
    }
    public static void fillDb() {
        Random r = new Random();

        SessionFactory sessionFactory = SessionFactoryMaker.getFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        // Crete students
        Student student1 = new Student();
        student1.setName("Laurynas");
        Student student2 = new Student();
        student2.setName("Jonas");
        Student student3 = new Student();
        student3.setName("Mantas");
        session.persist(student1);
        session.persist(student2);
        session.persist(student3);

        // Create exams
        Exam exam1 = new Exam();
        exam1.setName("CS");

        Exam exam2 = new Exam();
        exam2.setName("Math");

        Exam exam3 = new Exam();
        exam3.setName("English");


        // Questions for exams
        for (int i = 0; i < 3; i++) {
            Question question  = new Question();
            question.setQuestion("Question " + i);
            question.setExam(exam1);
            question.setAnswerA("answerA");
            question.setAnswerB("answerB");
            question.setAnswerC("answerC");
            question.setAnswerId(r.nextInt(3));
            session.persist(question);
        }

        for (int i = 0; i < 3; i++) {
            Question question  = new Question();
            question.setQuestion("Question " + i);
            question.setExam(exam2);
            question.setAnswerA("answerA");
            question.setAnswerB("answerB");
            question.setAnswerC("answerC");
            question.setAnswerId(r.nextInt(3));
            session.persist(question);
        }

        for (int i = 0; i < 3; i++) {
            Question question  = new Question();
            question.setQuestion("Question " + i);
            question.setExam(exam3);
            question.setAnswerA("answerA");
            question.setAnswerB("answerB");
            question.setAnswerC("answerC");
            question.setAnswerId(r.nextInt(3));
            session.persist(question);
        }
        //Assign exams to students
        ArrayList<Student> exam1_students = new ArrayList<>();
        exam1_students.add(student1);
        exam1_students.add(student2);
        exam1_students.add(student3);

        ArrayList<Student> exam2_students = new ArrayList<>();
        exam2_students.add(student1);
        exam2_students.add(student3);

        ArrayList<Student> exam3_students = new ArrayList<>();
        exam3_students.add(student2);


//        exam1.setStudents(exam1_students);
//        exam2.setStudents(exam2_students);
//        exam3.setStudents(exam3_students);

        session.persist(exam1);
        session.persist(exam2);
        session.persist(exam3);

        session.getTransaction().commit();


    }

    public static void takeExam(Scanner sc) {
        SessionFactory sessionFactory = SessionFactoryMaker.getFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        System.out.println("Enter Student name");
        String name = sc.nextLine();
        int result = 0;

        Student st = (Student) session.createQuery("from Student where name = :name").setParameter("name", name).uniqueResult();

        List<Exam> allExams = session.createQuery("from Exam").list();
        System.out.println("All exams");
        for (Exam e : allExams
        ) {
            System.out.println(e);

        }

        System.out.println("Enter Exam Id to take:");
        int examId = parseInt(sc.nextLine());

        Exam e = (Exam) session.createQuery("from Exam where id = :id").setParameter("id", examId).uniqueResult();


        List<Question> questions = session.createQuery("from Question where exam.id = :examId", Question.class)
                .setParameter("examId", e.getId())
                .list();

        for (Question q : questions) {
            System.out.println(q.getQuestion());
            String answerA = q.getAnswerA();
            String answerB = q.getAnswerB();
            String answerC = q.getAnswerC();
            System.out.println("Answers");
            System.out.println("0- " + answerA);
            System.out.println("1- " + answerB);
            System.out.println("2- " + answerC);

            System.out.println("Enter ID of correct Answer: ");
            int answer = Integer.parseInt(sc.nextLine());
            if (answer == q.getAnswerId()) {
                System.out.println("You're correct");
                result++;
            }else {
                System.out.println("sorry you're wrong");
            }

        }
        ExamResult results = new ExamResult();
        results.setExam(e);
        results.setStudent(st);
        results.setResult(result);
        session.persist(results);
        session.getTransaction().commit();
    }

    public static void calcExamTakenTime(Scanner sc){
        SessionFactory sessionFactory = SessionFactoryMaker.getFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        System.out.println("Enter ID of Exam which you want to see how many times was taken: ");
        int examId = parseInt(sc.nextLine());

        Long result = session.createQuery("from ExamResult where id = :id").setParameter("id", examId).stream().count();
        System.out.println("Exam with id " +examId + " taken " + result + " times");
    }
}
