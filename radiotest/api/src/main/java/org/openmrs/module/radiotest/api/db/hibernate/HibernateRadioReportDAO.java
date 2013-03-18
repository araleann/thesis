package org.openmrs.module.radiotest.api.db.hibernate;

import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioReport;
import org.openmrs.module.radiotest.api.db.RadioReportDAO;
import org.openmrs.module.radiotest.propertyeditor.RadioReportResultTransformer;

public class HibernateRadioReportDAO implements RadioReportDAO{
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
		
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

	@Override
	public String generateReport(RadioReport report) throws DAOException {
		// TODO Auto-generated method stub
		StringBuilder csv = new StringBuilder();
		Criteria criteria = report
								.getCriteria()
									.getExecutableCriteria(sessionFactory.getCurrentSession())
										.setResultTransformer(new RadioReportResultTransformer());
		List<Object[]> table = (List<Object[]>) criteria.list();
		
		for(Object[] row : table){
			csv.append(row[0]);
			for(int i = 1; i < row.length; i++){
				csv.append("," + row[i]);
			}
			csv.append("\n");
		}
		
//		try {
//			JFileChooser fc = new JFileChooser();
//			if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
//				PrintWriter out = new PrintWriter(fc.getSelectedFile());
//				out.print(csv.toString());
//				out.close();
//			}
//		} catch (Exception e){
//			e.printStackTrace();
//		}
		
		return csv.toString();
	}
}
