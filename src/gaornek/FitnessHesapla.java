package gaornek;

import java.awt.List;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
public class FitnessHesapla {

	public static double fitnessGetir(Birey birey) {
		// sayiAdetleriDizi indis de�eri bizim i�in 0-15 aras�ndaki say�lar�n adedini temsil edecektir.

		byte[] bireyinGeni=birey.genleriGetir();
		String virguldenSonra="";
		double aDegeri=3.5;
		
		for(int i=0;i<Baslangic.hassasiyet;i++)
		{
			virguldenSonra+=sayiyiGetir(
					Arrays.toString(
							Arrays.copyOfRange(bireyinGeni, i*9, (i*9)+10)
							)
					);
		}
		aDegeri=Double.parseDouble("3."+virguldenSonra);
		if(aDegeri<3.5)
		{
			aDegeri+=0.5;
		}
		

		//Bitleri Olu�turma
		String bitDizilimleri="";
		int[] sayiAdetleri=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		double sonXDegeri=0.5;
		int birlerinSayisi=0;
		String binnaryDizilimi="";
		for(int i=0;i<1000000;i++)
		{
			if(i%4==0&&i!=0)
			{
				sayiAdetleri[Integer.parseInt(binnaryDizilimi, 2)]++;
				binnaryDizilimi="";
			}
			sonXDegeri=aDegeri*sonXDegeri*(1-sonXDegeri);
			
			if(sonXDegeri>0.5)
			{
				//Dizideki 1 lerin toplam� bize o anki say�y� verecek .
				//�rne�in gen dizilimide 5 adet 1 , 4 adet 0 var ise o anki bit de�erimiz
				//5 olaca��ndan x de�erinin 0.5 b�y�k olma durumunda sayi de�erini artt�raca��z.
				birlerinSayisi++;
				binnaryDizilimi+="1";
			}else {

				binnaryDizilimi+="0";
			}
			
			
		}
		
		//Aralar�ndaki Fark� Minimize Edece�iz O y�zden En b�y�k fark de�eri bizim fitness de�rimiz olacak
		//En k���k de�eri arad���m�z i�in 1/De�er olmak zorunda. Fitness de�eri ters mant�k �al���r.
		//En b�y�k fitness de�eri bizim i�in en iyi birey olacakt�r.
		//Bu sebepten dolay� e�er 1/de�er �eklinde yapmaz isek bize en k�t� sonucu verecektir.
		// dizideki max de�er say�s� - min de�er say�s� bize aradaki fark� verecek.
		

        IntSummaryStatistics istatistik = Arrays.stream(sayiAdetleri).summaryStatistics();

        birey.sayilarinSayisiniDegistir(sayiAdetleri);
		birey.birlerinSayisiDegistir(birlerinSayisi);
		birey.aDegeriniDegistir(aDegeri);
		return 1.0/(istatistik.getMax()-istatistik.getMin());
	}
	
	static int sayiyiGetir(String deger)
	{
		int adet=0;
		for(int i=0;i<deger.length();i++)
		{
			if(deger.charAt(i)=='1')
			{
				adet++;
			}
		}
		return adet;
	}
}
