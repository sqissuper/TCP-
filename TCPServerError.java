package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerError {
    //端口号
    private static final int port = 9005;
    //内容长度
    private static final int len = 1024;

    public static void main(String[] args) throws IOException {
        //创建 TCP 服务端
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器已启动");
        //得到客户端连接
        Socket socket = serverSocket.accept();

        System.out.println(String.format("已连接，IP：%s,Port:%d",socket.getInetAddress().getHostAddress(),socket.getPort()));




        //解决方案1
//        try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
//            while(true) {
//                //按行分割读取内容
//                String msg = reader.readLine();
//                if(msg != null && !msg.equals(" ")) {
//                    System.out.println("收到消息：" + msg);
//                }
//            }
//        }

        //解决方案2
        try(InputStream inputStream = socket.getInputStream()) {
            while(true) {
                byte[] bytes = new byte[len];
                //将内容读取到数组中
                int result = inputStream.read(bytes,0,len);
                if(result > 0) {
                    String msg = new String(bytes);
                    System.out.println("收到消息：" + msg.trim());
                }
            }
        }
    }
}
