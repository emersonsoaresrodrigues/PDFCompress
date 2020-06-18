package br.com.PDF;


import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;




import javax.swing.filechooser.FileFilter;
import javax.swing.text.MaskFormatter;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfImageObject;

import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import java.awt.Component;

import javax.swing.JFormattedTextField;




@SuppressWarnings("serial")
public class PdfCompress_Main extends JFrame {
	
   

	private static JPanel LabelConsulta;
	private JLabel label_data;
	private static JLabel label_hora;
	private JButton btnPesquisar;

	private JTextField textField;
	private JLabel lblSelecioneOArquivo;

	public static float FACTOR = 0.5f;

	private JButton btnGerarPdfReduzido;

	private String dirEntrada = "";
	private String dirSaida = "";

	static JProgressBar jProgressBar = new JProgressBar();
	private static JRadioButton rdbtnAlta;
	private static JRadioButton rdbtnNormalrecomendado;
	private JSeparator separator;
	private JLabel lblNewLabel;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblSelecioneORange;
	private JRadioButton rdbtnExemplo;
	private JRadioButton rdbtnArquivoTotal;
	private JFormattedTextField jFormattedTextPagina;
	private String page = null;

	
	public static void main(String[] args) throws IOException, DocumentException {	
		
	
	

		try {
			
			new PdfCompress_Main().setVisible(true); 
				

		    } catch (Exception e) {
		    	
			e.printStackTrace();
		}
		
		LabelConsulta.add(jProgressBar)	;
	      
		//determina o tamanho do progressbar  
		jProgressBar.setBounds(200, 220, 300, 20);
		
		//determina valor minimo  
		jProgressBar.setMinimum(0); 
		
		//determina valor maximo  
		jProgressBar.setMaximum(100);
		
		//Faz aparecer o valor em porcentagem  
		jProgressBar.setStringPainted(true);  
		
		//determina o quanto a sua progressbar esta preenchida  
		
		 jProgressBar.setValue(0);
		 
		 
		 rdbtnNormalrecomendado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
			 if(rdbtnNormalrecomendado.isSelected()){
							
				 rdbtnAlta.setSelected(false);
	                 		
	               FACTOR = 0.5f;
							
						}	
				
				}
				
			 }
			
		    );
		 
		 rdbtnAlta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
			 if(rdbtnAlta.isSelected()){
							
				 rdbtnNormalrecomendado.setSelected(false);
	                 		
	               FACTOR = 0.2f;
							
						}	
				
				}
				
			 }
			
		    );
		 
		 
		 	 
	

		ScheduledExecutorService scheduler = Executors
				.newScheduledThreadPool(1); // agendador de serviços do Java
											// para exibir as horas a cada um
											// segundo

		scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				label_hora.setText(new SimpleDateFormat("HH:mm:ss:SSS")
						.format(new Date()));
			}

		}, 1, 1, TimeUnit.MILLISECONDS);
	}

	public PdfCompress_Main() throws IOException {
		setUndecorated(false);
		setResizable(false);
		setTitle("PDF Compress [1.1.0.0]                                            Desenvolvido por Emerson Soares");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 300, 704, 478);
		LabelConsulta = new JPanel();
		LabelConsulta.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		LabelConsulta.setAlignmentX(Component.RIGHT_ALIGNMENT);
		LabelConsulta.setVerifyInputWhenFocusTarget(false);
		LabelConsulta.setToolTipText("");
		LabelConsulta.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LabelConsulta);
		LabelConsulta.setLayout(null);

		{
			label_data = new JLabel("New label");
			label_data.setFont(new Font("Tahoma", Font.BOLD, 11));
			label_data.setBounds(10, 11, 231, 14);
			LabelConsulta.add(label_data);
			

			//funcs = new Funcs();
							
			label_data.setText(new Funcs().Mostra_data());
			{
				label_hora = new JLabel("");
				label_hora.setHorizontalTextPosition(SwingConstants.CENTER);
				label_hora.setHorizontalAlignment(SwingConstants.CENTER);
				label_hora.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_hora.setBounds(555, 11, 133, 14);
				LabelConsulta.add(label_hora);
			}
			{
				btnPesquisar = new JButton("Selecionar.:");
				btnPesquisar.setBounds(561, 118, 106, 23);
				LabelConsulta.add(btnPesquisar);
				{
					textField = new JTextField();
					textField.setEditable(false);
					textField.setFont(new Font("Tahoma", Font.BOLD, 11));
					textField.setBounds(77, 119, 459, 20);
					LabelConsulta.add(textField);
					textField.setColumns(10);
				}
				{
					lblSelecioneOArquivo = new JLabel("Selecione o Arquivo PDF para compress\u00E3o.:");
					lblSelecioneOArquivo.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblSelecioneOArquivo.setBounds(174, 61, 331, 14);
					LabelConsulta.add(lblSelecioneOArquivo);
				}
				{
					btnGerarPdfReduzido = new JButton("GERAR PDF REDUZIDO");
					btnGerarPdfReduzido.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
														
							
							if(textField.getText().trim().length() == 0 ){
								
								JOptionPane.showMessageDialog(null,"Por favor selecione um arquivo!");  
								
								
							}else {
								
								jProgressBar.setValue(50);
									
								
								try {
										
									manipulatePdf(dirEntrada, dirSaida);	
									
								     } catch (IOException | DocumentException e) {
									
								   JOptionPane.showMessageDialog(null,e.getMessage()); 
									 
								   return;
								
								    }
								
								jProgressBar.setValue(100);
										
							 JOptionPane.showMessageDialog(null,"PDF GERADO COM SUCESSO EM: "+ dirSaida); 
							 
							 							 
							 textField.setText("");
							 
							}
								
						}
					}
							
						
					);
					btnGerarPdfReduzido.setBounds(264, 169, 174, 23);
					LabelConsulta.add(btnGerarPdfReduzido);
				}
				{
					rdbtnAlta = new JRadioButton("Alta (Perda de Qualidade)");
					rdbtnAlta.setFont(new Font("Tahoma", Font.BOLD, 11));
					rdbtnAlta.setBounds(491, 331, 176, 23);
					LabelConsulta.add(rdbtnAlta);
					
					
					
					
				}
				{
					rdbtnNormalrecomendado = new JRadioButton("Normal (Recomendado)");
					rdbtnNormalrecomendado.setFont(new Font("Tahoma", Font.BOLD, 11));
					rdbtnNormalrecomendado.setBounds(89, 331, 207, 23);
					rdbtnNormalrecomendado.setSelected(true);
					LabelConsulta.add(rdbtnNormalrecomendado);
					
					
					
				}
				{
					separator = new JSeparator();
					separator.setBounds(0, 298, 698, 4);
					LabelConsulta.add(separator);
				}
				{
					lblNewLabel = new JLabel("Selecione o Tipo de Compress\u00E3o:");
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblNewLabel.setBounds(261, 305, 194, 14);
					LabelConsulta.add(lblNewLabel);
				}
				{
					separator_1 = new JSeparator();
					separator_1.setBounds(0, 298, 698, 72);
					LabelConsulta.add(separator_1);
				}
				{
					separator_2 = new JSeparator();
					separator_2.setBounds(0, 366, 698, 4);
					LabelConsulta.add(separator_2);
				}
				{
					lblSelecioneORange = new JLabel("Selecione o range de p\u00E1ginas para Compress\u00E3o:");
					lblSelecioneORange.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblSelecioneORange.setBounds(224, 381, 269, 14);
					LabelConsulta.add(lblSelecioneORange);
				}
				
                {
					
					MaskFormatter mascaraPagina = null;
					
                      try {
						
                        mascaraPagina = new MaskFormatter("#####-#####");
						mascaraPagina.setPlaceholderCharacter('_');
						
					} catch (ParseException e1) {
					
						e1.printStackTrace();
					}
					
					
                    jFormattedTextPagina = new JFormattedTextField(mascaraPagina);
                    jFormattedTextPagina.setEditable(false);
                    jFormattedTextPagina.setBounds(308, 413, 81, 20);
					LabelConsulta.add(jFormattedTextPagina);
					
					
					
					
				}
				
				{
					rdbtnExemplo = new JRadioButton("Exemplo (00001-00010)");
					rdbtnExemplo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							
							 jFormattedTextPagina.setEditable(true);
							 rdbtnArquivoTotal.setSelected(false);
							
							
						}
					});
					rdbtnExemplo.setFont(new Font("Tahoma", Font.BOLD, 11));
					rdbtnExemplo.setBounds(89, 412, 176, 23);
					LabelConsulta.add(rdbtnExemplo);
				}
				
				{
					rdbtnArquivoTotal = new JRadioButton("Arquivo Total");
					rdbtnArquivoTotal.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						
							 jFormattedTextPagina.setEditable(false);
							 jFormattedTextPagina.setText("");
							 rdbtnExemplo.setSelected(false);
							
						}
					});
					rdbtnArquivoTotal.setSelected(true);
					rdbtnArquivoTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
					rdbtnArquivoTotal.setBounds(491, 412, 207, 23);
					LabelConsulta.add(rdbtnArquivoTotal);
				}
				
				
				
				 
				
			
				btnPesquisar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
						
                         // FILTRO PARA SELECIONAR APENAS pdf
			            FileFilter filter = new FileFilter() {

			               
			                public boolean accept(File f) {
			                    String arquivo = f.getName().toUpperCase();
			                    return f.isDirectory()  || arquivo.endsWith(".PDF");
			                }

			               
			                public String getDescription() {
			                    return "*.PDF";
			                }
			            };


						JFileChooser fc = new JFileChooser();
						
						fc.setFileFilter(filter);

						int option = fc.showOpenDialog(null);

						if (option == JFileChooser.APPROVE_OPTION) {

							File file = fc.getSelectedFile();
						    
							try {

								textField.setText(file.getCanonicalPath());

								dirEntrada = file.getCanonicalPath();

								dirEntrada = dirEntrada.toUpperCase();

								dirSaida = dirEntrada.replace(".PDF",
										"_REDUZIDO.PDF");

								jProgressBar.setValue(0);

								if (!dirEntrada.contains(".PDF")) {

									JOptionPane
											.showMessageDialog(null,
													"Por favor Selecione apenas arquivos PDF!");

									textField.setText("");

								}
								 
							} catch (IOException erro) {

								erro.printStackTrace();
							}

						} else {

							JOptionPane.showMessageDialog(null,
									"Nenhum arquivo selecionado!");
						}

					}
				});

			}

		}

	}

	
	
	
	/**
	 * Manipulates a PDF file src with the file dest as result
	 * @param src the original PDF
	 * @param dest the resulting PDF
	 * @throws IOException
	 * @throws DocumentException 
	 */
	public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
		
		
	    PdfName key = new PdfName("ITXT_SpecialId");
	    
	    PdfName value = new PdfName("123456789");
	    
	    // Read the file
	    PdfReader reader = new PdfReader(src);
	    
	    
	    
	    int n = reader.getXrefSize();
	    
	    PdfObject object;
	    
	    PRStream stream;
	    // Look for image and manipulate image stream
	    for (int i = 0; i < n; i++) {
	    	
	        object = reader.getPdfObject(i);
	        
	        if (object == null || !object.isStream())
	        	
	            continue;
	      	        
	        stream = (PRStream)object;
	        
	       // if (value.equals(stream.get(key))) {
	        PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
	        
	         // System.out.println(stream.type());
	        if (pdfsubtype != null && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
	        	
	            PdfImageObject image = new PdfImageObject(stream);
	            BufferedImage bi = image.getBufferedImage();
	            if (bi == null) continue;
	            int width = (int)(bi.getWidth() * FACTOR);
	            int height = (int)(bi.getHeight() * FACTOR);
	            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            AffineTransform at = AffineTransform.getScaleInstance(FACTOR, FACTOR);
	            Graphics2D g = img.createGraphics();
	            g.drawRenderedImage(bi, at);
	            ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
	           
	            ImageIO.write(img, "JPG", imgBytes);
	            stream.clear();
	            stream.setData(imgBytes.toByteArray(), false, PRStream.BEST_COMPRESSION);
	            stream.put(PdfName.TYPE, PdfName.XOBJECT);
	            stream.put(PdfName.SUBTYPE, PdfName.IMAGE);
	            stream.put(key, value);
	            stream.put(PdfName.FILTER, PdfName.DCTDECODE);
	            stream.put(PdfName.WIDTH, new PdfNumber(width));
	            stream.put(PdfName.HEIGHT, new PdfNumber(height));
	            stream.put(PdfName.BITSPERCOMPONENT, new PdfNumber(8));
	            stream.put(PdfName.COLORSPACE, PdfName.DEVICERGB);
	        }
	    }
	    
	    // Save altered PDF
	    
	    if(rdbtnExemplo.isSelected()){
	    	
	    	 page = jFormattedTextPagina.getText();     
		    	
	         reader.selectPages(page); 
	    }   
	    
	    PdfStamper stamper = new PdfStamper( reader, new FileOutputStream(dest));
		     
	    stamper.close();
	    
	    reader.close();
	    
	    }
	   
	    
	}   
	
	

	
	