package com.example.File;

import java.util.ArrayList;
import java.util.List;

public class Data {
	public String pwd = "";
	public String sex = "";
	public List<Stage> stage = null;
	public List<Stage> stage_new = null;
	public String age = "";
	public String user;
	
	
	public Data() {
		stage = new ArrayList<Stage>();
		stage_new = new ArrayList<Stage>();
	}
	
}
