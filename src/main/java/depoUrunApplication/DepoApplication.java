package depoUrunApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

//                                     *** DepoUrunApplication ***

//DepoUrunApplication mini projemiz calisan bir fabrikada uygulanan sistemi simulize eden bir depo uygulamasidir.
//Bu proje 4 class tan olumaktadir. 1)DepoApplication    2)Urun    3)DepoYonetimi    4)ProjeInfo


public class DepoApplication extends DepoYonetimiYeni {    //Burada DepoApplication sinifindan DepoYonetimi sinifina IS - A relationship kurduk
                                                       //Boylece herhangi bir obje olmaksizin DepoYonetimi sinifindaki metodlara ulasabildik
    //------------------------------------------------------------------------------------------------------------------
   // 3) DepoApplication Sinifi
   //  Bu sinifin Amaci:
    // Bu sınıf, depo ürün işlem menüsünü ve kullanıcı etkileşimlerini yönetiyor.(Runner Class)
    // Bu amac icin mini projemizde temel bir depo yönetim sistemi geliştirdik.
    // Bu sistemde ürünlerin tanımlanması, listelenmesi, depoya girişi, rafta yerleştirilmesi
    // ve depodan çıkışı gibi işlevleri gerçekleştirecek bir uygulama oluşturduk.

    //------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {    //Ana method


        urunIslemMenusu();       // Islem secim menusu
    }


    //------------------------------------------------------------------------------------------------------------------

    private static void urunIslemMenusu ()   {

        Scanner input = new Scanner(System.in);

       // Kullanici hatali bir secim yaptiginda islem tekrari yapip duzeltmesini saglamak icin metodumuzda do while dongusunu kullandik

        int select = -1;   //Başlangıçta geçersiz bir değer atayarak döngüyü başlatıyoruz.

        do {
            System.out.println("=====================================================================================");
            System.out.println("                    ***       DEPO URUN ISLEM MENUSU        ***                      ");
            System.out.println("=====================================================================================");


            System.out.println(" Lutfen istediginiz islemin numarasini seciniz  " +
                    "\n1) Urun Tanimlama   ==> 1   \n2) Urun Listeleme   ==> 2   \n3) Depo Urun Girisi ==> 3 " +
                    "    \n4) Urunu Rafa Koyma ==> 4   \n5) Depo Urun Cikisi ==> 5    \n6) Urun Sil   \n0) CIKIS ");

            System.out.println("Islem No :  " );


            try {                                  // Islem numarasi icin veri dogrulama
                select = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Lütfen o ile 6 arasinda geçerli bir sayı giriniz");

                input.next(); // Geçersiz girişi atlamak için kullanılır.
                continue; // Döngünün başına dön.
            }
            


        System.out.println("-----------------------------------------------------------------------------------------");


        /*try-catch Kullanımı: Kullanıcının sayı dışında bir giriş yapması durumunda
             InputMismatchException hatası oluşabilir.
             Bu durumu ele almak için try-catch blokları kullandik,
             kullanıcıdan beklenmeyen bir girdi geldiğinde programın çökmesini önledi.*/

     //------------------------------------------------------------------------------------------------------------------

            switch(select) {

                case 1 :
                    urunTanimla();
                   break;

                case 2 :
                   urunListele ();
                   break;

                case 3 :
                  urunMiktariGiris();
                    break;

                case 4 :
                urunRafaKoy();
                    break;

                case 5 :
                 urunCikisi();
                    break;

                case 6:
                    urunSilme();
                    break;

                case 0 :
                    System.out.println("Iyi Gunler Dileriz");
                    break;

                default:
                    System.out.println("Hatali giris tekrar deneyiniz");
                    break;
            }


        }while(select!=0);  // Eger kullanici cikis icin 0 i secerse dongu bitiyor


      //  input.close();  // Scanner nesnesini kapat
    }

}  //class sonu


/*
Ekstra Notlar: Bu kisim projede sunulmayacak
Alternatif veri dogrulama
private DepoYonetimi depoYonetimi;

    public DepoApplication() {
        depoYonetimi = new DepoYonetimi(); // DepoYonetimi nesnesi oluşturuluyor
    }

    // Örnek bir metod
    public void urunListele() {
        depoYonetimi.urunListele(); // DepoYonetimi sınıfının metodunu kullanıyor
    }

    Projelerin çoğunda, kompozisyon tercih edilir (obje kullanimi), çünkü daha esnek bir yapı sağlar ve
    ileride sistemde yapılacak değişiklikleri daha kolay hale getirir.
    Ayrıca, sınıflar arasında daha temiz ve anlaşılır bir ilişki kurmanıza yardımcı olur.
 */