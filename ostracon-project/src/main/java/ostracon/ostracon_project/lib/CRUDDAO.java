package ostracon.ostracon_project.lib;

import java.io.Serializable;
import java.util.List;

public interface CRUDDAO 
{
	public <M> M retrieve(Class<M> model, Serializable id);
	public <M> M retrieve(Class<M> model, Serializable id, String ... eagerLoads);

	public <M> List<M> retrieveAll(Class<M> model);

	public <M> void create(M modelObject);

	public <M> M update(M modelObject);

	public <M> void delete(M model);
	
	public <M> void refresh(M model);
	
	public void flush();
}
