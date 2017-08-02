package au.com.terryflander;

import java.util.ArrayList;

public class Owner {
    private final String ownerName;
    private final String gender;
    private final int age;
    private final ArrayList<Pet> pets;

    @SuppressWarnings("Convert2Diamond")
    public Owner (String ownerName, String gender, int age) {
      this.ownerName = ownerName;
      this.gender = gender;
      this.age = age;
      this.pets = new ArrayList<Pet>();
    }

    public String getOwnerName() {
      return this.ownerName;
    }

    public String getGender() {
      return this.gender;
    }

    public int getAge() {
      return this.age;
    }

    public void addPet (String type, String petName) {
      this.pets.add(new Pet(type, petName));
    }

    public ArrayList<Pet> getPets() {
      return this.pets;
  }
}
