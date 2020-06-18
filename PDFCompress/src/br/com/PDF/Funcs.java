package br.com.PDF;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JOptionPane;


public class Funcs {
 

	public String Mostra_data() {
		
		String dataAtual;

		Date hoje = new Date();
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		
		dataAtual = df.format(hoje);
		
		return dataAtual;

	}

	
	public static String formatDate(Date data) {
		return formatDate(data, "dd/MM/yyyy");
	}

	public static String formatDatesql(Date data) {
		return formatDate(data, "yyyyMMdd");
	}

	public static String formatDate(Date data, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(data);
	}
    
    public static String retornaDirApp() throws IOException {  //retorna dir do projeto java

        return new File(".").getCanonicalPath();
    }

	public static Date strToDate(String data, String formato) {

		Date resultado = null;
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		try {
			resultado = sdf.parse(data);

		} catch (ParseException ex) {

			System.err
					.println("Exceção: "
							+ ex.toString()
							+ " lançada na classe: "
							+ Funcs.class.getName()
							+ " método: "
							+ Thread.currentThread().getStackTrace()[1]
									.getMethodName());
			// logger.error("Exceção: " + ex.toString() + " lançada na classe: "
			// + Funcs.class.getName() + " método: " +
			// Thread.currentThread().getStackTrace()[1].getMethodName());

		}
		return resultado;
	}
 

    public static boolean UsuarioValida(Connection conn,
            String Login,
            String Password) {

        boolean valido = false;

        CallableStatement callableStatement = null;

        String sp_validar = "{call sp_UsuarioValida("
                + "?,?,?,?,?,?,?)}";


        try {

            callableStatement = conn.prepareCall(sp_validar);

            callableStatement.setString("Login", Login); //@FilePath_Arquivo_Retomada as VARCHAR(255),
            callableStatement.setString("Password", Password); ////@FilePath_Arquivo_Spool as VARCHAR(255),
            callableStatement.registerOutParameter("UsuarioLogado", java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter("IdPerfilLogado", java.sql.Types.INTEGER);
            callableStatement.registerOutParameter("NivelAcesso", java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter("PerfilUsuario", java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter("Retorno", java.sql.Types.INTEGER);
                       
            
            callableStatement.execute();


            if (callableStatement.getInt("retorno") == 0) {

                System.out.println("usuario invalido");
                
                valido = false;
                
                 JOptionPane.showMessageDialog(null,"Usuário ou Senha Inválidos !","ATENÇÃO",JOptionPane.INFORMATION_MESSAGE); 
     

            } else {

                System.out.println("usuario valido "+ callableStatement.getString("UsuarioLogado"));
                valido = true;
                   
                
            }

        } catch (Exception ex) {

            System.err.println("Exceção: " + ex.toString());

        } finally {

            try {
                callableStatement.close();


            } catch (SQLException ex) {

                System.err.println("Exceção: " + ex.toString());

            }

        }
        
        return valido;


    }

    


    

 
}