package goct.query.demo.model;

import org.bytedeco.javacv.OpenCVFrameGrabber;

public class Camera {
    /**
     * 是否存储图片
     */
    public static enum Img{
        save,
        nosave
    }

    /**
     * 是否正常启动摄像头 true:正常启动/false:异常启动
     */
    public static boolean InitCamera;

    /**
     * 摄像头资源
     */
    public static OpenCVFrameGrabber grabber;

    /**
     * 是否保存图片,默认不保存
     */
    public static Img saveImage = Img.nosave;

    /**
     * 开始摄像头
     * @throws Exception 摄像头初始化失败！
     */
    public static void StartCamera() throws Exception {
        grabber.start();
    }

    /**
     * 停止摄像头
     * @throws Exception 摄像头关闭异常！
     */
    public static void StopCamera() throws Exception {
        grabber.stop();
    }

}
