package application;

public class BMICalc {
    public static Double getBMI(int id){
        double height = userManager.getUserProfile(id).height;
        double weight = userManager.getUserProfile(id).weight;
        return ((weight/height/height)*10000); //https://www.cdc.gov/nccdphp/dnpao/growthcharts/training/bmiage/page5_1.html
    }
}