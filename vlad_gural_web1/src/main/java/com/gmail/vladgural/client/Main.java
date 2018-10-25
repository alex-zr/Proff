package com.gmail.vladgural.client;

import com.gmail.vladgural.controller.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {

            // Запросс на ввод догина и пароля
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();

            System.out.println("Enter your password: ");
            String passWord = scanner.nextLine();


            //Проверка на доступ про помощи
            //сервлета LogPassServlet
            URL url = new URL(Utils.getURL() + "/logpass?login="+ login +
                                "&password=" + passWord);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            InputStream is = http.getInputStream();
            byte buf[];
            while(true) {
                buf = requestBodyToArray(is);
                if(buf.length!=0)
                    break;
            }
            is.close();

            String logResponse = new String(buf, StandardCharsets.UTF_8);
            System.out.println(logResponse);
            if(logResponse.equals("Access is denied"))
                return;

            //Запрос на вывод зарегестрированных пользователей
            //скрвлет UsersListServlet
            System.out.println("do you want display user list (y/n)");
            if(scanner.nextLine().equals("y")) {
                url = new URL(Utils.getURL() + "/userslist");
                http = (HttpURLConnection) url.openConnection();
                is = http.getInputStream();

                while(true) {
                    buf = requestBodyToArray(is);
                    if(buf.length!=0)
                        break;
                }
                String strBuf = new String(buf, StandardCharsets.UTF_8);
                Gson gson = new GsonBuilder().create();
                Users users = gson.fromJson(strBuf,Users.class);

                System.out.println("List users");
                for(String str:users.getList()){
                    System.out.println(str);
                }
                is.close();
            }


            //Запуск потока на вывод сдержимого чата
            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            //Запрос на ввод сообщения и передача его на сервер
            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;

                Message m = new Message(login, text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}