package com.java1234.weixin;


public class WeiXinConfig {
		
		// ��������Լ�Ҫ���ܺõ�˽��Key�ˣ��м�ֻ�ܷ����Լ��ĺ�̨��������ܷ����κο��ܱ�����Դ����Ŀͻ��˳����У�
		// ÿ���Լ�Post���ݸ�API��ʱ��Ҫ�����key���������ֶν���ǩ�������ɵ�ǩ�������Sign����ֶΣ�API�յ�Post���ݵ�ʱ��Ҳ����ͬ����ǩ���㷨��Post���������ݽ���ǩ������֤
		// �յ�API�ķ��ص�ʱ��ҲҪ�����key���Է��ص���������ǩ������API��Sign���ݽ��бȽϣ����ֵ��һ�£��п������ݱ����������۸�
		
		public final static String trade_type = "JSAPI";
		public final static String SIGN_TYPE = "MD5";//ǩ�����ܷ�ʽ
		
		
		
		//�����ϿƼ�Token��sisstoken
		public final static String Token = "sisstoken";
		
		
		//�����ϿƼ�-EncodingAESKey��GmULUeNedGYUMlNSQslwakSYCLdAVrGpljRKYTwpFcg
		
		public final static String key = "GmULUeNedGYUMlNSQslwakSYCLdAVrGpljRKYTwpFcg";
		
		
		
		
		//���� Ӧ����Կ AppSecret��cf741142af236e96a01db58dd0ac586f��
		//���ԡ�2fe93114d0d7d20b375ada90f9e54b3f��
		//(־��)��49803df50be4f35741032a424deeaf32�� ־��ʵҵ rzzhmy@163.com zhmy3699 13064458882
		public final static String appsecret = "49803df50be4f35741032a424deeaf32";
		// ���ϿƼ�=��wx2d7b8f851e2b4d2c�� 
		// ����  = ��wxa01f7fee886b54e9��
		//(־����)��wx83508396d0515fc7��
		public  final static String appID = "wx83508396d0515fc7";
		
		
		
		
		//΢�ŷ��Ϳͷ���Ϣ�Ľӿ�URL����
		String client_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=#ACCESS_TOKEN#";
		
		
		
		
		
		// ΢��֧��������̻���ID����ͨ���ںŵ�΢��֧������֮����Ի�ȡ����
		public  final static String mchID = "1253076501";

		// ����ģʽ�¸����̻���������̻���
		private final static String subMchID = "";

		// HTTPS֤��ı���·��
		private static String certLocalPath = "";

		// HTTPS֤�����룬Ĭ����������̻���MCHID
		private static String certPassword = "1253076501";

		// �Ƿ�ʹ���첽�̵߳ķ�ʽ���ϱ�API���٣�Ĭ��Ϊ�첽ģʽ
		private static boolean useThreadToDoReport = true;

		// ����IP
		private static String ip = "27.54.227.50";

		// �ӿ�����
		// URL��ַ��https://api.mch.weixin.qq.com/pay/unifiedorder
		public final static String pay_ok_url = "http://hjdv.v050.10000net.cn/3.jsp";
		
		//�ύpost�������ַ�������õ�prepay id
		public  static final String PREPAY_ID_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		//����url  �����û�����Ǯ
		public  static final String fukuan_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
		
		//���url   ���ںŸ��û������
		public  static final String hongbao_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";

		//΢�Ŷ�ά�� url ����֧��: ��ȡaccess_token�ӿ� /token
		//�����ַticket ����get
		public  static final String ticket = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxab3d090e1d6f4f9d&secret=91d925477990157e7d9466c8c0f513fa";

	
}
