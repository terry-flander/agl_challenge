package au.com.terryflander;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.json.*;

/**
 * Fetch JSON data from URL and return de-normalised data in an Array of Pet instances.
 */
class Pets {

  public static String getPetsByTypeFormatHtml(String sourceUrl, String petType) {
    String result = null;

    try {
      String lastGender = "";

      List<Pet> allPets = getPetListFromUrl(sourceUrl);
      result = getHtmlPetsFromList(allPets, petType);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String getHtmlPetsFromList(List<Pet>allPets, String petType) {
    StringBuilder result = new StringBuilder();

    try {
      String lastGender = "";

      List<Pet> somePets = getPetListByPetType(allPets, petType);

      for (Pet pet: somePets) {
        if (!lastGender.equals(pet.getGender())) {
          if (lastGender.length()!=0) {
            result.append("</ul>");
          }
          result.append(pet.getGender()).append("<ul>");
        }
        result.append("<li>").append(pet.getPetName()).append("</li>");
        lastGender = pet.getGender();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return result.toString();
  }

  private static List<Pet> getPetListByPetType(List<Pet> allPets, String petType) {
    List<Pet> result = null;
    try {
      result = allPets
          .stream()
          .filter(e -> e.getType().equals(petType))
          .sorted((p1, p2) -> p2.getGender().compareTo(p1.getGender()) != 0 ?
              p2.getGender().compareTo(p1.getGender()) :
              p2.getPetName().compareTo(p1.getPetName()) * -1)
          .collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private static List<Pet> getPetListFromUrl(String sourceUrl) {
    String text = getUrlText(sourceUrl);
    JSONArray arr = convertTextToJsonArray(text);
    return getPetList(arr);
  }

  protected static List<Pet> getPetList(JSONArray arr) {
    List<Pet> result = new ArrayList<>();
    for (int i = 0; i < arr.length(); i++) {
      JSONObject owner = arr.getJSONObject(i);
      JSONArray pets = getPets(owner);
      if (pets.length() != 0) {
        for (int j = 0; j < pets.length(); j++) {
          JSONObject pet = pets.getJSONObject(j);
          result.add(new Pet(owner.getString("name"),
              owner.getString("gender"),
              owner.getInt("age"),
              pet.getString("type"),
              pet.getString("name")));
        }
      }
    }
    return result;
  }

  public static JSONArray getPets(JSONObject owner) {
    JSONArray result = new JSONArray();
    if (!owner.isNull("pets")) {
      result = owner.getJSONArray("pets");
    }
    return result;

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

  public static String wrapJson(String source) {
    String result = source;
    if (result.length()==0) {
      result = "[]";
    }
    if (!result.startsWith("{")) {
        result = "{data:" + result + "}";
    }
    return result;
  }

  public static String getUrlText(String urlName) {
    StringBuilder result = new StringBuilder();
    try {
      URL url = new URL(urlName);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null)
        result.append(inputLine);
      in.close();
    } catch (Exception e) {
      System.out.println("ERROR: Url invalid");
    }
    return result.toString();
  }

}
