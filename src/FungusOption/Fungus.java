package FungusOption;

public class Fungus {
	
	private int ID_rodzaj, owocnik_typ, hymenofor_typ, wysyp_zarodnikow, ochrona, wartosc;
	private String nazwa_lacinska, nazwa_polska, owocnik_opis, hymenofor_rodzaj, hymenofor_opis,
	kapelusz_typ, kapelusz_garbek, kapelusz_powierzchnia, kapelusz_brzeg, kapelusz_opis,
	trzon_rodzaj, trzon_kolnierz, trzon_powierzchnia, trzon_bulwa, trzon_przekroj, trzon_opis,
	wysyp_zarodnikow_opis, srodowisko, sposob_zycia, okres, wystepowanie_opis, wartosc_opis, rodzaj;
	
	protected String getRodzaj(){
		return rodzaj;
	}
	
	protected void setRodzaj(String rodzaj){
		this.rodzaj = rodzaj;
	}
	
	protected int getID_rodzaj() {
		return ID_rodzaj;
	}
	protected void setID_rodzaj(int ID_rodzaj) {
		this.ID_rodzaj = ID_rodzaj;
	}
	protected int getOwocnik_typ() {
		return owocnik_typ;
	}
	protected void setOwocnik_typ(int owocnik_typ) {
		this.owocnik_typ = owocnik_typ;
	}
	protected int getHymenofor_typ() {
		return hymenofor_typ;
	}
	protected void setHymenofor_typ(int hymenofor_typ) {
		this.hymenofor_typ = hymenofor_typ;
	}
	protected String getHymenofor_rodzaj() {
		return hymenofor_rodzaj;
	}
	protected void setHymenofor_rodzaj(String hymenofor_rodzaj) {
		this.hymenofor_rodzaj = hymenofor_rodzaj;
	}
	protected int getWysyp_zarodnikow() {
		return wysyp_zarodnikow;
	}
	protected void setWysyp_zarodnikow(int wysyp_zarodnikow) {
		this.wysyp_zarodnikow = wysyp_zarodnikow;
	}
	protected int getOchrona() {
		return ochrona;
	}
	protected void setOchrona(int ochrona) {
		this.ochrona = ochrona;
	}
	protected int getWartosc() {
		return wartosc;
	}
	protected void setWartosc(int wartosc) {
		this.wartosc = wartosc;
	}
	protected String getNazwa_lacinska() {
		return nazwa_lacinska;
	}
	protected void setNazwa_lacinska(String nazwa_lacinska) {
		this.nazwa_lacinska = nazwa_lacinska;
	}
	protected String getNazwa_polska() {
		return nazwa_polska;
	}
	protected void setNazwa_polska(String nazwa_polska) {
		this.nazwa_polska = nazwa_polska;
	}
	protected String getOwocnik_opis() {
		return owocnik_opis;
	}
	protected void setOwocnik_opis(String owocnik_opis) {
		this.owocnik_opis = owocnik_opis;
	}
	protected String getHymenofor_opis() {
		return hymenofor_opis;
	}
	protected void setHymenofor_opis(String hymenofor_opis) {
		this.hymenofor_opis = hymenofor_opis;
	}
	protected String getKapelusz_typ() {
		return kapelusz_typ;
	}
	protected void setKapelusz_typ(String kapelusz_typ) {
		this.kapelusz_typ = kapelusz_typ;
	}
	protected String getKapelusz_garbek() {
		return kapelusz_garbek;
	}
	protected void setKapelusz_garbek(String kapelusz_garbek) {
		this.kapelusz_garbek = kapelusz_garbek;
	}
	protected String getKapelusz_powierzchnia() {
		return kapelusz_powierzchnia;
	}
	protected void setKapelusz_powierzchnia(String kapelusz_powierzchnia) {
		this.kapelusz_powierzchnia = kapelusz_powierzchnia;
	}
	protected String getKapelusz_brzeg() {
		return kapelusz_brzeg;
	}
	protected void setKapelusz_brzeg(String kapelusz_brzeg) {
		this.kapelusz_brzeg = kapelusz_brzeg;
	}
	protected String getKapelusz_opis() {
		return kapelusz_opis;
	}
	protected void setKapelusz_opis(String kapelusz_opis) {
		this.kapelusz_opis = kapelusz_opis;
	}
	protected String getTrzon_rodzaj() {
		return trzon_rodzaj;
	}
	protected void setTrzon_rodzaj(String trzon_rodzaj) {
		this.trzon_rodzaj = trzon_rodzaj;
	}
	protected String getTrzon_kolnierz() {
		return trzon_kolnierz;
	}
	protected void setTrzon_kolnierz(String trzon_kolnierz) {
		this.trzon_kolnierz = trzon_kolnierz;
	}
	protected String getTrzon_powierzchnia() {
		return trzon_powierzchnia;
	}
	protected void setTrzon_powierzchnia(String trzon_powierzchnia) {
		this.trzon_powierzchnia = trzon_powierzchnia;
	}
	protected String getTrzon_bulwa() {
		return trzon_bulwa;
	}
	protected void setTrzon_bulwa(String trzon_bulwa) {
		this.trzon_bulwa = trzon_bulwa;
	}
	protected String getTrzon_przekroj() {
		return trzon_przekroj;
	}
	protected void setTrzon_przekroj(String trzon_przekroj) {
		this.trzon_przekroj = trzon_przekroj;
	}
	protected String getTrzon_opis() {
		return trzon_opis;
	}
	protected void setTrzon_opis(String trzon_opis) {
		this.trzon_opis = trzon_opis;
	}
	protected String getWysyp_zarodnikow_opis() {
		return wysyp_zarodnikow_opis;
	}
	protected void setWysyp_zarodnikow_opis(String wysyp_zarodnikow_opis) {
		this.wysyp_zarodnikow_opis = wysyp_zarodnikow_opis;
	}
	protected String getSrodowisko() {
		return srodowisko;
	}
	protected void setSrodowisko(String srodowisko) {
		this.srodowisko = srodowisko;
	}
	protected String getSposob_zycia() {
		return sposob_zycia;
	}
	protected void setSposob_zycia(String sposob_zycia) {
		this.sposob_zycia = sposob_zycia;
	}
	protected String getOkres() {
		return okres;
	}
	protected void setOkres(int beg, int end) {
		String okres = "";
		if (beg==13 || end==13){
			okres = "13";
		} else {
			okres = beg+","+end;
		}
		this.okres = okres;
	}
	protected String getWystepowanie_opis() {
		return wystepowanie_opis;
	}
	protected void setWystepowanie_opis(String wystepowanie_opis) {
		this.wystepowanie_opis = wystepowanie_opis;
	}
	protected String getWartosc_opis() {
		return wartosc_opis;
	}
	protected void setWartosc_opis(String wartosc_opis) {
		this.wartosc_opis = wartosc_opis;
	}
	
}
