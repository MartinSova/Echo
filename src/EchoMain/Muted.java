package EchoMain;

/**
 * This class defines how our Echo works when it is in it's 'Muted' state 
 * NOTE: The print statements are to prove that our state transitions work correctly 
 *       and will be taken out in the next sprint
 */
public class Muted implements EchoState {
    
    Echo echo;
    
    /*
     * Creates Muted echo object
     *
     * @param newEcho Echo object in Muted state 
    */
    public Muted(Echo newEcho){
        echo = newEcho; 
    }
    
    /*
     * Overriden method
     * Turns Echo off and changes state to 'Off'
    */
    @Override
    public void turnOnOff() {
        echo.setEchoState(echo.getOffState());
    }
    
    /*
     * Overriden method
     * Unmutes Echo and changes state to 'Listening'
    */
    @Override
    public void mute() {
        echo.setEchoState(echo.getListeningState());
    }
    
    /*
     * Overriden method
     * Doesn't do anything in this state
    */
    @Override
    public void askQuestion() {
    }
}