package bank.text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author owt79
 */
public class InvalidAccountTypeException extends Exception {

    public InvalidAccountTypeException() {
        super("Invalid Account Type Selected.");
    }
    
}
