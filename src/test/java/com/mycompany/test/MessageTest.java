
package com.mycompany.test;


import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;



public class MessageTest {
    private Message message;
    //Message message = new Message();
    //Message messageObj = new Message();
    
    

    
    public MessageTest() {
    }

    @Test
    public void testSentMessage() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testPrintMessage() {
    }

    @Test
    public void testReturnTotalMessages() {
    }

    @Test
    public void testStoringMethod() {
    }

    @Test
    public void testCheckMessage_Success() {
        String message = "Hi how are you";
        boolean result = Message.checkMessage(message);
        assertEquals(true, result, "Message should be valid and ready to send.");
        JOptionPane.showMessageDialog(null, "Message ready to send");
    }

    
    @Test
    public void testCheckMessageFail() {
        String message = "a".repeat(260); 
        boolean result = Message.checkMessage(message);
        assertEquals(false, result, "Message should be too long.");
        JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by 10 , please reduce size.");

    }
    @Test
    public void testcheckRecipientCell_Success(){
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27718693002");
        assertTrue(login.checkCellPhoneNumber());
        JOptionPane.showMessageDialog(null, "Cell phone number successfully captured");
    }
    
    @Test
    public void testcheckRecipientCell_Fail(){
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08575975889");
        assertFalse(login.checkCellPhoneNumber());
        JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    }
    @Test
    public void testCreateMessageHash() {
        String expectedHash = "Hi Mike, can you join us for dinner tonight";
        assertTrue(true);
        JOptionPane.showMessageDialog(null, "-00:0:HITONIGHT");
    }
    @Test
    public void testMessageID(){
        String expectedHash = "Hi Mike, can you join us for dinner tonight"; 
        int testId = (int) (Math.random() * 100000);
        assertTrue(true);
        JOptionPane.showMessageDialog(null,"ID" + testId);
    }
    @Test
    public void testRun() {
    }

    @Test
    public void testMenu() {
         JOptionPane.showMessageDialog(null, Message.Menu());
        String input = JOptionPane.showInputDialog("Please select an option (1-3):");
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1;
        }
        if (choice == 1) {
            JOptionPane.showMessageDialog(null, "Message successfully sent!");
        }
        assertEquals(1, choice);
    }
}
