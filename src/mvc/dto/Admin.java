package mvc.dto;

public class Admin {
 
	
	
	private final String ADMINID ="WONDOPUNCH";
	private final String ADMINPW="12345";
	private final String ADMINNAME="박원두";
	
	public String getADMINID() {
		return ADMINID;
	}
	public String getADMINPW() {
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
