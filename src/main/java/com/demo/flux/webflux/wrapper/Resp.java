package com.demo.flux.webflux.wrapper;



import com.demo.flux.webflux.bean.Page;
import com.demo.flux.webflux.bean.PageRespEntry;
import com.demo.flux.webflux.bean.RespEntry;
import com.demo.flux.webflux.property.Property;

import java.util.List;

/****************************************************
 * @author Zhang Yebai
 * @description 
 * @date 2018/4/4 17:06  
 ****************************************************/
public class Resp {

	private Resp(){
	
	}
	
	public static <T> RespEntry<T> resp(T data){
		return resp(Property.Resp.SUCCESS_CODE, Property.Resp.SUCCESS_MESSAGE, data);
	}
	
	public static <T> RespEntry<T> resp(int code, T data){
		return resp(code, Property.Resp.SUCCESS_MESSAGE, data);
	}
	
	public static <T> RespEntry <T> resp(String message, T data){
		return resp(Property.Resp.SUCCESS_CODE, message, data);
	}
	public static <T> RespEntry<T> error(String message){
		return resp(Property.Resp.ERROR_CODE, message, null);
	}

	public static <T> RespEntry <T> error(int code, T data){
		return resp(code, Property.Resp.ERROR_MESSAGE, data);
	}
	
	public static <T> RespEntry <T> error(String message, T data){
		return resp(Property.Resp.ERROR_CODE, message, data);
	}
	
	public static <T> RespEntry <T> error(T data){
		return resp(Property.Resp.ERROR_CODE, Property.Resp.ERROR_MESSAGE, data);
	}
	
	public static <T> RespEntry <T> resp(int code, String message, T data){
		return new RespEntry<>(code, message, data);
	}

	public static <T> PageRespEntry<T> pageResp(List<T> data){
		return pageResp(Property.Resp.SUCCESS_CODE, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> PageRespEntry<T> pageResp(String message){
		return pageResp(Property.Resp.SUCCESS_CODE, message, null);
	}

	public static <T> PageRespEntry<T> pageResp(String message, List<T> data){
		return pageResp(Property.Resp.SUCCESS_CODE, message, data);
	}

	public static <T> PageRespEntry<T> pageResp(int code, List<T> data){
		return pageResp(code, Property.Resp.SUCCESS_MESSAGE, data);
	}

	public static <T> PageRespEntry<T> pageResp(int code, String message, List<T> data){
		return new PageRespEntry<T>().setCode(code).setMessage(message).setPage(new Page<>(data)).setData(data);
	}

}
