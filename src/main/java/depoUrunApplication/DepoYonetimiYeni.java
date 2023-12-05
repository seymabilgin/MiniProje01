package depoUrunApplication;

import java.util.*;

public class DepoYonetimiYeni {

    private static Map<Integer, Urun> urunListesi = new HashMap<>();
    private static Set<Integer> kullanilmayanIdler = new HashSet<>();
    private static int sonId = 1001;
    private static Scanner input = new Scanner(System.in);

    //Onceki DepoYonetimine ekledigimiz yenilikler :

   // Kullanıcı Girişi Yönetimi olarak : Kullanıcıdan sayısal ve metinsel giriş almak için kullanılan yardımcı metodlar ekledik
    // (kullaniciSayiGirisiAl, kullaniciMetinGirisiAl) iyi bir uygulama. Bu, kod tekrarını azaltır ve girişlerin doğruluğunu sağlar.

    // Kullanıcıdan sayısal giriş almak için yardımcı metod

    private static int kullaniciSayiGirisiAl(String mesaj) {
        int sayi = -1;
        while (sayi == -1) {
            try {
                System.out.println(mesaj);
                sayi = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Hata: Lütfen geçerli bir sayı giriniz!");
                input.nextLine();
            }
        }
        return sayi;
    }

    // Kullanıcıdan metinsel giriş almak için yardımcı metod

    private static String kullaniciMetinGirisiAl(String mesaj, String hataMesaji, int minUzunluk) {
        String giris;
        do {
            System.out.println(mesaj);
            giris = input.nextLine().trim().toLowerCase();
            if (giris.length() < minUzunluk) {
                System.out.println(hataMesaji);
            }
        } while (giris.length() < minUzunluk);
        return giris;
    }

   // Id olusturma

     private static int YeniIdOlustur() {
        if (kullanilmayanIdler.isEmpty()) {
            return sonId++;
        } else {
            int minKullanilmayanId = kullanilmayanIdler.stream().min(Integer::compareTo).get();
            kullanilmayanIdler.remove(minKullanilmayanId);
            return minKullanilmayanId;
        }
    }

    // Urun tanimlama

    public static void urunTanimla() {
        System.out.println("*** URUN TANIMLAMA ***");
// Urun özellikleri alınıyor
        String isim = kullaniciMetinGirisiAl("Urun ismini giriniz: ", "Urun ismi en az iki karakterden olusmalidir.", 2);
        String uretici = kullaniciMetinGirisiAl("Ureticisini giriniz:", "Uretici ismi en az iki karakterden olusmalidir.", 2);
        String birim = kullaniciMetinGirisiAl("Birimini giriniz: (cuval,koli,teneke)", "Birim ismi en az iki karakterden olusmalidir.", 2);

        // Yeni ürün mevcut degilse yeni urun  oluşturuluyor ve listeye ekleniyor

        boolean urunMevcut = false;

        for (Urun urun : urunListesi.values()) {
            if (urun.getUrunIsmi().equalsIgnoreCase(isim) &&
                    urun.getUretici().equalsIgnoreCase(uretici) &&
                    urun.getBirim().equalsIgnoreCase(birim)) {
                System.out.println("Bu isim ve üretici ile zaten bir ürün mevcut. Ürün ID'si: " + urun.getId());
                urunMevcut = true;
                break;
            }
        }

        if (!urunMevcut) {
            Urun yeniUrun = new Urun(YeniIdOlustur(), isim, uretici, 0, birim, null);
            urunListesi.put(yeniUrun.getId(), yeniUrun);
            System.out.println("Yeni ürün başarıyla eklendi. Ürün ID'si: " + yeniUrun.getId());
        }
    }


    // Urun bulma

    public static Urun urunBul(int id) {

        return urunListesi.get(id);
    }

        // Urun listeleme

    public static void urunListele() {
        System.out.println("*** URUN LISTESI ***");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-18s %-20s %-15s %-15s %-10s\n", "ID", "İsmi", "Üreticisi", "Miktarı", "Birimi", "Raf");
        System.out.println("-----------------------------------------------------------------------------------------");

        urunListesi.values().forEach(urun -> System.out.printf("%-10d %-18s %-20s %-15d %-15s %-10s\n",
                urun.getId(),
                urun.getUrunIsmi(),
                urun.getUretici(),
                urun.getMiktar(),
                urun.getBirim(),
                (urun.getRafNo() == null) ? "-" : urun.getRafNo()));

        System.out.println("-----------------------------------------------------------------------------------------");
    }

    // Urun miktari girisi

    public static void urunMiktariGiris() {
        System.out.println("*** URUN GIRISI ***");
        int id = kullaniciSayiGirisiAl("Urun ID'sini giriniz:");
        Urun urun = urunBul(id);

        if (urun != null) {
            int eklenenMiktar;
            do {
                eklenenMiktar = kullaniciSayiGirisiAl("Eklemek istediğiniz miktarı giriniz (0'dan büyük bir değer olmalı):");
                if (eklenenMiktar <= 0) {
                    System.out.println("Hata: Miktar 0'dan büyük olmalıdır.");
                }
            } while (eklenenMiktar <= 0);

            urun.setMiktar(urun.getMiktar() + eklenenMiktar);
            System.out.println("Urun girişi başarılı. Eklenen miktar: " + eklenenMiktar + " " + urun.getBirim() +
                    ". Yeni miktar: " + urun.getMiktar() + " " + urun.getBirim());
        } else {
            System.out.println("Hata: Bu ID'ye sahip bir ürün bulunamadı!");
        }
    }


    // Urunu rafa koyma

    public static void urunRafaKoy() {
        System.out.println("*** URUNU RAFA KOY ***");
        int id = kullaniciSayiGirisiAl("Urun ID'sini giriniz:");
        Urun urun = urunBul(id);
        if (urun != null) {
            String idStr = String.valueOf(id);
            String rafNo = idStr.length() > 3 ? idStr.substring(idStr.length() - 3) : idStr; // ID'nin son üç rakamını alır veya ID yeterince uzun değilse tümünü alır.
            urun.setRafNo(rafNo);
            System.out.println("Urun rafa yerleştirildi: Raf No " + rafNo);
        } else {
            System.out.println("Hata: Bu ID'ye sahip bir ürün bulunamadı.");
        }

    }

    // Urun cikisi

    public static void urunCikisi() {
        System.out.println("*** URUN CIKISI ***");
        int id = kullaniciSayiGirisiAl("Urun ID'sini giriniz:");
        Urun urun = urunBul(id);

        if (urun != null) {
            int cikarilanMiktar;
            do {
                cikarilanMiktar = kullaniciSayiGirisiAl("Cikarmak istediğiniz miktarı giriniz (0'dan büyük bir değer olmalı):");
                if (cikarilanMiktar <= 0) {
                    System.out.println("Hata: Miktar 0'dan büyük olmalıdır.");
                }
            } while (cikarilanMiktar <= 0);

            if (urun.getMiktar() >= cikarilanMiktar) {
                urun.setMiktar(urun.getMiktar() - cikarilanMiktar);
                System.out.println("Urun çıkışı başarılı. Çıkarılan miktar: " + cikarilanMiktar + " " + urun.getBirim() +
                        ". Kalan miktar: " + urun.getMiktar() + " " + urun.getBirim());
            } else {
                System.out.println("Yetersiz stok. Mevcut miktar: " + urun.getMiktar() + " " + urun.getBirim());
            }
        } else {
            System.out.println("Hata: Bu ID'ye sahip bir ürün bulunamadı!");
        }
    }

    // Urun silme

    public static void urunSilme() {
        System.out.println("*** URUN SILME ***");
        int id = kullaniciSayiGirisiAl("Urun ID'sini giriniz:");
        Urun urun = urunBul(id);

        if (urun != null) {
            urunListesi.remove(id);
            kullanilmayanIdler.add(id);
            System.out.println("Urun Listeden silinmistir");
        } else {
            System.out.println("Depoda bu urun bulunmamaktadir");
        }
    }
}
