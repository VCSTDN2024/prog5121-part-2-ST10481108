
package com.mycompany.test;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    //adding message to list
    public static int numberOfMessage = 0;
    public static List<Message> messageList = new ArrayList<>();


    private long id;
    private final int messageNum;
    private final String cellphoneNum;
    private final String message;

    public Message(int messageNum, String cellphoneNum, String message){
        
        //using random for message id
        Random random = new Random();
        this.id = 1000000000L + random.nextInt(900000000);
        if (!checkMessageID()){
            System.out.println("Incorrect id generated");
        }
        this.messageNum = messageNum;
        this.cellphoneNum = cellphoneNum;
        if (checkRecipientCell() == 0){
            System.out.println("Number doesn't exist");
        }
        this.message = message;
    }

    private boolean checkMessageID(){
        return String.valueOf(id).length() == 10;
    }

    private int checkRecipientCell(){
        
        //checking recipeint cellphone format
        return (cellphoneNum != null && cellphoneNum.length() == 10) ? 1 : 0;
    }

    private String createMessageHash(){
        
        //making Message Hashes
        String idValue = String.valueOf(id).substring(0,2);
        String messageNumValue = String.valueOf(messageNum);
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String messageValue = firstWord + lastWord;
        return String.format("%s:%s:%s", idValue, messageNumValue, messageValue);
    }

    public static String sentMessage(){
        return """
                Option 1) Send Message
                Option 2) Disregard Message
                Option 3) Store Message to send later
                """;
    }

    @Override
    public String toString() {
        
        //message format for storing 
        return String.format("MessageID: %d, Message Hash: %s, Recipient: %s, Message: %s.\n",
                id, createMessageHash(), cellphoneNum, message);
    }

    public static String printMessage(){
        
        //printing list of messages sent
        StringBuilder result = new StringBuilder();
        for (Message message : messageList){
            result.append(message.toString());
        }
        return result.toString();
    }

    public static int returnTotalMessages(){
        return messageList.toArray().length;
    }

    public void storingMethod() {
        
        //message for storing strings
        FileWriter newFile;
        try {
            newFile = new FileWriter("messages.json", true);

            String fileString = String.format("%d,%d,\"%s\",%s", id, messageNum, message, cellphoneNum);

            newFile.write (fileString);

            newFile.flush();
            newFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean checkMessage(String message){
        
        //checking string length
        if (message.length() < 250) {
            JOptionPane.showMessageDialog(null, ("Message ready to send"));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
            return false;
        }
    }
    public static void createMessage(int index) {
        String recipient = JOptionPane.showInputDialog("Please enter the recipient's phone number: ");

        String message;
        do {
            message = JOptionPane.showInputDialog("Please enter the message: ");
        } while (!Message.checkMessage(message));

        Message newMessage = new Message(index, recipient, message);

        int decision;
        while (true) {
            String input = JOptionPane.showInputDialog(null, Message.sentMessage());
            try {
                decision = Integer.parseInt(input);
                if (decision >= 1 && decision <= 3) {
                    break;
                }
                JOptionPane.showMessageDialog(null,"Invalid choice. Please enter 1, 2, or 3.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Invalid input. Please enter a number.");
            }
        }

        switch (decision) {
            case 1:
                JOptionPane.showMessageDialog(null,"Message was sent");
                JOptionPane.showMessageDialog(null, newMessage);
                Message.messageList.add(newMessage);
                break;
            case 2:
                JOptionPane.showMessageDialog(null,"Message was discarded");
                break;
            case 3:
                newMessage.storingMethod();
                JOptionPane.showMessageDialog(null, "Message was stored");
                break;
            default:
                break;
        }
    }
    public static void Run(boolean status){
        String welcomeMess = "Welcome to QuickChat.";
        JOptionPane.showMessageDialog(null,welcomeMess);
        int response;

        while (status){
            response = Integer.parseInt(JOptionPane.showInputDialog(Menu()));
            switch (response){
                case 1:
                    Message.numberOfMessage = Integer.parseInt(JOptionPane.showInputDialog("How many message do you wish to send?"));

                    createAllMessage();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Coming soon");
                    break;
                case 3:
                    status = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Please enter a valid option 1,2 or 3");
            }
        }
        JOptionPane.showMessageDialog(null, "Total Message sent: " + Message.returnTotalMessages());
        JOptionPane.showMessageDialog(null, Message.printMessage());
    }
     public static String Menu(){
        String result = "       Menu \n";
        result += "-".repeat(70);
        result += "\n";
        result += """
                Option 1) Send Message
                Option 2) Show recently sent messages
                Option 3) Quit
               """;
        return result;
    }
     private static void createAllMessage() {
        for (int index = 1; index < (Message.numberOfMessage + 1); index ++){
            //JOptionPane.showInputDialog("Message: " + index + " out of " + Message.numberOfMessage);
            Message.createMessage(index);
        }
     }
}


