package dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vo.Mem_VO;

@Repository
@Transactional
public class Mem_JDBC {
	@Autowired
	private SessionFactory sessionFactory;

	public Mem_JDBC(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

//	public static void main(String[] args) {
//		Mem_JDBC mbean = new Mem_JDBC(HibernateUtil.getSessionFactory());
//		 Mem_VO vo = mbean.select_id(10);
//		 System.out.println(vo);

//		 Mem_VO vo = mbean.select_email("pepa@aniaml.com");
//		 System.out.println(vo);
//		 Mem_VO vo = mbean.select_email("pepa@aniaml.com");
//		 System.out.println(vo);

//		 List<Mem_VO> vo = mbean.select_fri_info(3);
//		 	if(vo == null){
//			 	System.out.println("null");
//		 }else{
//			 System.out.println(vo);
//			 for(Mem_VO vv : vo){
//				 	System.out.println(vv);
//			 }
//		 };
		 
		 
//		Mem_VO vo = new Mem_VO();
//		vo.setmem_info("AAAA");
//		vo.setmem_name("AA");
//		vo.setmem_id(1);
//		boolean result = mbean.updata_Mem(vo);
//		System.out.println(result);
		
//		String a = "A";
//		byte[] r = a.getBytes();
//		Mem_VO vo = new Mem_VO();
//		vo.setmem_name("PAPAPAP");
//		vo.setmem_pwd(r);
//		vo.setmem_mail("POPO@gmaioxl.com");
//		boolean  result = mbean.insert_Mem(vo);
//		System.out.println(result);
//	}

	public Mem_VO select_id(int id) { // ok
			return 	(Mem_VO) getSession().get(Mem_VO.class, id);
	}

	public Mem_VO select_email(String mem_mail) { // OK
		Mem_VO result=null;
		try{
			String email = "From Mem_VO where mem_mail = :mem_mail";
			Query query = getSession().createQuery(email);
			query.setParameter("mem_mail", mem_mail);
			 result = (Mem_VO) query.getSingleResult();
		}catch (javax.persistence.NoResultException e) {
			return null;
		}
		return result;
	}

	

	public List<Mem_VO> select_fri_info(int mem_id) { // Ok
			String select_fri_info = "select * from  mem where mem_id in (select f.mem_fri_num from fri f join mem m  on m.mem_id = f.mem_id where f.mem_id =";
			List<Mem_VO> result  =  (List<Mem_VO>)getSession().createNativeQuery(select_fri_info + mem_id + ")").addEntity(Mem_VO.class).list();
			if(result.isEmpty()){
				return null;
			}
			return result;

	}

	
	
	public boolean updata_Mem(Mem_VO mbean) { // ok
		int i=0;
			if (mbean.getmem_pwd() != null && mbean.getmem_pwd().length != 0) {
				String updata_emp = "update mem set mem_name= :name , mem_pwd= :pwd ,mem_photo= :photo ,mem_info= :info where mem_id= :id";
				NativeQuery query = getSession().createNativeQuery(updata_emp);
				query.setParameter("name", mbean.getmem_name());
				query.setParameter("pwd", mbean.getmem_pwd());
				query.setParameter("photo", mbean.getmem_photo());
				query.setParameter("info", mbean.getmem_info());
				query.setParameter("id", mbean.getmem_id());
				i = query.executeUpdate();

			} else {
				String updata_emp = "update mem set mem_name= :name  ,mem_photo= :photo ,mem_info= :info where mem_id= :id";
				NativeQuery query = getSession().createNativeQuery(updata_emp);
				query.setParameter("name", mbean.getmem_name());
				query.setParameter("photo", mbean.getmem_photo());
				query.setParameter("info", mbean.getmem_info());
				query.setParameter("id", mbean.getmem_id());
				i = query.executeUpdate();
			}
		if (i == 1) {
			return true;
		}
		return false;
	}

	
	
	
	public boolean insert_Mem(Mem_VO bean) { // OK
			String insert_Mem = "insert into mem(mem_name,mem_mail,mem_pwd,mem_regtime) values (:name,:mail,:pwd,:regtime)";
			NativeQuery  query = getSession().createNativeQuery(insert_Mem);
			query.setParameter("name",bean.getmem_name());
			query.setParameter("mail",bean.getmem_mail());
			query.setParameter("pwd",bean.getmem_pwd());
			query.setParameter("regtime",new java.sql.Date(new java.util.Date().getTime()));
			int	i = query.executeUpdate();
		if(i == 1){
			return true;
		}
		return false;
	}

}
