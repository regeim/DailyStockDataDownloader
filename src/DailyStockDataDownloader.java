import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 * Created by Zoli on 2015.12.20..
 */
public class DailyStockDataDownloader {
    public static void main(String[] args) throws IOException {
        String fileDate = new SimpleDateFormat("yyyyMMdd'.txt'").format(new Date());
        File file = new File("C:\\Users\\Zoli\\Documents\\Stock\\DailyData\\results_quandl_"+fileDate);
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter writer = new FileWriter(file,true);
        try {
            Scanner scanner = new Scanner(new File("c:\\hello\\ticker_list.csv"));
             while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                String separator = System.getProperty("line.separator");
                String symbol=line.substring(0, line.indexOf(","));
                //System.out.print(symbol+separator);
                String key="uTxBCsJfy9NY1HhnpwNu";

                     String address = "https://www.quandl.com/api/v3/datasets/WIKI/" + symbol + "/data.csv?api_key="+key+"&rows=1";
                     URL link = new URL(address);
                     try {
                     InputStream in = new BufferedInputStream(link.openStream());
                     ByteArrayOutputStream out = new ByteArrayOutputStream();
                     byte[] buf = new byte[1024];
                     int n = 0;
                     while (-1 != (n = in.read(buf))) {
                         out.write(buf, 0, n);
                     }
                     out.close();
                     in.close();
                     byte[] response = out.toByteArray();
                     String responseText = new String(response, "UTF-8");
                     String actualText=new String(symbol+","+responseText.replace("Date,Open,High,Low,Close,Volume,Ex-Dividend,Split Ratio,Adj. Open,Adj. High,Adj. Low,Adj. Close,Adj. Volume","").replaceAll("(\\r|\\n)", "")+"\r\n");
                     writer.append(actualText);
                     writer.flush();
                     } catch (Exception e) {
                      // e.printStackTrace();
                     }
             }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.close();
    }
    }

