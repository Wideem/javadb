package commands;

import commands.enums.Command;

import java.util.Scanner;

public class CommandHandler {


    public static void printCommands() {
        for (Command c : Command.values()) {
            System.out.println(c);
        }
    }
}
