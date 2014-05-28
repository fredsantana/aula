package models.dto;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos_produtos")
public class PedidosProduto  {

    @Embeddable 
    public static class PK implements Serializable { 
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="pedido_id") 
        private Pedido pedido; 

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="produto_id") 
        private Produto produto; 

        /**
         * @return the produto
         */
        public Produto getProduto() {
            return produto;
        }

        /**
         * @param produto the produto to set
         */
        public void setProduto(Produto produto) {
            this.produto = produto;
        }

        /**
         * @return the pedido
         */
        public Pedido getPedido() {
            return pedido;
        }

        /**
         * @param pedido the pedido to set
         */
        public void setPedido(Pedido pedido) {
            this.pedido = pedido;
        }

    } 
    
    @Id
    private PK pk = new PK(); 	
    
    private int quantidade;
    private float total;

    /**
     * @return the pk
     */
    public PK getPk() {
        return pk;
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(PK pk) {
        this.pk = pk;
    }
    
    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }
    
}
