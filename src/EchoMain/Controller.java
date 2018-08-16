/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoMain;

/**
 *
 * @author jackmarsh
 */
public class Controller implements Runnable{

    @Override
    public void run() {
        // Record Sound
        RecordSound r = new RecordSound();
        r.record();
        
        if (!RecordSound.inter) {
            // Change to Answering state
            GUI.answeringNow = true;
            GUI.answeringMode();
            EchoMain.echo.askQuestion();
            
            // Speech to text
            SpeechToText s = new SpeechToText();
            String toWolfram = s.s2t();
            // Wolfram Alpha
            Computational c = new Computational();
            String toSpeak = c.compute(toWolfram);
            // Text to speech
            TextToSpeech t = new TextToSpeech();
            t.t2s(toSpeak);
            
            // Change to listening state
            GUI.answeringNow = false;
            GUI.answeringMode();
            EchoMain.echo.setEchoState(EchoMain.echo.getOffState());
            EchoMain.pwrBtn(EchoMain.t);
        }
        
    }
    
}
