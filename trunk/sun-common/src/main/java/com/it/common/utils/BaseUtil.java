package com.it.common.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 工具类
 *
 * @author 孙帅
 */
public class BaseUtil {
    private static String encoding = "UTF-8";

	/**
     * 获取系统当前时间组成的字符串，( GeShi : 显示格式 )
     *
     * @return 当前时间组成的字符串
     */
    public static String GetDataStr(String GeShi) {
        SimpleDateFormat sdf = new SimpleDateFormat(GeShi);
        String getDate = sdf.format(new Date());
        return getDate;
    }


    /**
     * 将指定的时间转为字符串格式
     *
     * @param date  指定的时间
     * @param GeShi 转换后显示的格式
     * @return
     */
    public static String GetDataStrs(Date date, String GeShi) {
        SimpleDateFormat sdf = new SimpleDateFormat(GeShi);
        String getDate = sdf.format(date);
        return getDate;
    }


	/**
	 * 将指定的时间转为字符串格式
	 *
	 * @param date  指定的时间
	 * @param GeShi 转换后显示的格式
	 * @return
	 */
	public static Date GetDataStrsToData(Date date, String GeShi) {
		SimpleDateFormat sdf = new SimpleDateFormat(GeShi);
		String getDate = sdf.format(date);
		return GetStringToDate(GeShi,getDate);
	}


	/**
	 * 将指定的时间转为i天之后的时间
	 *
	 * @param date  指定的时间
	 * @param afterDay 加i天
	 * @return
	 */
	public static Date GetDataStrsAfterI(Date date, int afterDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, afterDay);// +i天
		Date afterDate = c.getTime();
		return afterDate;
	}
    
    /**
     * 将指定的时间的Long值转为对应格式的字符串格式时间
     *
     * @param dateLong  指定的时间(Long值)
     * @param GeShi 转换后显示的格式
     * @return
     */
    public static String GetDataStrs(Long dateLong, String GeShi) {
        Date date = new Date(dateLong);
        SimpleDateFormat sdf = new SimpleDateFormat(GeShi);
        String getDate = sdf.format(date);
        return getDate;
    }

    /**
     * 计算两个时间的差值(秒数)
     *
     * @param date
     * @param date2
     * @return
     */
    public static double GetDataMinis(Date date, Date date2) {
        long t1 = date.getTime();
        long t2 = date2.getTime();
        double tnum = (t2 - t1) / 1000;
        return tnum;
    }

    /**
     * 将字符串时间转换成对应格式的DATA类型
     *
     * @param GeShi 格式
     * @param times 字符串时间
     * @return
     */
    public static Date GetStringToDate(String GeShi, String times) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(GeShi);
            return sdf.parse(times);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回当前时间的LONG值
     *
     * @return
     */
    public static Long GetTimeLong() {
        Date dt = new Date();
        return dt.getTime();
    }
    
    /**
     * 将对应的时间格式转换成String类型
     * @param geShi
     * @param times
     * @return
     */
    public static String getTimeStr(String geShi, String times){
    	try{
    		SimpleDateFormat sdf = new SimpleDateFormat(geShi);
    		Date time = sdf.parse(times);
    		return sdf.format(time);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}
	
	
    /**
     * 返回当天0点0分0秒的时间的Long值
     *
     * @return
     */
    public static Long GetZeroTimeLong() {
        String ZeroTimeStr = GetDataStr("yyyy-MM-dd 00:00:00");
        Date ZeroTimeDt = GetStringToDate("yyyy-MM-dd 00:00:00", ZeroTimeStr);
        return ZeroTimeDt.getTime();
    }

	/**
	 * 返回当天0点0分0秒的时间Date
	 * @return
	 */
	public static Date GetZeroTimeDate() {
		String ZeroTimeStr = GetDataStr("yyyy-MM-dd 00:00:00");
		Date ZeroTimeDt = GetStringToDate("yyyy-MM-dd 00:00:00", ZeroTimeStr);
		return ZeroTimeDt;
	}

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replace("-", "");
        return str;
    }

    /**
     * 返回随机字符串
     *
     * @param StrLength
     * @return
     */
    public static String GetRmStr(int StrLength) {
        String[] list = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer strB = new StringBuffer();
        for (int i = 0; i < StrLength; i++) {
            Random rd = new Random();
            int Ind = rd.nextInt(list.length);
            strB.append(list[Ind]);
        }
        return strB.toString();
    }

	/**
	 * 返回随机长度的数字
	 *
	 * @param StrLength
	 * @return
	 */
	public static String GetRmNumStr(int StrLength) {
		String[] list = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		StringBuffer strB = new StringBuffer();
		for (int i = 0; i < StrLength; i++) {
			Random rd = new Random();
			int Ind = rd.nextInt(list.length);
			strB.append(list[Ind]);
		}
		return strB.toString();
	}

	/**
	 * 返回一个类似UUID的编号
	 * @return
	 */
	public static String getObjIdNum(){
		Random rd = new Random();
		int ind = rd.nextInt(4)+4;//生成4-8位数
		StringBuilder uid = new StringBuilder(GetRmStr(ind)).append("-");

		Random rd2 = new Random();
		int ind2 = rd2.nextInt(4)+4;//生成4-8位数
		uid.append(GetRmStr(ind2)).append("-");

		Random rd3 = new Random();
		int ind3 = rd3.nextInt(4)+4;//生成4-8位数
		uid.append(GetRmStr(ind3));
		return uid.toString();
	}

    /**
     * List集合去重
     *
     * @param list
     * @return
     */
    public static List unique(List list) {
        List tempList = new ArrayList();
        for (Object i : list) {
            if (!tempList.contains(i)) {
                tempList.add(i);
            }
        }
        return tempList;
    }

    /**
     * String[]数组去重
     *
     * @param arr 需要去重的原始String[]对象
     * @return
     */
    public static String[] unique(String[] arr) {
        List<String> lists = new LinkedList<String>();
        for (int i = 0; i < arr.length; i++) {
            if (!lists.contains(arr[i])) {
                lists.add(arr[i]);
            }
        }
        return (String[]) lists.toArray(new String[lists.size()]);
    }
    


    /**
	 * 字符串混淆
	 * @param strS   
	 * @return
	 */
	public static String getEncode(String strS){
		StringBuilder EncodePass=new StringBuilder(GetRmStr(5));
		for (int i = 0; i < strS.length(); i++) {
			EncodePass.append(strS.substring(i, i+1)+GetRmStr(5));
		}
		return EncodePass.toString();
	}
	/**
	 * 还原字符串
	 * @param Str   需要还原的字符串
	 * @return
	 */
	public static String getDecode(String Str){
		StringBuilder EncodePass=new StringBuilder();
		for (int i = 5; i < Str.length(); i++) {
			EncodePass.append(Str.substring(i, i+1));
			i=i+5;
		}
		return EncodePass.toString();
	}

	/**
	 * 创建目录
	 * 判断目录是否存在，不存在就创建。
	 * @param dirPath
	 * @return
	 */
	public static Boolean CreDir(String dirPath){
		try {
			File myPath = new File(dirPath);
			if (!myPath.exists()) {//若此目录不存在，则创建之
				myPath.mkdir();
			}
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 写入本地文件
	 * @param path
	 * @param value
	 * @return
	 */
	public static String WriteTxt(String path,String value){
		BufferedWriter writer=null;
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(path);
			writer = new BufferedWriter(new OutputStreamWriter(fos,encoding));
			writer.write(value);
        } catch (Exception e) {
        	System.out.println("写入文件失败,地址："+path);
        	return "ERROR";
        }finally{
        	if(writer!=null){
				try {
					writer.close();
					writer=null;
				} catch (Exception e) {
					System.out.println("文件读写关闭输入流失败");
				}
			}
        	if(fos!=null){
				try {
					fos.close();
					fos=null;
				} catch (Exception e) {
					System.out.println("文件读写关闭输入流失败");
				}
			}
        }
		return "OK";
	}
	/**
	 * 读取本地文件
	 * @param path
	 * @return
	 */
	public static String ReadTxt(String path){
		FileInputStream fs=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			fs = new FileInputStream(path);
			isr = new InputStreamReader(fs,encoding);
			br = new BufferedReader(isr);
			StringBuilder value=new StringBuilder();
			while (true) {
				String DataLong=br.readLine();
				if(DataLong!=null){
					value.append(DataLong).append("\r\n");
				}else{
					break;
				}
			}
			return value.toString();
		} catch (Exception e) {
//			System.out.println("文件读取失败，地址："+path);
		}finally{
			if(br!=null){
				try {
					br.close();
					br=null;
				} catch (Exception e) { }
			}
			if(isr!=null){
				try {
					isr.close();
					isr=null;
				} catch (Exception e) { }
			}
			if(fs!=null){
				try {
					fs.close();
					fs=null;
				} catch (Exception e) { }
			}
		}
		return "";
	}

	/**
	 * 删除文件,只支持删除文件,不支持删除目录
	 * @param file
	 * @throws Exception
	 */
	public static void delFile(File file) throws Exception {
		if (!file.exists()) {
			throw new Exception("文件" + file.getName() + "不存在,请确认!");
		}
		if (file.isFile()) {
			if (file.canWrite()) {
				file.delete();
			} else {
				throw new Exception("文件" + file.getName() + "只读,无法删除,请手动删除!");
			}
		} else {
			throw new Exception("文件" + file.getName() + "不是一个标准的文件,有可能为目录,请确认!");
		}
	}
	/**
	 * 将文件转换成BYTE字符串(用于网络传输)
	 * @param path
	 * @return
	 */
	public static String FileToByte(String path){
		try{
			File audio=new File(path);
			FileInputStream inputFile = new FileInputStream(audio);
			byte[] buffer = new byte[(int) audio.length()];
			inputFile.read(buffer);
			inputFile.close();
			String ByteStr = new String(Base64.encodeBase64(buffer));
//			try{
//				delFile(audio);//读取完之后就删除临时文件
//			}catch (Exception e) { }
			return ByteStr;
		}catch (Exception e) { }
		return "";
	}


	/**
	 * 将图片流转成base64编码格式
	 * @param in
	 * @return
	 */
	public static String getBase64FromInputStream(InputStream in) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[100];
			int rc = 0;
			while ((rc = in.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			data = swapStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 返回字符串的MD5加密
	 * @param str
	 * @return
	 */
	public static String getMD5String(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			//一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 网络图片下载到本地
	 * @param imgUrl    网络图片地址
	 * @param savePath  本地地址，全路径，包含文件名
	 */
	public static void downloadPicture(String imgUrl,String savePath) {
		DataInputStream dataInputStream=null;
		FileOutputStream fileOutputStream=null;
		ByteArrayOutputStream output=null;
		try {
			URL url = new URL(imgUrl);
			dataInputStream = new DataInputStream(url.openStream());
			fileOutputStream = new FileOutputStream(new File(savePath));
			output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			fileOutputStream.write(output.toByteArray());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}finally {
			try{
				output.close();
			}catch (Exception e){}
			try{
				fileOutputStream.close();
			}catch (Exception e){}
			try{
				dataInputStream.close();
			}catch (Exception e){}
		}
	}

	/**
	 * 将base64字符串转成图片
	 * @param base64Str
	 * @param imgPath
	 */
	public static void base64ToImg(String base64Str,String imgPath){
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			OutputStream out = null;
			try {
				out = new FileOutputStream(imgPath);
				// Base64解码
				byte[] b = decoder.decodeBuffer(base64Str);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				out.write(b);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.flush();
				out.close();
			}
		}catch (Exception e){}
	}

	/**
	 * 字符串转16进制
	 *
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {
		char[] chars = "0123456789abcdef".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			// sb.append(' ');
		}
		return sb.toString().trim();
	}


	/**
	 * 16进制直接转换成为字符串(无需Unicode解码)
	 *
	 * @param hexStr
	 * @return
	 */
	public static String hexStr2Str(String hexStr) {
		String str = "0123456789abcdef";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}


}
