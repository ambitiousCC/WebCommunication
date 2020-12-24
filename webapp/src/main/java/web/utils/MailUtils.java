package web.utils;

import beans.User;
import beans.Visitor;
import org.apache.commons.lang.RandomStringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public final class MailUtils {
    private final String USER = "2146639830@qq.com"; // 发件人称号，同邮箱地址
    private final String PASSWORD = "szjqmygpcptidjej"; // 如果是qq邮箱可以使户端授权码，或者登录密码
    private final String mainUrl = "http://localhost:8082/main"; //主页
    private final String signUrl = "http://localhost:8082/login"; //登录
    private final String globalUrl = "http://localhost:8082/global"; //文章
    private final String adUrl = "http://localhost:8082/login"; //广告说明
    /**
     * @param user 收件人
     * @param url 激活链接
     */
    /* 发送验证信息的邮件 */
    public String mailOfActive(User user, String url) {
        String content = "<div id=\"qm_con_body\">\n" +
                "    <div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\">\n" +
                "        <div style=\"width:100%!important;font-family:Helvetica,Arial,sans-serif;background-color:#ececec;margin:0;padding:0\"\n" +
                "            bgcolor=\"#ececec\">\n" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#ececec\"\n" +
                "                bgcolor=\"#ececec\">\n" +
                "                <tbody>\n" +
                "                    <tr style=\"border-collapse:collapse\">\n" +
                "                        <td align=\"center\" bgcolor=\"#f9f6f6\"\n" +
                "                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                            <span style=\"font-size: 0;display: none;\">请尽快完成账号相关验证，如果该操作不是你触发的，请忽略。</span>\n" +
                "                            <table style=\"margin: 20px 10px;border: 1px solid #e5e5e5;border-radius: 5px;\" width=\"640\"\n" +
                "                                cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "                                <tbody>\n" +
                "                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                        <td width=\"640\"\n" +
                "                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                            <table width=\"640\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
                "                                                bgcolor=\"#FFFFFF\"\n" +
                "                                                style=\"background-color: #f7f7f7;border-bottom: 1px solid #e5e5e5;\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                        <td width=\"15\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"350\" valign=\"middle\" align=\"left\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                            <table width=\"350\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                                        <td width=\"350\" height=\"8\"\n" +
                "                                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif;line-height:8px;\">\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "                                                            <div style=\"font-size:12px;color:#33ae81\">\n" +
                "                                                                <a href=\"" + signUrl + "\"\n" +
                "                                                                    style=\"font-weight:bold;color:#333;text-decoration:none\"\n" +
                "                                                                    target=\"_blank\" rel=\"noopener\">社区 • 账号</a>\n" +
                "                                                            </div>\n" +
                "                                                            <table width=\"350\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                                        <td width=\"350\" height=\"8\"\n" +
                "                                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif;line-height:8px;\">\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"255\" valign=\"middle\" align=\"right\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                            <table width=\"255\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                                        <td width=\"255\" height=\"8\"\n" +
                "                                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif;line-height:8px;\">\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "                                                            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                                        <td valign=\"middle\"\n" +
                "                                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                                            <div style=\"font-size:12px;color:#33ae81\"><a\n" +
                "                                                                                    href=\"" + mainUrl + "\"\n" +
                "                                                                                    style=\"font-weight:bold;color:#333;text-decoration:none\"\n" +
                "                                                                                    target=\"_blank\" rel=\"noopener\">访问网站\n" +
                "                                                                                    ➜</a></div>\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "                                                            <table width=\"255\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                                        <td width=\"255\" height=\"8\"\n" +
                "                                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif;line-height:8px;\">\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"15\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                        <td width=\"640\" align=\"center\" bgcolor=\"#FFFFFF\"\n" +
                "                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                            <a href=\"" + mainUrl + "\" target=\"_blank\" rel=\"noopener\">\n" +
                "                                                <!-- 修改此处插入的图片 -->\n" +
                "                                                <img height=\"140\" width=\"640\"\n" +
                "                                                    src=\"https://s3.ax1x.com/2020/12/15/rK46iD.png\">\n" +
                "                                            </a>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                        <td width=\"640\" bgcolor=\"#ffffff\"\n" +
                "                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                            <table width=\"640\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"580\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                            <table width=\"580\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                                        <td width=\"580\"\n" +
                "                                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                                            <br><br>\n" +
                "                                                                            <p align=\"left\"\n" +
                "                                                                                style=\"font-size:18px;line-height:24px;color:#333;font-weight:bold;margin-top:0px;margin-bottom:18px;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                                                " + user.getUsername() + "，你好：\n" +
                "                                                                            </p>\n" +
                "                                                                            <div align=\"left\"\n" +
                "                                                                                style=\"font-size:13px;line-height:20px;color:#444444;margin-top:0px;margin-bottom:18px;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                                                <a href=\""+url+"?uid="+UuidUtil.getUuid()+"&amp;token="+UuidUtil.getUuid()+"&amp;code="+user.getCode()+"&amp;email="+user.getEmail()+"&amp;service=sync&amp;resume="+ RandomStringUtils.randomAlphanumeric(100)+"&amp;utm_medium=email&amp;utm_campaign=active-account&amp;utm_content=active-account\" style=\"color:#4183c4;font-weight:bold;\">点击激活你的账号</a>\n" +
                "                                                                                <br><br>\n" +
                "                                                                                如果该操作不是你触发的，请忽略 \uD83D\uDE4F\n" +
                "                                                                            </div>\n" +
                "                                                                            <div\n" +
                "                                                                                style=\"border-bottom-style:solid;border-bottom-width:1px;border-bottom-color:#eee;margin-bottom:30px\">\n" +
                "                                                                            </div>\n" +
                "                                                                            <p align=\"left\"\n" +
                "                                                                                style=\"font-size:18px;line-height:24px;color:#333;font-weight:bold;margin-top:0px;margin-bottom:18px;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                                                小众社区\n" +
                "                                                                            </p>\n" +
                "                                                                            <div align=\"left\"\n" +
                "                                                                                style=\"font-size:13px;line-height:20px;color:#444444;margin-top:0px;margin-bottom:18px;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                                                <table>\n" +
                "                                                                                    <tbody>\n" +
                "                                                                                        <tr\n" +
                "                                                                                            style=\"border-collapse:collapse\">\n" +
                "                                                                                            <td\n" +
                "                                                                                                style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif;font-size:13px;line-height:20px;padding:0 0 15px\">\n" +
                "                                                                                                <ul>\n" +
                "                                                                                                    <li>我们正在构建一个编程爱好者社区\n" +
                "                                                                                                    </li>\n" +
                "                                                                                                    <li>大家在这里相互信任，以\n" +
                "                                                                                                        平等•自由•奔放的价值观进行分享交流\n" +
                "                                                                                                    </li>\n" +
                "                                                                                                    <li>最终，希望大家能够找到与自己志同道合的伙伴，共同成长\n" +
                "                                                                                                    </li>\n" +
                "                                                                                                </ul>\n" +
                "                                                                                            </td>\n" +
                "                                                                                        </tr>\n" +
                "                                                                                    </tbody>\n" +
                "                                                                                </table>\n" +
                "                                                                            </div>\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                        <td width=\"640\" height=\"15\" bgcolor=\"#ffffff\"\n" +
                "                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                        <td width=\"640\"\n" +
                "                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                            <table width=\"640\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
                "                                                bgcolor=\"#333333\"\n" +
                "                                                style=\"color:#333;border-top: 1px solid #e0e0e0;box-shadow: 0 1px 1px white inset;background-color: #f7f7f7;\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"360\" height=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"60\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"160\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"360\" valign=\"top\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                            <span>\n" +
                "                                                                <p align=\"left\"\n" +
                "                                                                    style=\"font-size:12px;line-height:15px;color:#666666;margin-top:0px;margin-bottom:5px;white-space:normal\">\n" +
                "                                                                    <span>\n" +
                "                                                                        <a href=\"" + mainUrl + "\"\n" +
                "                                                                            style=\"color:#4183c4;font-weight:bold;text-decoration:none\"\n" +
                "                                                                            target=\"_blank\" rel=\"noopener\">主页</a>\n" +
                "                                                                        <span style=\"color:#999\">•</span>\n" +
                "                                                                        <a href=\"" + globalUrl + "\"\n" +
                "                                                                            style=\"color:#4183c4;font-weight:bold;text-decoration:none\"\n" +
                "                                                                            target=\"_blank\" rel=\"noopener\">社区</a>\n" +
                "                                                                        <span style=\"color:#999\">•</span>\n" +
                "                                                                        <a href=\"" + adUrl + "\"\n" +
                "                                                                            style=\"color:#4183c4;font-weight:bold;text-decoration:none\"\n" +
                "                                                                            target=\"_blank\" rel=\"noopener\">广告投放</a>\n" +
                "                                                                    </span>\n" +
                "                                                                </p>\n" +
                "                                                            </span>\n" +
                "                                                            <p align=\"left\"\n" +
                "                                                                style=\"font-size:12px;line-height:15px;color:#ccc;margin-top:0px;margin-bottom:15px\">\n" +
                "                                                                了解更多，请详见<a href=\"" + signUrl + "\" target=\"_blank\" style=\"color:#999;font-weight:bold;text-decoration:none\"\n" +
                "                                                                    rel=\"noopener\">社区简介</a>\n" +
                "                                                            </p>\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"40\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"180\" valign=\"top\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                            <p align=\"right\"\n" +
                "                                                                style=\"font-size:12px;line-height:18px;color:#999;margin-top:0px;margin-bottom:15px;white-space:normal\">\n" +
                "                                                                <span>© 2020 <a href=\"" + mainUrl + "\"\n" +
                "                                                                        style=\"color:#4183c4;font-weight:bold;text-decoration:none\"\n" +
                "                                                                        target=\"_blank\"\n" +
                "                                                                        rel=\"noopener\">bbs.ambitiouscc.com</a></span><br\n" +
                "                                                                    style=\"line-height:100%\"><span\n" +
                "                                                                    style=\"font-size:12px;color:#ccc;\">记录生活，连接点滴</span>\n" +
                "                                                            </p>\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                    <tr style=\"border-collapse:collapse\">\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"360\" height=\"15\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"60\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"160\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                        <td width=\"30\"\n" +
                "                                                            style=\"border-collapse:collapse;font-family:Helvetica,Arial,sans-serif\">\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "\n" +
                "        <style type=\"text/css\">\n" +
                "            .qmbox style,\n" +
                "            .qmbox script,\n" +
                "            .qmbox head,\n" +
                "            .qmbox link,\n" +
                "            .qmbox meta {\n" +
                "                display: none !important;\n" +
                "            }\n" +
                "        </style>\n" +
                "    </div>\n" +
                "</div>";
        return content;
    }

    public String mailOfPassword(String url, String email, String code,Visitor visitor) {
        String content = "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"310\"\n" +
                "  style=\"-webkit-text-size-adjust: 100%; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 310px; margin: 0 auto;\">\n" +
                "  <tbody>\n" +
                "    <tr style=\"page-break-before: always\">\n" +
                "      <td align=\"center\" id=\"bbs-logo\" style=\"padding: 20px 0;\">\n" +
                "        <img src=\"https://s3.ax1x.com/2020/12/18/rGji4O.png\"\n" +
                "          height=\"180\" width=\"310\" style=\"-ms-interpolation-mode: bicubic;\">\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr style=\"page-break-before: always\">\n" +
                "      <td valign=\"top\">\n" +
                "        <h1\n" +
                "          style=\"font-family: sans-serif; font-size: 21px; line-height: 29px; font-weight: normal; margin: 0 0 11px 0; text-align: center;\">\n" +
                "          需要重置您的密码吗？</h1>\n" +
                "\n" +
                "        <p class=\"primary\"\n" +
                "          style=\"font-family: sans-serif; font-size: 14px; line-height: 21px; font-weight: normal; margin: 0 0 21px 0; text-align: center;\">\n" +
                "          请在一小时内点击下面的按钮来创建新密码。此次重设密码的请求来自以下设备：</p>\n" +
                "\n" +
                "        <p\n" +
                "          style=\"font-family:sans-serif; font-size: 13px; line-height: 20px; font-weight: normal; margin: 0 0 24px 0px; text-align: center; color: #4a4a4f;\">\n" +
                "\n" +
                "          "+visitor.getBrowser()+"运行于"+visitor.getOS()+"<br>\n" +
                "          "+visitor.getAddress()+"（估计）<br>\n" +
                "          IP 地址："+visitor.getIP()+"<br>\n" +
                "          "+visitor.getTime()+"<br>\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr height=\"50\">\n" +
                "      <td align=\"center\" valign=\"top\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"email-button\"\n" +
                "          style=\"-webkit-text-size-adjust: 100%; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #0a84ff; border-radius: 4px; height: 50px; width: 310px !important;\">\n" +
                "          <tbody>\n" +
                "            <tr style=\"page-break-before: always\">\n" +
                "              <td align=\"center\" valign=\"middle\" id=\"button-content\"\n" +
                "                style=\"font-family: sans-serif; font-weight: normal; text-align: center; margin: 0; color: #fff; font-size: 20px; line-height: 100%;\">\n" +
                "\n" +
                "\n" +
                "                <a href=\""+url+"?uid="+UuidUtil.getUuid()+"&amp;token="+UuidUtil.getUuid()+"&amp;code="+code+"&amp;email="+email+"&amp;service=sync&amp;resume="+ RandomStringUtils.randomAlphanumeric(100)+"&amp;utm_medium=email&amp;utm_campaign=forgot-password&amp;utm_content=reset-password\"\n" +
                "                  id=\"button-link\"\n" +
                "                  style=\"font-family: sans-serif; color: #fff; display: block; padding: 15px; text-decoration: none; width: 280px; font-size: 18px; line-height: 26px;\"\n" +
                "                  rel=\"noopener\" target=\"_blank\">创建新密码</a>\n" +
                "\n" +
                "\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr style=\"page-break-before: always\">\n" +
                "      <td border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">\n" +
                "        <p class=\"secondary\"\n" +
                "          style=\"font-family: sans-serif; font-weight: normal; margin: 24px 0 12px 0; text-align: center; color: #737373; font-size: 11px; line-height: 18px; width: 310px !important; word-wrap:break-word\">\n" +
                "          这是一封自动发送的邮件；如果您并未要求但收到这封信件，您不需要进行任何操作。\n" +
                "          更多信息请访问 <a\n" +
                "            href=\""+mainUrl+"\"\n" +
                "            style=\"color: #0a84ff; text-decoration: none; font-family: sans-serif;\" rel=\"noopener\"\n" +
                "            target=\"_blank\">社区信息</a>。\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "\n" +
                "    <tr style=\"page-break-before: always\">\n" +
                "      <td valign=\"top\">\n" +
                "        <p\n" +
                "          style=\"font-family: sans-serif; font-weight: normal; margin: 0; text-align: center; color: #737373; font-size: 11px; line-height: 18px; width: 310px !important; word-wrap:break-word\">\n" +
                "          河南大学计算机与信息工程学院编程爱好者交流平台\n" +
                "          <br>\n" +
                "          <a href=\""+mainUrl+"\"\n" +
                "            style=\"color: #0a84ff; text-decoration: none; font-family: sans-serif; font-size: 11px; line-height: 18px;\"\n" +
                "            rel=\"noopener\" target=\"_blank\">社区隐私政策</a>\n" +
                "          <br>\n" +
                "          <a href=\""+mainUrl+"\"\n" +
                "            style=\"color: #0a84ff; text-decoration: none; font-family: sans-serif; font-size: 11px; line-height: 18px;\"\n" +
                "            rel=\"noopener\" target=\"_blank\">社区服务条款</a>\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>";
        return content;
    }

    public String mailOfOffUser(String url, String email, String code, Visitor visitor) {
        String content = "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"310\"\n" +
                "  style=\"-webkit-text-size-adjust: 100%; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 310px; margin: 0 auto;\">\n" +
                "  <tbody>\n" +
                "    <tr style=\"page-break-before: always\">\n" +
                "      <td align=\"center\" id=\"bbs-logo\" style=\"padding: 20px 0;\">\n" +
                "        <img src=\"https://s3.ax1x.com/2020/12/18/rGji4O.png\"\n" +
                "          height=\"180\" width=\"310\" style=\"-ms-interpolation-mode: bicubic;\">\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr style=\"page-break-before: always\">\n" +
                "      <td valign=\"top\">\n" +
                "        <h1\n" +
                "          style=\"font-family: sans-serif; font-size: 21px; line-height: 29px; font-weight: normal; margin: 0 0 11px 0; text-align: center;\">\n" +
                "          您要注销此账号吗</h1>\n" +
                "\n" +
                "        <p class=\"primary\"\n" +
                "          style=\"font-family: sans-serif; font-size: 14px; line-height: 21px; font-weight: normal; margin: 0 0 21px 0; text-align: center;\">\n" +
                "          请在一小时内点击下面的按钮来创建新密码。此次重设密码的请求来自以下设备：</p>\n" +
                "\n" +
                "        <p\n" +
                "          style=\"font-family:sans-serif; font-size: 13px; line-height: 20px; font-weight: normal; margin: 0 0 24px 0px; text-align: center; color: #4a4a4f;\">\n" +
                "\n" +
                "          "+visitor.getBrowser()+"运行于"+visitor.getOS()+"<br>\n" +
                "          "+visitor.getAddress()+"（估计）<br>\n" +
                "          IP 地址："+visitor.getIP()+"<br>\n" +
                "          "+visitor.getTime()+"<br>\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr height=\"50\">\n" +
                "      <td align=\"center\" valign=\"top\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"email-button\"\n" +
                "          style=\"-webkit-text-size-adjust: 100%; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #0a84ff; border-radius: 4px; height: 50px; width: 310px !important;\">\n" +
                "          <tbody>\n" +
                "            <tr style=\"page-break-before: always\">\n" +
                "              <td align=\"center\" valign=\"middle\" id=\"button-content\"\n" +
                "                style=\"font-family: sans-serif; font-weight: normal; text-align: center; margin: 0; color: #fff; font-size: 20px; line-height: 100%;\">\n" +
                "\n" +
                "\n" +
                "                <a href=\""+url+"?uid="+UuidUtil.getUuid()+"&amp;token="+UuidUtil.getUuid()+"&amp;code="+code+"&amp;email="+email+"&amp;service=sync&amp;resume="+ RandomStringUtils.randomAlphanumeric(100)+"&amp;utm_medium=email&amp;utm_campaign=forgot-password&amp;utm_content=reset-password\"\n" +
                "                  id=\"button-link\"\n" +
                "                  style=\"font-family: sans-serif; color: #fff; display: block; padding: 15px; text-decoration: none; width: 280px; font-size: 18px; line-height: 26px;\"\n" +
                "                  rel=\"noopener\" target=\"_blank\">注销用户</a>\n" +
                "\n" +
                "\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr style=\"page-break-before: always\">\n" +
                "      <td border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">\n" +
                "        <p class=\"secondary\"\n" +
                "          style=\"font-family: sans-serif; font-weight: normal; margin: 24px 0 12px 0; text-align: center; color: #737373; font-size: 11px; line-height: 18px; width: 310px !important; word-wrap:break-word\">\n" +
                "          这是一封自动发送的邮件；如果您并未要求但收到这封信件，您不需要进行任何操作。\n" +
                "          更多信息请访问 <a\n" +
                "            href=\""+mainUrl+"\"\n" +
                "            style=\"color: #0a84ff; text-decoration: none; font-family: sans-serif;\" rel=\"noopener\"\n" +
                "            target=\"_blank\">社区信息</a>。\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "\n" +
                "    <tr style=\"page-break-before: always\">\n" +
                "      <td valign=\"top\">\n" +
                "        <p\n" +
                "          style=\"font-family: sans-serif; font-weight: normal; margin: 0; text-align: center; color: #737373; font-size: 11px; line-height: 18px; width: 310px !important; word-wrap:break-word\">\n" +
                "          河南大学计算机与信息工程学院编程爱好者交流平台\n" +
                "          <br>\n" +
                "          <a href=\""+mainUrl+"\"\n" +
                "            style=\"color: #0a84ff; text-decoration: none; font-family: sans-serif; font-size: 11px; line-height: 18px;\"\n" +
                "            rel=\"noopener\" target=\"_blank\">社区隐私政策</a>\n" +
                "          <br>\n" +
                "          <a href=\""+mainUrl+"\"\n" +
                "            style=\"color: #0a84ff; text-decoration: none; font-family: sans-serif; font-size: 11px; line-height: 18px;\"\n" +
                "            rel=\"noopener\" target=\"_blank\">社区服务条款</a>\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>";
        return content;
    }

    public boolean sendMail(String content,String to, String title) {
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
//        new MailUtils().sendMail("你好，这是一封测试邮件，无需回复。","1297571059@qq.com","测试邮件");
        new MailUtils().sendMail(new MailUtils().mailOfPassword("http://localhost:8082/user/save/password.do","1297571059@qq.com","asdfasdfsadfasdfa",new Visitor()),"1297571059@qq.com","修改密码");
        System.out.println("发送成功");
    }
}
