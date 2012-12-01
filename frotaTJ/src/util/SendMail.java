package util;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import dao.Usuario;;

public class SendMail
{
	static private SendMail instance;       // A unica instancia
    static private int numclientes;
    private int mailErro=0;
    private final String Origem = "sistematjsc@gmail.com";
	private final String sMailHost = "smtp.gmail.com";
	private final String sUser = "sistematjsc@gmail.com";
	private final String sPasswd = "s1i2s3t4e5m6a7";
	private final String porta = "465";

    /**
     * Retorna uma unica instancia, criando uma se é a primeira vez
     * que o metodo é chamado.
     *
     * @return SendMail Retorna uma única instância do servidor.
     */
    static synchronized public SendMail getInstance()
	{
        if (instance == null)
		{
            instance = new SendMail();
        }
        
		numclientes++;
		return instance;
    }


	public boolean EnviarEmail(String sEmailDestinatario, String sAssunto, String sMsg)
	{

		boolean bEnviado = false;
		
		try
		{
			Session session = null;
			Properties props = new Properties();
				/*
			props.put("mail.smtp.host", sMailHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465"); 
				
				   	String mailport = "465";
				
				session = Session.getInstance(props, null);
			Authenticator auth = new SMTPAuthenticator(sUser, sPasswd);
			session = Session.getInstance(props, auth);
			*/
				
				
				props.put("mail.smtp.host", sMailHost);
				props.put("mail.smtp.auth", "true");
//				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.port", porta);
//	    		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//	    		props.put("mail.smtp.socketFactory.fallback", "false");
				Authenticator aut = new SmtpAuthenticator(sUser, sPasswd);				
				session = Session.getInstance(props, aut);
					    
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(Origem));
				message.setRecipients(Message.RecipientType.BCC, new InternetAddress[] { new InternetAddress(sEmailDestinatario) });

				message.setSubject(sAssunto);
				message.setContent(sMsg, "text/html");
				message.setSentDate(new java.util.Date());

				session.setDebug(true);

				Transport.send(message);
				
				bEnviado = true;
				
				
			
		}
		catch ( Exception e )
		{
			System.out.println("SendMail.EnviarEmail:"+e);
			return false;
		}
		finally
		{
			try
			{
				return bEnviado;
			}
			catch ( Exception e)
			{
				System.out.println("SendMail.EnviarEmail:"+e+" | sEmailDestinatario - "+sEmailDestinatario+
						" | Origem - "+Origem+
						" | sMailHost - "+sMailHost+
						" | sUser - "+sUser+
						" | sPasswd - "+sPasswd
						);
				return false;
			}
		}
	}



	

	public boolean EnviarEmailAnexoRelatorio(Set<Usuario> oDestinatarios, Set<Usuario> oDestinatariosEncaminhar, Set<Usuario> oResponsaveis, List<String> anexos, String sAssunto, String sMsg)
	{
		boolean bEnviado = false;
		try
		{
			Properties props = new Properties();
			/*
			props.put("mail.transport.protocol", "smtp"); 
			props.put("mail.smtp.host", sMailHost); 
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			Authenticator aut = new SMTPAuthenticator(sUser, sPasswd);
			*/
			
			props.put("mail.smtp.host", sMailHost);
			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.port", porta);
//    		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//    		props.put("mail.smtp.socketFactory.fallback", "false");
			Authenticator aut = new SmtpAuthenticator(sUser, sPasswd);
			Session session = Session.getInstance(props, aut);
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Origem));
			message.setSentDate(new java.util.Date());
			message.setSubject(sAssunto);

		    // cria a Multipart
		    Multipart mp = new MimeMultipart();
			
			// cria a primeira parte da mensagem
		    MimeBodyPart mbp1 = new MimeBodyPart();
		    mbp1.setText(sMsg);
		    mp.addBodyPart(mbp1);
		      
		    for(String anexo:anexos){
			    // cria a segunda parte da mensage
		    	FileDataSource fds = new FileDataSource(anexo);
		    	if(fds.getFile().exists()){
			    MimeBodyPart mbp2 = new MimeBodyPart();
			    // anexa o arquivo na mensagem
			    	mbp2.setDataHandler(new DataHandler(fds));
				    mbp2.setFileName(fds.getName());
				    mp.addBodyPart(mbp2);
			    }
		    }
				
			message.setContent(mp);
			int tamanhoVetorDestinatarios = 0;
			if(oDestinatarios != null){
				tamanhoVetorDestinatarios = oDestinatarios.size();
			}
			InternetAddress[] destinatarios = new InternetAddress[tamanhoVetorDestinatarios];
			if(oDestinatarios != null){
			int i = 0;
			    for ( Usuario usuario:oDestinatarios)
				{
			    	destinatarios[i] = new InternetAddress(usuario.getEmail()); 
			    	i++;
				}
			}

			int z = 0;
			
			int tamanhoVetor = 0;
			
			if(oDestinatariosEncaminhar != null){
				tamanhoVetor+=oDestinatariosEncaminhar.size();
			}
			if(oResponsaveis != null){
				tamanhoVetor+=oResponsaveis.size();
			}
			InternetAddress[] destinatariosEncaminhar = new InternetAddress[tamanhoVetor];
			if(oDestinatariosEncaminhar != null){
		    
		    int x = 0;
			    for ( Usuario usuario:oDestinatariosEncaminhar)
			    {
			    	destinatariosEncaminhar[x] = new InternetAddress(usuario.getEmail()); 
			    	x++;
			    }
			    z = x;
		    }
		    if(oResponsaveis != null){
			    for ( Usuario usuario:oResponsaveis)
			    {
			    	destinatariosEncaminhar[z] = new InternetAddress(usuario.getEmail()); 
			    	z++;
			    }
		    }
			message.setRecipients(Message.RecipientType.TO, destinatarios);
			message.setRecipients(Message.RecipientType.CC, destinatariosEncaminhar);
			session.setDebug(true);
			Transport.send(message);
			
			bEnviado = true;
		}
		catch ( Exception e )
		{
			System.out.println("SendMail.EnviarEmailAnexoRelatorio:"+e+
					" | sMailHost - "+sMailHost+
					" | sUser - "+sUser+
					" | sPasswd - "+sPasswd
			);
			return false;
		}
		finally
		{
			try
			{
				return bEnviado;
			}
			catch ( Exception e)
			{
				System.out.println("SendMail.EnviarEmail:"+e);
				return false;
			}
		}
	}

	
	
	

	
}