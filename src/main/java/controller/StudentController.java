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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/student")
public class StudentController extends HttpServlet {

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
