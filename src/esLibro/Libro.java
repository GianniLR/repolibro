package esLibro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Libro {

	public static void main(String[] args) {
		URL url;
		URLConnection uRLConnection=null;
		try {
			url = new URL("https://it.wikipedia.org/wiki/Il_nome_della_rosa");
			uRLConnection = url.openConnection();//apro la connessione
			uRLConnection.connect();//mi connetto
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int contRosa=0, contVita=0;
		String str="";
		boolean periodo=false;
		
		try {
			InputStream risp=uRLConnection.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(uRLConnection.getInputStream()));
			String riga=br.readLine();
			do {
				riga=br.readLine();
				
				if(periodo) {
					str+=riga;
				}
				
				if (riga!=null && riga.indexOf("<p>") >= 0) {
					periodo = true;
					str="";
				}
				if (riga!=null && riga.indexOf("</p>") >= 0) {
					periodo = false;
					String[] splitString = str.split(" ");
					for (int i = 0; i < splitString.length; i++) 
					{
						if (splitString[i].contains("rosa")) {
							contRosa++;
						} 
						else if (splitString[i].contains("vita")) {
							contVita++;
						}
						
					}
					
				}
				
				//System.out.println(riga);
			}while(riga!=null);
			
			br.close();
			
			System.out.println("ROSA: "+contRosa+"\nVITA: "+contVita);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
