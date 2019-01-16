package gaornek;

public class Populasyon {
	
	private Birey[] bireyler;
	private int bireySayisi=0;
	public Populasyon(int bireySayisi,boolean yeniMiOlusturulacak) {
		this.bireySayisi=bireySayisi;
		bireyler=new Birey[bireySayisi];
		if(yeniMiOlusturulacak)
		{
			// Ka� Bireyden Olu�acak
            for (int i = 0; i < bireySayisi; i++) {
                Birey yeniBirey = new Birey();
                yeniBirey.bireyOlustur();
                bireyEkle(i, yeniBirey);
                //Bireyler Olu�uyor
            }
		}
	}
	
	public Birey bireyiGetir(int indis) {
		return bireyler[indis];
	}
	
	 public Birey enIyiBirey() {
	        Birey tempBirey = bireyler[0];
	        // T�m bireylerin fitness de�erlerini kar��la�t�r
	        for (int i = 0; i < this.bireySayisi; i++) {
	            if (tempBirey.fitnessGetir() <= bireyiGetir(i).fitnessGetir()) {
	            	tempBirey = bireyiGetir(i);
	            }
	        }
	        return tempBirey;
	    }
	 
	 
	// bireyi Ekleme
	    public void bireyEkle(int indis, Birey birey) {
	        bireyler[indis] = birey;
	    }
	    
	    public int bireySayisi() {
	    	return this.bireySayisi;
	    }
	    
	
	

}
