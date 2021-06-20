<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Camera Example</title>
    <script type="text/javascript">
        // 扩展API加载完毕后调用onPlusReady回调函数
        document.addEventListener( "plusready", onPlusReady, false );
        var r = null;
        // 扩展API加载完毕，现在可以正常调用扩展API
        function onPlusReady() {
            // 获取设备默认的摄像头对象
            var cmr = plus.camera.getCamera();
            // ......
        }
    </script>
    <script>
        document.addEventListener("deviceready", onDeviceReady, false);
        function onDeviceReady() {
            pictureSource = navigator.camera.PictureSourceType;
            destinationType = navigator.camera.DestinationType; }
    </script>
</head>
<body>
</body>
</html>