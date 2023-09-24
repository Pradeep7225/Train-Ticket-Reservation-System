package com.pradeep.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pradeep.beans.TrainBean;
import com.pradeep.beans.TrainException;
import com.pradeep.constant.ResponseCode;
import com.pradeep.constant.UserRole;
import com.pradeep.service.TrainService;
import com.pradeep.service.impl.TrainServiceImpl;
import com.pradeep.utility.TrainUtil;

@WebServlet("/adminaddtrain")
public class AdminAddTrain extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TrainService trainService = new TrainServiceImpl();

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);
		try {
			TrainBean train = new TrainBean();
			train.setTr_no(Long.parseLong(req.getParameter("trainno")));
			train.setTr_name(req.getParameter("trainname").toUpperCase());
			train.setFrom_stn(req.getParameter("fromstation").toUpperCase());
			train.setTo_stn(req.getParameter("tostation").toUpperCase());
			train.setSeats(Integer.parseInt(req.getParameter("available")));
			train.setFare(Double.parseDouble(req.getParameter("fare")));
			String message = trainService.addTrain(train);
			if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(message)) {
				RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Train Added Successfully!</p1></div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Error in filling the train Detail</p1></div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}

	}

}
