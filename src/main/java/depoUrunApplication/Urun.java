package depoUrunApplication;

public class Urun     {

    // 2) Urun Sinifi
//Bu sinifin amaci:
//Bu sınıf bir pojo sinifidir. Yani her ürünün özelliklerini (id, urunIsmi, uretici, miktar, birim rafNo) içeren bir modeldir.
//Bu sınıfın sorumluluğu, ürünle ilgili bilgileri saklamak ve yönetmektir.
//Sinifta urune ait private fieldlar,bunalra ulasmak ve uzerinde degisiklik yapmak icin getter ve setter metodlari,
// bir default,bir paremetreli constructor ve toString metodunu icermektedir(urun objesinin string temsili).

    //------------------------------------------------------------------------------------------------------------------

    //Urun ozellikleri(fields):

   private int id;

   private String urunIsmi;

   private  String uretici;

   private int miktar;

   private String birim;

   private String rafNo;

    //------------------------------------------------------------------------------------------------------------------

   //getter - setter metodlari :

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrunIsmi() {
        return urunIsmi;
    }

    public void setUrunIsmi(String urunIsmi) {
        this.urunIsmi = urunIsmi;
    }


    public String getUretici() {
        return uretici;
    }

    public void setUretici(String uretici) {
        this.uretici = uretici;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String  getRafNo() {
        return rafNo;
    }

    public void setRafNo(String rafNo) {
        this.rafNo = rafNo;
    }

    //------------------------------------------------------------------------------------------------------------------

// Default Constructor:

   public Urun()  {

   }

    //------------------------------------------------------------------------------------------------------------------

   //Parametreli Constructor:

  public  Urun (int id , String urunIsmi, String uretici, int miktar, String birim, String rafNo) {

      this.id=id;
      this.urunIsmi=urunIsmi;
      this.uretici=uretici;
      this.miktar=miktar;
      this.birim=birim;
      this.rafNo=rafNo;
  }

    //------------------------------------------------------------------------------------------------------------------

  //  toString() metodu:

    @Override
    public String toString() {  // (urun objesinin string temsili)
        return "Urun{" +
                "id=" + id +
                ", urunIsmi='" + urunIsmi + '\'' +
                ", uretici='" + uretici + '\'' +
                ", miktar=" + miktar +
                ", birim='" + birim + '\'' +
                ", rafNo='" + rafNo + '\'' +
                '}';
    }

}  //class sonu


