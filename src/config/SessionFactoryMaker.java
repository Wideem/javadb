package config;

import entities.Exam;
import entities.Question;
import entities.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class SessionFactoryMaker {
    private static SessionFactory factory;

    private static void configureFactory() {
        try {
            factory = new Configuration()
                    .addAnnotatedClass(Exam.class)
                    .addAnnotatedClass(Question.class)
                    .addAnnotatedClass(Student.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static org.hibernate.SessionFactory getFactory() {
        if (factory == null) {
            configureFactory();
        }

        return factory;
    }
}
