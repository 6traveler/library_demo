package http_client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPClient {
	public static void main(String[] args) {
		try {
			new HTTPClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HTTPClient() {
		try {
			URL url = new URL("http://www.cqupt.edu.cn");

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			// 开始读取远程服务器的响应数据。
			BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());

			byte[] buffer = new byte[1024 * 10];
			int count = 0;
			while (true) {
				count = bis.read(buffer);
				if (count == -1) {
					break;
				}

				System.out.println(new String(buffer, 0, count, "UTF-8"));
			}

			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}