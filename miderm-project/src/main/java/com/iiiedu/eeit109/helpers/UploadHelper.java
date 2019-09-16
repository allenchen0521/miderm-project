package com.iiiedu.eeit109.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UploadHelper {
    public static String upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        Part part = request.getPart("upload");
        String header = part.getHeader("Content-Disposition");
        System.out.println(header);

        String filename = null;
        if (header.lastIndexOf("\\") != -1) {
            filename = header.substring(header.lastIndexOf("\\"), header.lastIndexOf("\""));
            System.out.println("filename:" + filename);
        } else {
            filename = header.substring(header.indexOf("filename=\"") + 10, header.length() - 1);
            System.out.println("filename:" + filename);
        }

        InputStream in = part.getInputStream();
        String applicationPath = request.getServletContext().getRealPath("");
        System.out.println("applicationPath:" + applicationPath);
        String basePath = applicationPath + File.separator + "imgUploadCK" + File.separator;
        System.out.println("basePath:" + basePath);
        File outputFilePath = new File(basePath + filename);
        OutputStream output = new FileOutputStream(outputFilePath);
        byte[] buff = new byte[1024];
        int length;
        while ((length = in.read(buff)) != -1) {
            output.write(buff, 0, length);
        }
        output.close();
        in.close();
        return filename;
    }
}
