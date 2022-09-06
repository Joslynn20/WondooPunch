package mvc.session;

import java.util.HashSet;
import java.util.Set;

import mvc.dto.Customer;




public class SessionSetImpl implements SessionSet {

	

	private static SessionSet ss = new SessionSetImpl();
	private Set<Customer> set;
	
	private SessionSetImpl() {
		set = new HashSet<>();
	}
	
	public static SessionSet getInstance() {
		return ss;
	}
	
	
	/**
	 * 사용자 찾기
	 * */
	public  Customer get(String sessionId) {
	 
		 
		for(Customer customer: set) {
			
			if(customer.getUserId().equals(sessionId) ) {
				
				return customer;
			
			}
		
		}
		return null;
	}
	
	
	
	//세션 객체들 반환
		public Set<Customer> getSet(){
			return set;
		}
	
		/**
		 * 로그인 된 사용자 추가
		 * */
		public void add(Customer customer) {
			set.add(customer);
		}
		
	/**
	 * 사용자 제거 - 로그아웃
	 * */
	public void remove(Customer customer) {
		set.remove(customer);
	}
	


        








}
