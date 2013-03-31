package org.openmrs.module.radiotest.utils;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;


public class EncryptValue 
	implements Serializable, UserType {

    private static final int[] SQL_TYPES = {Types.VARCHAR};
 
    
    public int[] sqlTypes() {
        return SQL_TYPES;
    }
    public Class<?> returnedClass() {
        return  java.lang.String.class;
    }

    public boolean equals(Object pObject1, Object pObject2) throws HibernateException {
        if (pObject1 == null && pObject2 == null) return true;
        if (pObject1 == null || pObject2 == null) return false;
        return (( String ) pObject1).equals(( String ) pObject2);
    }

    public int hashCode(Object o) throws HibernateException {
        return ((String) o).hashCode();
    }

    public Object nullSafeGet(ResultSet pResultSet, String[] pStrings, Object o) 
	throws HibernateException, SQLException {
        String encryptedValue = pResultSet.getString(pStrings[0]);
        try {
			return pResultSet.wasNull() ? null : RadioSecurity.decrypt(encryptedValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public void nullSafeSet(PreparedStatement pPreparedStatement, Object o, int i) 
	throws HibernateException, SQLException {
        if ( o == null ) {
            pPreparedStatement.setNull(i, Types.VARCHAR);
        } else {
            try {
				pPreparedStatement.setString(i, RadioSecurity.encrypt((String) o) );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    public Object assemble(Serializable pSerializable, Object o) throws HibernateException {
        return pSerializable;
    }

    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}