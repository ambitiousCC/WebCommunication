package web.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public final class MailUtils {
    private static final String USER = "2146639830@qq.com"; // 发件人称号，同邮箱地址
    private static final String PASSWORD = "szjqmygpcptidjej"; // 如果是qq邮箱可以使户端授权码，或者登录密码

    /**
     *
     * @param to 收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     */
    /* 发送验证信息的邮件 */
    public static boolean sendMail(String user,String to, String text, String title){
        String content =
                        "Email 地址验证" +'\n'+
                        "尊敬的用户："+user+"，\n" +
                        "这封信是由 某站·小奏社区 发送的。\n" +
                        "\n" +
                        "您收到这封邮件，是由于在 某站·小奏社区 进行了新用户注册，或用户修改 Email 使用 了这个邮箱地址。如果您并没有访问过 ，或没有进行上述操作，请忽略这封邮件。您不需要退订或进行其他进一步的操作。、\n" +
                        "\n" +
                        "----------------------------------------------------------------------\n" +
                        "帐号激活说明\n" +
                        "----------------------------------------------------------------------\n" +
                        "\n" +
                        "如果您是 某站·小奏社区 的新用户，或在修改您的注册 Email 时使用了本地址，我们需 要对您的地址有效性进行验证以避免垃圾邮件或地址被滥用。\n" +
                        "\n" +
                        "您只需点击下面的链接即可激活您的帐号：\n" +
                        "\n" +
                         text+
                        "\n" +
                        "(如果上面不是链接形式，请将该地址手工粘贴到浏览器地址栏再访问)\n" +
                        "\n" +
                        "建议将此邮件收藏，以备找回密码使用，感谢您的访问，祝您使用愉快！\n" +
                        "\n" +
                        "此致";
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception { // 做测试用
        MailUtils.sendMail("名山小奏","1297571059@qq.com","你好，这是一封测试邮件，无需回复。","测试邮件");
        System.out.println("发送成功");
    }
}
