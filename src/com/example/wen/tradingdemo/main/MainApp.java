package com.example.wen.tradingdemo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import com.example.wen.tradingdemo.authenticationconfig.ServerConfig;
import com.example.wen.tradingdemo.authenticationconfig.UserConfig;

import Decoder.BASE64Encoder;

public class MainApp {

	public static void main(String[] args) {
		ServerConfig server = new ServerConfig("http://tradingserver.zulutrade.com/");
		UserConfig user = new UserConfig("wenzhao12122", "cnnfnako");
		String command = "getInstruments";
		HttpClient client = HttpClientBuilder.create().build();
		System.out.println(server.getServerUrl()+command);
		HttpGet request = new HttpGet(server.getServerUrl()+command);
		String encoding = new BASE64Encoder().encode((user.getUserName()+":"+user.getPassword()).getBytes());
		request.setHeader(new BasicHeader("Authorization", "Basic " + encoding));
		request.setHeader(new BasicHeader("Content-Type", "text/event-stream; charset=UTF-8"));
		try {
			HttpResponse response = client.execute(request);
			System.out.println("Response code: "+response.getStatusLine().getStatusCode());
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while((line = reader.readLine())!= null){
				result.append(line);
			}
			System.out.println("Response: "+result);
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
