package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Mem_JDBC;
import vo.MemBilling;
import vo.MemShipping;
import vo.Mem_VO;
import vo.UserPayment;



@Service
public class MemService {

	@Autowired
	private Mem_JDBC mem;
	public MemService(Mem_JDBC mem) {
		this.mem = mem;
	}
	
		public Mem_VO login(String email,String pwd) {
			Mem_VO bean = mem.select_email(email);
			if(bean != null && Arrays.equals(bean.getmem_pwd(),pwd.getBytes())){
				return bean;
			}
			return null;
		}
	
		public Mem_VO insert(Mem_VO bean){
			Mem_VO result =	mem.select_email(bean.getmem_mail());
			if(result == null){
				mem.insert_Mem(bean);
				Mem_VO mbean = mem.select_email(bean.getmem_mail());
				 		 return mbean;				
			}
			return null;
		}
		
		public byte[]  select_photo (String Email){
			Mem_VO mbean = mem.select_email(Email);
			byte[]  vo  = mbean.getmem_photo();
			if(vo != null){	
			return vo;	
		}
		return null;
		}
		
	
		public boolean update(Mem_VO bean) {
			System.out.println( "bean = " +  bean);
			if(bean.getmem_photo().length == 0){
				Mem_VO	getimage = this.select(bean.getmem_id());
				bean.setmem_photo(getimage.getmem_photo());
				System.out.println("getimage = " + getimage);
			}
			boolean result = mem.updata_Mem(bean);
			if(result){
				return true;
			}
			return false;
		}
		
		public List<Mem_VO> select_fri_info(int id){			
			List<Mem_VO> mbean = mem.select_fri_info(id);
			if(mbean != null){
				return mbean;
			}
			return mbean;
		}
		
		
		public Mem_VO select_id(String email){
			Mem_VO result = null;
			result =	mem.select_email(email);
			if(result != null){
				return result;
			}
			return result;
		}
	
		
		public Mem_VO select(int id){
			Mem_VO mbean =mem.select_id(id);
			if(mbean != null){
				return mbean;
			}
			return null;
		}

		//希望新增的功能
	

		public Mem_VO findByEmail(String email) {
			// TODO Auto-generated method stub
			return null;
		}


		public void setMemDefaultPayment(Long defaultPaymentId, Mem_VO mem2) {
			// TODO Auto-generated method stub
			
		}

		public Mem_VO findByUsername(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		public void setUserDefaultShipping(Long defaultShippingId, Mem_VO user) {
			// TODO Auto-generated method stub
			
		}

		public void updateUserShipping(MemShipping memShipping, Mem_VO mem2) {
			// TODO Auto-generated method stub
			
		}

		public void updateUserBilling(MemBilling memBilling, UserPayment userPayment, Mem_VO mem2) {
			// TODO Auto-generated method stub
			
		}
		
		
}
