package org.openmrs.module.radiotest.api.db.hibernate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.RadioNote;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioReport;
import org.openmrs.module.radiotest.RadioResult;
import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.db.RadioReportDAO;
import org.springframework.util.StringUtils;

public class HibernateRadioReportDAO implements RadioReportDAO{
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	private List<String> propList;
	private List<String> headers;
	private List<String> fieldList;
	
	HashMap<Class<?>, String> prefix;
	HashMap<String, String> specialHeaders;
	HashMap<String, Projection> specialProjections;
		
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }
    
    private String getPrefix(Class<?> cls){
    	if(prefix == null){
    		prefix = new HashMap<Class<?>, String>();
        	
    		prefix.put(RadioPatient.class, "p");
    		prefix.put(RadioAlias.class, "a");
    		prefix.put(RadioCategory.class, "c");
    		prefix.put(RadioExam.class, "e");
    		prefix.put(RadioExamType.class, "et");
    		prefix.put(RadioTransaction.class, "t");
    		prefix.put(RadioResult.class, "r");
    		prefix.put(RadioNote.class, "n");
    	}
		
		return prefix.get(cls);
    }
    
    private String getHeader(String prop, String fieldname){
    	if(specialHeaders == null){
    		specialHeaders = new HashMap<String, String>();
    		
    		specialHeaders.put("e.name", "Exam Name");
    		specialHeaders.put("et.type", "Exam Type");
    		specialHeaders.put("nt.name", "Note Type");
    		specialHeaders.put("r.positive", "Result");
    		specialHeaders.put("n.type", "Note Type");
    		
    	}
    	
    	String header = specialHeaders.get(prop);
    	
    	return header != null? header : WordUtils.capitalize(fieldname.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2"));
    }
    
    private Projection getProjection(String prop){
    	if(specialProjections == null){
    		specialProjections = new HashMap<String, Projection>();
    		String[] yn = { "'Y'", "'N'" };
    		
    		specialProjections.put("p.philhealth", booleanProjection("ISNULL(p1_.philhealth)", new String[]{ "''", "p1_.philhealth"}, "philhealth"));
    		specialProjections.put("t.paid", booleanProjection("t6_.paid", yn, "paid"));
    		specialProjections.put("t.claimed", booleanProjection("t6_.claimed", yn, "claimed"));
    		specialProjections.put("r.positive", booleanProjection("r9_.positive", new String[]{ "'P'", "'N'" }, "result"));
    		specialProjections.put("r.findings", booleanProjection("r9_.positive", new String[]{ "r9_.findings", "''"}, "findings"));
    		specialProjections.put("n.type", booleanProjection("ISNULL(n7_.description)", new String[]{ "IFNULL(nt8_.name, '')", "n7_.description"}, "noteType"));
    		specialProjections.put("n.note", booleanProjection("ISNULL(n7_.note)", new String[]{ "''", "n7_.note"}, "note"));
    	}
    	Projection special = specialProjections.get(prop);
    	
    	return special != null? special : Projections.property(prop);
    }
    
    private Projection booleanProjection(String prop, String[] tf, String alias){
    	String sql = String.format("IF(%s, %s, %s) as '%s'", prop, tf[0], tf[1], alias);
    	return Projections.sqlProjection(sql, new String[]{ alias }, new Type[]{ new StringType() });
    }
    
    private Object getDetails(Class<?> cls, Object obj){
    	try {			
			for(Field fld : cls.getDeclaredFields()){
				fld.setAccessible(true);
				
				String fieldname = fld.getName();
				String prop = getPrefix(cls) + "." + fieldname;
								
				if(!(fieldname.equals("id") || fieldname.equals("voided"))){
					Object value = obj == null? null : fld.get(obj);
					if(value != null || fieldList.contains(prop)){
						Class<?> cs = fld.getType();
						if(value instanceof String){
							String val = (String) value;
							String newVal = StringUtils.hasText(val)? (val.equals("_")? "" : val) : null;
							fld.set(obj, newVal);
						} else if(Collection.class.isAssignableFrom(cs)){
							fld.set(obj, null);
							continue;
						}
						
						propList.add(prop);
						headers.add(getHeader(prop, fieldname));
					}
				}
				
				fld.setAccessible(false);
			}
		} catch(Exception e){
			
		}
		
		return obj;
	}
    
    private Criterion getCriterion(Class<?> cls, Object obj){
		try {
			obj = getDetails(cls, obj);
			
			if(obj == null){
				obj = cls.newInstance();
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return Example.create(obj)
					.ignoreCase()
					.excludeZeroes()
					.enableLike();
	}
    
    private ProjectionList createProjectionList(){
		ProjectionList pl = Projections.projectionList();
		
		for(String prop : propList){
			pl.add(getProjection(prop), prop);
		}
		
		return pl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String generateReport(RadioReport report) throws DAOException {
		// TODO Auto-generated method stub
		propList = new ArrayList<String>();
		headers = new ArrayList<String>();
		fieldList = report.getFieldList();
				
		Criteria criteria = sessionFactory.getCurrentSession()
								.createCriteria(RadioTransExam.class, "te")
									.createCriteria("te.patient", "p")
										.add(getCriterion(RadioPatient.class, report.getPatient()))
										.createCriteria("p.aliases", "a")
											.add(getCriterion(RadioAlias.class, report.getAlias()))
											.createCriteria("a.category", "c")
												.add(getCriterion(RadioCategory.class, report.getCategory()))
								.createCriteria("te.exam", "e")
									.add(getCriterion(RadioExam.class, report.getExam()))
									.createCriteria("e.type", "et")
										.add(getCriterion(RadioExamType.class, report.getExamType()))
								.createCriteria("te.transaction", "t")
									.add(getCriterion(RadioTransaction.class, report.getTransaction()))
									.add(Restrictions.ge("t.visitDate", report.getStartDate()))
									.add(Restrictions.le("t.visitDate", report.getEndDate()))
									.createCriteria("t.notes", "n", CriteriaSpecification.LEFT_JOIN)
										.add(getCriterion(RadioNote.class, report.getNote()))
										.createCriteria("n.type", "nt", CriteriaSpecification.LEFT_JOIN)
								.createCriteria("te.findings", "r")
									.add(getCriterion(RadioResult.class, report.getResult()))
									;
		
		RadioNoteType noteType = report.getNoteType();
		if(noteType != null){
			if(noteType.getId() == 0){
				criteria.add(Restrictions.isNotNull("n.description"));
			} else {
				criteria.add(Restrictions.eq("nt.name", noteType.getName()));
			}
		}
		
		criteria.setProjection(createProjectionList());
		List<Object[]> results = (List<Object[]>) criteria.list();
		
		StringBuilder csv = new StringBuilder();
		
		csv.append(StringUtils.collectionToCommaDelimitedString(headers));
		csv.append("\n");
		for(Object[] row : results){
			csv.append(StringUtils.arrayToCommaDelimitedString(row));
			csv.append("\n");
		}
		
		return csv.toString();
	}
}
