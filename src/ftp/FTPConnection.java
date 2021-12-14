package ftp;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilters;

public class FTPConnection {
	
	private static FTPClient client;
	private static String ftpIP = "192.168.0.2";//"";bdzitnas.myqnapcloud.com
	private static FTP_login_data ftpld;
	private static FTPFile[] files;
	
	public static void setFTPconnectionData() throws UnknownHostException, IOException{		
		client = new FTPClient();
		ftpld = new FTP_login_data();
		ftpld.setFTPData();
	}
	
	public static void getPhotoFiles(String path) {
		try {
			client.connect(ftpIP);
			boolean login = client.login(ftpld.getFTPLogin(), ftpld.getFTPPassword()); 
			if(login) {
				files = client.listFiles("/images/"+path);
				
				boolean logout = client.logout();
	            if (!logout) {
	            	JOptionPane.showMessageDialog(null,"B³¹d wylogowania z serwera FTP.","B³¹d po³¹czenia", JOptionPane.ERROR_MESSAGE);
	            }
				
			} else {
				JOptionPane.showMessageDialog(null, "B³¹d logowania na serwer FTP.", "B³¹d po³¹czenia", JOptionPane.ERROR_MESSAGE);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {            	
	            client.disconnect();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public FTPFile[] getFiles() {
		return files;
	}
	
	public static void setPhoto(JEditorPane ep, String path, int i){		
		ep.setText("<html><img src='ftp://"+ftpld.getFTPLogin()+":"+ftpld.getFTPPassword()+"@"+ftpIP+"/images/"+path+"/"+files[i].getName()+"' width='350' height='262'</html>");
    }
	
	public static void sendPhoto(String remotepath, String localpath){
		try {        	
        client.connect(ftpIP);            
        boolean login = client.login(ftpld.getFTPLogin(), ftpld.getFTPPassword());            
        if (login) {                           
            login = client.makeDirectory("/images/"+remotepath);
            if(login){
            	client.setFileType(FTP.BINARY_FILE_TYPE);
            	File localFile = new File(localpath);                
                String remoteFile = "/images/"+remotepath+"/"+remotepath+".jpg";
                InputStream inputStream = new FileInputStream(localFile);
                login = client.storeFile(remoteFile, inputStream);
                if(login){
                	
                }else{
                	JOptionPane.showMessageDialog(null, "B³¹d transferu plików na serwer FTP.", "B³¹d po³¹czenia", JOptionPane.ERROR_MESSAGE);
                }
                inputStream.close();   	
            	
            }else{
            	JOptionPane.showMessageDialog(null, "Nie mo¿na utworzyæ folderu", "B³¹d serwera FTP", JOptionPane.WARNING_MESSAGE);
            }            
            boolean logout = client.logout();
            if (!logout) {
            	JOptionPane.showMessageDialog(null,"B³¹d wylogowania z serwera FTP.","Po³¹czenie", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        	JOptionPane.showMessageDialog(null,"B³¹d logowania do serwera FTP.","Po³¹czenie", JOptionPane.ERROR_MESSAGE);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {            	
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	}

	public static void deletePhoto(String remotepath){
		try {        	
	        client.connect(ftpIP);            
	        boolean login = client.login(ftpld.getFTPLogin(), ftpld.getFTPPassword());            
	        if (login) {                       
	        	boolean deleted = client.deleteFile("/images/"+remotepath+"/"+remotepath+".jpg");
	            if(deleted){
		            deleted = client.removeDirectory("/images/"+remotepath);		       
		            if (deleted) {
		                JOptionPane.showMessageDialog(null, "Pozycjê usuniêto pomyœlnie.", "Usuwanie", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "B³¹d usuwania pozycji z serwera FTP.","Usuwanie", JOptionPane.ERROR_MESSAGE);
		            }
	            }else{
	            	JOptionPane.showMessageDialog(null, "B³¹d usuwania pliku na serwerze FTP.","Usuwanie", JOptionPane.ERROR_MESSAGE);
	            }	                    
	            boolean logout = client.logout();
	            if (!logout) {
	            	JOptionPane.showMessageDialog(null,"B³¹d wylogowania z serwera FTP.","Po³¹czenie", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(null,"B³¹d logowania do serwera FTP.","Po³¹czenie", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {            	
	            client.disconnect();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
