package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.zip.Adler32;

/**
 * 模块名称： 公共<br>
 */
public class CommonUtils {
	private static Log logger = LogFactory.getLog(CommonUtils.class);
	
	public static final String SEG = "-";
	private static final char[] chars = new char[] { '1', '2', '3', '4', '5',
			'6', '7', '8', '9' };

	public CommonUtils() {
	}

	/**
	 * 不以科学技术法方式显示
	 * @param d 小数
	 * @param scale 保留位数
	 * @return
	 */
	public static String change(BigDecimal d, int scale) {
		BigDecimal d2 = new BigDecimal(Integer.toString(1));

		return subZeroAndDot(d.divide(d2,scale,BigDecimal.ROUND_HALF_DOWN).toString());
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * @param s
	 * @return
	 */
	private static String subZeroAndDot(String s){
		if(s.indexOf(".") > 0){
			s = s.replaceAll("0+?$", "");//去掉多余的0，用""替换掉0+?$
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;
	}


	public static long Gseq(int length) {
		String s = RandomStringUtils.random(length, chars);
		return Long.parseLong(s);
	}

	public static String getSeq() throws IOException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		String[] str = ip.split("\\.");
		String ipStr = str[2].toString() + str[3].toString();
		if (ipStr == null || ipStr.equals("")) {
			ipStr = "tsp";
		}
		return suid(ipStr, Gseq(9));
	}

	public static Long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	public static final String globalTimer() {
		return new SimpleDateFormat("yyMMddHHmmss").format(new Date(System
				.currentTimeMillis()));
	}

	public static final String suid(String localhost, long seq) {
		return localhost + SEG + getCurrentTimeMillis() + SEG + seq;
	}

	/**
	 * 指令校验和(无符号)
	 * 
	 * @param bytes
	 *            要校验的字节数组
	 * @return 校验和
	 */
	public static String Checknum(byte[] bytes) {
		Adler32 a = new Adler32();
		a.update(bytes);

		String hex = Integer.toHexString((int) (a.getValue()) - 1)
				.toUpperCase();
		if (hex.length() > 4) {
			hex = hex.substring(hex.length() - 4);
		}

		return hex;
	}
	
	// 判断json消息中是否包括某个key
	public static void checkParameter(JSONObject reqBody, String[] toCheck) {
		for (String param : toCheck) {
			if(param.indexOf(",") > 0){
				// 逗号分割
				String[] params = param.split(",");
				boolean notExist = true;
				// 遍历json串
				for(String one : params){
					// 判断集合对象中是否包含指定的键名。置换notExist的值
					if(reqBody.containsKey(one)){
						notExist = false;
						break;
					}
				}
				if(notExist){
					logger.error("参数" + param + "不能全部为空");
//					throw new Exception("缺少请求参数");
				}
				
			}else{
				if (!reqBody.containsKey(param)) {
					logger.error("参数" + param + "是必须的");
//					throw new BeeStoreException(ResultCode.PARAM_ERROR, "缺少请求参数");
				}
			}
	
		}
	}

	
	 /** 
     * @Description:  32位小写MD5 
     */  
    public static String toLow32MD5(String str){
        String reStr = null;
        try {  
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());  
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){  
                int bt = b&0xff;  
                if (bt < 16){  
                    stringBuffer.append(0);  
                }   
                stringBuffer.append(Integer.toHexString(bt));
            }  
            reStr = stringBuffer.toString();  
        } catch (NoSuchAlgorithmException e) {
        	logger.error(e);
        }  
        return reStr;  
    }  
    
    /**
     * 在字符串数组中查找某字符串
     * 
     * @param toFind
     * @param scope
     * @return true：未找到， false：找到
     */
    public static boolean notIn(String toFind, String[] scope){
    	if(toFind != null && scope != null && scope.length > 0){
    		for(String temp : scope){
    			if(toFind.equals(temp)){
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }

	/**
	 * 获取x位随机数数
	 *
	 */
	public static int randomNumber(int x){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < x; i++) {
			result = result * 10 + array[i];
		}
		return result;
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
     * 产生64位随机字符 
     * @return
     */
    public static String random64(){
    	return generateUUID() + generateUUID();
    }


	/**
	 * 生成随机密码
	 *
	 * @param pwd_len
	 * 生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String genRandomNum(int pwd_len) {
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = {'0','1','2','3','4','5','6','7','8','9'};

		StringBuffer pwd = new StringBuffer();
		Random r = new Random();
		while (count < pwd_len) {
		// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	public static int randomNum(int max, int min){
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++){
			System.out.println(randomNum(8,5));
		}
	}
}
