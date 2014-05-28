/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generic;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

public abstract class GenericModel2<E> {

    protected abstract EntityManager getEntityManager();
    protected abstract Class<E> getEntityClass();
    

    public void save(E obj) throws Exception{
        getEntityManager().persist(obj);
    }
    
    public void update(E obj) throws Exception{
        getEntityManager().merge(obj);
    }
    
    public boolean delete(E obj) throws Exception{
        try{
            getEntityManager().remove(obj);
            return true;
        }catch(Exception ex){ 
            return false;
        }
    }
    
    public boolean delete(long id) throws Exception{
        try{
            E obj;
            obj = (E) getEntityManager().find(getEntityClass(), id);
            getEntityManager().remove(obj);
            return true;
        }catch(Exception ex){ 
            return false;
        }
    }
    
    public List<E> findNamedQueries(String nquery) {
        List<E> found =  getEntityManager().createNamedQuery(nquery).getResultList();
        return found;
    }
    
    public List<E> find(String query) {
        List<E> found =  getEntityManager().createQuery(query).getResultList();
        return found;
    }
    
    public List<E> findAll() {
        
        String table = "";
        
        Entity mdl = getEntityClass().getAnnotation(Entity.class);
            
        if(mdl.name().equals("")){
            table = getEntityClass().getSimpleName();
        }else{
            table = mdl.name();
        }
        
        List<E> found =  getEntityManager().createQuery("SELECT t FROM "+table+" t").getResultList();
        
        return found;
    }
    
    public E findById(Long id) {
        E found =  (E) getEntityManager().find(getEntityClass(), id);
        return found;
    }

}
