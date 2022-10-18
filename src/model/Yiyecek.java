package model;

public class Yiyecek {
/**

	*private int hazirlamaSuresi;
	*private int tuketmeSuresi;
	
	*public int getHazirlamaSuresi() {
	*	return hazirlamaSuresi;
	*}
	*public void setHazirlamaSuresi(int hazirlamaSuresi) {
	*	this.hazirlamaSuresi = hazirlamaSuresi;
	*}
	*public int getTuketmeSuresi() {
	*	return tuketmeSuresi;
	*}
	*public void setTuketmeSuresi(int tuketmeSuresi) {
	*	this.tuketmeSuresi = tuketmeSuresi;
	*}
	*/
	
	public final String name;
	public final int cookTimeMS;

	public Yiyecek(String name, int cookTimeMS) {
		this.name = name;
		this.cookTimeMS = cookTimeMS;
	}

	public String toString() {
		return name;
	}

}
