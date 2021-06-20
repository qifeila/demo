package goct.query.demo.service;




import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;


import java.awt.image.BufferedImage;
import java.util.Hashtable;

@Service
public class QrcodeService {
    public String QrParse(BufferedImage bImage) {
        // TODO Auto-generated method stub
        MultiFormatReader reader = new MultiFormatReader();
        BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bImage)));
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
//设置编码格式
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        try {
            Result result = reader.decode(binaryBitmap,hints);

            //System.out.println("二维码文本内容:"+result.getText());
            return result.getText();
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            //图片中不包含二维码 do not
            //e.printStackTrace();
            return null;
        }
    }

}
