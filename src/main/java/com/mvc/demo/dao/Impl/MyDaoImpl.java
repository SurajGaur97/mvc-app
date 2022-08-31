package com.mvc.demo.dao.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mvc.demo.dao.MyDao;
import com.mvc.demo.entity.Employee;
import com.mvc.demo.model.MyModel;

@Component
public class MyDaoImpl implements MyDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public MyModel save(MyModel myModel) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Employee employee = new Employee();
			employee.setName(myModel.getName());
			employee.setCity(myModel.getCity());
			session.save(employee);
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return myModel;
	}

	public MyModel findById(Integer id) {
		Session session = null;
		Transaction transaction = null;
		MyModel model = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			model = new MyModel();
			Employee employee = null;
			
			//------------------ By SQL Query and Iterator ------------------
			Query query = session.createSQLQuery("select * from employee1 where id = :id")
					.addScalar("id", IntegerType.INSTANCE)
					.addScalar("name", StringType.INSTANCE)
					.addScalar("city", StringType.INSTANCE)
					
					.setParameter("id", id);

			List<Object[]> result = query.getResultList();
			
			Iterator<Object[]> it = result.iterator();
			while(it.hasNext()) {
			     Object[] line = it.next();
			     employee = new Employee();
			     employee.setId((Integer)line[0]);
			     employee.setName(line[1].toString());
			     employee.setCity(line[2].toString());
			}
			//------------------ By SQL Query and Iterator ------------------
			
			//OR-----------
			
			//------------------ By using Spring Method 'find()'------------------
			//employee = session.find(Employee.class, id);
			//------------------ By using Spring Methods 'find()'------------------
			
			model.setEmployee(employee);
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return model;
	}

	public MyModel findAll() {
		Session session = null;
		Transaction transaction = null;
		MyModel myModel = new MyModel();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Query query = session.createSQLQuery("select * from employee1")
					.addScalar("id", IntegerType.INSTANCE)
					.addScalar("name", StringType.INSTANCE)
					.addScalar("city", StringType.INSTANCE);			
			
			List<Employee> employeeList = new ArrayList<Employee>();
			List<Object[]> result = query.getResultList();
			
			Iterator<Object[]> it = result.iterator();
			while(it.hasNext()) {
			     Object[] line = it.next();
			     Employee employee = new Employee();
			     employee.setId((Integer)line[0]);
			     employee.setName(line[1].toString());
			     employee.setCity(line[2].toString());
			     employeeList.add(employee);
			}
			
			myModel.setEmployeeList(employeeList);
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return myModel;
	}

	public String deleteById(Integer id) {
		Session session = null;
		Transaction transaction = null;
		String name = "";
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Query getName = session.createSQLQuery("select name from employee1 where id = :id")
					.addScalar("name", StringType.INSTANCE)
					.setParameter("id", id);
			
			Query query = session.createSQLQuery("delete from employee1 where id = :id")
					.setParameter("id", id);
			
			
			List<Object[]> result = getName.getResultList();
			name = result.get(0) + "";
			
			int flag = query.executeUpdate();
			if(flag == 0) {
				name = "Data not deleted!";
			}
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return name;
	}

	
	public String updateById(MyModel myModel) {
		Session session = null;
		Transaction transaction = null;
		String oldName = "";
		String newName = myModel.getName();
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Employee employee = new Employee();
			
			Query getName = session.createSQLQuery("select name from employee1 where id = :id")
					.addScalar("name", StringType.INSTANCE)
					.setParameter("id", myModel.getId());
			
			List<Object[]> result = getName.getResultList();
			oldName = result.get(0) + "";
			
			employee.setId(myModel.getId());
			employee.setName(myModel.getName());
			employee.setCity(myModel.getCity());
			
			session.update(employee);
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return oldName + " to " + newName;
	}
}
