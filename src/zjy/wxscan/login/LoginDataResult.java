package zjy.wxscan.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zjy.wxscan.login.entity.BaseWxUserInfo;
import zjy.wxscan.login.entity.WxDepartmentInfo;
import zjy.wxscan.login.utils.IWxManager;
import zjy.wxscan.login.utils.WxTokenHelper;

/**
 * Servlet implementation class LoginDataResult
 */
@WebServlet("/LoginDataResult")
public class LoginDataResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginDataResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*		String time = request.getParameter("time");
				long timeLong = Long.parseLong(time);
			*/
		//sendRedirec相对于服务器根目录，多个工程可以进行互动
		//request.getRequestDispatcher("/DyjDigikey/search.jsp").forward(request, response);
		//response.sendRedirect("/DyjDigikey");;
//		String token = WxTokenHelper.getToken(corpId, corpsecret);
		String token = WxTokenHelper.getContactToken();
		String flag = request.getParameter("flag");
		String uid = "123123";
		String time = request.getParameter("time");
		//IWxManager.DepartmentManger.getAllParts(token,"");
		String partName="Test123";
		String token2 = token;
		WxDepartmentInfo mPart = IWxManager.DepartmentManger.getWxPartIdByName(token2, partName);
		if(mPart!=null){
			System.out.println("------------Part exists="+mPart.getId());
		}else{
			int newpartId=IWxManager.DepartmentManger.addPart(token2, partName);
			System.out.println("------------addNewPart="+newpartId);
		}
		if (!"delete".equals(flag)) {
			BaseWxUserInfo bUser = new BaseWxUserInfo();
			bUser.setUserid(uid);
			bUser.setName("testName");
			//Integer = new Integer[] { 1 };
			List<Integer> deps = new ArrayList<>();
			deps.add(1);
			int[] deps2 = new int[] { 1 };
			bUser.setDepartment(deps2);
			bUser.setEmail("TestEmail@163.com");
			bUser.setGender("1");
			//18865500856
			bUser.setMobile("16619709101");
			boolean addUser = IWxManager.addUser(token2, bUser);
			if (addUser) {
				response.getWriter().append("insert=okokokokokok");
			} else {
				response.getWriter().append("insert=error");
			}
		} else {
			boolean addUser = IWxManager.deleteUser(token2, Integer.parseInt(uid));
			if (addUser) {
				response.getWriter().append("deleteUser=okokokokokok");
			} else {
				response.getWriter().append("deleteUser=error");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
