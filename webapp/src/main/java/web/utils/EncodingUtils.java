package web.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class EncodingUtils {

    public static void main(String[] args) {
        String ImgStr = "";
        String saveURL = "\\D:\\JAVA";
        GenerateImage(ImgStr,saveURL);
    }

    public static String GetBaseCode(String base64) {
        return base64.substring(base64.indexOf(",") + 1);
    }

    public static String GetImgType(String base64) {
        return  base64.substring(base64.indexOf("/")+1,base64.indexOf(";"));
    }

    //图片转化成base64字符串
    public static String GetImageStr()
    {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "D:\\tupian\\a.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static String GenerateImage(String imgStr,String saveURL)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
        {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            String base64 = GetBaseCode(imgStr);
            byte[] b = decoder.decodeBuffer(base64);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String fileName = UuidUtil.getUuidFileName("."+GetImgType(imgStr));
            String URL = saveURL + "\\" + fileName;
            OutputStream out = new FileOutputStream(URL);
            out.write(b);
            out.flush();
            out.close();
            // 返回图片名称
            return fileName;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

