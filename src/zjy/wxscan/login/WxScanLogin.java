package zjy.wxscan.login;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b1b.tc.checker.utils.net.StreamRead;

public class WxScanLogin {
	String WX_SCAN_CODE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid={APPID}&redirect_uri={REUTL}&response_type=code&scope=snsapi_login&state={STATE}#wechat_redirect";
	// 千万要记住，这个是微信开放平台的APPID
	String WX_PLATFROM_APPID = "wx3636754f0b243b3f";
	// 你的回调地址
	String scanReUrl = "http://你的网址/user/wxLoginCallback";

	public void weixinRetrun(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取回调url(非必填，只是附带上你扫码之前要进入的网址，具体看业务是否需要)
		String url = request.getParameter("reurl");
		// 拼接扫码登录url
		String wxLoginurl = WX_SCAN_CODE_URL;
		wxLoginurl = wxLoginurl.replace("{APPID}", WX_PLATFROM_APPID).replace("{REUTL}", scanReUrl)
				.replace("{STATE}", url);
		wxLoginurl = response.encodeURL(wxLoginurl);
		response.sendRedirect(wxLoginurl);
	}


}
