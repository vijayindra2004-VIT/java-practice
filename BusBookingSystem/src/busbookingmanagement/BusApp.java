/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busbookingmanagement;

/**
 *
 * @author guruv
 */
import busbookingmanagement.gui.user.LoginFrame;

public class BusApp {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });

    }
}
