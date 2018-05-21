package br.com.execHiber;

import br.com.execHiber.persistence.HibernateUtil;

import javax.swing.JMenuItem;
import org.hibernate.Session;

import br.com.execHiber.model.Cliente2;
import br.com.execHiber.persistence.ClienteHibernateDAO;
import br.com.execHiber.persistence.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Cliente2 c1 = new Cliente2();
		c1.setNome("Huguinho");
		c1.setEndereco("End do Huguinho");
		c1.setFone("1234");
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			ClienteHibernateDAO<Cliente2> clienteDao = new ClienteHibernateDAO<Cliente2>(session);
		
		clienteDao.beginTransaction();
		clienteDao.persistir(c1);
		clienteDao.commit();
		clienteDao.closeSession();
	}
}
