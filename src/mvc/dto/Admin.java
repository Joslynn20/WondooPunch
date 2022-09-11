package mvc.dto;

public class Admin {
 
	
	
	private final static String ADMINID ="WONDOPUNCH";
	private final static String ADMINPW="12345";
	private final static String ADMINNAME="박원두";
	
	public static String getADMINID() {
		return ADMINID;
	}
	public static String getADMINPW() {
		return ADMINPW;
	}
	public String getADMINNAME() {
		return ADMINNAME;
	}

      
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ADMINID.hashCode();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		                     
		     if(obj instanceof Admin) {
		    	Admin admin   =(Admin)obj;
		                      
		      return this.equals(admin.getADMINID());
		      
		     
		     }
		
		return false;
	}
	
	





}
