package com.example.File;

import java.util.ArrayList;
import java.util.List;

public class Data {
	public String pwd = "";
	public String sex = "";
	public List<DataStage> stage = null;
	public List<DataStage> stage_new = null;
	public String age = "";
	public String user ="";
	
	
	public Data() {
		stage = new ArrayList<DataStage>();
		stage_new = new ArrayList<DataStage>();
	}
	
}
