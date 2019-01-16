package gaornek;

public class GenetikAlgoritma {

    /* GA parameters */
    private static final double caprazlamaDegeri = 0.5;
    private static final double mustasyonDegeri = 0.065;
    private static final int secilecekKisiSayisi = 10;
    private static final boolean enIyiKoru = true;
    
    public static Populasyon evrimGecirt(Populasyon populasyon) {

    	Populasyon yeniPopulasyon= new Populasyon(populasyon.bireySayisi(), false);
    	
    	//en iyi bireyi koru , yani yeni populasyona en iyi bireyi ekleyelim ki ,
    	//en iyi de�erimizin �zerinden sonuca ula�abilelim.
    	if (enIyiKoru) {
            yeniPopulasyon.bireyEkle(0, populasyon.enIyiBirey());
        }
    	
    	int baslangic=0;
    	if(enIyiKoru)
    	{
    		baslangic=1;
    	}
    	for (int i = baslangic; i < yeniPopulasyon.bireySayisi(); i++) {
            Birey birinciBirey = randomBireySec(populasyon);
            Birey ikinciBirey = randomBireySec(populasyon);
            Birey yeniBirey= caprazlamaYap(birinciBirey, ikinciBirey);
            yeniPopulasyon.bireyEkle(i, yeniBirey);
        }
    	
    	   // Mutate population
        for (int i = baslangic; i < yeniPopulasyon.bireySayisi(); i++) {
        	bireyiMutasyonaUgrat(yeniPopulasyon.bireyiGetir(i));
        }

        return yeniPopulasyon;
    	
    	
    	
    }
    

   //�aprazlama i�leminin ger�ekle�tirilmesi
    private static Birey caprazlamaYap(Birey birinciBirey, Birey ikinciBirey) {
        Birey caprazlananBirey = new Birey();
        // T�m genleri dola�arak caprazlama de�erinin alt�nda veya �st�nde ise 1. veya 2.
        //genlerdeki de�eri yeni genimize atayarak yeni bir birey yarat�yoruz...
        for (int i = 0; i < Baslangic.genAdedi; i++) {
            // �aprazlama i�lemini ger�ekle�tir.
            if (Math.random() <= caprazlamaDegeri) {
            	caprazlananBirey.geniDegistir(i, birinciBirey.geniGetir(i));
            } else {
            	caprazlananBirey.geniDegistir(i, ikinciBirey.geniGetir(i));
            }
        }
        return caprazlananBirey;
    }

    // Bireyler tekrar etmemesi i�in mutasyon ge�irtmek gerekiyor.
    // Sonsuz d�ng�den veya ayn� �eylerin tekrarlamas�n�n �n�ne ge�mek i�in mutasyon ge�irmeliyiz.
    private static void bireyiMutasyonaUgrat(Birey birey) {
        // T�m genleri tek tek dola��yoruz
        for (int i = 0; i < Baslangic.genAdedi; i++) {
            if (Math.random() <= mustasyonDegeri) {
                // Yeni bir gen olu�turuyoruz (Gen 1 de gelebilir 0 da gelebilir.)
            	//Istenilirse o anki gen de�eri 0 ise 1 e veya 1 ise 0 a �evirilebiliriz.
            	//Fakat gen de�erini random atamay� tercih ettim ben.
                birey.geniDegistir(i, (byte) Math.round(Math.random()));
            }
        }
    }

    // Bir birey toplulu�u olu�turaca��z random,
    // Bu olu�an bireylerin aras�ndaki en iyi bireyleri secip yeni populasyonumuza ekleyece�iz.
    // Populasyonun kisi sayisini genelde toplam populasyon/50 �eklinde se�mek gerekiyor.
    private static Birey randomBireySec(Populasyon populasyon) {
        // secilen populasyonumuzu olu�turuyoruz
    	Populasyon secilenPopulasyon = new Populasyon(secilecekKisiSayisi, false);
        // ka� tane birey ekleyecek isek. Belirtilen say� kadar populasyona birey ekliyoruz.
    	// Se�ilen bireyler rastgele se�ilen bireylerdir.
    	// Zamanla de�i�ebilece�i i�in Algoritma her seferinde ayn� sonucu vermeyecektiz.
    	// Buradaki rastsall��� kald�r�p kendi kurgunuzu yapabilirsiniz..
        for (int i = 0; i < secilecekKisiSayisi; i++) {
            int secilenBireyinSirasi = (int) (Math.random() * populasyon.bireySayisi());
            secilenPopulasyon.bireyEkle(i, populasyon.bireyiGetir(secilenBireyinSirasi));
        }
        return secilenPopulasyon.enIyiBirey();
    }

}
