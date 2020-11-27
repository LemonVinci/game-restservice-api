package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.game.model.Contacto;


public class UI {
	
	private static final int DOT = 200, DASH = DOT * 3, FREQ = 800;
	static JTextField txtTelefono;
	static JTextField txtMail;
	static JTextField txtName;
	
	static JLabel lblTelefono;
	static JLabel lblMail;
	static JLabel lblName;
	
	static JButton bEditarContacto;
	static JButton bCrearContacto;
	static JButton buttonCrearContacto;
	static JButton buttonBorrarContacto;
	//lists 
    static JList contactos; 
    //contactList index where user steped
    static Integer index;
    
    public static List<Contacto> listContacto;
    
    static JFrame frame;
	
	public static void main (String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		buildComponentsAlta();
	}
	
	private static void buildFrameAlta() {
		Font font = new Font("SansSerif", Font.PLAIN, 15);
		if(frame!=(null)) 
			frame.dispose();
	    frame=new JFrame();

	    frame.add(bCrearContacto);
	    frame.add(bEditarContacto);
	    frame.add(buttonCrearContacto);
	    frame.add(txtTelefono);
	    frame.add(txtName);
	    frame.add(txtMail);
	    frame.add(lblTelefono);
	    frame.add(lblName);
	    frame.add(lblMail);
		
	    frame.setFont(font);
	    frame.setSize(800,650);
	    frame.setLayout(null);
	    frame.setVisible(true);
	    frame.getContentPane().setBackground(new Color(50, 50, 50));
	}
	
	private static void buildFrameModificaciones() {
		Font font = new Font("SansSerif", Font.PLAIN, 15);
		if(frame!=(null)) 
			frame.dispose();
	    frame=new JFrame();

	    frame.add(bCrearContacto);
	    frame.add(bEditarContacto);
	    frame.add(buttonCrearContacto);
	    frame.add(buttonBorrarContacto);
	    frame.add(txtTelefono);
	    frame.add(txtName);
	    frame.add(txtMail);
	    frame.add(lblTelefono);
	    frame.add(lblName);
	    frame.add(lblMail);
	    frame.add(contactos);
		
	    frame.setFont(font);
	    frame.setSize(800,650);
	    frame.setLayout(null);
	    frame.setVisible(true);
	    frame.getContentPane().setBackground(new Color(50, 50, 50));
	}

	private static List<Contacto> jsonFormatterContacto(String s) {
		List<Contacto> contactList = new ArrayList<Contacto>();
		//Test for formatter
		//String s = "[{\"id\":1,\"name\":\"nana ferrari\",\"email\":\"asd@gmail.com\",\"telefono\":\"4864378\"},{\"id\":2,\"name\":\"martin chevrolet\",\"email\":\"aassdd@gmail.com\",\"telefono\":\"4285289\"},{\"id\":14,\"name\":\"sabala\",\"email\":\"asd@gmail.com\",\"telefono\":\"41112222\"},{\"id\":15,\"name\":\"nombre\",\"email\":\"telefono\",\"telefono\":\"mail\"},{\"id\":16,\"name\":\"ejemplo1\",\"email\":\"ejemplo3\",\"telefono\":\"ejemplo2\"},{\"id\":17,\"name\":\"\",\"email\":\"\",\"telefono\":\"\"}]";
		s = s.replace(",{", "");
		s = s.replace("{", "");
		s = s.replace("[", "");
		s = s.replace("]", "");
		Contacto c;
		String[] pairs = s.split("}");
		for (int i=0;i<pairs.length;i++) {
		    String pair = pairs[i];
		    String[] pairsProps = pair.split(",");
		    c = new Contacto();
		    for (int x=0; x< pairsProps.length ;x++) {
		    	String[] keyValue = pairsProps[x].split(":");
		    	if(keyValue[0].equals("\"id\"")) {
		    		c.setId(Integer.valueOf(keyValue[1].replace("\"","")));
		    	}else if(keyValue[0].equals("\"name\"")) {
		    		c.setName(keyValue[1].replace("\"",""));
		    	}else if(keyValue[0].equals("\"email\"")) {
		    		c.setEmail(keyValue[1].replace("\"",""));
		    	}else if(keyValue[0].equals("\"telefono\"")) {
		    		c.setTelefono(keyValue[1].replace("\"",""));
		    		contactList.add(c);
		    	}
		    }
		}
		return contactList;
	}
	
	private static void buildComponentsAlta(){
		Font font = new Font("SansSerif", Font.PLAIN, 17);
		//////////TEXTBOX
		txtName = new JTextField();
		txtName.setBounds(260,180,333,40);
		txtName.setFont(font);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(260,250,333,40);
		txtTelefono.setFont(font);
		
		txtMail = new JTextField();
		txtMail.setBounds(260,320,333,40);
		txtMail.setFont(font);
		////FIN TEXTBOX
		
		//////////BOTON
		
		bCrearContacto=new JButton("Crear nuevo contacto");
		bCrearContacto.setBounds(0, 0, 290, 65);
		bCrearContacto.setBackground(new Color(130,130,255));
		bCrearContacto.setFont(font);
		bCrearContacto.setForeground(Color.WHITE);
		
		 bEditarContacto=new JButton("Editar contacto");
		 bEditarContacto.setBounds(290, 0, 290, 65);
		 bEditarContacto.setBackground(new Color(90, 90, 150));
		 bEditarContacto.setFont(font);
		 bEditarContacto.setForeground(Color.WHITE);	
			
		
		buttonCrearContacto=new JButton("Crear contacto");
		buttonCrearContacto.setBounds(330, 480, 190, 65);
		buttonCrearContacto.setBackground(new Color(130,130,255));
		buttonCrearContacto.setFont(font);
		buttonCrearContacto.setForeground(Color.WHITE);

		
		//////////LABEL
		font = new Font("Kalinga", Font.PLAIN, 17);
		lblName = new JLabel("Nombre del contacto:");
		lblName.setBounds(60,180,333,40);
		lblName.setFont(font);
		lblName.setForeground(Color.LIGHT_GRAY);
				
		lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(170,250,333,40);
		lblTelefono.setFont(font);
		lblTelefono.setForeground(Color.LIGHT_GRAY);
		
		lblMail = new JLabel("Mail:");
		lblMail.setBounds(200,320,333,40);
		lblMail.setFont(font);
		lblMail.setForeground(Color.LIGHT_GRAY);
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
					
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		bEditarContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					buildComponentsModificaciones();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		bEditarContacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                bEditarContacto.setBackground((new Color(130,130,255)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bEditarContacto.setBackground((new Color(90, 90, 150)));
            }
        });	
		//////////FIN BOTON
		
		buildFrameAlta();
	}
	
	private static void buildComponentsModificaciones() throws ClientProtocolException, IOException{
		Font font = new Font("SansSerif", Font.ITALIC, 17);
		buildContactList();
		
		contactos = new JList(buildNameList().toArray());
		contactos.setBounds(35,100,270,350);
		contactos.setFont(font);
		contactos.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 1) {
		            index = list.locationToIndex(evt.getPoint());
		            txtName.setText(listContacto.get(index).getName());
		            txtMail.setText(listContacto.get(index).getEmail());
		            txtTelefono.setText(listContacto.get(index).getTelefono());
		        }
		    }
		});
		
		font = new Font("SansSerif", Font.PLAIN, 17);
		//contactos.addListSelectionListener(s); 
		//////////TEXTBOX
		txtName = new JTextField();
		txtName.setBounds(360,180,300,40);
		txtName.setFont(font);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(360,280,300,40);
		txtTelefono.setFont(font);
		
		txtMail = new JTextField();
		txtMail.setBounds(360,380,300,40);
		txtMail.setFont(font);
		////FIN TEXTBOX
		
		//////////BOTON
		bCrearContacto=new JButton("Crear nuevo contacto");
		bCrearContacto.setBounds(0, 0, 290, 65);
		bCrearContacto.setBackground(new Color(90, 90, 150));
		bCrearContacto.setFont(font);
		bCrearContacto.setForeground(Color.WHITE);
		
		 bEditarContacto=new JButton("Editar contacto");
		 bEditarContacto.setBounds(290, 0, 290, 65);
		 bEditarContacto.setBackground(new Color(130,130,255));
		 bEditarContacto.setFont(font);
		 bEditarContacto.setForeground(Color.WHITE);	
			
		
		buttonCrearContacto=new JButton("Modificar contacto");
		buttonCrearContacto.setBounds(450, 480, 210, 65);
		buttonCrearContacto.setBackground(new Color(130,130,255));
		buttonCrearContacto.setFont(font);
		buttonCrearContacto.setForeground(Color.WHITE);
		
		buttonBorrarContacto=new JButton("Borrar contacto");
		buttonBorrarContacto.setBounds(130, 480, 175, 65);
		buttonBorrarContacto.setBackground(new Color(130,130,255));
		buttonBorrarContacto.setFont(font);
		buttonBorrarContacto.setForeground(Color.WHITE);

		//////////LABEL
		font = new Font("Kalinga", Font.PLAIN, 17);
		lblName = new JLabel("Nombre del contacto:");
		lblName.setBounds(360,130,333,40);
		lblName.setFont(font);
		lblName.setForeground(Color.LIGHT_GRAY);
		
		lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(360,230,333,40);
		lblTelefono.setFont(font);
		lblTelefono.setForeground(Color.LIGHT_GRAY);
				
		lblMail = new JLabel("Mail:");
		lblMail.setBounds(360,330,333,40);
		lblMail.setFont(font);
		lblMail.setForeground(Color.LIGHT_GRAY);
		////FIN LABEL
		
		buttonCrearContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Contacto contacto = new Contacto();
				
				List<Contacto> contactoList = new ArrayList<Contacto>();
				contactoList.add(contacto);


				HttpClient httpclient = HttpClients.createDefault();
				HttpPut httpput = new HttpPut("http://localhost:8080/update");
				httpput.setHeader("Accept", "*/*");
				httpput.setHeader("Content-type", "application/json");
				try {
					//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));			
					httpput.setEntity(new StringEntity("{\r\n" + 
							"\"id\": \"" + listContacto.get(index).getId() + "\",\r\n" + 
							"\"name\": \"" + txtName.getText()+ "\",\r\n" + 
							"\"email\": \"" + txtMail.getText().toString()+ "\",\r\n" + 
							"\"telefono\":\""+ txtTelefono.getText().toString() + "\"\r\n" + 
							"}", Charset.forName("UTF-8")));

					HttpResponse response = httpclient.execute(httpput);
					//refresh de la lista
					buildContactList();
					buildFrameModificaciones();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		buttonBorrarContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(index != null) {
				
					HttpClient httpclient = HttpClients.createDefault();
					HttpDelete httpDelete = new HttpDelete("http://localhost:8080/delete/" + String.valueOf(listContacto.get(index).getId()));
					httpDelete.setHeader("Accept", "*/*");
					httpDelete.setHeader("Content-type", "application/json");
					try {
						//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));			
						/*httpput.setEntity(new StringEntity("{\r\n" + 
								"\"id\": \"" + listContacto.get(index).getId() + "\",\r\n" + 
								"\"name\": \"" + txtName.getText()+ "\",\r\n" + 
								"\"email\": \"" + txtMail.getText().toString()+ "\",\r\n" + 
								"\"telefono\":\""+ txtTelefono.getText().toString() + "\"\r\n" + 
								"}", Charset.forName("UTF-8")));*/

						HttpResponse response = httpclient.execute(httpDelete);
					}catch(Exception e) {
						e.printStackTrace();					
					}
				}
			}
		});
		bCrearContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buildComponentsAlta();
			}
		});
		
		bCrearContacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                bCrearContacto.setBackground((new Color(130,130,255)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bCrearContacto.setBackground((new Color(90, 90, 150)));
            }
        });	
		//////////FIN BOTON
		
		buildFrameModificaciones();
	}
	
	private static void buildContactList() throws ClientProtocolException, IOException {
		
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://localhost:8080/Contacto");
		  // Get HttpResponse Status
		HttpResponse response = httpClient.execute(httpget);
        System.out.println(response.getStatusLine().toString());

        HttpEntity entity = response.getEntity();
        Header headers = entity.getContentType();
        System.out.println(headers);
        try {
	        if (entity != null) {
	        	 String responseString = EntityUtils.toString(entity);
	        	 System.out.println(responseString);
	        	 listContacto = jsonFormatterContacto(responseString);
	        }
        }catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private static List<String> buildNameList() {
		List<String> contactNames = new ArrayList<String>();
		for(Contacto contacto : listContacto){
			contactNames.add(contacto.getName());
		}
		return contactNames;
	}
}
