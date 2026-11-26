// Lucas Walker
// AT CS
// Homework #12

import java.util.Scanner;

public class Homework12 {
    public static void main(String[] args) {
        Cracker cracker = new Cracker();
        boolean notDone = true;
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Welcome to walkerHash, the ultimate cracking algorithm! Please type in an example command. Use h or help for help");
        while (notDone) {
            String command = input.nextLine();
            String[] sep = command.split(" ");
            if (sep[0].equals("add")) {
                if (sep.length > 2 && sep.length < 4) {
                    try {
                        cracker.addEntry(sep[1], sep[2]);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Illegal input of password characters. Legal Inputs are the following: "
                                + Cracker.alphabet + "\n");
                        helpMessage();
                    }
                } else {
                    helpMessage();
                }
            } else if (sep[0].equals("crack")) {
                if (sep.length > 1) {
                    if (sep[1].equals("dictionaryAttack")) {
                        if (sep.length == 3) {
                            System.out.println(cracker.dictionaryAttack(sep[2]));
                        } else {
                            helpMessage();
                        }
                    } else if (sep[1].equals("bruteForce") && sep.length == 3) {
                        try {
                            System.out.println(cracker.bruteForce(Integer.parseInt(sep[2])));
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Illegal input of max length. Length must inputed as an integer number \n");
                            helpMessage();
                        }
                    } else {
                        helpMessage();
                    }
                } else if (sep[0].equals("rename")) {
                    if (sep.length == 2) {

                    }
                } else {
                    helpMessage();
                }
            } else if (sep[0].equals("quit")) {
                notDone = false;
            } else if (sep[0].equals("list")) {
                System.out.println("Number of profiles: " + Profile.profileCounter);
                System.out.println(cracker.getProfiles());
            } else if (sep[0].equals("stats")) {
                System.out.println(cracker.getAttempts());
            } else {
                helpMessage();
            }
        }
        input.close();
    }

    public static void helpMessage() {
        System.out.println(
                "Legal arguments:\n\ncrack <type> <subCommand>\nThe only acceptable types are bruteForce and dictionaryAttack.\nFor dictionaryAttack, you must also input afterwarcs the path to the wordlist. For bruteForce, you will need to input the max length the bruteForce algorithm should run up to.\nadd <username> <password>\nThis command adds a new user to the cracker.\nlist\nThis commands lists all the users in the cracker object username and also includes their hashed password\nstats\nThis command lists the number of attempts it took to guess the password\nquit\nThis command quits the program");
    }
}
