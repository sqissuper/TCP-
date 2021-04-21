package tcp;

import java.io.*;
import java.net.Socket;

public class TCPClientError {
    //IP
    private  static final String IP = "127.0.0.1";
    //端口号
    private  static final int Port = 9005;
    public static void main(String[] args) throws IOException {
        //创建Socket 连接服务器
        Socket socket = new Socket(IP,Port);
        //字符串末尾加\n
        String msg = "hello world!\n";
        //得到发送对象
        try(OutputStream outputStream = socket.getOutputStream()) {
            //规定流的大小就为1024
            byte[] bytes = new byte[1024];
            int index = 0;
            for(Byte item : msg.getBytes()) {
                bytes[index++] = item;
            }
            for (int i = 0; i < 10; i++) {
                //发消息给服务器端
                outputStream.write(bytes);
                outputStream.flush();
            }
        }
    }
}
