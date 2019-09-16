package com.iiiedu.eeit109.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.iiiedu.eeit109.entities.JSONFileUpload;
import com.iiiedu.eeit109.helpers.UploadHelper;

@WebServlet("/admin/InsertProductCkEditorServlet")
@MultipartConfig
public class InsertProductCkEditorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("upload")) {
            doPost_Upload(request,response);
        } else {
            if(action.equalsIgnoreCase("browsefile")) {
                doGet_BrowseFile(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet_BrowseFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        String basePath = applicationPath + File.separator + "imgUploadCK";
        File folder = new File(basePath);
        request.setAttribute("files", folder.listFiles());
        request.setAttribute("CKEditorFuncNum", request.getParameter("CKEditorFuncNum"));
        request.getRequestDispatcher("/admin/browsefile.jsp").forward(request, response);
    }
    
    protected void doPost_Upload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filename = UploadHelper.upload(request, response);
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(new JSONFileUpload("./imgUploadCK/"+filename)));
        out.flush();
        out.close();
    }

}
