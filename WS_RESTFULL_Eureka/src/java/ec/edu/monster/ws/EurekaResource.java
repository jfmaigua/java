/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ec.edu.monster.ws;

import ec.edu.monster.modelo.Movimiento; 
import ec.edu.monster.servicio.EurekaService; 
import java.util.ArrayList; 
import java.util.List; 
import javax.ws.rs.core.Context; 
import javax.ws.rs.core.UriInfo; 
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes; 
import javax.ws.rs.FormParam; 
import javax.ws.rs.GET; 
import javax.ws.rs.POST; 
import javax.ws.rs.Path; 
import javax.ws.rs.PUT; 
import javax.ws.rs.PathParam; 
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.Response; 

/**
 * REST Web Service
 *
 * @author elian
 */
@Path("generic")
public class EurekaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public EurekaResource() {
    }

    /**
     * Retrieves representation of an instance of ec.edu.monster.ws.GenericResource
     * @return an instance of java.lang.String
     */
    
    @GET 
    @Path("/movimientos/{cuenta}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getMovimientos(@PathParam("cuenta") String cuenta) { 
        List<Movimiento> lista; 
        //proceso 
        try { 
            //recuperar movimientos 
            EurekaService service = new EurekaService(); 
            lista = service.leerMovimientos(cuenta); 
        } catch (Exception e) { 
            //en caso de error, retorne una lista vacia 
            lista = new ArrayList<>();
        } 
        //retorno 
        return Response.ok(lista).build(); 
    }

    @POST 
    @Path("/deposito") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Response registrarDeposito(@FormParam("cuenta") String cuenta, @FormParam("importe") double importe) { 
        int estado; 
        //proceso 
        String codEmp = "0001"; 
        try { 
            EurekaService service = new EurekaService(); 
            service.registrarDeposito(cuenta, importe, codEmp); 
            estado = 1; 
        } catch (Exception e) { 
            estado = -1; 
        } 
        //retorno 
        return Response.ok(estado).build(); 
    } 
}
