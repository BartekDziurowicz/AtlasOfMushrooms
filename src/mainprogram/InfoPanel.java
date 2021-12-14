package mainprogram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import Connection.Connect;
import Connection.Statements;
import ftp.FTPConnection;
import net.proteanit.sql.DbUtils;

public class InfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Box mainverbox = Box.createVerticalBox(), mainhorbox = Box.createHorizontalBox(),
			leftbox = Box.createVerticalBox(), rightbox = Box.createVerticalBox();
	private JTable infotable;
	private JEditorPane header = new JEditorPane(), characteristic = new JEditorPane(), zdjecie = new JEditorPane();
	private JTextArea owocnik = new JTextArea(), hymenofor = new JTextArea(), zarodniki = new JTextArea(),
			kapelusz = new JTextArea(), trzon = new JTextArea(), wartosc = new JTextArea(),
			srodowisko = new JTextArea();

	protected InfoPanel(Dimension dm) {

		setLayout(new GridLayout(1, 1));
		setBackground(Color.WHITE);

		infotable = new JTable();

		mainverbox.add(getEditorPane(header, "Grzyb"));
		mainverbox.add(getEditorPane(characteristic, "Charakterystyka"));

		leftbox.add(getTextArea(owocnik, "Owocnik", dm));
		leftbox.add(getTextArea(hymenofor, "Hymenofor", dm));
		leftbox.add(getTextArea(zarodniki, "Zarodniki", dm));
		leftbox.add(getTextArea(kapelusz, "Kapelusz", dm));
		leftbox.add(getTextArea(trzon, "Trzon", dm));
		rightbox.add(getEditorPane(zdjecie, "Zdjêcie"));
		//rightbox.add(counterPanel);
		leftbox.add(getTextArea(srodowisko, "Œrodowisko", dm));
		leftbox.add(getTextArea(wartosc, "Wartoœæ", dm));

		mainverbox.add(mainhorbox);
		mainhorbox.add(leftbox);
		mainhorbox.add(rightbox);

		leftbox.add(Box.createGlue());
		rightbox.add(Box.createGlue());
		add(mainverbox);

	}

	protected void setInfoTableData() {
		Statements.setStatement(
				"SELECT grzyb.*, rodzaj.Rodzaj, rodzina.Rodzina, rzad.Rzad, podklasa.Podklasa, klasa.Klasa, podtyp.Podtyp, typ.Typ FROM grzyb "
						+ "INNER JOIN rodzaj ON grzyb.ID_rodzaj=rodzaj.ID_rodzaj "
						+ "INNER JOIN rodzina ON rodzaj.ID_rodzina=rodzina.ID_rodzina "
						+ "INNER JOIN rzad ON rodzina.ID_rzad=rzad.ID_rzad "
						+ "INNER JOIN podklasa ON rzad.ID_podklasa=podklasa.ID_podklasa "
						+ "INNER JOIN klasa ON podklasa.ID_klasa=klasa.ID_klasa "
						+ "INNER JOIN podtyp ON klasa.ID_podtyp=podtyp.ID_podtyp "
						+ "INNER JOIN typ ON podtyp.ID_typ=typ.ID_typ " + "WHERE grzyb.ID_grzyb="
						+ Statements.getIDGrzyb());
		Connect.getConnection(Statements.getStatement(), true);
		try {
			infotable.setModel(DbUtils.resultSetToTableModel(Connect.getResult()));
		} finally {

			setHeader();
			setCharakteristic();
			setOwocnikInfo();
			setHymenoforInfo();
			setZarodnikiInfo();
			setKapeluszInfo();
			setTrzonInfo();
			setZdjecie();
			setWartosc();
			setSrodowisko();
			Connect.closeConnection();
		}
	}

	private void setHeader() {
		String info = "<html><font face ='arial' size='4' font color='298A40'><b>";
		if (infotable.getValueAt(0, 3).toString().length() > 0) {
			info = info + infotable.getValueAt(0, 3) + "</b><font size='2' font color='gray'> <i>("
					+ infotable.getValueAt(0, 32) + " " + infotable.getValueAt(0, 2) + ")</i><br>";
		} else {
			info = info + infotable.getValueAt(0, 32) + " " + infotable.getValueAt(0, 2)
					+ "</b><font size='2' font color='gray'><br>";
		}
		info = info + infotable.getValueAt(0, 38) + " -> " + infotable.getValueAt(0, 37) + " -> "
				+ infotable.getValueAt(0, 36) + " -> " + infotable.getValueAt(0, 35) + " -> "
				+ infotable.getValueAt(0, 34) + " -> " + infotable.getValueAt(0, 33) + " -> "
				+ infotable.getValueAt(0, 32) + "</html>";
		header.setText(info);
	}

	private void setCharakteristic() {
		StringBuilder icon = new StringBuilder();

		icon.append("<html><img src='" + new File("img/owocnik_typ/" + infotable.getValueAt(0, 4) + ".png").toURI()
				+ "'>&nbsp");

		icon.append("<img src='" + new File("img/hymenofor_typ/" + infotable.getValueAt(0, 6) + ".png").toURI()
				+ "'>&nbsp");

		String[] hym_rod = infotable.getValueAt(0, 7).toString().split(",");
		for (int i = 0; i < hym_rod.length; i++) {
			icon.append("<img src='" + new File("img/hymenofor_rodzaj/" + hym_rod[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] kap_typ = infotable.getValueAt(0, 9).toString().split(",");
		for (int i = 0; i < kap_typ.length; i++) {
			icon.append("<img src='" + new File("img/kapelusz_typ/" + kap_typ[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] kap_gar = infotable.getValueAt(0, 10).toString().split(",");
		for (int i = 0; i < kap_gar.length; i++) {
			icon.append("<img src='" + new File("img/kapelusz_garbek/" + kap_gar[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] kap_pow = infotable.getValueAt(0, 11).toString().split(",");
		for (int i = 0; i < kap_pow.length; i++) {
			icon.append("<img src='" + new File("img/kapelusz_powierzchnia/" + kap_pow[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] kap_brz = infotable.getValueAt(0, 12).toString().split(",");
		for (int i = 0; i < kap_brz.length; i++) {
			icon.append("<img src='" + new File("img/kapelusz_brzeg/" + kap_brz[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] trz_rod = infotable.getValueAt(0, 14).toString().split(",");
		for (int i = 0; i < trz_rod.length; i++) {
			icon.append("<img src='" + new File("img/trzon_rodzaj/" + trz_rod[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] trz_kol = infotable.getValueAt(0, 15).toString().split(",");
		for (int i = 0; i < trz_kol.length; i++) {
			icon.append("<img src='" + new File("img/trzon_kolnierz/" + trz_kol[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] trz_pow = infotable.getValueAt(0, 16).toString().split(",");
		for (int i = 0; i < trz_pow.length; i++) {
			icon.append("<img src='" + new File("img/trzon_powierzchnia/" + trz_pow[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] trz_bul = infotable.getValueAt(0, 17).toString().split(",");
		for (int i = 0; i < trz_bul.length; i++) {
			icon.append("<img src='" + new File("img/trzon_bulwa/" + trz_bul[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] trz_prz = infotable.getValueAt(0, 18).toString().split(",");
		for (int i = 0; i < trz_prz.length; i++) {
			icon.append("<img src='" + new File("img/trzon_przekroj/" + trz_prz[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] zar_wys = infotable.getValueAt(0, 20).toString().split(",");
		for (int i = 0; i < zar_wys.length; i++) {
			icon.append("<img src='" + new File("img/wysyp_zarodnikow/" + zar_wys[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] sro = infotable.getValueAt(0, 22).toString().split(",");
		for (int i = 0; i < sro.length; i++) {
			icon.append("<img src='" + new File("img/srodowisko/" + sro[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] spo_zyc = infotable.getValueAt(0, 23).toString().split(",");
		for (int i = 0; i < spo_zyc.length; i++) {
			icon.append("<img src='" + new File("img/sposob_zycia/" + spo_zyc[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] och = infotable.getValueAt(0, 26).toString().split(",");
		for (int i = 0; i < och.length; i++) {
			icon.append("<img src='" + new File("img/ochrona/" + och[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		String[] war = infotable.getValueAt(0, 27).toString().split(",");
		for (int i = 0; i < war.length; i++) {
			icon.append("<img src='" + new File("img/wartosc/" + war[i] + ".png").toURI() + "'>");
		}
		icon.append("&nbsp");

		icon.append("</html>");
		characteristic.setText(icon.toString());
	}

	private JEditorPane getEditorPane(JEditorPane ep, String title) {
		ep.removeAll();
		Border titledborder = BorderFactory.createTitledBorder(title);
		((javax.swing.border.TitledBorder) titledborder).setTitleColor(new Color(240, 99, 34));
		ep.setBorder(new CompoundBorder(titledborder, BorderFactory.createEmptyBorder(3, 5, 5, 5)));
		ep.setEditable(false);
		ep.setFocusable(false);
		ep.setContentType("text/html");
		ep.revalidate();
		return ep;
	}

	private JTextArea getTextArea(JTextArea ta, String title, Dimension dm) {
		ta.removeAll();
		Border titledborder = BorderFactory.createTitledBorder(title);
		((javax.swing.border.TitledBorder) titledborder).setTitleColor(new Color(240, 99, 34));
		ta.setBorder(new CompoundBorder(titledborder, BorderFactory.createEmptyBorder(2, 5, 5, 5)));
		int w = (int) (dm.getWidth() * 0.80 - 425);
		ta.setMinimumSize(new Dimension(w, Integer.MAX_VALUE));
		ta.setMaximumSize(new Dimension(w, Integer.MAX_VALUE));
		ta.setSize(new Dimension(w, Integer.MAX_VALUE));
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		ta.setEditable(false);
		ta.setFocusable(false);
		ta.revalidate();
		return ta;
	}

	private void setOwocnikInfo() {
		String info = (String) infotable.getValueAt(0, 5);
		owocnik.setText(info);
	}

	private void setHymenoforInfo() {
		String info = (String) infotable.getValueAt(0, 8);
		hymenofor.setText(info);
	}

	private void setZarodnikiInfo() {
		String info = (String) infotable.getValueAt(0, 21);
		zarodniki.setText(info);
	}

	private void setKapeluszInfo() {
		String info = (String) infotable.getValueAt(0, 13);
		kapelusz.setText(info);
	}

	private void setTrzonInfo() {
		String info = (String) infotable.getValueAt(0, 19);
		trzon.setText(info);
	}

	private void setZdjecie() {
		String name = infotable.getValueAt(0, 32) + " " + infotable.getValueAt(0, 2);
		FTPConnection.getPhotoFiles(name);
		FTPConnection.setPhoto(zdjecie, name, 0);
	}

	private void setWartosc() {
		String info = (String) infotable.getValueAt(0, 28);
		wartosc.setText(info);
	}

	private void setSrodowisko() {
		String info = (String) infotable.getValueAt(0, 25);
		srodowisko.setText(info);
	}

}
