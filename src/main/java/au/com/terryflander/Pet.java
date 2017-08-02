package au.com.terryflander;

class Pet {
  private final String type;
  private final String petName;

  public Pet (String type, String petName) {
    this.type = type;
    this.petName = petName;
  }

  public String getType() {
    return this.type;
  }

  public String getPetName() {
    return this.petName;
  }

}
