package main.java.com.oracle.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.oracle.beans.MobileDevice;

/**
 * Servlet implementation class AddToCart
 * Add @authoer Osama Oransa
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id!=null && !id.isEmpty()){
			MobileDevice device=null;
			switch(id){
			case "1":
				device = new MobileDevice();
				device.setId(1);
				device.setName("Samsung");
				device.setPrice(120);
				break;
			case "2":
				device = new MobileDevice();
				device.setId(2);
				device.setName("iPhone");
				device.setPrice(130);
				break;
			case "3":
				device = new MobileDevice();
				device.setId(3);
				device.setName("HTC");
				device.setPrice(95);
				break;
			case "4":
				device = new MobileDevice();
				device.setId(4);
				device.setName("Sony");
				device.setPrice(100);
				break;
			}
			if(device!=null){
				List<MobileDevice> list=null;
				if(request.getSession().getAttribute("CART")==null){
					list = new ArrayList<MobileDevice>();
					list.add(device);
					request.getSession().setAttribute("CART", list);
				} else {
					list = (List<MobileDevice>) request.getSession().getAttribute("CART");
					list.add(device);
					request.getSession().setAttribute("CART", list);
				}
			}
		}
		System.out.println("Go to Home Page");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
