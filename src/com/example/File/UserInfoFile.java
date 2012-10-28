package com.example.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

public class UserInfoFile {
	File file = null;
	Data d = null;

	public UserInfoFile(File f) {
		file = f;
	}

	public void writeFile() {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			// bw.write( data );
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAllData() {
		String tmp = "";
		if (d != null) {
			Log.e("tag", "all data");
			tmp = d.user + " " + d.pwd + " " + d.sex + " " + d.age + " \n";
			if (d.stage != null) {
				for (int x = 0; x < d.stage.size(); x++) {
					tmp += d.stage.get(x).id;
					tmp += d.stage.get(x).date;
					tmp += d.stage.get(x).score;
					tmp += "\n";
				}
			}
		} else {
			Log.e("tag", "null data ");
		}
		return tmp;
	}

	public String getFileName() {
		if (file != null)
			return file.getName();
		else
			return "";
	}

	String data = "";

	public Data getData() {
		try {

			if (file.isFile()) {
				Log.e("tag", "file.isFile()");

//				BufferedReader br = new BufferedReader(new FileReader(file));
//				String str = "";
//				while ((str = br.readLine()) != null) {
//					data += str + "\n";
//				}
//				br.close();

				SAXParserFactory spf = SAXParserFactory.newInstance();   
			    SAXParser sp = spf.newSAXParser();   
			    XMLReader xr = sp.getXMLReader();   
			    DataHandler dataHandler = new DataHandler();   
			    xr.setContentHandler(dataHandler);   
				xr.parse(new InputSource(new FileInputStream(file)));
				d = dataHandler.getData();
				
				if (d == null) {
					Log.e("tag getData ", " d = null");
				} else {
					Log.e("tag getData ", " d not null");
				}
			} else {
				Log.e("tag", " not file.isFile()");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("SAX XML", "sax parse io error", e);
			return null;
		} catch (ParserConfigurationException pce) {
			Log.e("SAX XML", "sax parse error", pce);
			return null;
		} catch (SAXException se) {
			Log.e("SAX XML", "sax error", se);
			return null;
		}

		return d;
	}
}
