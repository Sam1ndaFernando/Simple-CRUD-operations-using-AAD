package controller;

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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo : Save Student
        if (!req.getContextPath().toLowerCase().startsWith("application/json") || req.getContentType()==null) {

            //send Error
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);


        }

        //todo : Save Student
        /*String id = UUID.randomUUID().toString();
        Jsonb jsonb = JsonbBuilder.create();
        studentDto studentDto = jsonb.fromJson(req.getReader(), studentDto.class);
        studentDto.setId(id);

        System.out.println(studentDto);*/


       //todo : Save Student with array type
        String id = UUID.randomUUID().toString();
        Jsonb jsonb = JsonbBuilder.create();
        List<StudentDto> studentList = jsonb.fromJson(req.getReader(), new ArrayList<StudentDto>() {}.getClass().getGenericSuperclass());
        studentList.forEach(System.out::println);


        /*BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        var writer = resp.getWriter();


        reader.lines().forEach(line-> stringBuilder.append(line+"\n"));

        System.out.println(stringBuilder);
        writer.write(stringBuilder.toString());
        writer.close();*/

        //JSON manipulate with Parson

      /*  JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        System.out.println(jsonObject.getString("email"));*/

        JsonReader reader = Json.createReader(req.getReader());
        JsonArray jsonValues = reader.readArray();
        for (int i = 0; i < jsonValues.size(); i++) {

            JsonObject jsonObject = jsonValues.getJsonObject(i);
            System.out.println(jsonObject.getString("name"));

            var writer = resp.getWriter();
            writer.write(toString());
        }
    }
}
