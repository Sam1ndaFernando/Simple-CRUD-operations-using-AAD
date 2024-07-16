package controller;

import dao.StudentDataProcess;
import dto.StudentDto;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UtilProcess;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/student"
//        initParams = {
//          @WebInitParam(name = "driver-class",value = "com.mysql.cj.jdbc.Driver"),
//          @WebInitParam(name = "dbURL",value = "jdbc:mysql://localhost:3306/aad67JavaEE?createDatabaseIfNotExist=true"),
//          @WebInitParam(name = "dbUserName",value = "root"),
//          @WebInitParam(name = "dbPassword",value = "mysql"),
//        }
)
public class StudentController extends HttpServlet {
    Connection connection;
    static String SAVE_STUDENT = "INSERT INTO student (id,name,city,email,level) VALUES (?,?,?,?,?)";
    static String GET_STUDENT = "SELECT * FROM student WHERE id=?";
    static String UPDATE_STUDENT = "UPDATE student SET name=?,city=?,email=?,level=? WHERE id=?";
    static String DELETE_STUDENT = "DELETE FROM student WHERE id=?";

    @Override
    public void init() throws ServletException {
        try{
            var driverClass = getServletContext().getInitParameter("driver-class");
            var dbUrl = getServletContext().getInitParameter("dbURL");
            var userName = getServletContext().getInitParameter("dbUserName");
            var password = getServletContext().getInitParameter("dbPassword");
            // Get configs from servlet
//            var driverCalss = getServletConfig().getInitParameter("driver-class");
//            var dbUrl = getServletConfig().getInitParameter("dbURL");
//            var userName = getServletConfig().getInitParameter("dbUserName");
//            var password = getServletConfig().getInitParameter("dbPassword");
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var studentId = req.getParameter("id");
        var dataProcess = new StudentDataProcess();
        try(var Writer=resp.getWriter()){
            var student = dataProcess.getStudent(studentId, connection);
            System.out.println(student);
            resp.setContentType("application/json");
            var jsonb = JsonbBuilder.create();
            jsonb.toJson(student, Writer);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //send error
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType()==null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        // Persist Data
        try(var writer =resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            StudentDto studentDto = jsonb.fromJson(req.getReader(), StudentDto.class);
            studentDto.setId(UtilProcess.ganarateId());
            var saveData = new StudentDataProcess();
            if (saveData.saveStudent(studentDto,connection)) {
                writer.write("Data saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                writer.write("Data not saved");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPatch(req, resp);
    }
}
