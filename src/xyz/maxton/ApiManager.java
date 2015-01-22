package xyz.maxton;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * The ApiManager Class
 * 
 * @author Max
 */
public class ApiManager {
    private Gson gson;
    private final String api = "http://cs.max-ton.net/api/api.php";
    private URL url;
    private final String secret="supersecret";
    
    /**
     * Default constructor.
     */
    public ApiManager(){
        gson = new Gson();
        try {
            url = new URL(api);
        } catch (MalformedURLException ex) {}
    }
    
    
    /**
     * Sends the score to the server, returning false if it could not connect.
     * The score can be any long, positive or negative. The name is truncated
     * by the server at 160 chars.
     * 
     * 
     * @param score the score of the game to send
     * @param name the name that the user inputs
     * @return true upon success, false upon failure
     */
    public boolean sendScore(long score,String name){
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            long unixTime = System.currentTimeMillis() / 1000L;
            String hash = DigestUtils.md5Hex(2*score+""+unixTime+secret);
            name = name.replace("\\", "\\\\");
            name = name.replace("\"","\\\"");
            String json = "{\"method\":\"upload\",\"data\":"
                    + "{\"score\":"+score+",\"name\":\""+name+"\",\"timestamp\":"+unixTime+",\"hash\":\""+hash+"\",\"secret\":\""+secret+"\"}}";
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", ""+json.length());
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = br.readLine();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    /**
     * Retrieves the high score list from the server.
     * 
     * @return array of the top 30 scores, in the ScoreObject format. (empty array if could not connect)
     */
    public ScoreObject[] getTopScores(){
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String json = "{\"method\":\"top\",\"data\":{\"limit\":30}}";
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", ""+json.length());
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = br.readLine();
            ApiResult fromJson = gson.fromJson(response, ApiResult.class);
            return fromJson.result;
        } catch (IOException ex) {
            Logger.getLogger(ApiManager.class.getName()).log(Level.SEVERE, null, ex);
            return new ScoreObject[] {};
        }
    }
}