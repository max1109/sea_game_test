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

public class UserInfoFile {
	String name = "";
	String pwd = "";
	String sex = "";
	String stage = "";
	String age = "";
	File file = null;
	
	UserInfoFile( String name , String pwd , String sex , String stage,String age ) {
		this.file = new File( Environment.getExternalStorageDirectory() , name);
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.age = age;
		this.stage = stage;
		
		Log.e("game info file" , "path = " + Environment.getExternalStorageDirectory());
	}
	
	UserInfoFile (File f ) {
		this.file = f;
	}
	
	public void writeFile() {
		try {
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
//			bw.write( data );
			bw.flush();
			bw.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return name;
	}
	
	public String getData() {
		try {
			 
			if (file.isFile()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String str = "";
				while( ( str = br.readLine()) != null ) {
					str += str;
				}
//				data = str;
				br.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "data";
	}
}
