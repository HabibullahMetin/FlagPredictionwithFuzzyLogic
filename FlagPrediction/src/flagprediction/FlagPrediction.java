/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flagprediction;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Habibullah
 */
public class FlagPrediction {

   
    public static void main(String[] args) throws URISyntaxException, IOException {
        
        System.out.println("Bulanık");
       //Flag.bayrakTahmin();  // tek tek bayrağı tahmin etmek için gereken fonksiyon çağrısı
        Flag.analyze();  // tüm verisetini okuyup hata hesabının yapıldığı fonksiyon çağrısı
    }
 
}
