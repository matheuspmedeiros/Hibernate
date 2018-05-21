package br.com.execHiber.persistence;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
//import br.edu.etec.lojainformatica.model.Cliente;

import br.com.execHiber.model.Cliente2;

public class ClienteHibernateDAO<T> implements IDAO<T> {
	Logger logger = Logger.getLogger(ClienteHibernateDAO.class.getName());
	private Transaction currentTransaction;
	Session session;

	public ClienteHibernateDAO(Session session) {
		this.session = session;
	}

	public int encontrarPeloNome(String nome) {
		logger.info("public int encontrarPeloNome(String nome) {...");
		CriteriaBuilder cB = this.session.getCriteriaBuilder();
		CriteriaQuery<Cliente2> qry = cB.createQuery(Cliente2.class);
		Root<Cliente2> from = qry.from(Cliente2.class);
		qry.select(from);
		qry.where(cB.and(cB.equal(from.get("nome"), nome)// ,cB.equal(from.get("OutroCampo"), vlrParaProcurar)
		));
		Query<Cliente2> createdQuery = session.createQuery(qry);
		List<Cliente2> resultList = createdQuery.getResultList();
		return resultList.size();
	}

	public void persistir(T o) {
		session.save(o);
	}

	public void excluiTodos() {
		CriteriaBuilder cB = this.session.getCriteriaBuilder();
		CriteriaDelete<Cliente2> qryDelete = cB.createCriteriaDelete(Cliente2.class);
		Root<Cliente2> deleteFrom = qryDelete.from(Cliente2.class);
		this.session.createQuery(qryDelete).executeUpdate();
	}

	public List<T> Listar() {
		CriteriaBuilder cB = this.session.getCriteriaBuilder();
		CriteriaQuery<Cliente2> qry = cB.createQuery(Cliente2.class);
		qry.select(qry.from(Cliente2.class));
		Query<T> createdQuery = (Query<T>) this.session.createQuery(qry);
		return createdQuery.getResultList();
	}

	public void alterar(T e) {
		this.session.merge(e);
	}

	public void excluir(Integer id) {
		this.session.remove(id);
	}

	public T encontrarPeloId(Integer id) {
		return (T) this.session.byId(Cliente2.class).load(id);
	}

	public void closeSession() {
		this.session.close();
	}

	public void beginTransaction() {
		this.currentTransaction = this.session.beginTransaction();
	}

	public void commit() {
		this.currentTransaction.commit();
	}
}