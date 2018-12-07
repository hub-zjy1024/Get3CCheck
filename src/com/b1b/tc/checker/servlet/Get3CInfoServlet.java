package com.b1b.tc.checker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b1b.tc.checker.mgr.DataInterface;
import com.b1b.tc.checker.mgr.DataPro;

/**
 * Servlet implementation class Get3CInfoServlet
 */
@WebServlet("/Get3CInfoServlet")
public class Get3CInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isDebug = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Get3CInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*{
	 "part_type": "cables-wires-management",
	 "part_detial": "bushings-grommets",
	 "digikey_id": "RPC3901-ND",
	 "part_id": "DSC-.5-48",
	 "detial_page_url": "/product-detail/zh/essentra-components/DSC-.5-48/RPC3901-ND/2410902",
	 "manu": "EssentraComponents",
	 "description": "GROMMETEDGESOLIDPA4",
	 "current_amount": "108",
	 "manu_current_amount": null,
	 "not_inventory": "",
	 "min_buy_amount": "1",
	 "CNY": "",
	 "USD": "5.89",
	 "xl": "DSC",
	 "status": "在售",
	 "field_content": "[\"\",\"http://www.essentracomponents.com.sg/images-line/pdfs/DSC.pdf\",\"//media.digikey.com/Photos/Essentra%20Components%20Photos/MFG_DSC-.5-48_tmb.jpg\",\"/product-detail/zh/essentra-components/DSC-.5-48/RPC3901-ND/2410902\",\"DSC-.5-48\",\"EssentraComponents\",\"GROMMETEDGESOLIDPA4\",\"108-立即发货可供应:108\",\"$5.89000\",\"1最低订购数量:1\",\"DSC\",\"在售\",\"扣眼-边缘，实心型\",\"直面板\",\"0.031~0.062（0.79mm~1.57mm）\",\"边缘-4长（1.22m）\",\"-\",\"-\",\"聚酰胺（PA），尼龙\",\"灰色\",\"-\"]",
	 "price_break": "{\r\n \"得捷编号\": \"RPC3901-ND\",\r\n \"price_break\": [\r\n {\r\n \"价格分段\": \"1\",\r\n \"单价\": \" 5.89000\",\r\n \"总价\": \"$5.89\"\r\n },\r\n {\r\n \"价格分段\": \"10\",\r\n \"单价\": \"5.15700\",\r\n \"总价\": \"$51.57\"\r\n },\r\n {\r\n \"价格分段\": \"25\",\r\n \"单价\": \"4.42040\",\r\n \"总价\": \"$110.51\"\r\n },\r\n {\r\n \"价格分段\": \"100\",\r\n \"单价\": \"3.68360\",\r\n \"总价\": \"$368.36\"\r\n },\r\n {\r\n \"价格分段\": \"500\",\r\n \"单价\": \"3.24156\",\r\n \"总价\": \"$1,620.78\"\r\n },\r\n {\r\n \"价格分段\": \"1,000\",\r\n \"单价\": \"2.94688\",\r\n \"总价\": \"$2,946.88\"\r\n }\r\n ]\r\n}",
	 "update_time": "2018-11-11T20:49:20",
	 "price_break_update_time": "2018-11-09T16:57:12",
	 "create_time": "2018-09-04T18:59:34"
	}*/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String partno = request.getParameter("partno");
		DataInterface pro = new DataPro();
		String res = pro.getData(partno);
		response.getWriter().append(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
