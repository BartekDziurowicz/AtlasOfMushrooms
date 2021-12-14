package FungusOption;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import Connection.Connect;
import Connection.Statements;
import mainprogram.Mainframe;
import mainprogram.User;

public class FungusStart {

	private FungusFrame fungusframe;
	private Fungus fungus;
	private JTabbedPane tabbedpane;
	private TaksonomyPanel takspanel;
	private BasicPanel basicpanel;
	private HymenoforPanel hymenoforpanel;
	private KapeluszPanel kapeluszpanel;
	private TrzonPanel trzonpanel;
	private BottomPanel bottompanel;
	private ZarodnikPanel zarodnikpanel;
	private WystepowaniePanel wystepowaniepanel;
	private WartoscPanel wartoscpanel;
	private FileChooser choosephoto;

	public FungusStart(Mainframe mf, User user) {
		fungus = new Fungus();
		basicpanel = new BasicPanel();
		hymenoforpanel = new HymenoforPanel();
		kapeluszpanel = new KapeluszPanel();
		trzonpanel = new TrzonPanel();
		zarodnikpanel = new ZarodnikPanel();
		wystepowaniepanel = new WystepowaniePanel();
		wartoscpanel = new WartoscPanel();
		takspanel = new TaksonomyPanel(fungus, basicpanel);
		choosephoto = new FileChooser("Dodaj zdjêcie");

		tabbedpane = new JTabbedPane();
		tabbedpane.addTab("Taksonomia", takspanel);
		tabbedpane.addTab("Owocnik", basicpanel);
		tabbedpane.addTab("Hymenofor", hymenoforpanel);
		tabbedpane.addTab("Kapelusz", kapeluszpanel);
		tabbedpane.addTab("Trzon", trzonpanel);
		tabbedpane.addTab("Zarodniki", zarodnikpanel);
		tabbedpane.addTab("Wystêpowanie", wystepowaniepanel);
		tabbedpane.addTab("Wartoœæ", wartoscpanel);
		fungusframe = new FungusFrame(mf);
		bottompanel = new BottomPanel(fungusframe, addAction(fungus, user.getRoleID()));
		bottompanel.setAddPhotoButton(choosephoto);

		fungusframe.add(tabbedpane, BorderLayout.CENTER);
		fungusframe.add(bottompanel, BorderLayout.SOUTH);
	}

	private ActionListener addAction(Fungus f, int userid) {
		ActionListener adding = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (basicpanel.checkData() == true) {
					if (hymenoforpanel.checkData() == true) {
						if (kapeluszpanel.checkData() == true) {
							if (trzonpanel.checkData() == true) {
								if (zarodnikpanel.checkData() == true) {
									if (wystepowaniepanel.checkData() == true) {
										if (wartoscpanel.checkData() == true) {

											basicpanel.setFungusData(f);
											hymenoforpanel.setFungusData(f);
											kapeluszpanel.setFungusData(f);
											trzonpanel.setFungusData(f);
											zarodnikpanel.setFungusData(f);
											wystepowaniepanel.setFungusData(f);
											wartoscpanel.setFungusData(f);
											if (checkIfFungusExistInDataBase(f) == false) {
												if (choosephoto.checkData(f) == true) {

													choosephoto.setFungusData(f);
													String statement = "INSERT INTO grzyb VALUES (DEFAULT,'"
															+ f.getID_rodzaj() + "','" + f.getNazwa_lacinska() + "','"
															+ f.getNazwa_polska() + "','" + f.getOwocnik_typ() + "','"
															+ f.getOwocnik_opis() + "','" + f.getHymenofor_typ() + "','"
															+ f.getHymenofor_rodzaj() + "','" + f.getHymenofor_opis()
															+ "','" + f.getKapelusz_typ() + "','"
															+ f.getKapelusz_garbek() + "','"
															+ f.getKapelusz_powierzchnia() + "','"
															+ f.getKapelusz_brzeg() + "','" + f.getKapelusz_opis()
															+ "','" + f.getTrzon_rodzaj() + "','"
															+ f.getTrzon_kolnierz() + "','" + f.getTrzon_powierzchnia()
															+ "','" + f.getTrzon_bulwa() + "','" + f.getTrzon_przekroj()
															+ "','" + f.getTrzon_opis() + "','"
															+ f.getWysyp_zarodnikow() + "','"
															+ f.getWysyp_zarodnikow_opis() + "','" + f.getSrodowisko()
															+ "','" + f.getSposob_zycia() + "','" + f.getOkres() + "','"
															+ f.getWystepowanie_opis() + "','" + f.getOchrona() + "','"
															+ f.getWartosc() + "','" + f.getWartosc_opis() + "','"
															+ "photo" + "','" + userid + "',CURRENT_DATE())";
													sendFungusToDatabase(statement);
													JOptionPane.showMessageDialog(null,
															"Pozycja zosta³a dodana pomyœlnie.", "Informacja.",
															JOptionPane.INFORMATION_MESSAGE);
													fungusframe.dispose();
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		};
		return adding;
	}

	private void sendFungusToDatabase(String statement) {
		Statements.setStatement(statement);
		Connect.getConnection(Statements.getStatement(), false);
		Connect.closeConnection();
	}

	private boolean checkIfFungusExistInDataBase(Fungus f) {
		boolean check = true;
		int r = 0;
		Statements.setStatement("SELECT COUNT(grzyb.ID_grzyb) AS `rows` FROM grzyb WHERE grzyb.ID_rodzaj="
				+ f.getID_rodzaj() + " AND grzyb.nazwa_lacinska='" + f.getNazwa_lacinska() + "'");
		Connect.getConnection(Statements.getStatement(), true);
		try {
			while (Connect.getResult().next()) {
				r = Connect.getResult().getInt(1);
			}
			if (r > 0) {
				JOptionPane.showMessageDialog(null, "Grzyb o podanej nazwie ju¿ istnieje.", "Duplikat.",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				check = false;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "B³¹d po³¹czenia!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			Connect.closeConnection();
		}
		return check;
	}

}
