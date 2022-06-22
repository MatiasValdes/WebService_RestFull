package com.ryc.restful.ws_restful.resource;

import com.ryc.restful.ws_restful.annotation.Secured;
import com.ryc.restful.ws_restful.modelo.Employees;
import com.ryc.restful.ws_restful.modelo.Navegation;
import com.ryc.restful.ws_restful.dao.EmployeesDAO;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author RyC
 */
@Path("/employees")
//@Produces(MediaType.APPLICATION_JSON) --> De esta forma se deja la etiqueta global
//@Consumes(MediaType.APPLICATION_JSON) --> De esta forma se deja la etiqueta global
public class EmployeesResource {
    
    EmployeesDAO employeesDAO = null;
    
    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employees employee, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        employeesDAO = new EmployeesDAO();
        Employees emp = employeesDAO.addEmploye(employee);
        
        /**
         * Informacion de localizacion del recurso 
         * Response.created(located)
         * located = URI
         */
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(emp.getId())).build();
        return Response.created(uri)
                //.entity(emp)
                .build();
        
       /*return Response.status(Response.Status.CREATED)
                .entity(emp)
                .build();*/
        
        //return employeesDAO.addEmploye(employee);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    /**
     * Se agrega QueryParam como filtro
     */
    public Response getEmployees(@QueryParam("department_id") int department_id, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        employeesDAO = new EmployeesDAO();
        
        List<Employees> emp = new ArrayList<Employees>();
        
        if(department_id > 0)
            emp = employeesDAO.getEmployeesByDepartmentID(department_id);
        else
            emp = employeesDAO.EmployeesList();
        
        GenericEntity entity = new GenericEntity<List<Employees>>(emp){};
        
        return Response.status(200)
                .entity(entity)
                .build();
        /*
        return Response.status(200)
                .entity(entity)
                .build();
        */
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getEmployeeById(@PathParam("id") int id, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        
        employeesDAO = new EmployeesDAO();
        Employees employee = employeesDAO.getEmployeeById(id);
        String link = uriInfo.getBaseUri().toString() + "jobs/" + employee.getJOB_ID();
        
        Navegation navegation = new Navegation("Job", link, "Locacion del recurso JOB");
        
        if(employee != null)
        {
            employee.getNavegation().add(navegation);
        }
        
        return Response.status(200)
                .entity(employee)
                .build();
    }
    
    @DELETE
    @Path("/{id}")
    @Secured
    public Response deleteEmployee(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        employeesDAO = new EmployeesDAO();
        boolean status = false;
        
        status = employeesDAO.deleteEmployee(id);
        
        if(!status)
            return Response.status(404)
                .build();
            
        return Response.status(204)
                .build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response updateEmployee(@PathParam("id") int id, Employees employee, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        employeesDAO = new EmployeesDAO();
        Employees emp;
        
        emp = employeesDAO.getEmployeeById(id);
        if (emp == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            employeesDAO.updateEmployee(employee, id);
            
            URI uri = uriInfo.getAbsolutePath();
            
            return Response.status(204)
                .contentLocation(uri)
                //.entity(emp)
                .build();
        }
        
        //emp = employeesDAO.updateEmployee(employee, id);
        
        /*return Response.status(204)
                .contentLocation(uri)
                //.entity(emp)
                .build();*/
    }

}
