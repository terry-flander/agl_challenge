package au.com.terryflander;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.json.*;

/**
 * Fetch JSON data from PetDataSource and return HTML Formatted list of Pets by Type,
 * sorted by Owner gender and then alphabetically by Pet Name.
 */
class Pets {

  public static String getPetsByTypeFormatHtml(PetDataSource pds, String petType) {
    String result = null;

    try {

      List<Owner> allOwners = getOwnerList(pds.getPetData());

      List<ReportPet> reportPets = getPetsFromOwners(allOwners);

      reportPets = selectPetsByPetType(reportPets, petType);

      result = formatHtmlFromReportPetList(reportPets);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  protected static List<Owner> getOwnerList(JSONArray arr) {
    List<Owner> result = new ArrayList<>();

    for (int i = 0; i < arr.length(); i++) {
      result.add(OwnerFactory.buildOwnerFromJSON(arr.getJSONObject(i)));
    }
    return result;
  }

  protected static List<ReportPet> getPetsFromOwners(List<Owner> allOwners) {
    @SuppressWarnings("Convert2Diamond") List<ReportPet> result = new ArrayList<ReportPet>();
    for (Owner owner: allOwners) {
      if (owner.getPets()!=null) {
        for (Pet pet: owner.getPets()) {
          result.add(new ReportPet(owner.getGender(), pet.getType(), pet.getPetName()));
        }
      }
    }
    return result;
  }

  protected static List<ReportPet> selectPetsByPetType(List<ReportPet> allPets, String petType) {
    List<ReportPet> result = null;
    try {
      result = allPets
          .stream()
          .filter(e -> e.getPetType().equals(petType))
          .sorted((p1, p2) -> p2.getGender().compareTo(p1.getGender()) != 0 ?
              p2.getGender().compareTo(p1.getGender()) :
              p2.getPetName().compareTo(p1.getPetName()) * -1)
          .collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  protected static String formatHtmlFromReportPetList(List<ReportPet>allPets) {
    StringBuilder result = new StringBuilder();

    try {
      String lastGender = "";
      for (ReportPet pet: allPets) {
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

  static class ReportPet {
    final String gender;
    final String petType;
    final String petName;

    public ReportPet (String gender, String petType, String petName) {
      this.gender = gender;
      this.petName = petName;
      this.petType = petType;
    }

    public String getGender() {
      return this.gender;
    }

    public String getPetType() {
      return this.petType;
    }

    public String getPetName() {
      return this.petName;
    }

  }

}
