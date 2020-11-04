package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.keycloak.util.JsonSerialization;

import com.game.controller.GameController;
import com.game.model.Contacto;
import com.game.service.ContactoService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javassist.expr.Instanceof;


public class UIEditarContacto {
	
	private static final int DOT = 200, DASH = DOT * 3, FREQ = 800;
	static JTextField txtTelefono;
	static JTextField txtMail;
	static JTextField txtName;
	
	static JLabel lblTelefono;
	static JLabel lblMail;
	static JLabel lblName;
	
	 //lists 
    static JList contactos; 
	
	public static void main (String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Font font = new Font("SansSerif", Font.PLAIN, 22);
		String line = "";
		String buffer = null; 	
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httppost = new HttpGet("http://localhost:8080/Contacto");
		StringBuilder stringBuilder = new StringBuilder();
		JsonParser jsonParser = new JsonParser();
		String month[]= { "nana ferrari asd@gmail.com 4864378", 
				"martin chevrolet aassdd@gmail.com 4285289",
				"sabala asd@gmail.com 41112222"}; 
		
		//httppost.setHeader("Accept", "*/*");
		//httppost.setHeader("Content-type", "application/json");
		
			//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));			
			/*httppost.setEntity(new StringEntity("{\r\n" + 
					"\"name\": \"" + txtName.getText()+ "\",\r\n" + 
					"\"email\": \"" + txtMail.getText().toString()+ "\",\r\n" + 
					"\"telefono\":\""+ txtTelefono.getText().toString() + "\"\r\n" + 
					"}", Charset.forName("UTF-8")));
*/
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			//new InputStreamReader(response.getEntity().getContent());
			
			
				
			
		contactos = new JList(month);
		contactos.setBounds(10,100,400,400);
		contactos.setFont(font);
		font = new Font("SansSerif", Font.PLAIN, 30);
		//contactos.addListSelectionListener(s); 
		//////////TEXTBOX
		txtName = new JTextField();
		txtName.setBounds(800,150,333,40);
		txtName.setFont(font);
		
		txtMail = new JTextField();
		txtMail.setBounds(800,200,333,40);
		txtMail.setFont(font);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(800,250,333,40);
		txtTelefono.setFont(font);
		////FIN TEXTBOX
		
		//////////BOTON
		JButton bEditarContacto=new JButton("Editar contacto");
		bEditarContacto.setBounds(0, 0, 280, 80);
		bEditarContacto.setBackground(new Color(40,130,255));
		bEditarContacto.setFont(font);
		bEditarContacto.setForeground(Color.WHITE);
		//buttonCrearContacto.setFont(new Font());
		JButton bCrearContacto=new JButton("Crear nuevo contacto");
		bCrearContacto.setBounds(280, 0, 280, 80);
		bCrearContacto.setBackground(new Color(40,130,255));
		bCrearContacto.setFont(font);
		bCrearContacto.setForeground(Color.WHITE);
		//buttonCrearContacto.setFont(new Font());
		
		JButton buttonCrearContacto=new JButton("Crear contacto");
		buttonCrearContacto.setBounds(330, 500, 280, 80);
		buttonCrearContacto.setBackground(new Color(40,130,255));
		buttonCrearContacto.setFont(font);
		buttonCrearContacto.setForeground(Color.WHITE);
		//buttonCrearContacto.setFont(new Font());
        // these next two lines do the magic..
		//buttonCrearContacto.setContentAreaFilled(false);
		//buttonCrearContacto.setOpaque(true);
        
		
		//////////LABEL
		font = new Font("SansSerif", Font.PLAIN, 25);
		lblMail = new JLabel("Mail:");
		lblMail.setBounds(500,250,333,40);
		lblMail.setFont(font);
		lblMail.setForeground(Color.WHITE);
		
		lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(500,200,333,40);
		lblTelefono.setFont(font);
		lblTelefono.setForeground(Color.WHITE);
		
		lblName = new JLabel("Nombre del contacto:");
		lblName.setBounds(500,150,333,40);
		lblName.setFont(font);
		lblName.setForeground(Color.WHITE);
		////FIN LABEL
		buttonCrearContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Contacto contacto = new Contacto();
				
				List<Contacto> contactoList = new ArrayList<Contacto>();
				contactoList.add(contacto);


				HttpClient httpclient = HttpClients.createDefault();
				HttpPost httppost = new HttpPost("http://localhost:8080/addContacto");
				httppost.setHeader("Accept", "*/*");
				httppost.setHeader("Content-type", "application/json");
				try {
					//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));			
					httppost.setEntity(new StringEntity("{\r\n" + 
							"\"name\": \"" + txtName.getText()+ "\",\r\n" + 
							"\"email\": \"" + txtMail.getText().toString()+ "\",\r\n" + 
							"\"telefono\":\""+ txtTelefono.getText().toString() + "\"\r\n" + 
							"}", Charset.forName("UTF-8")));

					HttpResponse response = httpclient.execute(httppost);
					//HttpEntity entity = response.getEntity();

					/*if (entity != null) {
					    try (InputStream instream = entity.getContent()) {
					        // do something useful
					    }
					}*/
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		//////////FIN BOTON
		
		buildFrame(buttonCrearContacto,bCrearContacto,bEditarContacto,font);
	}
	
	private static void buildFrame(JButton button,JButton button2,JButton button3,Font font) {
		JFrame f=new JFrame();
		
		
		f.add(button);
		f.add(button2);
		f.add(button3);
		f.add(txtTelefono);
		f.add(txtName);
		f.add(txtMail);
		f.add(lblTelefono);
		f.add(lblName);
		f.add(lblMail);
		f.add(contactos);
		
		f.setFont(font);
		f.setSize(1200,700);
		f.setLayout(null);
		f.setVisible(true);
		f.getContentPane().setBackground(Color.BLACK);
	}
	/*bModel.addChangeListener(new ChangeListener(){
	@Override
	public void stateChanged(ChangeEvent arg0) {
	
		if(bModel.isPressed()) {
		
		}
	}
});
*/
}
