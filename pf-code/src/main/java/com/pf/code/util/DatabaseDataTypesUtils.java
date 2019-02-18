package com.pf.code.util;


import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */
public class DatabaseDataTypesUtils {

	private final static IntStringMap _preferredJavaTypeForSqlType = new IntStringMap();
	
	private final static Map<String, String> _jdbcTypes = new HashMap<>();
	 
	public static boolean isFloatNumber(int sqlType,int size,int decimalDigits) {
		String javaType = getPreferredJavaType(sqlType,size,decimalDigits);
		if(javaType.endsWith("Float") || javaType.endsWith("Double") || javaType.endsWith("BigDecimal")) {
			return true;
		}
		return false;
	}
	
	public static boolean isIntegerNumber(int sqlType,int size,int decimalDigits) {
		String javaType = getPreferredJavaType(sqlType,size,decimalDigits);
		if(javaType.endsWith("Long") || javaType.endsWith("Integer") || javaType.endsWith("Short")) {
			return true;
		}
		return false;
	}

	public static boolean isDate(int sqlType,int size,int decimalDigits) {
		String javaType = getPreferredJavaType(sqlType,size,decimalDigits);
		if(javaType.endsWith("Date") || javaType.endsWith("Timestamp")) {
			return true;
		}
		return false;
	}
	
	public static String getPreferredJavaType(int sqlType, int size,
			int decimalDigits) {
		if ((sqlType == Types.DECIMAL || sqlType == Types.NUMERIC)
				&& decimalDigits == 0) {
			if (size == 1) {
				// https://sourceforge.net/tracker/?func=detail&atid=415993&aid=662953&group_id=36044
				return "java.lang.Boolean";
			} else if (size < 3) {
				return "java.lang.Byte";
			} else if (size < 5) {
				return "java.lang.Short";
			} else if (size < 10) {
				return "java.lang.Integer";
			} else if (size < 19) {
				return "java.lang.Long";
			} else {
				return "java.math.BigDecimal";
			}
		}
		String result = _preferredJavaTypeForSqlType.getString(sqlType);
		if (result == null) {
			result = "java.lang.Object";
		}
		return result;
	}
	
	/**
	 * 获取jdbc对应的type
	 * mybatis用
	 * @param type
	 * @return
	 */
	public static String getJdbcType(String type){
		return _jdbcTypes.get(type);
	}
		   
   static {
      _preferredJavaTypeForSqlType.put(Types.TINYINT, "Byte");
      _preferredJavaTypeForSqlType.put(Types.SMALLINT, "Short");
      _preferredJavaTypeForSqlType.put(Types.INTEGER, "Integer");
      _preferredJavaTypeForSqlType.put(Types.BIGINT, "Long");
      _preferredJavaTypeForSqlType.put(Types.REAL, "Float");
      _preferredJavaTypeForSqlType.put(Types.FLOAT, "Double");
      _preferredJavaTypeForSqlType.put(Types.DOUBLE, "Double");
      _preferredJavaTypeForSqlType.put(Types.DECIMAL, "java.math.BigDecimal");
      _preferredJavaTypeForSqlType.put(Types.NUMERIC, "java.math.BigDecimal");
      _preferredJavaTypeForSqlType.put(Types.BIT, "Boolean");
      _preferredJavaTypeForSqlType.put(Types.CHAR, "String");
      _preferredJavaTypeForSqlType.put(Types.VARCHAR, "String");
      // according to resultset.gif, we should use java.io.Reader, but String is more convenient for EJB
      _preferredJavaTypeForSqlType.put(Types.LONGVARCHAR, "String");
      _preferredJavaTypeForSqlType.put(Types.BINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.VARBINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.LONGVARBINARY, "java.io.InputStream");
      _preferredJavaTypeForSqlType.put(Types.DATE, "java.sql.Date");
      _preferredJavaTypeForSqlType.put(Types.TIME, "java.sql.Time");
      _preferredJavaTypeForSqlType.put(Types.TIMESTAMP, "java.sql.Timestamp");
      _preferredJavaTypeForSqlType.put(Types.CLOB, "java.sql.Clob");
      _preferredJavaTypeForSqlType.put(Types.BLOB, "java.sql.Blob");
      _preferredJavaTypeForSqlType.put(Types.ARRAY, "java.sql.Array");
      _preferredJavaTypeForSqlType.put(Types.REF, "java.sql.Ref");
      _preferredJavaTypeForSqlType.put(Types.STRUCT, "Object");
      _preferredJavaTypeForSqlType.put(Types.JAVA_OBJECT, "Object");
      _jdbcTypes.put("VARCHAR2", "VARCHAR");
      _jdbcTypes.put("NUMBER", "INTEGER");
      _jdbcTypes.put("DATE", "DATE");
      _jdbcTypes.put("INT", "INTEGER");
	   _jdbcTypes.put("TEXT", "VARCHAR");
	   _jdbcTypes.put("DATETIME", "DATE");
   }
		
   private static class IntStringMap extends HashMap {

		public String getString(int i) {
			return (String) get(new Integer(i));
		}

		public String[] getStrings(int i) {
			return (String[]) get(new Integer(i));
		}

		public void put(int i, String s) {
			put(new Integer(i), s);
		}

		public void put(int i, String[] sa) {
			put(new Integer(i), sa);
		}
	}
	   
}
