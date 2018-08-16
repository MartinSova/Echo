/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoMain;

import static EchoMain.EchoMain.echo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author martinsova
 */
public class PowerButton extends JButton {

    /**
     * power button constructor
     *
     * @param img - image used for the button
     */
    PowerButton(ImageIcon img) {
        setIcon(img); // setting the icon to the ImageIcon in the parameter (img)
        setBorder(null); // setting border to null
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GUI.pwrDownClick(); // simulate a power button down click

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (echo.getEchoState() == echo.answering) {
                    GUI.pwrBtnReleaseClickAnsMode();
                    // if in answering mode only simulate an "empty" click without an action to it
                } else {
                    /* if in muted state when turned off, revert to listening so all states
                     are correctly set when turned off again (or muted in off state) */
                    if (echo.getEchoState() == echo.muted) {
                        echo.setEchoState(echo.getListeningState());
                    }
                    GUI.pwrReleaseClick(); // simulate power button click (on release)
                    EchoMain.pwrBtn(EchoMain.t);
                    // call power button method in EchoMain (since power button has been pressed)
                }
            }
        });
    }
}
