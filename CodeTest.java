import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodeTest {

    public static void main(String args[]) {

        readInput();


    }

    private static void performCalculation(String filename, String equation) {
        double ds = 0;
        double mean = 0;

        try {
           System.out.println(System.getProperty("java.io.tmpdir"));
//           Stream<String> values = Files.lines(Paths.get("input.csv"));
           Stream<String> readFile = Files.lines(Paths.get(System.getProperty("java.io.tmpdir")+"\\"+filename));
           List<String> skippedHeader = readFile.skip(1).collect(Collectors.toList());
           List<InputData> dataList = new ArrayList<>();
            for (String s : skippedHeader) {
                String[] line = s.split(",");
                InputData data = new InputData(Integer.parseInt(line[0]),
                                                Integer.parseInt(line[1]));
                dataList.add(data);
            }

            for (InputData data : dataList) {
                double y1 = Double.parseDouble(String.format("%.1f",11.3 + 1.35*data.getX()));
                double ds1 = Double.parseDouble(String.format("%.1f",(y1-data.getY())));
                System.out.println("y1 " +y1);
                System.out.println("ds1 " +ds1);
                ds += Double.parseDouble(String.format("%.2f",ds1*ds1));
                System.out.println("Double value of ds is " + ds);

            }
            mean = Double.parseDouble(String.format("%.2f", ds /dataList.size()));
            System.out.println("Mean value is :: "+mean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInput() {
        System.out.println("Enter the input file name");
        Scanner scan = new Scanner(System.in);
        String filename = scan.next();
        if(filename.isBlank()){
            System.out.println("File name cannot be empty");
            scan.next();
        }
        System.out.println("Enter the formula");
        String formula = scan.next();
        performCalculation(filename, formula);
    }
}

class InputData{
    int x;
    int y;

    public InputData(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
