package com.example.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class GameInfoFile {
	String file_name = ""; // data_time_stage
	String data = "";
	File file = null;
	
	GameInfoFile( String file_name , String data) {
		this.file = new File( Environment.getExternalStorageDirectory() , file_name);
		this.data = data; 
		Log.e("game info file" , "path = " + Environment.getExternalStorageDirectory());
	}
	
	public void writeFile() {
		try {
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write( data );
			bw.flush();
			bw.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return file_name;
	}
	
	public String getData() {
		try {
			 
			if (file.isFile()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String str = "";
				while( ( str = br.readLine()) != null ) {
					str += str;
				}
				data = str;
				br.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}
