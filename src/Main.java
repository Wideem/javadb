import commands.*;
import config.SessionFactoryMaker;
import entities.Exam;
import entities.Question;
import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        SessionFactoryMaker.getFactory();
        System.out.println("DB connection successful!");
        runProgram();
    }

    private static void runProgram() {
        Scanner sc = new Scanner(System.in);
        boolean runProgram = true;
        while (runProgram) {
            try {
                CommandHandler.printCommands();
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> Question.create(sc);
                    case "2" -> CommandHandler.takeExam(sc);
                    case "3" -> CommandHandler.fillDb();
                    case "4" -> Question.update(sc);
                    case "5" -> CommandHandler.calcExamTakenTime(sc);
                    case "6" -> CommandHandler.calcExamAvgScore(sc);
                    case "7" -> CommandHandler.calcSelectedCount(sc);
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

}