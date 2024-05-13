        
package yolcuları.koltuklara.atama;

import java.util.ArrayList;
import java.util.Random;

public class Main{   
    
    public static void main(String[] args){
        
        double[][] uzaklikMatrixi;
        double[][] bosMatrix;
        double value;
        ArrayList<Integer> otobus;
        double min;
        int eklenecek = 0;
        ArrayList<Double> otobusUzakliklar = new ArrayList<>();
        int minKisi;
        Random random = new Random();
        int satirSayisi = 0;
        String[] isimler = {"Emir","Yağız","Ege","Çağan", "Sarp", "Kerem", "Deniz",
            "Kağan", "Mert", "Görkem","Burak","Meriç","Berke","Efe"," Doruk","Bartu",
            "Emirhan","Zeynep"," Ela","Duru","Yaren","Ceren","Ece","Melis","Naz",
            "Su","Berra","Ecem","Sena","İrem","Alara","Azra","Dilay","İdil","Eylül",
            "İpek","Yağmur","Ahmet", "Emre", "Yasin"};
        ArrayList<String> isimlerDuzenli = new ArrayList<>();;
        ArrayList[][] koltukDuzeni;
        
        bosMatrix= new double[40][40];
        uzaklikMatrixi = uzaklikMatrixiOlusturma(bosMatrix);
        
        ArrayList<Integer> bosOtobus = new ArrayList<>();
        otobus = otobuseYerlesme(bosOtobus, uzaklikMatrixi);
        
        for (int i=0; i<40; i++){
            int kisiSirasi = (int) otobus.get(i);
            isimlerDuzenli.add(isimler[kisiSirasi]);
        }
        
        for (int i=0; i<40; i++){
            for (int k=0; k<40; k++){
                System.out.print(uzaklikMatrixi[i][k]);
                System.out.print("   ");
            }
            System.out.println(" ");
        }
        for (int i=0; i<10; i++){
            satirSayisi = i*4;
        
            for (int j=0; j<2; j++){
                System.out.print(otobus.get(satirSayisi+j) + "        ");
            }
            System.out.print("        ");
            for (int k=2; k<4; k++){
                System.out.print(otobus.get(satirSayisi+k) + "        ");
            }
            System.out.println(" ");
            
            
            for (int j=0; j<2; j++){
                System.out.print(isimlerDuzenli.get(satirSayisi+j) + "   ");
            }
            System.out.print("           ");
            for (int k=2; k<4; k++){
                System.out.print(isimlerDuzenli.get(satirSayisi+k) + "   ");
            }
            System.out.println(" ");
        }
            otobusUzakliklar.add(0.0);
            for (int j=1; j<4; j++){
                otobusUzakliklar.add(uzaklikMatrixi[otobus.get(j)][otobus.get(j-1)]);
                }
            
            for (int i=4; i<40; i++){
                int k = i+1;
                if ((k%4)==0){
                        otobusUzakliklar.add(uzaklikMatrixi[otobus.get(i)][otobus.get(i-1)]+
                                uzaklikMatrixi[otobus.get(i)][otobus.get(i-4)]+
                                uzaklikMatrixi[otobus.get(i)][otobus.get(i-5)]);
                    
                }
                
                else if((((k+1)%4)==0) || (k%2) == 0){
                        otobusUzakliklar.add(uzaklikMatrixi[otobus.get(i)][otobus.get(i-1)]+
                                uzaklikMatrixi[otobus.get(i)][otobus.get(i-3)]+
                                uzaklikMatrixi[otobus.get(i)][otobus.get(i-4)]+
                                uzaklikMatrixi[otobus.get(i)][otobus.get(i-5)]);
                    }
                else{
                        otobusUzakliklar.add(uzaklikMatrixi[otobus.get(i)][otobus.get(i-3)]+
                                uzaklikMatrixi[otobus.get(i)][otobus.get(i-4)]);
                }

            }
            for (int i=0; i<10; i++){
            satirSayisi = i*4;
            
                for (int j=0; j<2; j++){
                    System.out.print((Math.round(otobusUzakliklar.get(satirSayisi+j)*100.0)/100.0) +  "     ");
                }
                System.out.print("           ");
                for (int k=2; k<4; k++){
                    System.out.print((Math.round(otobusUzakliklar.get(satirSayisi+k)*100.0)/100.0)+ "     ");
                }
                System.out.println(" ");
            }
            
            int uzaklikToplam = 0;
            for (int i=0; i < 40; i++){
                uzaklikToplam += otobusUzakliklar.get(i);
            }
            System.out.println(uzaklikToplam);
    }
    public static double[][] uzaklikMatrixiOlusturma(double[][] matrix){
        Random random = new Random();
        double value = 0;
        for (int i=0; i < (40); i++){
            for (int j=0; j < (40); j++){
                
                if (i < j){
                    double randomDeger = (10) * random.nextDouble();
                    value = Math.round(randomDeger*10.0)/10.0;
                    matrix[i][j] = value;
                }
                
                else if (i == j){
                    matrix[i][j] = 0;
                }
                
                else if (i > j){
                    matrix[i][j] = matrix[j][i];
                }
            }
        }
        return matrix;
    }
    
    public static ArrayList<Integer> otobuseYerlesme(ArrayList<Integer> otobus, double [][] uzaklikMatrixi){
        double value;
        double min;
        int eklenecek = 0;
        int minKisi;
        Random random = new Random();
        minKisi = random.nextInt((40));
        otobus.add(minKisi);
        for (int j=0; j<3; j++){
            min = 11;
            for (int i=0; i<40; i++){
                if (min > uzaklikMatrixi[minKisi][i] && !(otobus.contains(i))){
                    min = uzaklikMatrixi[minKisi][i];
                    eklenecek = i;
                }
            }
            minKisi = eklenecek;
            otobus.add(eklenecek);
        }
        for (int i=4; i<40; i++){
            int k = i+1;
            if ((k%4)==0){
                min = 100;
                for(int j=0; j<(40); j++){
                    value = uzaklikMatrixi[otobus.get(i-1)][j] + uzaklikMatrixi[otobus.get(i-4)][j] + uzaklikMatrixi[otobus.get(i-5)][j];
                    if (min > value && !(otobus.contains(j))){
                        min = value;
                        eklenecek = j;
                    }
                }
                otobus.add(eklenecek);
            }
            else if((((k+1)%4)==0) || (k%2) == 0){
                min = 100;
                for(int j=0; j<(40); j++){
                    value = uzaklikMatrixi[otobus.get(i-1)][j] + uzaklikMatrixi[otobus.get(i-3)][j] +
                            uzaklikMatrixi[otobus.get(i-4)][j] + uzaklikMatrixi[otobus.get(i-5)][j];
                    if (min>value && !(otobus.contains(j))){
                        min = value;
                        eklenecek = j;
                    }
                }
                otobus.add(eklenecek);
            }
            else{
                min = 100;
                for(int j=0; j<(40); j++){
                    value = uzaklikMatrixi[otobus.get(i-3)][j] + uzaklikMatrixi[otobus.get(i-4)][j];
                    if (min>value && !(otobus.contains(j))){
                        min = value;
                        eklenecek = j;
                    }
                }
                otobus.add(eklenecek);
            }
        }
        return otobus;
    }
}