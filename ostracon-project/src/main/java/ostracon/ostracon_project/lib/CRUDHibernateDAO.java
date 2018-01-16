package ostracon.ostracon_project.lib;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

@Repository
public class CRUDHibernateDAO implements CRUDDAO 
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public <M> M retrieve(final Class<M> model, final Serializable id)
	{
		return retrieve(model, id, (String[]) null);
	}
	
	@Override
	public <M> M retrieve(final Class<M> model, final Serializable id, String ... eagerLoads)
	{
		try
		{
			M entity = entityManager.getReference(model, id);	
			return entity;
		}
		catch (EntityNotFoundException e)
		{
			throw e;
		}
	}

	@Override
	public <M> void refresh(M model)
	{
		entityManager.refresh(model);
	}
	
	@Override
	public <M> void create(M modelObject)
	{
		try
		{
			entityManager.persist(modelObject);
		}
		catch(RuntimeException e)
		{
			throw e;
		}
	}
	
	@Override
	public <M> M update(M modelObject)
	{
		try
		{
			return entityManager.merge(modelObject);
		}
		catch(RuntimeException e)
		{
			throw e;
		}
	}
	
	@Override
	public <M> List<M> retrieveAll(Class<M> model) 
	{
		try
		{
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<M> query = criteriaBuilder.createQuery(model);
			Root<M> root = query.from(model);
			query.select(root);
			List<M> resultList = entityManager.createQuery(query).getResultList();
			return resultList;
		}
		catch (HibernateException e)
		{
			throw e;
		}
	}
	
	@Override
	public <M> void delete(M model)
	{
		try{
			M todelete = entityManager.contains(model) ? model : entityManager.merge(model);
			entityManager.remove(todelete);
		}
		catch(HibernateException e){
			throw e;
		}
	}

	@Override
	public void flush()
	{
		entityManager.flush();
	}
}
