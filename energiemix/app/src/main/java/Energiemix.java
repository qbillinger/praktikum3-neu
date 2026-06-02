import java.nio.file.*;
import java.util.Arrays;
import java.io.IOException;
import java.net.URISyntaxException;

public class Energiemix {
  /**
   * Verarbeitet eine CSV-Datei und berechnet den Anteil erneuerbarer Energien
   * pro Jahr für ein bestimmtes Land.
   *
   * @param csv         Der Inhalt der CSV-Datei als String
   * @param countryCode Der ISO-3-Ländercode des gesuchten Landes (z.B. "DEU")
   * @return            Ein String mit Jahr und prozentualem Anteil erneuerbarer
   *                    Energien, eine Zeile pro Jahr (z.B. "1965 1.518143\n")
   */
  private static String csvToPlot(String csv, String countryCode) {
    // TODO implement conversion
    //split lines of csv document
    String[] csvLines = csv.split("\n");
    //String builder for result
    StringBuilder sb = new StringBuilder();
    //iterate through lines and split the columns
      for (int i = 1; i< csvLines.length; i++) {
            String[] csvLine = csvLines[i].split(",", -1);
            if(csvLine[1].equals(countryCode)){
              //get the values from csvLines
              double otherRenewables, biofuels, solar, wind, hydropower, nuclear, gas, coal, oil;
              double renewableRes, totalRes, partRes;
              otherRenewables = parseOrZero(csvLine[3]);
              biofuels = parseOrZero(csvLine[4]);
              solar = parseOrZero(csvLine[5]);
              wind = parseOrZero(csvLine[6]);
              hydropower = parseOrZero(csvLine[7]);
              nuclear = parseOrZero(csvLine[8]);
              gas = parseOrZero(csvLine[9]);
              coal = parseOrZero(csvLine[10]);
              oil = parseOrZero(csvLine[11]);
              //calculate the result
              renewableRes = otherRenewables + biofuels + solar + wind;
              totalRes = renewableRes + nuclear + gas + coal + oil;
              partRes = (renewableRes / totalRes) * 100;
              //append result to string builder
              sb.append(csvLine[2]).append(" ").append(partRes).append("\n");
            }
      }
    return sb.toString();
  }

  /**
   * Parses a string to a double and returns 0.0 if the string is empty
   * @param s String
   * @return double
   */
  private static double parseOrZero(String s){
    if(s.trim().isEmpty()) return 0.0;
    return Double.parseDouble(s);
  }

  public static void main(String[] args) {

    // DON'T CHANGE

    try {
      String csv = Files.readString(
          Paths.get(
              ClassLoader.getSystemClassLoader()
                  .getResource("energy-consumption-by-source-and-country.csv")
                  .toURI()));

      String countryCode = "DEU";
      String plotData = csvToPlot(csv, countryCode);
      Files.write(Paths.get("energiemix.txt"), Arrays.asList(plotData.split("\r?\n")));
    } catch (IOException ioException) {
      System.err.println(ioException.getMessage());
      System.exit(1);
    } catch (URISyntaxException malformedURLException) {
      System.err.println(malformedURLException.getMessage());
      System.exit(1);
    }

  }

}
