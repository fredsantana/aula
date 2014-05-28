package api;


import generic.GenericModel2;
import generic.GenericRest2;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import models.ProdutoDao;
import models.dto.Produto;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
public class Produtos extends GenericRest2<Produto> {

    @EJB
    ProdutoDao dao;

    @Override
    public GenericModel2 getModel() {
        return dao;
    }
    
    /*@Path("/delete/{id}")
    @DELETE
    //@TransactionAttribute(REQUIRED)
    public Response delete2(@PathParam("id") long id) {


        Produto c = (Produto) getModel().findById(id);
        
        System.out.println(c.getNome());
        
        if (c == null) {
            //Registro nao encontrado
            return Response.ok(Render.JSON(Result.SYSERROR("erro"))).type("application/json").build();
        }

        try {
            //dao.getEntityManager2().joinTransaction();
            dao.getEntityManager2().remove(c);
            //dao.getEntityManager2().flush();
            return Response.ok(Render.JSON(Result.OK("Cargo apagado com sucesso."))).type("application/json").build();
            /*if(dao.delete(c)){
                return Response.ok(Render.JSON(Result.OK("Cargo apagado com sucesso."))).type("application/json").build();
            }else{
                return Response.ok(Render.JSON(Result.SYSERROR("Não foi possivel exlcuir."))).type("application/json").build();
            }*/
        /*} catch (Exception ex) {
            ex.printStackTrace();
            return Response.ok(Render.JSON(Result.SYSERROR(ex+""))).type("application/json").build();
        }
    }*/

    @Override
    public Produto getDtoToSave(MultivaluedMap<String, String> form) {
        Produto c = new Produto();
        c.setNome(form.getFirst("produto.nome"));
        c.setDescricao(form.getFirst("produto.descricao"));
        c.setValor(Float.parseFloat(form.getFirst("produto.valor")));
        c.setQuantidade(Integer.parseInt(form.getFirst("produto.quantidade")));
        return c;
    }

    @Override
    public void setDtoToSave(Produto dto, MultivaluedMap<String, String> form) {
        dto.setNome(form.getFirst("produto.nome"));
        dto.setDescricao(form.getFirst("produto.descricao"));
        dto.setValor(Float.parseFloat(form.getFirst("produto.valor")));
        dto.setQuantidade(Integer.parseInt(form.getFirst("produto.quantidade")));
    }
}