package ru.itis.logistic_service.servlets;

import java.io.File;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.itis.logistic_service.models.User;
import ru.itis.logistic_service.services.CompanyService;
import ru.itis.logistic_service.services.UsersService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@WebServlet("/uploadAvatar")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,    // 2MB
//        maxFileSize = 1024 * 1024 * 10,        // 10MB
//        maxRequestSize = 1024 * 1024 * 50)    // 50MB
public class UploadAvatarServlet extends HttpServlet {

    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */

    private UsersService usersService;

    //    private static final String SAVE_DIRECTORY = "uploadFiles";
    private static final String SAVE_DIRECTORY = "C:" + File.separator + "Users" + File.separator + "Nadin" + File.separator
            + "Desktop" + File.separator + "logisticServiceUploadFiles" + File.separator + "avatars";

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
    }

    private Random random = new Random();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User profileUser = null;

        String cookie = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("auth")) {
                    if (usersService.isExistByCookie(cookies[i].getValue())) {

                        cookie = cookies[i].getValue();

                        profileUser = usersService.getUserByUUID(cookies[i].getValue());
                        System.out.println("Change photo " + profileUser.toString());

                        break;

                    } else {

                        System.out.println("Если нет куки auth, значит не заходил");
                        response.sendRedirect("/signIn");
                        return;

                    }
                }
            }
        }

        //проверяем является ли полученный запрос multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Создаём класс фабрику
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем два мегабайт
        factory.setSizeThreshold(1024 * 1024 * 2);

        String appPath = request.getServletContext().getRealPath("");
        System.out.println(appPath);
        // устанавливаем временную директорию
//        String tempDir =  appPath + SAVE_DIRECTORY ;
//        System.out.println(tempDir);
        System.out.println(SAVE_DIRECTORY);
        File fileSaveDir = new File(SAVE_DIRECTORY);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        factory.setRepository(fileSaveDir);

        //Создаём сам загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);

        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {

                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //если принимаемая часть данных является полем формы
                    processFormField(item);
                } else {
                    //в противном случае рассматриваем как файл
                    assert profileUser != null;
                    processUploadedFile(item, SAVE_DIRECTORY, profileUser.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.sendRedirect("/profile");

    }

    /**
     * Сохраняет файл на сервере, в папке uploadFiles.
     * Сама папка должна быть уже создана.
     *
     * @param item
     * @throws Exception
     */

    private void processUploadedFile(FileItem item, String dir, Long userId) throws Exception {

        File uploadedFile = null;
//        String path;
        //выбираем файлу имя пока не найдём свободное
        System.out.println(item.getName());
        String path = "";
        String fileName = random.nextInt() + item.getName();
        do {
//            path = getServletContext().getRealPath("/uploadFiles/" + random.nextInt() + item.getName());
            path = dir + File.separator + fileName;
            System.out.println(path);
//            System.out.println(getServletContext().getRealPath("/uploadFiles/"));
//            System.out.println(path);
            uploadedFile = new File(path);
        } while (uploadedFile.exists());

        //создаём файл
        uploadedFile.createNewFile();
        //записываем в него данные
        item.write(uploadedFile);

        if (!path.equals("")) {
            System.out.println(path + "  путь для бд");
            usersService.setProfileImage(path, userId);
        }

    }

    /**
     * Выводит на консоль имя параметра и значение
     */

    private void processFormField(FileItem item) {
        System.out.println(item.getFieldName() + "=" + item.getString());
    }

}
