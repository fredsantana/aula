/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import generic.GenericModel2;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.dto.Produto;

/**
 *
 * @author itakenami
 */
@Stateless
public class ProdutoDao extends GenericModel2<Produto> {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<Produto> getEntityClass() {
        return Produto.class;
    }
    
    public void BaixarEstoque(long id, int qtd) throws Exception{

        Produto pro = this.findById(id);
        
        if(pro.getQuantidade() < qtd)
            throw new Exception(String.format("Produto %s com estoque insuficiente. Estoque disponível: %d", pro.getNome(), pro.getQuantidade()));
        
        pro.setQuantidade(pro.getQuantidade() - qtd);
        
        this.update(pro);
    }
    
}
