import java.nio.file.*;
import java.util.Arrays;
import java.io.IOException;
import java.net.URISyntaxException;

public class Energiemix {

  private static String csvToPlot(String csv, String countryCode) {
    // TODO implement conversion
    return String.format("1965 1.518143%n1966 1.637798%n1967 1.559359%n1968 1.482081");
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
