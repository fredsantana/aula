package models.dto;


import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "produtos")
@XmlRootElement(name = "produto")
public class Produto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nome;
    private String descricao;
    private float valor;
    private int quantidade;
    
    /*@OneToMany(mappedBy = "produto")
    private Set<PedidoProduto> pedidoProdutos;*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
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
     * @return the pedidoProdutos
     */
    /*public Set<PedidoProduto> getPedidoProdutos() {
        return pedidoProdutos;
    }*/

    /**
     * @param pedidoProdutos the pedidoProdutos to set
     */
    /*public void setPedidoProdutos(Set<PedidoProduto> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }*/
    
    
}
