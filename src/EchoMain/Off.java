/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoMain;

/**
 * This class defines how our Echo works when it is in it's 'Off' state NOTE:
 * The print statements are to prove that our state transitions work correctly
 * and will be taken out in the next sprint
 */
public class Off implements EchoState {

    Echo echo;

    /*
     * Creates Off echo object
     *
     * @param newEcho Echo object in Off state 
     */
    public Off(Echo newEcho) {
        echo = newEcho;
    }

    /*
     * Overriden method
     * Turns Echo on and changes state to 'Listening'
     */
    @Override
    public void turnOnOff() {
        echo.setEchoState(echo.getListeningState());

    }

    /*
     * Overriden method
     * Doesn't do anything in Off state
     */
    @Override
    public void mute() {
    }

    /*
     * Overriden method
     * Doesn't do anything in Off state
     */
    @Override
    public void askQuestion() {
    }

}
