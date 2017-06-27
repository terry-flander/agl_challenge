package au.com.terryflander;

@SuppressWarnings("unused")
class Pet {
  private final String ownerName;
  private final String gender;
  private final int age;
  private final String type;
  private final String petName;

  public Pet (String ownerName, String gender, int age, String type, String petName) {
    this.ownerName = ownerName;
    this.gender = gender;
    this.age = age;
    this.type = type;
    this.petName = petName;
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

  public String getType() {
    return this.type;
  }

  public String getPetName() {
    return this.petName;
  }

}
