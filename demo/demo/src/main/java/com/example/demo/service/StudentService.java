package com.example.demo.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.example.demo.hibernate.UtilRest;
import com.example.demo.modelper.Student;

@Service
public class StudentService {
	
	public Student create(Student student) {
		
		Transaction transaction = null;
		try(Session session = UtilRest.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
			
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}
	
	public Student getFromDatabase(int studentId) {
		
		Transaction  transaction = null;
		Student student = null;
		try(Session session = UtilRest.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			student =session.byId(Student.class).getReference(studentId);
			transaction.commit();
			
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;		
	}
	public Student update(int id, Student update) {
		Transaction transaction = null;
		Student student = null;
		try(Session session = UtilRest.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			student = session.byId(Student.class).getReference(id);
			if(update.getFirstName() != null) {
				student.setFirstName(update.getFirstName());
			}
			if(update.getLastName() !=null) {
				student.setLastName(update.getLastName());			
			}
			if(update.getEmail() != null) {
				student.setEmail(update.getEmail());			
			}
			session.update(student);
			transaction.commit();
		}catch(Exception e) {
			if(transaction !=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}
	public void delete(int id) {
		Transaction transaction = null;
		Student student = null;
		try (Session session = UtilRest.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			student = session.byId(Student.class).getReference(id);
			session.delete(student);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
		
}
