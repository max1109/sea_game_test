package com.example.File;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class DataHandler extends DefaultHandler {
	private Data _data;
	private boolean _pwd = false, _user = false, _stage = false, _age = false,
			_sex = false;

	public Data getData() {
		return _data;
	}

	@Override
	public void startDocument() throws SAXException {
		_data = new Data();
//		Log.e("DataHandler", "startDocument");
	}

	@Override
	public void endDocument() throws SAXException {
//		Log.e("DataHandler", "endDocument");
	}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
//		Log.e("DataHandler", "startElement");
		if (localName.equals("user")) {
			_user = true;
		} else if (localName.equals("pwd")) {
			_pwd = true;
		} else if (localName.equals("sex")) {
			_sex = true;
		} else if (localName.equals("stage")) {
			DataStage s = new DataStage();
			s.id = atts.getValue("id");
			s.date = atts.getValue("date");
			_data.stage.add(s);
			_stage = true;

		} else if (localName.equals("age")) {
			_age = true;
		}

	}

	/**
	 * Called at the end of the element. Setting the booleans to false, so we
	 * know that we've just left that tag.
	 * 
	 * @param namespaceURI
	 * @param localName
	 * @param qName
	 * @throws SAXException
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException 
	{
//		Log.e("DataHandler", "endElement");

		if (localName.equals("user")) {
			_user = false;
		} else if (localName.equals("pwd")) {
			_pwd = false;
		} else if (localName.equals("sex")) {
			_sex = false;
		} else if (localName.equals("stage")) {
			_stage = false;
		} else if (localName.equals("age")) {
			_age = false;
		}

	}

	/**
	 * Calling when we're within an element. Here we're checking to see if there
	 * is any content in the tags that we're interested in and populating it in
	 * the Config object.
	 * 
	 * @param ch
	 * @param start
	 * @param length
	 */
	@Override
	public void characters(char ch[], int start, int length) {
//		Log.e("DataHandler", "characters");
		String chars = new String(ch, start, length);
		chars = chars.trim();

		if (_user) {
			_data.user = chars;
			_user = false;
		} else if (_pwd) {
			_data.pwd = chars;
			_pwd = false;
		} else if (_sex) {
			_data.sex = chars;
			_sex = false;
		} else if (_stage) {
			int last = _data.stage.size() - 1;
			_data.stage.get(last).score = chars;
			_stage = false;
		} else if (_age) {
			_data.age = chars;
			_age = false;
		}

	}

}
