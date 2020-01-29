/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flagprediction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Habibullah
 */
public class Flag {
    private FIS fis;
    private final double area;
    private final double population;
    private final double bars;
    private final double stripes;
    private final double colours;
    private final double sunstars;
    private final double landmass;
    private final double language;
    private final double religion;
    private double tahmin;
    
    public Flag(double area, double population, double bars, double stripes, double colours, double sunstars, double landmass, double language, double religion) throws URISyntaxException {
        this.area = area;
        this.population = population;
        this.bars = bars;
        this.stripes = stripes;
        this.colours = colours;
        this.sunstars = sunstars;
        this.landmass = landmass;
        this.language = language;
        this.religion = religion;
        
        File dosya = new File(getClass().getResource("model.fcl").toURI());
        fis = FIS.load(dosya.getPath(), true);
        
        fis.setVariable("area", area);
        fis.setVariable("population", population);
        fis.setVariable("bars", bars);
        fis.setVariable("stripes", stripes);
        fis.setVariable("colours", colours);
        fis.setVariable("sunstars", sunstars);
        fis.setVariable("landmass", landmass);
        fis.setVariable("language", language);
        fis.setVariable("religion", religion);
        fis.evaluate();
    }
    
    public FIS getModel() {
        return fis;
    }

    @Override
    public String toString() {
         String cikti = "kapladığı alan : " + area + "\n nüfus : " + population
         + "\n dikey çizgi sayısı : " + bars + "\n yatay çizgi sayısı : " + stripes
         + "\n renk sayısı : " + colours + "\n simge sayısı : " + sunstars
         + "\n kıta : " + landmass + "\n dil : " + language + "\n din : " + religion;
         tahmin=Math.round(fis.getVariable("flag").getValue());
         cikti += "\n Bayrak No : " + tahmin;
         return cikti;  
    }
    
   static void bayrakTahmin() throws URISyntaxException {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Kapladığı Alan(0-22402) : ");
        final int area = scan.nextInt();
        System.out.print("Nüfus(0-1008) : ");
        final int population = scan.nextInt();
        System.out.print("Dikey Çizgi Sayısı(0-5) : ");
        final int bars = scan.nextInt();
        System.out.print("Yatay Çizgi Sayısı(0-14) : ");
        final int stripes = scan.nextInt();
        System.out.print("Renk Sayısı(1-8) : ");
        final int colours = scan.nextInt();
        System.out.print("Simge Sayısı(0-50) : ");
        final int sunstars = scan.nextInt();
        System.out.print("Kıta (1-6) : ");
        final int landmass = scan.nextInt();
        System.out.print("Dil (1-10) : ");
        final int language = scan.nextInt();
        System.out.print("Din (0-7) : ");
        final int religion = scan.nextInt();
        
        Flag bayrak = new Flag(area, population, bars, stripes, colours, sunstars, landmass, language, religion);
        System.out.println("-----------------------------");
        System.out.println(bayrak);
        
        JFuzzyChart.get().chart(bayrak.getModel());
        JFuzzyChart.get().chart(bayrak.getModel().getVariable("flag"),true);
        JFuzzyChart.get().chart(bayrak.getModel().getVariable("flag").getDefuzzifier(), "Bayrak" , true);
        
        //rule yazdırma
        System.out.println("Rules :");
        final List<Rule> rules = bayrak.getModel().getFunctionBlock("model").getFuzzyRuleBlock("kuralBlok1").getRules();
        final List<Rule> workedRules = rules.stream().filter(rule -> rule.getDegreeOfSupport() > 0).collect(Collectors.toList());
        for (Rule rule : workedRules) {
            System.out.println(rule);
        }
        
    }
    
    static void analyze() throws FileNotFoundException, IOException, URISyntaxException  {
        
        double area[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double population[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double bars[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double stripes[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double colours[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double sunstars[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double landmass[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double languages[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double religions[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double flags[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
       FileInputStream file = new FileInputStream("data/bayrak.xlsx");
       XSSFWorkbook wb = new XSSFWorkbook(file);
       XSSFSheet sayfa = wb.getSheet("Sheet1");
       //area = new String[sayfa.getRow(6)];
       for(int i = 0; i < 193; i++) {
            area[i] = sayfa.getRow(i+1).getCell(4).getNumericCellValue();  // E hücreleri
       }
       for(int i = 0; i < 193; i++) {
            population[i] = sayfa.getRow(i+1).getCell(5).getNumericCellValue();
       }
       for(int i = 0; i < 193; i++) {
            bars[i] = sayfa.getRow(i+1).getCell(8).getNumericCellValue();
       }
       for(int i = 0; i < 193; i++) {
            stripes[i] = sayfa.getRow(i+1).getCell(9).getNumericCellValue();
       }
       for(int i = 0; i < 193; i++) {
            colours[i] = sayfa.getRow(i+1).getCell(10).getNumericCellValue();
       }
       for(int i = 0; i < 193; i++) {
            sunstars[i] = sayfa.getRow(i+1).getCell(23).getNumericCellValue();
       }
       for(int i = 0; i < 193; i++) {
            landmass[i] = sayfa.getRow(i+1).getCell(2).getNumericCellValue();
       }
       for(int i = 0; i < 193; i++) {
            languages[i] = sayfa.getRow(i+1).getCell(6).getNumericCellValue();
       }
       for(int i = 0; i < 193; i++) {
            religions[i] = sayfa.getRow(i+1).getCell(7).getNumericCellValue();
       }
       for(int i = 0; i < 193; i++) {
            flags[i] = sayfa.getRow(i+1).getCell(1).getNumericCellValue();
       }
       
      int sayacDogru = 0;
      int sayacYanlis = 0;
       
       for(int i = 0; i < 193; i++) {
           
            Flag bayraklar = new Flag(area[i], population[i], bars[i], stripes[i],colours[i], sunstars[i], landmass[i], languages[i], religions[i]);
            System.out.println("tahmin değeri : " + bayraklar);
            System.out.println("Gerçek Değer :" + flags[i]);
            System.out.println("-------------------------------");
            
            
            if(bayraklar.tahmin == flags[i]) {
                sayacDogru++;
            }
            else {
                sayacYanlis++;
            }
        }
       
        System.out.println("Doğru Sayısı : " + sayacDogru);
        System.out.println("Yanlış Sayısı : " + sayacYanlis);
        
        //Classification Hesaplanması
        int hataHesabi;

        hataHesabi = (100*sayacYanlis)/193;
        System.out.println("Hata Yüzdesi : %" + hataHesabi);
 
    }

}
