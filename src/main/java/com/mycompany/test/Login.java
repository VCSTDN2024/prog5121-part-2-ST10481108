
package com.mycompany.test;


public class Login {
    private Account account;
    
    public Login(String username, String password, String cellPhoneNumber){
        
        account = new Account(username, password, cellPhoneNumber);
        System.out.println(account.toString());
    }

    public boolean checkUserName() {
        String username = account.getUsername();
        if (username.equalsIgnoreCase("")) {
            return false;
        }
        if (!username.contains("_")) {
            return false;
        }
        if (username.length() > 5) {
            return false;
        }
        return true;
    }

    public boolean checkPasswordComplexity() {
        String password = account.getPassword();
        if (password.equalsIgnoreCase("")) {
            return false;
        }
        if (password.length() < 8) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasUppercase = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (!Character.isDigit(c) && !Character.isLetter(c)) {
                hasSpecial = true;
            }
            if (hasDigit && hasUppercase && hasSpecial) {
                break;
            }
        }
        if (!hasDigit || !hasUppercase || !hasSpecial) {
            return false;
        }

        return true;

    }

    public boolean checkCellPhoneNumber() {
        //return account.getPhoneNumber().matches("7");
        return account.getPhoneNumber().matches("^\\+\\d{8,15}$");
        //return account.getPhoneNumber().matches("+" + "27");
        //return account.getPhoneNumber().matches("^\\+\\d{1,3}\\d{7,10}$");
        //return account.getPhoneNumber().contains("5");
        //return account.getPhoneNumber().contains("5");
        //return true;
        //return account.getPhoneNumber().startsWith("+27");
    }

    public String registerUser() {
        String result = "";

        if (!checkUserName()) {
            result = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
            
            return result;
        } else {
            result += "Username successfully captured\n";
        }

        if (!checkPasswordComplexity()) {
            result = "Password is not correctly formated please ensure that the passsword contains at least eight characters, a capital letter, a number, and a special character.\n";
            result += "/n";
            
            return result;
        } else {
            result += "Password successfully captured\n";
        }
        if (!checkCellPhoneNumber()) {
            result = "Cell phone number incorrectly formatted or does not contain international code\n";

            
            return result;
        } else {
            result += "Cell phone number successfully added";
        }

        result += "Registeration was successful!";
       Test.accounts[Test.numOfAccount] = account;
        Test.numOfAccount++;

        return result;
    }

    public boolean loginUser(String enterUsername, String enterPassword) {
        return this.account.getUsername().equals(enterUsername) && 
                this.account.getPassword().equals(enterPassword);
    }

    public String returnLoginStatus(String enterUsername, String enterPassword) {
        if (loginUser(enterUsername, enterPassword)) {

            String[] usernameSplit = enterUsername.split("_");

            String message = String.format("Welcome %s, %s it is great to see you", usernameSplit[0], usernameSplit[1]);
            
            return message;
        } 
        return "A failed login";
       
        
    }

}
