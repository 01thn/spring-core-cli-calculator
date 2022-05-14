package org.example.entity;

import org.example.service.Calculator;
import org.example.storage.InMemoryUserStorage;
import org.springframework.stereotype.Component;

@Component
public class Application {

    private ConsoleWriter cw;
    private ConsoleReader cr;

    private InMemoryUserStorage userStorage;

    public Application(ConsoleWriter cw, ConsoleReader cr, InMemoryUserStorage userStorage) {
        this.cw = cw;
        this.cr = cr;
        this.userStorage = userStorage;
    }

    public void start() {
        cw.output("Hi there!");
        cw.startMenu();
        switch (cr.getInt()) {
            case 1:
                while(!signUp()){}
                cw.output("Congrats! You're successfully signed up");
        }
        int code = calculate();
        cw.output("Application was stopped with code " + code);
    }

    private boolean signUp() {
        boolean result = false;
        cw.output("How can I call you?");
        String name = cr.getWord();
        cw.output("Nice! What do you wanna use as login?");
        String login = cr.getWord();
        cw.output("Choose a password for your account");
        String password = cr.getWord();
        if (userStorage.userSignUp(login, name, password)) {
            result = true;
        } else {
            cw.output("Something went wrong");
        }
        return result;
    }

    private int calculate() {
        boolean isActive = true;
        while (isActive) {
            cw.output("Please, input first variable: ");
            double var1 = cr.getDouble();
            cw.output("Please, input second variable: ");
            double var2 = cr.getDouble();
            cw.output("What do you want to do?");
            cw.output("Input 1 - for sum\nInput 2 - for minus\nInput 3 - for multiply\nInput 4 - for divide");

            int answer = cr.getInt();
            double result;
            switch (answer) {
                case 1:
                    result = Calculator.sum(var1, var2);
                    cw.outputResult(result);
                    break;
                case 2:
                    result = Calculator.minus(var1, var2);
                    cw.outputResult(result);
                    break;
                case 3:
                    result = Calculator.multiply(var1, var2);
                    cw.outputResult(result);
                    break;
                case 4:
                    result = Calculator.divide(var1, var2);
                    cw.outputResult(result);
                    break;
                default:
                    cw.output("Something went wrong. Try again");
                    continue;
            }
            cw.output("Do you want to continue?\nInput 0 for exit or any other number to continue");
            int userAnswer = cr.getInt();
            if (userAnswer == 0) isActive = false;
        }
        System.out.println("Bye!");
        return 0;
    }
}