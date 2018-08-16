
package EchoMain;

/**
 * This class defines how our Echo works when it is in it's 'Listening' state 
 * NOTE: The print statements are to prove that our state transitions work correctly 
 *       and will be taken out in the next sprint
 */
public class Listening implements EchoState {
    
    Echo echo;
    
    
    /*
     * Creates Listening echo object
     *
     * @param newEcho Echo object in Listening state 
    */
    public Listening(Echo newEcho){
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
    * Mutes the Echo and changes it state to 'Muted'
    */
    @Override
    public void mute() {
        echo.setEchoState(echo.getMutedState());
        
    }

    /*
    * Overriden method
    * Means a question has been asked by the user and changes state to 'Answering'
    */
    @Override
    public void askQuestion() {
        echo.setEchoState(echo.getAnsweringState());
        
    }
    

    
}
