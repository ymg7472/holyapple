package hack;

import java.io.BufferedReader;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.io.PrintWriter;

import java.net.Socket;



public class HackMain {



	public static void main(String[] args) {

		try {

			Socket sock = new Socket("server.devleo.tech", 8003);

			InputStream in = sock.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String line = null;

			while((line = br.readLine()) != null){

				System.out.println(line);

			}

			br.close();

			sock.close();

		} catch (Exception e) {

			System.out.println(e);

		}

	}

}


