package au.com.terryflander;

/**
 * Applet to fetch Pet List from URL and output as HTML
 *
 */
class AglPets {

  public static void main(String[] args) {
    try {
      if (args.length > 2) {
        System.err.println("AglPets usage: [ <url>  [petType] ]");
        System.err.println("(defaults to: http://agl-developer-test.azurewebsites.net/people.json Cat");
        System.exit(1);
      } else {
        String sourceUrl = args.length > 0 ? args[0]
            : "http://agl-developer-test.azurewebsites.net/people.json";
        String petType = args.length > 1 ? args[1] : "Cat";

        System.out.println(Pets.getPetsByTypeFormatHtml(sourceUrl, petType));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
