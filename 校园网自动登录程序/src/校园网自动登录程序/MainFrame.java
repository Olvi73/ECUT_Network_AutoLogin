package У԰���Զ���¼����;


import cn.hutool.http.HttpRequest;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;//����awt���еļ������¼���
import java.awt.event.ActionEvent;//����awt���е�ActionEvent�¼���


public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1450498460078768368L;
	MainFrame(){
		
		try {
			init();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	JLabel UserName = new JLabel("�˺�:"); 
	JLabel UserPassword = new JLabel("����:"); 
	JLabel type=new JLabel("��������:");
	JTextField userField=new JTextField(15);
	JPasswordField passwordField=new JPasswordField(15);
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
		setTitle("У԰����¼");
		
		setLayout(null);
		setResizable(false);
		this.setBounds(553,254,600,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);  
		
		 choice.add("У԰��"); 
		 choice.add("�й��ƶ�"); 
		 choice.add("�й���ͨ"); 
		 choice.add("�й�����"); 
		 choice.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		 
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
		 
		 Container ct= getContentPane();
		 ct.add(UserName);
		 ct.add(UserPassword);
		 ct.add(type);
		 ct.add(userField);
		 ct.add(passwordField);
		 ct.add(choice);
		 ct.add(B_submit);
		 ct.add(B_reset);
		 ct.add(B_logout);
		 ct.add(RB);
		 ct.add(Auto);
		 ct.add(AutoClose);
		 
		 
		 UserName.setBounds(150, 100, 70, 25);
		 userField.setBounds(230, 100, 160, 30);
		 UserPassword.setBounds(150, 145, 70, 25);
		 passwordField.setBounds(230, 145, 160, 30);
		 RB.setBounds(410,140,180,35);
		 Auto.setBounds(410,190,180,35);
		 AutoClose.setBounds(410,240,180,35);
		 
		 type.setBounds(110, 190, 100, 25);
		 choice.setBounds(230, 190, 120, 25);
		 B_logout.setBounds(100, 300,100,40);
		 B_reset.setBounds(230, 300, 100, 40);
		 B_submit.setBounds(370, 300, 100, 40);
		 
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
	        
	        String login_method= "1";
	        String user_account="drcom";
	        String user_password="123";
	        
	        System.out.println(user_account);
	        System.out.println(user_password);
	       
	        String body = HttpRequest.get("http://172.21.255.105:801/eportal/")
	                .form("c", c)
	                .form("a", a2)
	                .form("login_method", login_method)
	                .form("user_account", user_account)
	                .form("user_password", user_password)
	                .form("ac_logout",0)
	           	    .execute()
	                .body();
	        System.out.println(body);
	        
	        String result="({\"result\":\"1\",\"msg\":\"ע���ɹ�\"})";
	        
	        if(body.equals(result))
	        
				 JOptionPane.showMessageDialog(null,"ע���ɹ�","�ɹ�",JOptionPane.INFORMATION_MESSAGE);	
	        
				 else
				 JOptionPane.showMessageDialog(null, "ע��ʧ��","ʧ��",JOptionPane.WARNING_MESSAGE );
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
		}
		if(RB.isSelected())
		{
			
			if(flagAuto!=false)
			{
				Auto.setSelected(true);
			}
			if(flagAutoClose!=false)
			{
				AutoClose.setSelected(true);
			}
		}
		else
		{
			Auto.setEnabled(false);
			AutoClose.setEnabled(false);
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
        String user_account=userField.getText().trim()+Ntype;
        String user_password=new String(passwordField.getPassword());
        
  //      System.out.println(user_account);
  //      System.out.println(user_password);
       
        String body = HttpRequest.get("http://172.21.255.105:801/eportal/")
                .form("c", c)
                .form("a", a)
                .form("login_method", login_method)
                .form("user_account", user_account)
                .form("user_password", user_password)
                .execute()
                .body();
        System.out.println(body);
        
        String result="({\"result\":\"1\",\"msg\":\"��֤�ɹ�\"})";
        
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
			 JOptionPane.showMessageDialog(null, "��¼ʧ��","ʧ��",JOptionPane.WARNING_MESSAGE );
	}
public static void main(String[] args) {
	new MainFrame();
  }
}

