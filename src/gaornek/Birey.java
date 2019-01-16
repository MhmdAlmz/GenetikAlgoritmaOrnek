package gaornek;

public class Birey {

	//Virg�lden sonra ilk 10 karakterlik hassasiyette arayaca��z sistemi.
	//T�m onluk par�alardaki birlerin toplam� , o an ki say� de�erini verecek.
	private  byte[] genler=new byte[Baslangic.genAdedi];
	private double fitness=0;

	private double aDegeri=3.5;
	private int birlerinSayisi=0;
	private int[] sayilarinSayisi;
	
	public void sayilarinSayisiniDegistir(int[] sayilarinSayisi) {
		this.sayilarinSayisi=sayilarinSayisi;
	}
	public int[] sayilarinSayisiniGetir() {
		return this.sayilarinSayisi;
	}
    public void birlerinSayisiDegistir(int sayi) {
    	this.birlerinSayisi=sayi;
    }
    public int birlerinSayisiGetir() {
    	return this.birlerinSayisi;
    }
    

    public void aDegeriniDegistir(double aDegeri) {
    	this.aDegeri=aDegeri;
    }
    public double aDegeriniGetir() {
    	return this.aDegeri;
    }
	
	public void bireyOlustur() {
		for (int i=0;i<Baslangic.genAdedi;i++)
		{
				genler[i]=(byte) Math.round(Math.random());
		}
	}
	public byte geniGetir(int indis) {
		return genler[indis];
		
	}
	
	public void geniDegistir(int indis,byte deger)
	{
		fitness = 0;
		genler[indis]=deger;
	}
	
	
	public double fitnessGetir() {
		if(fitness==0)
		{
			fitness=FitnessHesapla.fitnessGetir(this);
			
		}
		return fitness;
	}
	
	public byte[] genleriGetir() {
		return this.genler;
	}
	
	@Override
    public String toString() {
		//geni okumak i�in ihtiyac�m�z var
        String genDizilimi = "";
        for (int i = 0; i < Baslangic.genAdedi; i++) {
        	genDizilimi += geniGetir(i);
        }
        return genDizilimi;
    }
	
}
