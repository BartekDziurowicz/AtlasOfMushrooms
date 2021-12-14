package mainprogram;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import Connection.Statements;
import FungusOption.DeletePosition;
import FungusOption.FungusStart;
import fungopedia.InfoFrame;

public class Start {

	private Mainframe mainframe;
	private MainHorSplit mainhorsplit;
	private MainVerSplit mainversplit;
	private MainTopPanel maintoppanel;
	private MenuBar mainmenu;
	private Menu menuitemgrzyb, menuitemuzytkownik, menuitempomoc;
	private Tree taksonomiatree;
	private ScrollPane taksonomiascroll, fungustablescroll, infopanelscroll;
	private MainTable maintable;
	private InfoPanel infopanel;
	private SearchTextField searchtextfield;
	private TopMenuButton searchbutton, ascending, descending;
	private CounterPanel counterpanel;
	private MenuItem dodaj, edytuj, usun, passchange, logout, close, fungopedia;
	private String[] dataowocnik = { "owocnik_typ" }, datahymenofor = { "hymenofor_typ", "hymenofor_rodzaj" },
			datakapelusz = { "kapelusz_typ", "kapelusz_garbek", "kapelusz_powierzchnia", "kapelusz_brzeg" },
			datatrzon = { "trzon_rodzaj", "trzon_kolnierz", "trzon_powierzchnia", "trzon_bulwa", "trzon_przekroj" },
			datazarodniki = { "wysyp_zarodnikow" }, datasrodowisko = { "srodowisko", "sposob_zycia" },
			datawartosc = { "ochrona", "wartosc" };
	private User user;
	private Footer footer;

	public void start() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		System.out.println(screenSize);
		startObjects();

		mainframe = new Mainframe();
		mainhorsplit = new MainHorSplit();
		mainversplit = new MainVerSplit();
		maintoppanel = new MainTopPanel();
		mainmenu = new MenuBar();
		taksonomiatree = new Tree();
		taksonomiascroll = new ScrollPane(taksonomiatree);
		infopanel = new InfoPanel(screenSize);

		infopanelscroll = new ScrollPane(infopanel);
		maintable = new MainTable();
		fungustablescroll = new ScrollPane(maintable);		
		counterpanel = new CounterPanel(maintable);
		searchtextfield = new SearchTextField(counterpanel, maintable);
		searchbutton = new TopMenuButton(searchtextfield, maintable, counterpanel);
		ascending = new TopMenuButton("ascendingicon", "ASC", false);
		descending = new TopMenuButton("descendingicon", "DESC", true);
		user = new User();
		footer = new Footer(user);

		menuitemgrzyb = new Menu("Grzyb");
		dodaj = new MenuItem("inserticon", "Dodaj", maintable);
		edytuj = new MenuItem("editicon", "Edytuj", maintable);
		usun = new MenuItem("deleteicon", "Usuñ", maintable);
		menuitemgrzyb.add(dodaj);
		menuitemgrzyb.add(edytuj);
		menuitemgrzyb.add(usun);
		menuitemuzytkownik = new Menu("U¿ytkownik");
		passchange = new MenuItem("passicon", "Zmieñ has³o");
		logout = new MenuItem("logouticon", "Wyloguj");
		close = new MenuItem("closeicon", "WyjdŸ");
		menuitemuzytkownik.add(passchange);
		menuitemuzytkownik.add(logout);
		menuitemuzytkownik.add(close);
		menuitempomoc = new Menu("Pomoc");
		fungopedia = new MenuItem("infoicon", "Fungopedia");
		menuitempomoc.add(fungopedia);

		mainhorsplit.setLeftComponent(taksonomiascroll);
		mainversplit.setLeftComponent(fungustablescroll);
		mainversplit.setRightComponent(infopanelscroll);
		mainhorsplit.setRightComponent(mainversplit);
		mainframe.getContentPane().add(mainhorsplit, BorderLayout.CENTER);

		mainmenu.add(menuitemgrzyb);
		mainmenu.add(menuitemuzytkownik);
		mainmenu.add(menuitempomoc);
		maintoppanel.addToTopLeft(mainmenu);
		maintoppanel.addToBottomLeft(new MenuBar(maintable, "Owocnik", dataowocnik, counterpanel));
		maintoppanel.addToBottomLeft(new MenuBar(maintable, "Hymenofor", datahymenofor, counterpanel));
		maintoppanel.addToBottomLeft(new MenuBar(maintable, "Kapelusz", datakapelusz, counterpanel));
		maintoppanel.addToBottomLeft(new MenuBar(maintable, "Trzon", datatrzon, counterpanel));
		maintoppanel.addToBottomLeft(new MenuBar(maintable, "Wysyp", datazarodniki, counterpanel));
		maintoppanel.addToBottomLeft(new MenuBar(maintable, "Œrodowisko", datasrodowisko, counterpanel));
		maintoppanel.addToBottomLeft(new MenuBar(maintable, "Wartoœæ", datawartosc, counterpanel));

		maintoppanel.addToTopRight(ascending);
		maintoppanel.addToTopRight(descending);
		maintoppanel.addToTopRight(searchtextfield);
		maintoppanel.addToTopRight(searchbutton);
		maintoppanel.addToTopRight(counterpanel);
		mainframe.getContentPane().add(maintoppanel, BorderLayout.NORTH);

		mainframe.getContentPane().add(footer, BorderLayout.SOUTH);

		startData();
		addActions();
		
	}
	
	public static void main(String[] args) {
		Start newstart = new Start();
		newstart.start();
	}	

	private void startObjects() {
		Statements.setTable();
	}

	private void startData() {
		taksonomiatree.setTree();
		maintable.setTableData();
		infopanel.setInfoTableData();
		roleAccess(dodaj, 2);
		roleAccess(usun, 1);
	}

	private void addActions() {
		maintable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Statements.setIDGrzyb((Integer) maintable.getValueAt(maintable.getSelectedRow(), 0));
				infopanel.setInfoTableData();

				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						infopanelscroll.getVerticalScrollBar().setValue(0);
						infopanelscroll.getHorizontalScrollBar().setValue(0);
					}
				});
			}
		});		

		taksonomiatree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent f) {
				if (f.getPath().getPathCount() == 9) {
					javax.swing.SwingUtilities.invokeLater(new Runnable() { // invoke
																			// later,
																			// poniewaz
																			// najpierw
																			// triggerowal
																			// sie
																			// ten
																			// listener
																			// a
																			// pozniej
																			// z
																			// tree,
																			// powinno
																			// byc
																			// odwrotnie,
																			// poniewaz
																			// musizostac
																			// zaktualizowany
																			// idgrzyb
																			// w
																			// statements
						public void run() {
							infopanel.setInfoTableData();
							javax.swing.SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									infopanelscroll.getVerticalScrollBar().setValue(0);
									infopanelscroll.getHorizontalScrollBar().setValue(0);
								}
							});
						}
					});
				}
			}
		});

		ascending.addActionListener(ascending.getOrder(descending));
		descending.addActionListener(descending.getOrder(ascending));
		passchange.addActionListener(passchange.changePass(mainframe));
		logout.addActionListener(logout.logOut(mainframe));
		close.addActionListener(close.close(mainframe));
		fungopedia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InfoFrame infof = new InfoFrame();
			}

		});
		dodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FungusStart fungus = new FungusStart(mainframe, user);
			}
		});
		usun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeletePosition dp = new DeletePosition(maintable);
			}
		});
	}

	private void roleAccess(Component component, int access) {
		if (user.getRoleID() > access) {
			component.setEnabled(false);
		} else {
			component.setEnabled(true);
		}
	}

}
