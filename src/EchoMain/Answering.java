
package EchoMain;

/**
 * This class defines how our Echo works when it is in it's 'Answering' state 
 * NOTE: The print statements are to prove that our state transitions work correctly 
 *       and will be taken out in the next sprint
 */
public class Answering implements EchoState {
    
    boolean questionAnswered = true; 
    Echo echo;
    
    /*
     * Creates Answering object
     *
     * @param newEcho Echo object in Answering state 
    */
    public Answering(Echo newEcho){
        echo = newEcho; 
    }
    
    /*
     * Overriden method
     * Doesn't do anything in Answering state
    */
    @Override
    public void turnOnOff() {
        System.out.println("Can't turn echo off in answering mode");
       
    }
    
    /*
    * Overriden method
    * Doesn't do anything in Answering state
    */
    @Override
    public void mute() {
        System.out.println("Can't mute the echo in answering mode");
    }
    
    /*
     * Overriden method
     * Changes the state once a question has been answered
    */
    @Override
    public void askQuestion() {
        // Once question has been answered change to listening state
        if(questionAnswered = true){
             System.out.println("Question answered, back to listening");
             echo.setEchoState(echo.getListeningState());
        }
    }
   
}