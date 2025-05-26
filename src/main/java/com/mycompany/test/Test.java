
package com.mycompany.test;

import javax.swing.JOptionPane;

import javax.swing.JOptionPane;


public class Test {

    private static final int MAX_ACCOUNT = 100;
    public static Account[] accounts = new Account[MAX_ACCOUNT];
    public static int numOfAccount = 0;

    public static void main(String[] args) {
        //String usernameData = "kyl_1", passwordData = "Test_123", cellphoneData = "0050";

        String userName = JOptionPane.showInputDialog("Enter valid UserName: ");
        String password = JOptionPane.showInputDialog("Enter valid password: ");
        String cellphone = JOptionPane.showInputDialog("Enter valid phone number: ");

        Login login = new Login(userName, password, cellphone);

        JOptionPane.showMessageDialog(null, login.registerUser());

        userName = JOptionPane.showInputDialog("Enter valid UserName: ");
        password = JOptionPane.showInputDialog("Enter valid password: ");

        //JOptionPane.showMessageDialog(null, login.returnLoginStatus(userName, password));

        boolean status = login.loginUser(userName, password);

        if (!status){
            System.exit(-1);
        }
        Message.Run(status);
    }

}
