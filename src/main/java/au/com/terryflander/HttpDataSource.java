package au.com.terryflander;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONArray;

public class HttpDataSource extends PetDataSource {

  public JSONArray getPetData() {
    StringBuilder result = new StringBuilder();
    try {
      URL url = new URL(sourceName);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null)
        result.append(inputLine);
      in.close();
    } catch (Exception e) {
      System.out.println("ERROR: Url invalid");
    }
    return convertTextToJsonArray(result.toString());
  }

}