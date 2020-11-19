package net;


import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HttpRequest;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;//����awt���еļ������¼���
import java.awt.event.ActionEvent;//����awt���е�ActionEvent�¼���


class MyItemListener implements ItemListener
{
	private ImageIcon checked=new ImageIcon(MainFrame.class.getResource("/image/checked.png"));
	private ImageIcon unchecked=new ImageIcon(MainFrame.class.getResource("/image/unchecked.png"));
	public void itemStateChanged(ItemEvent e)
	{
		JCheckBox jcb = (JCheckBox)e.getItem() ;
		{
			if(jcb.isSelected())
			{
				jcb.setIcon(checked) ;
			}else jcb.setIcon(unchecked) ;
		}
	}
};

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1450498460078768368L;
	MainFrame(){
		try {
			init();
			this.validate(); 	//�ػ�����
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	private ImageIcon iconLogin=new ImageIcon(MainFrame.class.getResource("/image/login.png"));
	private ImageIcon iconLogout=new ImageIcon(MainFrame.class.getResource("/image/logout.png"));
	private ImageIcon iconReset=new ImageIcon(MainFrame.class.getResource("/image/reset.png"));
	private ImageIcon checked=new ImageIcon(MainFrame.class.getResource("/image/checked.png"));
	private ImageIcon unchecked=new ImageIcon(MainFrame.class.getResource("/image/unchecked.png"));
	JLabel UserName = new JLabel("�˺�:"); 
	JLabel UserPassword = new JLabel("����:"); 
	JLabel type=new JLabel("��������:");
	JTextField userField=new JTextField(10);
	JPasswordField passwordField=new JPasswordField(10);
	final Choice choice = new Choice(); 

	 JButton B_submit=new JButton("��¼");
	 JButton B_reset=new JButton("����");
	 JButton B_logout=new JButton("ע��");
	 JCheckBox RB=new JCheckBox("��ס����");
	 JCheckBox Auto=new JCheckBox("�Զ���¼");
	 JCheckBox AutoClose=new JCheckBox("�Զ��ر�");
	 boolean flag=false;
	 boolean flagAuto=false;
	 boolean flagAutoClose=false;


	public void init() throws IOException {

		SetFont.InitGlobalFont(new Font("΢���ź�", Font.PLAIN, 23));
		setLayout(null);
		LinkedHashSet<String> ipSet=NetUtil.localIpv4s();
    	String IPaddr="";
    	for (String ip : ipSet) {
            if(ip.indexOf("172") == 0) {
            	IPaddr = ip;
            }
        }
    	if(IPaddr.equals(""))
    		IPaddr="��ȡip��ַʧ�ܽ��޷���½";
		setTitle("By:Olvi73  �汾:v2.2  ��ǰIP:"+IPaddr);
		
		setLayout(new FlowLayout());
		setResizable(false);
		this.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-280, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-150,593,180);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		B_submit.setIcon(iconLogin);
		B_logout.setIcon(iconLogout);
		B_reset.setIcon(iconReset);
		
		 choice.add("У԰��"); 
		 choice.add("�й��ƶ�"); 
		 choice.add("�й���ͨ"); 
		 choice.add("�й�����"); 
		 choice.setFont(new Font("΢���ź�", Font.PLAIN, 22));
		 
		 passwordField.setEchoChar('*');
		
		 UserName.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 UserPassword.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 type.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 userField.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 passwordField.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 B_submit.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 B_reset.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 B_logout.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 RB.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 Auto.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 AutoClose.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 RB.setIcon(unchecked);
		 Auto.setIcon(unchecked);
		 AutoClose.setIcon(unchecked);
		 
		 Container ct= getContentPane();
		 ct.add(UserName);
		 ct.add(userField);
		 ct.add(UserPassword);
		 ct.add(passwordField);
		 ct.add(type);
		 ct.add(choice);
		 ct.add(RB);
		 ct.add(Auto);
		 ct.add(AutoClose);

		 RB.addItemListener(new MyItemListener()) ;
		 Auto.addItemListener(new MyItemListener()) ;
		 AutoClose.addItemListener(new MyItemListener()) ;
		 B_submit.setPreferredSize(new Dimension(130, 45));
		 B_logout.setPreferredSize(new Dimension(130, 45));
		 ct.add(B_logout);
		 ct.add(B_submit);

		JButton Bt_gap=new JButton(" ");
		Bt_gap.setContentAreaFilled(false);
		Bt_gap.setBorderPainted(false);
		Bt_gap.setEnabled(false);

		ct.add(Bt_gap);
		ct.add(B_reset);
		 
		 ImageIcon icon=new ImageIcon(MainFrame.class.getResource("/image/icon.png"));
		 this.setIconImage(icon.getImage());
		 addPassword();
		if(Auto.isSelected())
		{
			Login();
		}
		 
	B_reset.addActionListener(new ActionListener() {//�����ð�ť��Ӽ����¼�
				@Override
				public void actionPerformed(ActionEvent arg0) {
					userField.setText("");//���û����ı����������
					passwordField.setText("");//�������ı����������
					choice.select(0);
					RB.setSelected(false);
					Auto.setSelected(false);
					AutoClose.setSelected(false);
					try {
	    				canclePwd();
	    			} catch (IOException e) {
	    				// TODO �Զ����ɵ� catch ��
	    				e.printStackTrace();
	    			}
				}
				
			});
	
	 B_submit.addActionListener(new ActionListener() {//��ȷ����ť��Ӽ����¼�
		@Override
		public void actionPerformed(ActionEvent arg0) {
		String pw=new String(passwordField.getPassword());
		if(userField.getText().equals("")||pw.equals(""))
		JOptionPane.showMessageDialog(null,"������������Ϣ��","����",JOptionPane.WARNING_MESSAGE);
		else
		Login();
		}
	});
	 B_logout.addActionListener(new ActionListener() {//��ȷ����ť��Ӽ����¼�
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
	        String c= "Portal";
	        String a2= "logout";
	        String wlan_user_ip= "";
	        
	        String login_method= "1";
	        String user_account="drcom";
	        String user_password="123";
	        
	        LinkedHashSet<String> ipSet=NetUtil.localIpv4s();
	    	for (String ip : ipSet) {
	            if(ip.indexOf("172") == 0) {
	            	wlan_user_ip = ip;
	            	System.out.println("����ipΪ��"+ip);
	            }
	        }
	        
	        System.out.println(user_account);
	        System.out.println(user_password);
	       
	        String body = HttpRequest.get("http://172.21.255.105:801/eportal/")
	                .form("c", c)
	                .form("a", a2)
	                .form("wlan_user_ip",wlan_user_ip)
	                .form("login_method", login_method)
	                .form("user_account", user_account)
	                .form("user_password", user_password)
	                .form("ac_logout",0)
	           	    .execute()
	                .body();
	        System.out.println(body);
	        
	        String result="({\"result\":\"1\",\"msg\":\"\\u6ce8\\u9500\\u6210\\u529f\"})";
	        
	        if(body.equals(result))
	        
				 JOptionPane.showMessageDialog(null,"ע���ɹ�","�ɹ�",JOptionPane.INFORMATION_MESSAGE);	
	        
				 else
				 {
					 JOptionPane.showMessageDialog(null, "ע��ʧ��\n"+"������Ϣ:"+body.substring(2,body.length()-2).replace("\"",""),"ʧ��",JOptionPane.ERROR_MESSAGE );
					 try {
							FileWriter fw = new FileWriter("error.txt",true);
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
							fw.write(df.format(new Date())+"      ע��������Ϣ:  "+body.substring(2,body.length()-2).replace("\"",""));
							fw.write("\r\n");
							fw.close();
						} catch (IOException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
				 }
			}
		});
	 RB.addActionListener(new ActionListener() {//��ȷ����ť��Ӽ����¼�
			@Override
			public void actionPerformed(ActionEvent arg0) {
			 if(RB.isSelected())
			 {
				 Auto.setEnabled(true);
				 AutoClose.setEnabled(true);
			 }
			 else
			 {
				 Auto.setEnabled(false);
				 Auto.setSelected(false);
				 AutoClose.setEnabled(false);
				 AutoClose.setSelected(false);
				 
			 }
			}
		});

}
	private void canclePwd() throws IOException{
		
		FileWriter fw = new FileWriter("users.txt");
		fw.close();
	}
	
	private void remPwd(String Name, String Password,String Type,String auto,String AutoClose) throws IOException {
		
		FileWriter fw = new FileWriter("users.txt");
		fw.write(Name);
		fw.write(" ");
		fw.write(Type);
		fw.write(" ");
		fw.write(auto);
		fw.write(" ");
		fw.write(AutoClose);
		fw.write(" ");
		String EnCode=new String(DesUtil.getInstance("Default").getEnCodeString(Password));
		fw.write(EnCode);
		fw.close();
	}
	private void addPassword() throws IOException {
		File f=new File("users.txt");
		if(!f.exists())
		{
			try {
				f.createNewFile();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		BufferedReader buf = new BufferedReader(new FileReader("users.txt"));
		String line = buf.readLine();
		if (line != null) {
			flag=true;
			String[] users=line.split(" ");
			userField.setText(users[0]);
			String Decode=new String(DesUtil.getInstance("Default").getDecodeString(users[4]));
			passwordField.setText(Decode);
			String Ck=new String(users[2]);
			String Close=new String(users[3]);
			if(Ck.equals("True"))
			{
				flagAuto=true;
			}
			if(Close.equals("True"))
			{
				flagAutoClose=true;
			}
			
			if(users[1].equals("@campus"))
			{	
				choice.select("У԰��");
			}
			else if(users[1].equals("@cmcc"))
			{	
				choice.select("�й��ƶ�");
			}
			else if(users[1].equals("@unicom"))
			{	
				choice.select("�й���ͨ");
			}
			else if(users[1].equals("@telecom"))
			{	
				choice.select("�й�����");
			}
		}
		buf.close();
		if(flag!=false)
		{
			RB.setSelected(true);
			RB.setIcon(checked);
		}
		if(RB.isSelected())
		{
			
			if(flagAuto!=false)
			{
				Auto.setSelected(true);
				Auto.setIcon(checked);
			}
			if(flagAutoClose!=false)
			{
				AutoClose.setSelected(true);
				AutoClose.setIcon(checked);
			}
		}
		else
		{
			Auto.setEnabled(false);
			Auto.setIcon(unchecked);
			AutoClose.setEnabled(false);
			AutoClose.setIcon(unchecked);
		}
		
	}
	private void Login()
	{
		String Ntype = null;
		if(choice.getSelectedItem().equals("У԰��"))
		{	
			Ntype="@campus";
		}
		else if(choice.getSelectedItem().equals("�й��ƶ�"))
		{	
			Ntype="@cmcc";
		}
		else if(choice.getSelectedItem().equals("�й���ͨ"))
		{	
			Ntype="@unicom";
		}
		else if(choice.getSelectedItem().equals("�й�����"))
		{	
			Ntype="@telecom";
		}
		
		String AutoCk="False";
		String AutoCls="False";
		
		if(Auto.isSelected())
		{
			AutoCk="True";
		}
		if(AutoClose.isSelected())
		{
			AutoCls="True";
		}
		
		
		String c= "Portal";
        String a= "login";
        String login_method= "1";
//        String callback= "dr1004";
        String user_account=userField.getText().trim()+Ntype;
        String user_password=new String(passwordField.getPassword());
        String wlan_user_ip= "";
        
        LinkedHashSet<String> ipSet=NetUtil.localIpv4s();
    	for (String ip : ipSet) {
            if(ip.indexOf("172") == 0) {
            	wlan_user_ip = ip;
            	System.out.println("����ipΪ��"+ip);
            	
            }
        }
    	
  //      System.out.println(user_account);
  //      System.out.println(user_password);
       
        String body = HttpRequest.get("http://172.21.255.105:801/eportal/")
                .form("c", c)
                .form("a", a)
//                .form("callback",callback)
                .form("login_method", login_method)
                .form("user_account", user_account)
                .form("user_password", user_password)
                .form("wlan_user_ip",wlan_user_ip)
                .execute()
                .body();
        System.out.println(body);
        
        String result="({\"result\":\"1\",\"msg\":\"\\u8ba4\\u8bc1\\u6210\\u529f\"})";
        
        if(body.equals(result))
        {	 
        	if(RB.isSelected())
    		{
    		String pwd=new String(passwordField.getPassword());
    		try {
    			remPwd(userField.getText().trim(),pwd,Ntype,AutoCk,AutoCls);
    		} catch (IOException e) {
    			// TODO �Զ����ɵ� catch ��
    			e.printStackTrace();
    			}
    		}
    		else
    		{
    			try {
    				canclePwd();
    			} catch (IOException e) {
    				// TODO �Զ����ɵ� catch ��
    				e.printStackTrace();
    			}
    		}
			 JOptionPane.showMessageDialog(null,"��¼�ɹ�","�ɹ�",JOptionPane.INFORMATION_MESSAGE);	
			 if(AutoClose.isSelected())
			 System.exit(0);
        }
		 else
		 {
			 if(body.substring(2,body.length()-2).replace("\"","").equals("result:0,msg:,ret_code:2"))
			 {
				 JOptionPane.showMessageDialog(null, "��ǰ�˺ſ����Ѿ���¼","����",JOptionPane.WARNING_MESSAGE );
			 }
			 else
			 {
				 JOptionPane.showMessageDialog(null, "��¼ʧ��\n"+"������Ϣ:"+body.substring(2,body.length()-2).replace("\"",""),"ʧ��",JOptionPane.ERROR_MESSAGE );
				 try {
						FileWriter fw = new FileWriter("error.txt",true);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						fw.write(df.format(new Date())+"      ��¼������Ϣ:  "+body.substring(2,body.length()-2).replace("\"",""));
						fw.write("\r\n");
						fw.close();
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
			 }
		 }
	}
public static void main(String[] args) {
	new MainFrame();
  }
}

