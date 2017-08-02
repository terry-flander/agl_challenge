package au.com.terryflander;

import org.json.JSONArray;
import org.json.JSONObject;

public class OwnerFactory {

  public static Owner buildOwnerFromJSON(JSONObject ownerObj) {

    JSONArray pets = getPets(ownerObj);

    Owner result = new Owner(ownerObj.getString("name"),
          ownerObj.getString("gender"),
          ownerObj.getInt("age"));

    if (pets.length() != 0) {
      for (int j = 0; j < pets.length(); j++) {
        JSONObject pet = pets.getJSONObject(j);
        result.addPet(pet.getString("type"),pet.getString("name"));
      }
    }
    return result;
  }

  private static JSONArray getPets(JSONObject owner) {
    JSONArray result = new JSONArray();
    if (!owner.isNull("pets")) {
      result = owner.getJSONArray("pets");
    }
    return result;

  }

}
