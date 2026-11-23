// Lucas Walker
// AT CS
// Homework #12

import java.util.Scanner;

public class Homework12 {
    public static void main(String[] args) {
        boolean notDone = false;
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Welcome to walkerHash, the ultimate dehasing algorithm! Please type in an example command. Use h or help for help");
        while (notDone) {
            String command = input.nextLine();
            String[] commandsSeprated = command.split(" ");
            if (commandsSeprated[0].equals("hash")) {

            } else if (commandsSeprated[0].equals("crack")) {
                if (commandsSeprated[1].equals("bruteFore")) {
                    int length = Integer.parseInt(commandsSeprated[2]);
                } else if (commandsSeprated[1].equals("dictionary")) {
                    String listPath = commandsSeprated[3];
                }
            } else if (commandsSeprated[0].equals("help") || commandsSeprated[0].equals("h")) {
                helpMessage();
            } else {
                throw new IllegalArgumentException("Not valid command input. Use h or help for help");
            }
        }
    }

    public static void helpMessage() {
        System.out.println(
                "Legal arguments:\n\nhash\nReturns to you a hashed version of the inputed password\ncrack <type> <subCommand>\nThe only acceptable types are bruteForce and dictionaryAttack.\nFor dictionaryAttack, you must also input afterwarcs the path to the wordlist. For bruteForce, you will need to input the max length the bruteForce algorithm should run up to.");
    }
}
