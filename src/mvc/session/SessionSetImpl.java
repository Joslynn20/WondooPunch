package mvc.session;

import java.util.HashSet;
import java.util.Set;

import mvc.dto.Admin;
import mvc.dto.Customer;




public class SessionSetImpl implements SessionSet {

	

	private static SessionSet ss = new SessionSetImpl();
	private Set<Object> set;
	
	private SessionSetImpl() {
		set = new HashSet<>();
	}
	
	public static SessionSet getInstance() {
		return ss;
	}
	
	
	
	/**
	 * 사용자 찾기
	 * */
	public  Object get(String sessionId) {
	 
		 
		for(Object member : set) {
			
			if(member instanceof Admin ) {
	              Admin admin  =(Admin) member;    	     		
     	          if(admin.getADMINID().equals(sessionId)) 
	                 return admin;	
			}else if(member instanceof Customer) {  
				   Customer customer = (Customer) member;
				    if(customer.getUserId().equals(sessionId))
				     return customer;
				    
			}
		 				
		}
			
		
		return null;
	}
	
	
	
	//세션 객체들 반환
		public Set<Object> getSet(){
			return set;
		}
	
		/**
		 * 로그인 된 사용자 추가
		 * */
		public void add( Object  member) {
			set.add(member);
		}
		
	/**
	 * 사용자 제거 - 로그아웃
	 * */
	public void remove(Object member) {
		set.remove(member);
	}
	


        







}
