package org.openmrs.module.radiotest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.openmrs.BaseOpenmrsData;
import org.springframework.util.StringUtils;

public abstract class RadioData extends BaseOpenmrsData {

	List<String> headers;
	
	public List<String> getAliasList(){
		List<String> aliasList = new ArrayList<String>();
		headers = new ArrayList<String>();
		Field[] fields = this.getClass().getDeclaredFields();
		
		try {
			for(Field f : fields){
				if(!f.getName().equals("id")){
					Object obj = f.get(this);
					if(obj != null){
						if(!StringUtils.hasText(obj.toString())){
							f.set(this, null);
						}
						
						aliasList.add(getPrefix() + "." + f.getName());
						headers.add(WordUtils.capitalize(f.getName().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2")));
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return aliasList;
	}
	
	public List<String> getHeaders(){
		return headers;
	}
	
	abstract public String getPrefix();
}
