package com.zhidisoft.result;

public class JsonResult {
	private boolean success; // 请求操作是否成功 
	private String message; // 提示信息
	private Object result; // 要传递到前端的结果
	
	
	
	
	

	public static JsonResult buildSuccessResult(){
		return new JsonResult(true,null,null);
	}
	
	public static JsonResult buildSuccessResult(String message){
		return new JsonResult(true,message,null);
	}
	
	public static JsonResult buildSuccessResult(Object result){
		return new JsonResult(true,null,result);
	}
	
	
	public static JsonResult buildSuccessResult(String message,Object result){
		return new JsonResult(true,message,result);
	}
	
	
	public static JsonResult buildFailResult(){
		return new JsonResult(false,null,null);
	}
	
	public static JsonResult buildFailResult(String message){
		return new JsonResult(false,message,null);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}

	public JsonResult(boolean success, String message, Object result) {
		super();
		this.success = success;
		this.message = message;
		this.result = result;
	}
	
	

	public JsonResult() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
