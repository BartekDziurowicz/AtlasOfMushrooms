package FungusOption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import ftp.FTPConnection;

public class FileChooser extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File dest;

	protected FileChooser(String name) {
		setChooserLanguage();
		setText(name);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fc.setDialogTitle("Wybierz zdjêcie");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpg");
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int returnValue = fc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			setDest(selectedFile);
		}
	}

	private void setDest(File d) {
		this.dest = d;
	}

	private File getDest() {
		return dest;
	}

	//private void copyFile(File source, File dest) throws IOException {
	//	Files.copy(source.toPath(), dest.toPath());
	//}
	


	protected boolean checkData(Fungus f) {
		boolean check = false;
		if (getDest() == null || getDest().length() == 0) {
			JOptionPane.showMessageDialog(null, "Wybierz zdjêcie.", "Brak zdjêcia.", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (getDest().length() > 307200) {
				JOptionPane.showMessageDialog(null, "Maksymalny rozmiar zdjêcia\nnie mo¿e przekraczaæ 300kb.",
						"Rozmiar pliku.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				//String fungus_name = f.getRodzaj() + "_" + f.getNazwa_lacinska();
				//Path path = Paths.get("C:\\xampp\\htdocs\\atlas_grzybow\\images\\" + fungus_name);
				//if (!Files.exists(path)) {
					check = true;
				//} else {
				//	JOptionPane.showMessageDialog(null, "Grzyb o podanej nazwie ju¿ istnieje.", "Duplikat pliku.",
				//			JOptionPane.INFORMATION_MESSAGE);
				//}
			}
		}
		return check;
	}

	protected void setFungusData(Fungus f) {
		String fungus_name = f.getRodzaj() + " " + f.getNazwa_lacinska();
		//Path path = Paths.get("C:\\xampp\\htdocs\\atlas_grzybow\\images\\" + fungus_name);
		//try {
		//	Files.createDirectories(path);
		//} catch (IOException g) {
		//	g.printStackTrace();
		//}
		//File desFile = new File("C:/xampp/htdocs/atlas_grzybow/images/" + fungus_name + "/" + fungus_name + ".jpg");
		//try {
		//	copyFile(getDest(), desFile);
		//} catch (IOException e1) {
		//	e1.printStackTrace();
		//}
		FTPConnection.sendPhoto(fungus_name, getDest().toString());
	}

	private void setChooserLanguage() {
		UIManager.put("FileChooser.saveButtonText", "Zapisz"); 
		UIManager.put("FileChooser.openButtonText", "Otworz"); 
		UIManager.put("FileChooser.cancelButtonText", "Anuluj"); 
		UIManager.put("FileChooser.updateButtonText", "Modyfikuj"); 
		UIManager.put("FileChooser.helpButtonText", "Pomoc"); 
		UIManager.put("FileChooser.fileNameLabelText", "Plik:"); 
		UIManager.put("FileChooser.filesOfTypeLabelText", "Typu:"); 
		UIManager.put("FileChooser.upFolderToolTipText", "Do góry o jeden poziom"); 
		UIManager.put("FileChooser.homeFolderToolTipText", "Folder domowy"); 
		UIManager.put("FileChooser.newFolderToolTipText", "Utwórz nowy folder"); 
		UIManager.put("FileChooser.listViewButtonToolTipText", "Lista"); 
		UIManager.put("FileChooser.detailsViewButtonToolTipText", "Szczegó³y");
		UIManager.put("FileChooser.lookInLabelText", "Szukaj w:");
		UIManager.put("FileChooser.cancelButtonToolTipText", "Anuluj");
		UIManager.put("FileChooser.helpButtonToolTipText", "Pomoc");
		UIManager.put("FileChooser.updateButtonToolTipText", "Modyfikuj");
		UIManager.put("FileChooser.saveButtonToolTipText", "Zapisz");
		UIManager.put("FileChooser.openButtonToolTipText", "Otwórz");
		//UIManager.put("FileChooser.");
	}

}
