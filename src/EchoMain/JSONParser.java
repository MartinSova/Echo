package EchoMain;

/**
 * The JSONParser is used to parse input JSON data from WolframAlpha and
 * Microsoft Speech-to-Text and get the relevant text output as a plaintext
 * String.
 *
 * @author Egor Grigorev
 * @version 1.0
 * @since 2017-02-18
 */
public class JSONParser {

    public float confidence; // store values for confidence from cognitive/wolfram
    public boolean success = true; //

    /**
     * Parses the JSON data sent back from WolframAlpha and extracts the
     * JsonObject which contains the plaintext answer to the query given. It
     * then returns this as a String.
     *
     * @param json is the input JSON data
     * @return result is the output plaintext String once the JSON has been
     * parsed
     */
    public String parseWolfram(String json) {

        // TODO: More robust Exception handling!!!!
        String result = "";
        String check;
        int firstIndex, secondIndex;

        this.success = true; //Redundancy

        try {
            
            // This block checks whether Wolfram has successfully processed the input query
            firstIndex = json.indexOf("success");
            firstIndex = json.indexOf(":", firstIndex);
            secondIndex = json.indexOf(",", firstIndex);
            check = json.substring(firstIndex + 2, secondIndex); //string result begins 2 chars from first index
            
            
            if (check.equals("false")) {
                result = "I'm sorry, but I couldn't quite understand your question.";
                success = false;
            } else {
                //finds the relevant "plaintext" json object
                firstIndex = json.indexOf("plaintext");
                secondIndex = json.indexOf("plaintext", firstIndex + 1);

                //string result begins 14 chars from secondIndex
                firstIndex = secondIndex + 14;
                secondIndex = json.indexOf("\"", firstIndex + 0);

                result = json.substring(firstIndex, secondIndex);

            }
        } catch (Exception e) {
            System.out.println("ERROR! MissingWolframException!");
            /*
            TEST IF WE NEED THIS (PROBABLY NOT)
            GUI.answeringNow = false;
            GUI.answeringMode();
            EchoMain.echo.setEchoState(EchoMain.echo.getOffState());
            EchoMain.pwrBtn(GUI.t);
            */
        }

        return clean(result);
    }

    /**
     * Parses the JSON data sent back from Cognitive Services and extracts the
     * JsonObject which contains the plaintext answer to the query given. It
     * then returns this as aERROR! MissingException! String.
     *
     * @param json is the input JSON data
     * @return result is the output plaintext String once the JSON has been
     * parsed
     */
    public String parseCognitive(String json) {

        // TODO: More robust Exception handling!!!!
        String result = "";
        String check;
        int firstIndex, secondIndex;

        this.success = true; //Redundancy

        try {
            
            // This block checks whether or not Cognitive services has succeeded in parsing the input data
            firstIndex = json.indexOf("status");
            secondIndex = json.indexOf(":", firstIndex);
            firstIndex = json.indexOf("\"", secondIndex);
            secondIndex = json.indexOf("\"", firstIndex + 1);

            check = json.substring(firstIndex + 1, secondIndex);
            
            if (!check.equals("success")) {
                success = false;
            } else {
                firstIndex = json.indexOf("name") + 7;
                secondIndex = json.indexOf("\"", firstIndex);

                result = json.substring(firstIndex, secondIndex);
                
            }
            
        } catch (Exception e) {
            System.out.println("ERROR! MissingCognitiveException!");
            /*
            PROBABLY DON'T NEED THIS
            GUI.answeringNow = false;
            GUI.answeringMode();
            EchoMain.echo.setEchoState(EchoMain.echo.getOffState());
            EchoMain.pwrBtn(GUI.t);
            */
        }

        return result;
    }

    public String clean(String input) {
        /**
         * Takes a String (input) and strips all line feeds, carriage returns
         * and tabs contained within the text.
         *
         * @param input Unformatted string containing \t || \r || \n
         * @return result Formatted output string
         */

        input = input.replace("\\n", " ").replace("\\t", " ").replace("\\r", " ");
        String result = input.replace("\n", " ").replace("\t", " ").replace("\r", " ");

        return result;

    }

}
