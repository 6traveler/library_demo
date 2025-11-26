package socket_client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
	public static void main(String args[]) {
		doGet("www.cqupt.edu.cn", 80, "http://www.cqupt.edu.cn"); // 按照GET请求方式访问HTTPServer
	}

	/** 按照GET请求方式访问HTTPServer */
	public static void doGet(String host, int port, String uri) {
		Socket socket = null;

		try {
			socket = new Socket(host, port); 
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			/* 创建HTTP请求 */
			StringBuffer sb = new StringBuffer("GET " + uri + " HTTP/1.1\r\n");
			sb.append("Accept: */*\r\n");
			sb.append("Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,ja;q=0.6\r\n");
			sb.append("Accept-Encoding: deflate\r\n");
			sb.append("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36\r\n");
			sb.append("Host: www.cqupt.edu.cn\r\n");
			sb.append("Connection: Keep-Alive\r\n\r\n");

			/* 发送HTTP请求 */
			OutputStream socketOut = socket.getOutputStream(); // 获得输出流
			socketOut.write(sb.toString().getBytes());

			Thread.sleep(2000); // 睡眠2秒，等待响应结果

			/* 接收响应结果 */
			InputStream socketIn = socket.getInputStream(); // 获得输入流
			int size = socketIn.available();
			byte[] buffer = new byte[size];
			socketIn.read(buffer);
			System.out.println(new String(buffer, "UTF-8")); // 打印响应结果

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}