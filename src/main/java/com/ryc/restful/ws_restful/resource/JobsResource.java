package com.ryc.restful.ws_restful.resource;

import com.ryc.restful.ws_restful.modelo.Jobs;
import com.ryc.restful.ws_restful.dao.JobsDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author RyC
 */
@Path("/jobs")
//@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JobsResource {
    
    JobsDAO jobDAO = null;
    
    @GET
    public List<Jobs> getJobs() throws SQLException, ClassNotFoundException {
        System.out.println("Method getJobs()");
        jobDAO = new JobsDAO();
        
        return jobDAO.JobsList();
    }
    
    @GET
    @Path("/{id}")
    public Jobs getJobById(@PathParam("id") String id) throws SQLException, ClassNotFoundException {
        System.out.println("Method getJobById( "+ id +" )");
        jobDAO = new JobsDAO();
        
        return jobDAO.getJobById(id);
    }
    
}
