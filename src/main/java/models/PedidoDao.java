/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import generic.GenericModel2;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.dto.Pedido;
import models.dto.PedidosProduto;
import models.dto.Produto;

/**
 *
 * @author itakenami
 */
@Stateless
public class PedidoDao extends GenericModel2<Pedido> {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private ProdutoDao dao_produto;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<Pedido> getEntityClass() {
        return Pedido.class;
    }
    
    @Override
    public void save(Pedido obj) throws Exception{
        try{
            
            float total=0;
            
            for(PedidosProduto itens : obj.getItens()){
                int qtd = itens.getQuantidade();
                long id = itens.getPk().getProduto().getId();
                Produto produto = dao_produto.findById(id);
                
                dao_produto.BaixarEstoque(id, qtd);
                
                itens.setTotal(produto.getValor()*qtd);
                
                total += itens.getTotal();
            }
            obj.setTotal(total);
            super.save(obj);
        }
        catch(Exception ex){
            throw ex;
        }
    }
    
}
