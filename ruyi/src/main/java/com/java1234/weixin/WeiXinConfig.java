package com.java1234.weixin;


public class WeiXinConfig {
		
		// 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
		// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
		// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改
		
		public final static String trade_type = "JSAPI";
		public final static String SIGN_TYPE = "MD5";//签名加密方式
		
		
		
		//（蚂蚁科技Token）sisstoken
		public final static String Token = "sisstoken";
		
		
		//（蚂蚁科技-EncodingAESKey）GmULUeNedGYUMlNSQslwakSYCLdAVrGpljRKYTwpFcg
		
		public final static String key = "GmULUeNedGYUMlNSQslwakSYCLdAVrGpljRKYTwpFcg";
		
		
		
		
		//蚂蚁 应用密钥 AppSecret【cf741142af236e96a01db58dd0ac586f】
		//测试【2fe93114d0d7d20b375ada90f9e54b3f】
		//(志化)【49803df50be4f35741032a424deeaf32】 志华实业 rzzhmy@163.com zhmy3699 13064458882
		public final static String appsecret = "49803df50be4f35741032a424deeaf32";
		// 蚂蚁科技=【wx2d7b8f851e2b4d2c】 
		// 测试  = 【wxa01f7fee886b54e9】
		//(志华门)【wx83508396d0515fc7】
		public  final static String appID = "wx83508396d0515fc7";
		
		
		
		
		//微信发送客服消息的接口URL如下
		String client_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=#ACCESS_TOKEN#";
		
		
		
		
		
		// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
		public  final static String mchID = "1253076501";

		// 受理模式下给子商户分配的子商户号
		private final static String subMchID = "";

		// HTTPS证书的本地路径
		private static String certLocalPath = "";

		// HTTPS证书密码，默认密码等于商户号MCHID
		private static String certPassword = "1253076501";

		// 是否使用异步线程的方式来上报API测速，默认为异步模式
		private static boolean useThreadToDoReport = true;

		// 机器IP
		private static String ip = "27.54.227.50";

		// 接口链接
		// URL地址：https://api.mch.weixin.qq.com/pay/unifiedorder
		public final static String pay_ok_url = "http://hjdv.v050.10000net.cn/3.jsp";
		
		//提交post到这个地址，可以拿到prepay id
		public  static final String PREPAY_ID_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		//付款url  付到用户的零钱
		public  static final String fukuan_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
		
		//红包url   公众号给用户发红包
		public  static final String hongbao_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";

		//微信二维码 url 基础支持: 获取access_token接口 /token
		//请求地址ticket 方法get
		public  static final String ticket = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxab3d090e1d6f4f9d&secret=91d925477990157e7d9466c8c0f513fa";

	
}
