package com.koreait.fashionshop.model.common;

import lombok.Data;

@Data
public class MessageData {
	private int resultCode;//실패,성공 여부 판단코드
	private String msg;//클라이언트가 보게 될 메세지
	private String url;//새롭게 요청할 페이지가 있다면 그 url
}
