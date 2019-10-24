package zjy.wxscan.login.utils.wcf;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by 张建宇 on 2016/12/20.
 */

public class WebserviceUtils {

	public static final String NAMESPACE = "http://tempuri.org/";
	public static final String ROOT_URL = "http://172.16.6.160:8006/";
	//public static final String ROOT_URL = "http://210.51.190.36:7500/";
	// 服务名，带后缀名的
	public static final String MartService = "MartService.svc";
	public static final String MartStock = "MartStock.svc";
	public static final String Login = "Login.svc";
	public static final String MyBasicServer = "MyBasicServer.svc";
	public static final String ForeignStockServer = "ForeignStockServer.svc";
	public static final String PMServer = "PMServer.svc";
	public static final String IC360Server = "IC360Server.svc";
	public static final String SF_Server = "SF_Server.svc";
	public static final String ChuKuServer = "ChuKuServer.svc";
	private static final int VERSION_10 = SoapEnvelope.VER10;
	private static final int VERSION_11 = SoapEnvelope.VER11;
	private static final int VERSION_12 = SoapEnvelope.VER12;
	public static boolean debug = false;
	/**
	 * 扫描二维码的返回请求码
	 */
	public static final int QR_REQUESTCODE = 100;
	/**
	 * 设备No
	 */
	public static String DeviceNo = "";
	/**
	 * 交互码
	 */
	public static String WebServiceCheckWord = "sdr454fgtre6e655t5rt4";
	/**
	 * 设备ID
	 */
	public static String DeviceID = "ZTE-T U880";

	/**
	 * 获取Url 不能随意拼接，得自己根据wsdl文档
	 * 
	 * @param serviceName
	 *            以svc结尾的service名称
	 * @return
	 */
	private static String getTransportSEtUrl(String serviceName) {
		// return ROOT_URL + serviceName + "?singleWsdl";
		return ROOT_URL + serviceName;
	}

	/**
	 * 不能随意拼接，得自己根据wsdl文档
	 * 
	 * @param serviceName
	 * @param methodName
	 * @return
	 */
	public static String getSoapAcction(String serviceName, String methodName) {
		String action = NAMESPACE + "I" + serviceName.substring(0, serviceName.indexOf(".")) + "/"
				+ methodName;
		//	System.out.println("action:" + action);
		return action;
	}

	/**
	* @param request
	* @param envolopeVesion {@link org.ksoap2.SoapEnvelope}
	* @param serviceName    以svc结尾的service名称
	* @return
	* @throws IOException
	* @throws XmlPullParserException
	*/
	private static String getWcfResult(SoapObject request, int envolopeVesion, String serviceName)
			throws IOException, XmlPullParserException {
		int timeout = 30 * 1000;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(envolopeVesion);
		//.net开发的ws服务必须设置为true
		envelope.dotNet = true;
		//       envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		//创建HttpTransportSE对象
		HttpTransportSE ht = new HttpTransportSE(getTransportSEtUrl(serviceName), timeout);
		//有些不需要传入soapAction，根据wsdl文档
		if (envolopeVesion == VERSION_12) {
			ht.call(null, envelope);
		} else {
			ht.call(getSoapAcction(serviceName, request.getName()), envelope);
		}
		Object sob = envelope.getResponse();
		if (sob instanceof SoapFault) {
			throw new IOException("error requeset", (SoapFault) sob);
		} else if (sob instanceof SoapObject) {
			if (debug) {
				int len = sob.toString().length();
				String outStr = sob.toString();
				if (len > 1000) {
					len = 1000;
					outStr = sob.toString().substring(0, len) + "(...)";
				}
				System.out.println("WebserviceUtils->getWcfResult(): soapObj==" + outStr);
			}
		} else if (sob instanceof SoapPrimitive) {
			if (debug) {
				int len = sob.toString().length();
				String outStr = sob.toString();
				if (len > 1000) {
					len = 1000;
					outStr = sob.toString().substring(0, len) + "(...)";
				}
				System.out.println("WebserviceUtils->getWcfResult(): soapPrimitive==" + outStr);
			}
		} else {
			System.out.println("Soap response Unknow");
		}
		return sob.toString();
	}

	public static String getWcfResult(LinkedHashMap<String, Object> properties, String method,
			String serviceName) throws IOException, XmlPullParserException {
		SoapObject request = getRequest(properties, method);
		return getWcfResult(request, VERSION_11, serviceName);
	}

	/**
	 * 获取SoapObject请求对象
	 * 
	 * @param properties
	 *            方法的参数，有序，建议集合使用LinkedHashMap，如果没有，可以传入null
	 * @param method
	 *            方法的名称
	 * @return
	 */
	public static SoapObject getRequest(LinkedHashMap<String, Object> properties, String method) {
		SoapObject request = new SoapObject(WebserviceUtils.NAMESPACE, method);
		System.out.println("call "+method);
		if (properties != null) {
			// 设定参数
			Set<String> set = properties.keySet();
			for (String string : set) {
				request.addProperty(string, properties.get(string));
				System.out.println(
						String.format("param-value='%s' - '%s'", string, properties.get(string)));
			}
		}
		return request;
	}

	/**
	 * @param request
	 *            返回Envelope对象
	 * @param envolopeVesion
	 *            {@link org.ksoap2.SoapEnvelope}
	 * @param serviceName
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	private static SoapSerializationEnvelope getEnvelope(SoapObject request, int envolopeVesion,
			String serviceName, int timeout) throws IOException, XmlPullParserException {
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(envolopeVesion);
		envelope.dotNet = true;
		// envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		// 创建HttpTransportSE对象
		HttpTransportSE ht = new HttpTransportSE(getTransportSEtUrl(serviceName), timeout);
		// 有些不需要传入soapAction，根据wsdl文档
		ht.call(getSoapAcction(serviceName, request.getName()), envelope);
		return envelope;
	}

	/**
	 * @param request
	 * @param envolopeVesion
	 *            {@link org.ksoap2.SoapEnvelope}
	 * @param serviceName
	 *            以svc结尾的service名称
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static SoapPrimitive getSoapPrimitiveResponse(SoapObject request, int envolopeVesion,
			String serviceName) throws IOException, XmlPullParserException {
		SoapSerializationEnvelope envelope = getEnvelope(request, envolopeVesion, serviceName,
				30 * 1000);
		SoapPrimitive sob = (SoapPrimitive) envelope.getResponse();
		return sob;
	}

	/**
	 * @param namespace
	 * @param method
	 * @param soapAction
	 * @param transUrl
	 * @param properties
	 * @param envolopeVersion
	 * @param timeout
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	private static SoapSerializationEnvelope getEnvelope(String namespace, String method,
			String soapAction, String transUrl, LinkedHashMap<String, Object> properties,
			int envolopeVersion, int timeout) throws IOException, XmlPullParserException {
		SoapObject request = new SoapObject(namespace, method);
		// 设置方法参数，无参数直接传入null值
		if (properties != null) {
			Iterator<String> iterator = properties.keySet().iterator();
			while (iterator.hasNext()) {
				String s = iterator.next();
				String value = (String) properties.get(s);
				request.addProperty(s, value);
			}
		}

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(envolopeVersion);
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;
		HttpTransportSE se = new HttpTransportSE(transUrl, timeout);
		if (envolopeVersion == SoapEnvelope.VER11 && soapAction != null) {
			se.call(soapAction, envelope);
		} else if (envolopeVersion == SoapEnvelope.VER12) {
			se.call(null, envelope);
		} else {
			// 协议版本和soapAction不匹配
			return null;
		}
		return envelope;
	}

}
