# agl_challenge
The application aglPets is designed to output by default the HTML formatted list of Cats by Male and Female owners. The source is the URL : http://agl-developer-test.azurewebsites.net/people.json and the selected pet will be "Cat". These values can be overidded at run time as described below. 

### Install

1. Clone or Download from GitHub
2. Unpack in `<work-directory>`
3. `cd to <work-directory>/agl_challenge`
4. `mvn clean package`

This will compile the source and run the test programs.

### Run

The application can run with no arguments and produce the required output. It can also allows one or two arguments to override the source url and if the url override is specified can choose a different pet 'type'.

Examples:

```
mvn exec:java -Dexec.mainClass=au.com.terryflander.AglPets
mvn exec:java -Dexec.mainClass=au.com.terryflander.AglPets -Dexec.args="http://agl-developer-test.azurewebsites.net/people.json"
mvn exec:java -Dexec.mainClass=au.com.terryflander.AglPets -Dexec.args="http://agl-developer-test.azurewebsites.net/people.json Cat"
mvn exec:java -Dexec.mainClass=au.com.terryflander.AglPets -Dexec.args="http://agl-developer-test.azurewebsites.net/people.json Dog"
```