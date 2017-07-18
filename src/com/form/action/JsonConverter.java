package com.form.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.main.form.MainForm;

public class JsonConverter {
	public void convertTrendsValue() {
		try {
			JSONParser parser = new JSONParser();
			FileInputStream fileInputStream = new FileInputStream(
					new File(
							"C:\\Eclipes Work\\Political_Leaning\\trends.txt"));
			byte[] byt = new byte[fileInputStream.available()];
			fileInputStream.read(byt);
			String str = new String(byt);
			str = str.replace(str, "[" + str + "]");
//			str = str.replace("\"entities\":{\"url\":{\"urls\":[{", "");
			System.out.println(str);
			try {
				Object obj = parser.parse(str);
				JSONArray array = (JSONArray) obj;
				JSONObject obj2 = (JSONObject) array.get(0);
				JSONArray trends = (JSONArray) obj2.get("statuses");
				for (int i = 0; i < trends.size(); i++) {
					JSONObject jsonObject = (JSONObject) trends.get(i);
					Vector<String> rowData = new Vector<String>();
					rowData.add(jsonObject.get("id").toString());
					System.out.println("object get ID show table"
							+ jsonObject.get("id").toString());

					rowData.add(jsonObject.get("text").toString());
					System.out.println("object get urls show table"
							+ jsonObject.get("text").toString());
					rowData.add(jsonObject.get("created_at").toString());
					System.out.println("object get created_at show table"
							+ jsonObject.get("created_at").toString());
					
					MainForm.defaultTableModel.addRow(rowData);
					
				}
			} catch (Exception pe) {
				pe.printStackTrace();
			} finally {
				fileInputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void convertSearchValue(String fileName) {
		try {
			JSONParser parser = new JSONParser();
			FileInputStream fileInputStream = new FileInputStream(new File(
					fileName));
			byte[] byt = new byte[fileInputStream.available()];

			fileInputStream.read(byt);
			String s = new String(byt);
			try {
				Object obj = parser.parse(s);
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray trends = (JSONArray) jsonObject.get("statuses");
				System.out.println(trends.size());
				for (int i = 0; i < trends.size(); i++) {
					JSONObject jsObject = (JSONObject) trends.get(i);
					JSONObject jObject = (JSONObject) jsObject.get("user");
					Vector<String> rowData = new Vector<String>();
					rowData.add(jObject.get("id").toString());
					rowData.add(jObject.get("screen_name").toString());
					rowData.add(jObject.get("name").toString());
					rowData.add(jObject.get("listed_count").toString());

					MainForm.defaultTableModelUser.addRow(rowData);
				}

			} catch (Exception pe) {
				pe.printStackTrace();
			} finally {
				fileInputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	

	
}


