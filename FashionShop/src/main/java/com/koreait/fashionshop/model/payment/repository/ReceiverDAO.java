package com.koreait.fashionshop.model.payment.repository;

import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.Receiver;

public interface ReceiverDAO {
	public void insert(Receiver receiver);
}
