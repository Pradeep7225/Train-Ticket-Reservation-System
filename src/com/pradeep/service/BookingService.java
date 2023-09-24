package com.pradeep.service;

import java.util.List;

import com.pradeep.beans.HistoryBean;
import com.pradeep.beans.TrainException;

public interface BookingService {

	public List<HistoryBean> getAllBookingsByCustomerId(String customerEmailId) throws TrainException;

	public HistoryBean createHistory(HistoryBean bookingDetails) throws TrainException;

}
