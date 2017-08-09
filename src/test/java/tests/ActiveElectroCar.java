package tests;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;



public class ActiveElectroCar {
    String dateNow;
    String dateYeaterday;

    public static final String url = "jdbc:mysql://ovirt.ria.com:3316/";
    public static final String dbName = "seo";
    public static final String mysqldriver = "com.mysql.jdbc.Driver";
    public static final String user = "msks";
    public static final String pass = "gtnhjdbx";
    public static Connection connection;
    String LogFileFolder = System.getProperty("user.dir") + "/src/test/java/main/resources/";



    String newJson;
    String phone ;



    @BeforeClass
    public void GetConnectionToDataBase() throws Exception {

        try {
            Class.forName(mysqldriver).newInstance();
            connection = DriverManager.getConnection(url + dbName, user, pass);

            System.out.println("CONNECTING TO DATABASE");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test(priority = 1)
    public void getDataId() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy")-1;
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
//        String date = sdf.format(new Date());
////        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//        System.out.println(timeStamp ); //15/10/2013
    }


    @Test(priority = 2)
    public void test1_seo_mysql() throws Exception {
        FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/src/test/java/main/resources/sql.csv");

        writer.append("id пользователя");
        writer.append(',');
        writer.append(" id автомобиля");
        writer.append(',');
        writer.append("Тип топлива");
        writer.append(',');
        writer.append("Создано/переопубликаовано");
        writer.append(',');
        writer.append("Описание объявления");
        writer.append(',');
        writer.append("Название компании");
        writer.append(',');
        writer.append("Описание компании");
        writer.append(',');
        writer.append("Номер телефона");
        writer.append(',');
        writer.append("Ссылка на объявление");
        writer.append('\n');

        getYesterdayDateString();
        dateNow();
        System.out.println(dateNow);
        System.out.println(dateYeaterday);

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select a.user_id, a.auto_id, a.fuel_id, os.created_at, " +
                "a.description as 'Desc_ads',\n" +
                "COALESCE (c.name, ca.name) as 'Name_company',\n" +
                "COALESCE (c.description, ca.description) as 'Desc_company' from auto3.auto a\n" +
                "inner JOIN auto3.order_services os USING (user_id)\n" +
                "inner join auto3.companies c using (user_id)\n" +
                "inner join auto4_cars.autosalons ca using (user_id)\n" +
                "WHERE os.service_id = 237 AND os.created_at BETWEEN '"+dateYeaterday+" 10:00:00' AND '"+dateNow+" 10:00:00'\n" +
                "and a.fuel_id in (6) and a.status_id=0 and a.category_id=1\n" +
                "group by a.auto_id\n" +
                "order by a.user_id;");
        while (rs.next()) {
            String user_id = rs.getString("user_id");
            String auto_id = rs.getString("auto_id");
            String fuel_id = rs.getString("fuel_id");
            String created_at = rs.getString("created_at");
            String description1 = rs.getString("Desc_ads").replaceAll(",",";").replaceAll("\n"," ");
            String description2 = rs.getString("Name_company");
            String description3 = rs.getString("Desc_company");

//            FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/src/test/java/main/resources/sql.csv");
            writer.append(user_id);
            writer.append(',');
            writer.append(auto_id);
            writer.append(',');
            writer.append(fuel_id);
            writer.append(',');
            writer.append(created_at);
            writer.append(',');
            writer.append("-");
            writer.append(',');
            writer.append(description2);
            writer.append(',');
            writer.append("-");
            writer.append(',');
            getJsonFinalPage(auto_id);
            writer.append(phone);
            writer.append(',');
            writer.append("https://auto.ria.com/auto___" + auto_id + ".html");
            writer.append('\n');

//            String description = rs.getString("Описание объявления");
//            String auto_id = rs.getString("auto_id");
//            String auto_id = rs.getString("auto_id");
//            String auto_id = rs.getString("auto_id");

//            String text = ""+user_id+"  "+auto_id+" "+fuel_id+" "+created_at+"";
//
//            System.out.print(user_id + "\n");
//            System.out.print(auto_id + "\n");
//            writeFile(LogFileFolder+"/sql.csv", text);
        }

        writer.flush();
        writer.close();
    }

    @Test(priority = 3, enabled = false)
    public static void sendEmail()throws Exception{

        try{
            final String fromEmail = "iryna.melnik@ria.com"; //requires valid gmail id
            final String password = "vtkmybr1991"; // correct password for gmail id
//                final String toEmail = "oleksandr.voynarovskyy@ria.com"; // can be any email id

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("iryna.melnik@ria.com"));
            message.setSubject("Beacon alert!!!");
            message.setFileName(System.getProperty("user.dir") + "/src/test/java/main/resources/test.csv");
            Transport.send(message);
            System.out.println("Mail Sent to  iryna.melnik@ria.com ");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void writeFile(String dir, String text) throws Exception {
        try (FileWriter writer = new FileWriter(dir, true)) {
            // запись всей строки
            writer.write(text + "\n");

            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }


    @AfterClass
    public void CloseConnectionToDataBase() {

        try {
            connection.close();
            System.out.println("CONNECTING DATABASE CLOSE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getJsonFinalPage(String id) throws Exception {
        final int subLenFirst = 6;
        final int subLenSecond = 4;
        final int scaleFirst = 100;
        final int divSecond = 10000;

        String idFirst = id;
        int idSecondInt = Integer.parseInt(id.substring(0, subLenFirst))
                + roundString(id.substring(subLenFirst), scaleFirst);
        String idSecond = Integer.toString(idSecondInt);

        int ThreeInt = Integer.parseInt(id.substring(0, subLenSecond))
                + roundString(id.substring(subLenSecond), divSecond);
        String idThree = Integer.toString(ThreeInt);

        newJson = "https://c-ua1.riastatic.com/demo/bu/searchPage/v2/view/auto/" + idThree + "/" + idSecond + "/" + id + "?lang_id=2";

        JSONObject forPhone = readJsonFromUrl(newJson);
        phone = forPhone.getJSONObject("userPhoneData").getString("phone");

    }

    public int roundString(String string, int scale) {
        return Math.round(Float.parseFloat(string) / scale);
    }

//    public void getPtoneNumber(){
//        JSONObject forPhone = readJsonFromUrl()
//    }


    /**
     * JSON
     */

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (Exception e) {
            JSONObject json = new JSONObject();
            return json;
        } finally {
            is.close();
        }
    }

/**get date**/
    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateYeaterday =dateFormat.format(yesterday());
        System.out.println(dateYeaterday);
        return dateFormat.format(yesterday());
    }

    public void dateNow(){
        dateNow = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        System.out.println(dateNow);
    }



}
