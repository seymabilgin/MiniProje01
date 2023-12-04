package depoUrunApplication;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
public class DepoYonetimi {

 // 3) DepoYonetimi Sinifi
// Bu sinifin amaci:
// Bu sınıf, depodaki ürünlerin yönetimini ve işlemlerini gerçekleştiren bir iş mantığı sınıfıdır.
// Bu sınıf, Urun nesnelerinin koleksiyonunu tutmak ve bu nesneler üzerinde işlemler yapmak içindir.
// Baslica 5 method ve yardimci metodlardan olusmaktadir.
//------------------------------------------------------------------------------------------------------------------
 // Map urunListesi :
// Oncelikle urünleri saklamak ve listelemek icin bir Map yapisi olusturduk,
// cunku bu yapi ürünlerin ID'lerine göre hızlı erişim sağlar, ve bu da ürün yönetimini kolaylaştırır.
// Bu yapi  ürün ekleme, güncelleme, silme ve sorgulama gibi işlemlerimiz icin kolaylik sagladi.

    //------------------------------------------------------------------------------------------------------------------

    private static Map<Integer, Urun> urunListesi = new HashMap<>();

    private static int sonId = 1001;    // yeni urun ilk id degeri: 1001 olacak sekilde ayarladik


    private static Scanner input = new Scanner(System.in);

    //------------------------------------------------------------------------------------------------------------------

//Metodlar:

 // 1) *** Urun Tanimlama Metodu ***

 //   urunTanimla(): Yeni bir urun nesnesi oluşturma ve listeye ekleme metodu

public static void urunTanimla ()  {

    System.out.println("*** URUN TANIMLAMA ***");

    System.out.println("---------------------------------------------------------------------------------------------");

    // Ürün ismi için veri doğrulama

    String isim;
   do {

    System.out.println("Urun ismini giriniz: ");

      isim=input.nextLine().trim().toLowerCase();

    if ( isim.length() < 2) {

        System.out.println("Urun ismi en az iki karakterden olusmalidir. ");
    }
    }while(isim.length()<2);

        String uretici;

// Üretici ismi için veri doğrulama

       do {
           System.out.println("Ureticisini giriniz:");
           uretici = input.nextLine().trim().toLowerCase();
           if (uretici.length() < 2) {
               System.out.println("Lutfen en az iki karakterden olusan bir uretici ismi giriniz.");
           }
       } while (uretici.length() < 2);

// Birim için veri doğrulama

    String birim;

    do {
        System.out.println("Birimini giriniz: (cuval,koli,teneke)");
        birim = input.nextLine().trim().toLowerCase();
        if (birim.length() < 2) {
            System.out.println("Lutfen en az iki karakterden olusan bir birim giriniz.");
        }
    } while (birim.length() < 2);

    // Urun sinifindan obje olusturduk (yeniUrun objesi)

    Urun yeniUrun = new Urun();

    // yeniIdOlustur() metodunu cagirdik ve yeniId ismi atadik

     int yeniId = yeniIdOlustur();

     // yeniUrun objesinin ozelliklerini kulanicinin verdigi degerler ile set metodu sayesinde degistirdik.

     yeniUrun.setId(yeniId);
     yeniUrun.setUrunIsmi(isim);
     yeniUrun.setUretici(uretici);
     yeniUrun.setBirim(birim);
     yeniUrun.setMiktar(0);    // Başlangıçta miktarı 0 olarak ayarladik
     yeniUrun.setRafNo(null); // Başlangıçta raf numarası null

// yeniurun objesini urunEkle metodunu cagirarak listeye ekledik

    urunEkle( yeniUrun);    // urun listeye ekleme

//Kullaniciya yeni urun Id sini bildirdik

    System.out.println("Yeni ürünün ID'si: " + yeniId);

    }  //metod sonu

    //------------------------------------------------------------------------------------------------------------------

    //Ana metodlarin icinde kullanilan ekstra yardimci metodlar :

 // a) yeniIdOlustur(): her yeni ürün eklediğinizde, sonId'yi artırarak yeni bir ID atayan metod:

    private static int yeniIdOlustur() {
        return sonId++;
    }

//  b) urunEkle(Urun urun): urunu listeye ekleme metodu

    public static void urunEkle(Urun urun) {

        if (urunBul(urun.getId()) == null) {
            urunListesi.put(urun.getId(), urun);
            System.out.println("Urun başarıyla eklendi" );
        } else {
            System.out.println("Bu ID'ye sahip bir ürün zaten var.");
        }
    }

// c) urunBul(int id): listeden urün arama metodu

    public static Urun urunBul(int id) {
        return urunListesi.get(id);
    }

    //------------------------------------------------------------------------------------------------------------------

    // 2) *** Urun Listeleme Metodu ***

    // urunListele () : Mevcut ürünlerin listesini görüntüler

    public static void urunListele () {

        System.out.println("*** URUN LISTESI ***");

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-18s %-20s %-15s %-15s %-10s\n",
                "ID", "İsmi", "Üreticisi", "Miktarı", "Birimi", "Raf");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (Urun urun1 : urunListesi.values()) {
            System.out.printf("%-10d %-18s %-20s %-15d %-15s %-10s\n",   //format ile yaziyi daha hizali hale getirdik
                    urun1.getId(),
                    urun1.getUrunIsmi(),
                    urun1.getUretici(),
                    urun1.getMiktar(),
                    urun1.getBirim(),
                    urun1.getRafNo() == null ? "-" : urun1.getRafNo());
        }

        System.out.println("-----------------------------------------------------------------------------------------");

                    System.out.println("\n");

                    // Urun sınıfındaki toString() metodunu override ettik ve Listeyi son hali ile kullaciya goruntuledik

                }// metod sonu

    //------------------------------------------------------------------------------------------------------------------

    // 3) *** Urun Girisi Metodu ***

    // urunMiktariGiris(): Belirli bir ID'ye sahip ürünün miktarını arttirir

    public static void urunMiktariGiris ()  {


        System.out.println("*** URUN GIRISI ***");
        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Urun ID'sini giriniz:");

        //Kullanicinin girdigi id degeri icin veri dogrulama yaptik
        int id = -1;
        while (id == -1) {
            try {                         //  Try Catch ile int veri dogrulama
                id = input.nextInt();
                input.nextLine();         //  buffer
            } catch (InputMismatchException e) {
                System.out.println("Hata:Lütfen geçerli bir ID giriniz!");
                input.nextLine();        // Geçersiz girişi atlamak için kullanılır.
            }
        }

        /*try-catch Kullanımı: Kullanıcının sayı dışında bir giriş yapması durumunda
             InputMismatchException hatası oluşabilir.
             Bu durumu ele almak için try-catch blokları kullandik,
             kullanıcıdan beklenmeyen bir girdi geldiğinde programın çökmesini önledi.*/


        // urunBul(id) metodu ile urunu listede arattik
        // eger urun mevcut ise urun girisi yaptik ve kullaniciya yeni miktari belirttik

        Urun urun = urunBul(id);

        if (urun != null) {

            int eklenenMiktar=0;      // Veri dogrulama:int sayi degeri olmali ve 0 dan buyuk olmali

            do {
                System.out.println("Eklemek istediğiniz miktarı giriniz:");

                if (!input.hasNextInt() || (eklenenMiktar = input.nextInt()) <= 0) {

                    System.out.println("Hata: Lütfen 0'dan buyuk bir sayı degeri giriniz!");

                    input.nextLine(); // Geçersiz girdiyi temizleyin ve döngüyü devam ettirin
                    continue;
                }
                input.nextLine(); // Yeni satır karakterini temizle

            } while (eklenenMiktar <= 0);

            urun.setMiktar(urun.getMiktar() + eklenenMiktar);

            System.out.println("Urun girişi başarılı. Yeni miktar: " + urun.getMiktar() + " " + urun.getBirim());
        } else {
            System.out.println("Hata: Bu ID'ye sahip bir ürün bulunamadı!");

        }

    } //metod sonu

    //------------------------------------------------------------------------------------------------------------------

    // 4) *** Urunu Rafa Koyma ***

// urunRafaKoy (): Belirli bir ID'ye sahip ürün için raf numarasını günceller

    public static void urunRafaKoy ()  {

        System.out.println("*** URUNU RAFA KOY ***");
        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Urun ID'sini giriniz:");

        int id = -1;
        while (id == -1) {     // int veri dogrulama
            try {
                id = input.nextInt();
                input.nextLine(); //  buffer
            } catch (InputMismatchException e) {
                System.out.println("Hata: Lütfen geçerli bir ID giriniz!");
                input.nextLine(); // Geçersiz girişi atlamak için kullanılır.
            }
        }

        // urunBul(id) metodu ile urunu listede arayip eger var ise urun icin urun id ye uyumlu raf numarasi veriyoruz.

        Urun urun = urunBul(id);

        if (urun != null) {
            String rafNo= String.valueOf(urun.getId()).substring(1);
            urun.setRafNo(rafNo);
            System.out.println("Urun rafa yerleştirildi: " + "Raf"+ rafNo);
        } else {
            System.out.println("Hata: Bu ID'ye sahip bir ürün bulunamadı.");
        }

    } //metod sonu

    //------------------------------------------------------------------------------------------------------------------

    // 5) *** Urun Cikisi ***

// urunCikisi ( ): Belirli bir ID'ye sahip ürünün miktarını azaltan metod

    public static void  urunCikisi ( )  {

        System.out.println("*** URUN CIKISI ***");
        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Urun ID'sini giriniz:");

        int id = -1;
        while (id == -1) {     // int veri dogrulama
            try {
                id = input.nextInt();
                input.nextLine(); //  buffer
            } catch (InputMismatchException e) {
                System.out.println("Hata: Lütfen geçerli bir ID giriniz.");
                input.nextLine(); // Geçersiz girişi atlamak için kullanılır.
            }
        }

        Urun urun = urunBul(id);

            if(urun != null) {

                int cikarilanMiktar=0;      // Veri dogrulama: Deger sayi degeri olmali ve 0 dan buyuk olmali

                do {
                    System.out.println("Cikarmak istediğiniz miktarı giriniz (0'dan büyük bir değer olmalı):");

                    if (!input.hasNextInt() || (cikarilanMiktar = input.nextInt()) <= 0) {

                        System.out.println("Hata: Lütfen 0'dan buyuk bir sayı degeri giriniz!");

                        input.nextLine(); // Geçersiz girdiyi temizleyin ve döngüyü devam ettirin
                        continue;
                    }
                    input.nextLine(); // Yeni satır karakterini temizle

                } while (cikarilanMiktar <= 0);

            if ( urun.getMiktar() >= cikarilanMiktar) {

                urun.setMiktar(urun.getMiktar() - cikarilanMiktar);
                System.out.println("Urun çıkışı başarılı. Kalan miktar: " + urun.getMiktar() + " " + urun.getBirim());

            } else  {
                    System.out.println("Yetersiz stok. Mevcut miktar: " + urun.getMiktar() + " " + urun.getBirim());   //miktar 0 'in altina dusmemeli

                }

            } else {
                System.out.println("Hata: Bu ID'ye sahip bir ürün bulunamadı!");
            }

        }//metodsonu

    //------------------------------------------------------------------------------------------------------------------

   // Projemizin taniminda olmayan istege bagli eklenebilecek bir metod

    //  *** Urun Silme Methodu ***

// urunSil(): Bu method Belirli bir Id ye sahip urunu listeden siler

    public static void urunSil() {

        System.out.println("*** URUN SILME ***");
        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Urun ID'sini giriniz:");

        int id = -1;
        while (id == -1) {     // int veri dogrulama
            try {
                id = input.nextInt();
                input.nextLine(); //  buffer
            } catch (InputMismatchException e) {
                System.out.println("Hata: Lütfen geçerli bir ID giriniz!");
                input.nextLine(); // Geçersiz girişi atlamak için kullanılır.
            }
        }

        // urunBul(id) metodu ile listeden urunu arattik, eger mevcut ise remove(id) metodu ile listeden sildik

        Urun urun = urunBul(id);

        if (urun != null) {
            urunListesi.remove(id);    // Mevcut urunu listeden tum ozellikleri ile beraber mi siliniyor denenmeli
            System.out.println("Urun Listeden silinmistir");
        } else {
            System.out.println("Depoda bu urun bulunmamaktadir");

        }
    }

    //  input.close();  // Scanner nesnesini kapat

}  //class sonu


//Ekstra Notlar ; Bu kisim projede sunulmayacak

// Hata Kontrolleri ve Kullanıcı Girdisi Doğrulaması:

// Her metodta, kullanıcı girdisi alırken ve bu girdileri işlerken hata kontrolleri ve
// doğrulamalar yapmak önemlidir. Özellikle nextInt() ve nextLine() kullanımlarında dikkatli olunmalıdır.

// Hata Kontrolleri: Miktar azaltma işlemi sırasında miktarın 0'ın altına düşmemesi gibi durumlar için kontrol onemlidir.

/*

// Alternatif veri dogrulama yontemleri biz diger yollari tercih ettik

System.out.println("Ürün miktarını giriniz:");
while (!scanner.hasNextInt()) {
    System.out.println("Hata: Lütfen geçerli bir sayı giriniz!");
    scanner.next(); // Geçersiz girdiyi temizleyin
}
int miktar = scanner.nextInt();

Kullanıcı hatalı bir giriş yaptığında, program hata yakalamaz;
sadece doğru türde bir giriş bekler.

//Alternatif veri dogrulama yontemi

   int eklenenMiktar=-1;   // int veri dogrulama

             while(eklenenMiktar==-1) {

                 try {
                     eklenenMiktar = input.nextInt();
                     input.nextLine();  //buffer

                 } catch (InputMismatchException e) {

                     System.out.println("Lütfen geçerli bir miktar giriniz.");
                     input.nextLine(); // Geçersiz girişi atlamak için kullanılır.
                 }
             }


 int cikarilanMiktar=-1;   // int veri dogrulama

                while(cikarilanMiktar==-1) {

                    try {
                        cikarilanMiktar = input.nextInt();
                        input.nextLine();  //buffer

                    } catch (InputMismatchException e) {

                        System.out.println("Lütfen geçerli bir miktar giriniz.");
                        input.nextLine(); // Geçersiz girişi atlamak için kullanılır.
                    }
                }

 System.out.println("Urun ID'sini giriniz:");   //  Veri dogrulama  int degerlerde karakter girisini engellemek

        int id = -1;
        while (id == -1) {    // always true
            try {
                id = input.nextInt();
                input.nextLine(); // newline karakterini temizle(input buffer)
            } catch (InputMismatchException e) {
                System.out.println("Lütfen geçerli bir ID giriniz.");

                input.nextLine(); // Geçersiz girişi atlamak için kullandik
            }
        }

 Urun urun = urunBul(id);   // listede yok ise null verir

            if (urun == null) {
                System.out.println("Bu ID'ye sahip bir ürün bulunamadı.");

            } else {
     */


