package org.openmrs.module.radiotest.propertyeditor;

import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;

import org.hibernate.transform.ResultTransformer;

public class RadioReportResultTransformer implements ResultTransformer {
	
	@Override
	public List transformList(List coll) {
		// TODO Auto-generated method stub
		return coll;
	}

	@Override
	public Object transformTuple(Object[] row, String[] aliases) {
		// TODO Auto-generated method stub		
		try {
			JFileChooser fc = new JFileChooser();
			if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
				PrintWriter out = new PrintWriter(fc.getSelectedFile());
				for(String alias : aliases){
					out.println(alias);
				}
				out.println();
				
				for(Object data : row){
					out.println(data);
				}
				
				out.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return row;
	}

}
