/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import generic.GenericModel2;
import generic.GenericRest2;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import models.PedidoDao;
import models.ProdutoDao;
import models.dto.Pedido;
import models.dto.PedidosProduto;
import models.dto.Produto;
import utils.Result;
import utils.transform.Exclude;
import utils.transform.Render;


/**
 *
 * @author itakenami
 */
@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
public class Pedidos extends GenericRest2<Pedido> {

    @EJB
    PedidoDao dao;

    @Override
    public GenericModel2 getModel() {
        return dao;
    }

    @Override
    public Pedido getDtoToSave(MultivaluedMap<String, String> form) {
        
        Pedido p = new Pedido();
        p.setCpf(Long.parseLong(form.getFirst("pedido.cpf")));
        p.setNome(form.getFirst("pedido.nome"));
        p.setEnderecoEntrega(form.getFirst("pedido.enderecoEntrega"));
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        
        try {
            p.setDataPedido(sdf.parse(form.getFirst("pedido.dataPedido")));
        } catch (ParseException ex) {
            
        }
        
        Set<PedidosProduto> lista_itens = new LinkedHashSet<PedidosProduto>();        
        int index = 0;
        Set<String> lista = form.keySet();
        for (String param : lista) {
            if(param.indexOf("id[")>-1){
                PedidosProduto item = new PedidosProduto();
                Long pro_id = new Long(form.getFirst(String.format("pedido.produto.id[%d]",index)));
                int qtd_pro = Integer.parseInt(form.getFirst(String.format("pedido.produto.qtd[%d]",index)));
                Produto pro = new Produto();
                pro.setId(pro_id);
                item.setQuantidade(qtd_pro);            
                
                item.getPk().setProduto(pro);
                item.getPk().setPedido(p);
                lista_itens.add(item);
                index++;
            }
        }
        
        p.setItens(lista_itens);
        
        return p;
        
    }

    @Override
    public void setDtoToSave(Pedido dto, MultivaluedMap<String, String> form) {
        
        dto.setCpf(Long.parseLong(form.getFirst("pedido.cpf")));
        dto.setNome(form.getFirst("pedido.nome"));
        dto.setEnderecoEntrega(form.getFirst("pedido.enderecoEntrega"));
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        
        try {
            dto.setDataPedido(sdf.parse(form.getFirst("pedido.dataPedido")));
        } catch (ParseException ex) {
            
        }
        
        Set<PedidosProduto> lista_itens = new LinkedHashSet<PedidosProduto>();        
        int index = 0;
        Set<String> lista = form.keySet();
        for (String param : lista) {
            if(param.indexOf("id[")>-1){
                PedidosProduto item = new PedidosProduto();
                Long pro_id = new Long(form.getFirst(String.format("pedido.produto.id[%d]",index)));
                int qtd_pro = Integer.parseInt(form.getFirst(String.format("pedido.produto.qtd[%d]",index)));
                Produto pro = new Produto();
                pro.setId(pro_id);
                item.setQuantidade(qtd_pro);            
                
                item.getPk().setProduto(pro);
                item.getPk().setPedido(dto);
                lista_itens.add(item);
                index++;
            }
        }
        
        dto.setItens(lista_itens);
    }
    
    @Path("/")
    @GET
    @Override
    public Response listAll() {
        
        List<Pedido> found = dao.findAll();
        
        Exclude e = new Exclude().exclude("pedido");
        
        try {
            return Response.ok(Render.JSON(Result.OK(found),e)).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.ok(Render.JSON(Result.SYSERROR(ex.getMessage()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }
        
    }
    
    @Path("/")
    @POST
    @Override
    public Response save(MultivaluedMap<String, String> form) {
        
        Pedido obj = getDtoToSave(form);
        
        Exclude e = new Exclude().exclude("pedido");
        
        try {
            getModel().save(obj);
            return Response.ok(Render.JSON(Result.OK(e))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.ok(Render.JSON(Result.SYSERROR(ex.getMessage()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }
    }
    
}
