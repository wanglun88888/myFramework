package Util.convert;

import java.io.ByteArrayInputStream;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
  
import org.apache.batik.transcoder.TranscoderException;  
import org.apache.batik.transcoder.TranscoderInput;  
import org.apache.batik.transcoder.TranscoderOutput;  
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;  
  
/** 
 *@Description: 将svg转换为png格式的图片 
 */  
public class SvgPngConverter {  
   
    /** 
     *@Description: 将svg字符串转换为png 
     *@Author: 
     *@param svgCode svg代码 
     *@param pngFilePath  保存的路径 
     *@throws IOException io异常 
     *@throws TranscoderException svg代码异常 
    */  
    public static void convertToPng(String svgCode,String pngFilePath) throws IOException,TranscoderException{  
  
        File file = new File (pngFilePath);  
  
        FileOutputStream outputStream = null;  
        try {  
            file.createNewFile ();  
            outputStream = new FileOutputStream (file);  
            convertToPng (svgCode, outputStream);  
        } finally {  
            if (outputStream != null) {  
                try {  
                    outputStream.close ();  
                } catch (IOException e) {  
                    e.printStackTrace ();  
                }  
            }  
        }  
    }  
       
    /** 
     *@Description: 将svgCode转换成png文件，直接输出到流中 
     *@param svgCode svg代码 
     *@param outputStream 输出流 
     *@throws TranscoderException 异常 
     *@throws IOException io异常 
     */  
    public static void convertToPng(String svgCode,OutputStream outputStream) throws TranscoderException,IOException{  
        try {  
            byte[] bytes = svgCode.getBytes ("UTF-8");  
            TranscoderInput input = new TranscoderInput (new ByteArrayInputStream (bytes));  
            TranscoderOutput output = new TranscoderOutput (outputStream);
//            PNGTranscoder t = new PNGTranscoder ();  
//            t.transcode (input, output);            
            JPEGTranscoder transcoder = new JPEGTranscoder();
            transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1));
            transcoder.transcode(input, output);
            outputStream.flush ();  
        } 
        finally {  
            if (outputStream != null) {  
                try {  
                    outputStream.close ();  
                } catch (IOException e) {  
                    e.printStackTrace ();  
                }  
            }  
        }  
    }  
}  