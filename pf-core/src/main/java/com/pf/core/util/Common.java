package com.pf.core.util;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class Common {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;
	
	private static final String[] IMAGE_EXT = new String[]{".png", ".jpg", ".jpeg", ".bmp", ".gif", ".PNG", ".JPG", ".JPEG", ".BMP", ".GIF"};

	private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

	private static final List<String> EXCLUDE_SQL_TYPE = new ArrayList<>();

	static{
		EXCLUDE_SQL_TYPE.add("BLOB");
		EXCLUDE_SQL_TYPE.add("CLOB");
		EXCLUDE_SQL_TYPE.add("DATE");
	}

	/**
	 * 判断变量是否为空
	 *
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(Integer s) {
		if (null == s) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return false;
		} else {
			return true;
		}
	}
	

	/**
	 * 使用率计算
	 * 
	 * @return
	 */
	public static String fromUsage(long free, long total) {
		Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(d);
	}

	/**
	 * 保留两个小数
	 * 
	 * @return
	 */
	public static String formatDouble(Double b) {
		BigDecimal bg = new BigDecimal(b);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 返回当前时间　格式：yyyy-MM-dd hh:mm:ss
	 * 
	 * @return String
	 */
	public static String fromDateH(long addTime) {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.format(new Date(System.currentTimeMillis() + addTime));
	}

	/**
	 * 返回当前时间　格式：yyyy-MM-dd hh:mm:ss
	 * 
	 * @return String
	 */
	public static String fromDateH() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.format(new Date());
	}
	
	/**
	 * 返回当前时间　格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String fromDateY() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}
	
	/**
	 * 返回当前时间　格式：yyyyMM
	 * 
	 * @return String
	 */
	public static String fromDateM() {
		DateFormat format1 = new SimpleDateFormat("yyyyMM");
		return format1.format(new Date());
	}
	
	public static String getYear(){
		return Calendar.getInstance().get(Calendar.YEAR) + "";
	}

	/**
	 * 用来去掉List中空值和相同项的。
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 取得html网页内容 UTF8编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlUTF8(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "UTF-8");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * 取得html网页内容 GBK编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlGBK(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "GBK");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * @param inputStream
	 * @param uncode
	 *            编码 GBK 或 UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String readHtml(InputStream inputStream, String uncode) throws Exception {
		InputStreamReader input = new InputStreamReader(inputStream, uncode);
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		return contentBuf.toString();
	}

	/**
	 * 
	 * @return 返回资源的二进制数据 @
	 */
	public static byte[] readInputStream(InputStream inputStream) {

		// 定义一个输出流向内存输出数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 定义一个缓冲区
		byte[] buffer = new byte[1024];
		// 读取数据长度
		int len = 0;
		// 当取得完数据后会返回一个-1
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				// 把缓冲区的数据 写到输出流里面
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 得到数据后返回
		return byteArrayOutputStream.toByteArray();

	}


	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 去除字符串最后一个逗号,若传入为空则返回空字符串
	 * 
	 * @descript
	 * @param para
	 * @return
	 * @version 1.0
	 */
	public static String trimComma(String para) {
		if (isNotEmpty(para)) {
			if (para.endsWith(",")) {
				return para.substring(0, para.length() - 1);
			} else {
				return para;
			}
		} else {
			return "";
		}
	}

	/**
	 * 将Map形式的键值对中的值转换为泛型形参给出的类中的属性值 t一般代表pojo类
	 * 
	 * @descript
	 * @param t
	 * @param params
	 * @version 1.0
	 */
	public static <T extends Object> T flushObject(T t, Map<String, ?> params) {
		if (params == null || t == null) {
			return t;
		}
		Class<?> clazz = t.getClass();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields();

				for (int i = 0; i < fields.length; i++) {
					String name = fields[i].getName(); // 获取属性的名字
					Object value = params.get(name);
					if (value != null && !"".equals(value)) {
						// 注意下面这句，不设置true的话，不能修改private类型变量的值
						fields[i].setAccessible(true);
						if("java.lang.Integer".equals(fields[i].getType().getName()) 
								|| "int".equals(fields[i].getType().getName())){
							fields[i].set(t, Integer.parseInt(value.toString()));
						}else if("java.lang.Float".equals(fields[i].getType().getName())
								|| "float".equals(fields[i].getType().getName())){
							fields[i].set(t, Float.parseFloat(value.toString()));
						}else if("java.lang.Double".equals(fields[i].getType().getName())
								|| "double".equals(fields[i].getType().getName())){
							fields[i].set(t, Double.parseDouble(value.toString()));
						}else if("java.util.Date".equals(fields[i].getType().getName())){
							fields[i].set(t, strToDate(value.toString()));
						}else if("java.lang.Byte".equals(fields[i].getType().getName())){
							fields[i].set(t, Byte.parseByte(value.toString()));
						}else if("java.lang.Long".equals(fields[i].getType().getName())){
							fields[i].set(t, Long.parseLong(value.toString()));
						}else if("java.math.BigDecimal".equals(fields[i].getType().getName())){
							fields[i].set(t, new BigDecimal(value.toString()));
						}else{
							fields[i].set(t, value);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return t;
	}

	/**
	 * html转议
	 * 
	 * @descript
	 * @param content
	 * @return
	 * @author LJN
	 * @date 2015年4月27日
	 * @version 1.0
	 */
	public static String htmltoString(String content) {
		if (content == null) {
			return "";
		}
		String html = content;
		html = html.replace("'", "&apos;");
		html = html.replaceAll("&", "&amp;");
		html = html.replace("\"", "&quot;"); // "
		html = html.replace("\t", "&nbsp;&nbsp;");// 替换跳格
		html = html.replace(" ", "&nbsp;");// 替换空格
		html = html.replace("<", "&lt;");
		html = html.replaceAll(">", "&gt;");

		return html;
	}

	/**
	 * html转议
	 * 
	 * @descript
	 * @param content
	 * @return
	 * @author LJN
	 * @date 2015年4月27日
	 * @version 1.0
	 */
	public static String stringtohtml(String content) {
		if (content == null) {
			return "";
		}
		String html = content;
		html = html.replace("&apos;", "'");
		html = html.replaceAll("&amp;", "&");
		html = html.replace("&quot;", "\""); // "
		html = html.replace("&nbsp;&nbsp;", "\t");// 替换跳格
		html = html.replace("&nbsp;", " ");// 替换空格
		html = html.replace("&lt;", "<");
		html = html.replaceAll("&gt;", ">");

		return html;
	}

	/**
	 * 是否为整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric1(String str) {
		return NUMBER_PATTERN.matcher(str).matches();
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack) {
		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件, 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			@Override
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getExtName(String fileName){
		if(fileName != null && fileName.contains(".")){
			return fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		return "";
	}
	
	/**
	 * 生成32位GUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 字符串转日期
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(str);
		}catch(ParseException e){
			
		}
		return new Date();
	}
	
	/**
	 * 字符串转日期
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str, String format){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(str);
		}catch(ParseException e){
			
		}
		return new Date();
	}
	
	/**
	 * 日期转字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 日期转字符串
	 * @param date
	 * @return
	 */
	public static String date2String(Date date){
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	/**
	 * 根据user-agent获取客户端类型
	 * @param agent
	 * @return
	 */
	public static String getOS(String agent){
		if(agent != null && !"".equals(agent) && !"null".equals(agent)){
			if(agent.indexOf("iPhone") > 0){
				return "iPhone";
			}else if(agent.indexOf("Android") > 0){
				return "Android";
			}
		}
		return "";
	}
	
	/**
	 * 判断是否支持的图片文件
	 * @param extName
	 * @return
	 */
	public static boolean isImage(String extName){
		if(!isEmpty(extName)){
			return Arrays.asList(IMAGE_EXT).contains(extName);
		}
		return false;
	}
	
	/**
	 * 数字转周日历
	    * @Title: dayToCalendar
	    * @param @param day
	    * @param @return    参数
	    * @return Integer    返回类型
	    * @throws
	 */
	public static Integer numberToCalendar(Integer day){
		if(day==1){
			return Calendar.MONDAY;
		}else if(day==2){
			return Calendar.TUESDAY;
		}else if(day==3){
			return Calendar.WEDNESDAY;
		}else if(day==4){
			return Calendar.THURSDAY;
		}else if(day==5){
			return Calendar.FRIDAY;
		}else if(day==6){
			return Calendar.SATURDAY;
		}else if(day==7){
			return Calendar.SUNDAY;
		}
		return null;
	}
	
	/**
	 * 根据长度生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRadomString(int length){
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();
	}
	
	/**
	 * 将空转换为空字符串
	 * @param str
	 * @return
	 */
	public static String nullToString(String str){
		return str == null ? "" : str;
	}
	
	public static String formatGender(String gender){
		if("M".equals(gender)){
			return "1";
		}else if("F".equals(gender)){
			return "2";
		}else{
			return "0";
		}
	}
	
	/**
	 * 时间比较
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(String date1, String date2){
		
		if(isEmpty(date1) || isEmpty(date2)) {
			return -1;
		}
		
		String format = "yyyy-MM-dd HH:mm:ss";
		
		if(date1.length() < 19 || date2.length() < 19){
			format = "yyyy-MM-dd";
		}
		
		DateFormat df = new SimpleDateFormat(format);
		
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return -1;
	}

	public static boolean sqlTypeExclude(String type){
		if(type == null){
			return false;
		}
		return EXCLUDE_SQL_TYPE.contains(type.toUpperCase());
	}

}
