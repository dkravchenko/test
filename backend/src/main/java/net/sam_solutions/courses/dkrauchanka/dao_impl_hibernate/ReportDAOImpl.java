package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sam_solutions.courses.dkrauchanka.dao.ReportDAO;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import net.sam_solutions.courses.dkrauchanka.domain.User;
import net.sam_solutions.courses.dkrauchanka.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReportDAOImpl implements ReportDAO{
        public static final Logger log = Logger.getLogger(ReportDAOImpl.class);
	private SessionFactory sessionFactory;
	
	public ReportDAOImpl(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void addReport(Report report) {
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(report);
                transaction.commit();
		session.close();
	}

	public List<Report> listReport(int page, int count) {
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		Query query = (Query)session.createQuery("from Report");
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
                List<Report> list = query.list();
                transaction.commit();
		session.close();
		return list;
	}
        
        public List<Report> listReportByUser(int page, int count, String login){
            Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                UserDAOImpl userDao = new UserDAOImpl();
                User user = userDao.getUser(login);
		Query query = (Query)session.createQuery("from Report where user=:user").setParameter("user", user);
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
                List<Report> list = query.list();
                transaction.commit();
		session.close();
		return list;
        }
        
        public List<Report> listReportByDate(int page, int count, String date){
            Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dat = null;
                try{
                    dat = formatter.parse(date);
                } catch(ParseException e){
                   log.error("Cannot parse date");
                }
		Query query = (Query)session.createQuery("SELECR r from Report r where cast(r.dateOfReport as date)=:date").setDate("date", dat);
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
                List<Report> list = query.list();
                transaction.commit();
		session.close();
		return list;
        }
        
        public Long countReport(){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select count(*) from Report");
            Long temp = (Long) query.uniqueResult();
            transaction.commit();
            session.close();
            return temp;
        }
        
        public Long countReportByUser(String login){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDAOImpl userDao = new UserDAOImpl();
            User user = userDao.getUser(login);
            Query query = session.createQuery("select count(*) from Report where user=:user").setParameter("user", user);
            Long temp = (Long) query.uniqueResult();
            transaction.commit();
            session.close();
            return temp;
        }
	
	public Report getReport(Integer id){
		Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
		Report report = (Report)session.get(Report.class,id);
                transaction.commit();
		session.close();
		return report;
	}

	public void removeReport(Report report) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(report);
		transaction.commit();
		session.close();
	}
}
