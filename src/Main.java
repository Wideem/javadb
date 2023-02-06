import entities.Exam;
import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactoryMaker.getFactory();
        System.out.println("DB connection successful!");
        testManyToMany();
    }


    public static void testManyToMany() {
        SessionFactory sessionFactory = SessionFactoryMaker.getFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Student student1 = new Student();
        student1.setName("Laurynas");
        Student student2 = new Student();
        student2.setName("Jonas");
        Student student3 = new Student();
        student3.setName("Mantas");
        session.persist(student1);
        session.persist(student2);
        session.persist(student3);
        session.getTransaction().commit();

        Exam exam1 = new Exam();
        exam1.setName("Music entities.Exam");

        session.persist(exam1);
        Exam exam2 = new Exam();
        exam2.setName("Mathematics entities.Exam");

        session.persist(exam2);

        Exam exam3 = new Exam();
        exam3.setName("Biology entities.Exam");

        session.persist(exam3);


    }
}