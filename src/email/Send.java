package email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Send {
	   
	static Properties prop = null;
	
	static InputStream  in = null;
	
	static MailSenderInfo mailInfo = null;
	
	static SimpleMailSender sms = null;
	
    public Send(String filePath) {
    	
    	prop = new Properties();
    	
        in = getClass().getResourceAsStream(filePath);
    	
    	mailInfo = new MailSenderInfo();
    	
    	sms = new SimpleMailSender(); 
    	
        try {
    		
			prop.load(in);
			
			mailInfo.setMailServerHost(prop.getProperty("stmp"));    
		    mailInfo.setMailServerPort(prop.getProperty("port"));    
		    mailInfo.setValidate(true);    
		    mailInfo.setUserName(prop.getProperty("username")); 
		    mailInfo.setPassword(prop.getProperty("password"));//������������    
		    mailInfo.setFromAddress(prop.getProperty("username"));    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
    
	public static boolean email(String toAddress,String Subject , String Content){
		    
	     mailInfo.setToAddress(toAddress);    
	     mailInfo.setSubject(Subject);    
	     mailInfo.setContent(Content);    
	     
	     if(Send.prop.getProperty("type").equals("html")){
	    	 return sms.sendHtmlMail(mailInfo);
	     }else if(Send.prop.getProperty("type").equals("text")){
	    	 return sms.sendTextMail(mailInfo);
	     }else{
	    	 System.out.println("�������ʼ�����");
	     }
		 
	     return false;
	}
	
	
	
	
	public static void main(String[] args){   
        //�������Ҫ�������ʼ�   
		Send send = new Send("/email.properties");
		
		System.out.println(send.mailInfo.toString());
		
		send.email("1138494584@qq.com", "�ʼ�����", "<a href='#'>�ʼ�����</a>");
   
	}  

}
