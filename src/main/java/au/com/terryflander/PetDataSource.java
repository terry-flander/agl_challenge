package au.com.terryflander;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Multiple sources for JSON Owner/Pet objects are allowed but the format must always be validated
 * to prevent errors occuring when unpacking Objects. Accomodate some variation in package received.
 */
abstract public class PetDataSource {

  protected static String sourceName = null;

  abstract public JSONArray getPetData();

  public void setSourceName(String source) {
    sourceName = source;
  }

  public static String getSourceName() {
    return sourceName;
  }

  public static JSONArray convertTextToJsonArray(String text) {
    JSONArray result = new JSONArray();
    try {
      JSONObject obj = new JSONObject(wrapJson(text));
      result = obj.getJSONArray("data");
    } catch (Exception e) {
      System.out.println("ERROR: Could not parse text as valid JSON: " + text);
    }
    return result;
  }

  protected static String wrapJson(String source) {
    String result = source;
    if (result.length()==0) {
      result = "[]";
    }
    if (!result.startsWith("{")) {
      result = "{data:" + result + "}";
    }
    return result;
  }
}
