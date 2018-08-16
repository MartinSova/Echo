package EchoMain;

import javax.swing.SwingUtilities;

/**
 *
 * @author Jack+Martin
 */
public class EchoMain {

    static Echo echo = new Echo(); //starts in off
    public static String TOKEN = "";
    static Thread t = new Thread(new Controller());
// a RecordSound() thread
    
    /*
     * Renew an access token --- they expire after 10 minutes.
     */
    static String renewAccessToken(String key1) {
        final String method = "POST";
        final String url
                = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
        final byte[] body = {};
        final String[][] headers
                = {{"Ocp-Apim-Subscription-Key", key1}, {"Content-Length", String.valueOf(body.length)}
                };
        byte[] response = HttpConnect.httpConnect(method, url, headers, body);
        return new String(response);
    }
    /**
     *
     * @author Jack+Martin
     * @param t a Thread that runs RecordSound
     */
    public static synchronized void pwrBtn(Thread t) {

        if (echo.getEchoState() == echo.off) {
            // Turn on echo
            echo.turnOnOff();
            // Create Thread
            t = new Thread(new Controller());

            // Thread isnt interrupted
            RecordSound.inter = false;
            startThread(t);

        } else if (echo.getEchoState() == echo.listening) {
            // Turn off echo
            echo.turnOnOff();
            // Thread is interrupted
            RecordSound.inter = true;
            interruptThread(t);
        }
    }

    /**
     * @author Jack+Martin
     * @param t a Thread for recording from microphone
     */
    public static synchronized void muteBtn(Thread t) {
        // Mute recording thread
        echo.mute();
        if (echo.getEchoState() == echo.muted) {
            // Interrupt Thread
            RecordSound.inter = true;
            interruptThread(t);
        } else {
            // Restart recording
            t = new Thread(new Controller());
            RecordSound.inter = false;
            startThread(t);
        }

    }

    /**
     * @author Jack+Martin
     * @param t a Thread to be started
     */
    public static synchronized void startThread(Thread t) {
        t.start();
    }

    /**
     * @author Jack+Martin
     * @param t a Thread to be interrupted
     */
    public static synchronized void interruptThread(Thread t) {
        t.interrupt();
    }

    public static void main(String[] args) {
        // Initialise GUI using swing utilities
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });

        // Create thread to renew token every 9 mins
        Thread tokenRefresh = new Thread(() -> {
            while (true) {
                try {
                    final String KEY1 = "6cfd3579bfee48fc97efde7e9e03d742";

                    TOKEN = renewAccessToken(KEY1);
                    Thread.currentThread().sleep(9 * 60 * 1000);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        tokenRefresh.start();
    }
}
