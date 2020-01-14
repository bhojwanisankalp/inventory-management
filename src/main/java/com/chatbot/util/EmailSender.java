package com.chatbot.util;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.chatbot.model.Orders;
import com.chatbot.model.ProductOrdered;
import com.chatbot.model.User;

/*
 * Utility class to configure and send email 
 * */
public class EmailSender {
	
	@Autowired(required = true)
	@Qualifier(value = "mailSender")
	private static JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	/*
	 * Utility method to send Email
	 * @param String value of sender-email, receiver-email, subject of email and Message in email of HTML type content
	 * */
	public static void sendMail(String from, String to, String subject, String msg) {


		mailSender.send(new MimeMessagePreparator() {
			  public void prepare(MimeMessage mimeMessageObj) throws MessagingException {
			    MimeMessageHelper messageObj = new MimeMessageHelper(mimeMessageObj, true, "UTF-8");
			    messageObj.setFrom(from);
			    messageObj.setTo(to);
			    messageObj.setSubject(subject);
			    messageObj.setText(msg, true);
			   // messageObj.addAttachment("Template.doc", new File("Template.doc"));
			  }
			});
	}
	public static String htmlInvoice(User user ,Orders order, List<ProductOrdered> ordered) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		StringBuilder produtInfo =new StringBuilder();
		for(ProductOrdered productOrder:ordered) {
			produtInfo.append("                                <tr>\n" +
					"                                  <td width=\"80%\" class=\"purchase_item\"><span class=\"f-fallback\">"+productOrder.getName()+"</span></td>\n" + 
					"                                  <td class=\"align-right\" width=\"20%\" class=\"purchase_item\"><span class=\"f-fallback\">"+productOrder.getPrice()+"</span></td>\n"+
								"                                </tr>\n" );
		
		}
		
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
				"<html>\n" + 
				"  <head>\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" + 
				"    <meta name=\"x-apple-disable-message-reformatting\" />\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
				"    <title></title>\n" + 
				"    <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" + 
				"    /* Base ------------------------------ */\n" + 
				"    \n" + 
				"    @import url(\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap\");\n" + 
				"    body {\n" + 
				"      width: 100% !important;\n" + 
				"      height: 100%;\n" + 
				"      margin: 0;\n" + 
				"      -webkit-text-size-adjust: none;\n" + 
				"    }\n" + 
				"    \n" + 
				"    a {\n" + 
				"      color: #3869D4;\n" + 
				"    }\n" + 
				"    \n" + 
				"    a img {\n" + 
				"      border: none;\n" + 
				"    }\n" + 
				"    \n" + 
				"    td {\n" + 
				"      word-break: break-word;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .preheader {\n" + 
				"      display: none !important;\n" + 
				"      visibility: hidden;\n" + 
				"      mso-hide: all;\n" + 
				"      font-size: 1px;\n" + 
				"      line-height: 1px;\n" + 
				"      max-height: 0;\n" + 
				"      max-width: 0;\n" + 
				"      opacity: 0;\n" + 
				"      overflow: hidden;\n" + 
				"    }\n" + 
				"    /* Type ------------------------------ */\n" + 
				"    \n" + 
				"    body,\n" + 
				"    td,\n" + 
				"    th {\n" + 
				"      font-family: \"Nunito Sans\", Helvetica, Arial, sans-serif;\n" + 
				"    }\n" + 
				"    \n" + 
				"    h1 {\n" + 
				"      margin-top: 0;\n" + 
				"      color: #333333;\n" + 
				"      font-size: 22px;\n" + 
				"      font-weight: bold;\n" + 
				"      text-align: left;\n" + 
				"    }\n" + 
				"    \n" + 
				"    h2 {\n" + 
				"      margin-top: 0;\n" + 
				"      color: #333333;\n" + 
				"      font-size: 16px;\n" + 
				"      font-weight: bold;\n" + 
				"      text-align: left;\n" + 
				"    }\n" + 
				"    \n" + 
				"    h3 {\n" + 
				"      margin-top: 0;\n" + 
				"      color: #333333;\n" + 
				"      font-size: 14px;\n" + 
				"      font-weight: bold;\n" + 
				"      text-align: left;\n" + 
				"    }\n" + 
				"    \n" + 
				"    td,\n" + 
				"    th {\n" + 
				"      font-size: 16px;\n" + 
				"    }\n" + 
				"    \n" + 
				"    p,\n" + 
				"    ul,\n" + 
				"    ol,\n" + 
				"    blockquote {\n" + 
				"      margin: .4em 0 1.1875em;\n" + 
				"      font-size: 16px;\n" + 
				"      line-height: 1.625;\n" + 
				"    }\n" + 
				"    \n" + 
				"    p.sub {\n" + 
				"      font-size: 13px;\n" + 
				"    }\n" + 
				"    /* Utilities ------------------------------ */\n" + 
				"    \n" + 
				"    .align-right {\n" + 
				"      text-align: right;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .align-left {\n" + 
				"      text-align: left;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .align-center {\n" + 
				"      text-align: center;\n" + 
				"    }\n" + 
				"    /* Buttons ------------------------------ */\n" + 
				"    \n" + 
				"    .button {\n" + 
				"      background-color: #3869D4;\n" + 
				"      border-top: 10px solid #3869D4;\n" + 
				"      border-right: 18px solid #3869D4;\n" + 
				"      border-bottom: 10px solid #3869D4;\n" + 
				"      border-left: 18px solid #3869D4;\n" + 
				"      display: inline-block;\n" + 
				"      color: #FFF;\n" + 
				"      text-decoration: none;\n" + 
				"      border-radius: 3px;\n" + 
				"      box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16);\n" + 
				"      -webkit-text-size-adjust: none;\n" + 
				"      box-sizing: border-box;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .button--green {\n" + 
				"      background-color: #22BC66;\n" + 
				"      border-top: 10px solid #22BC66;\n" + 
				"      border-right: 18px solid #22BC66;\n" + 
				"      border-bottom: 10px solid #22BC66;\n" + 
				"      border-left: 18px solid #22BC66;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .button--red {\n" + 
				"      background-color: #FF6136;\n" + 
				"      border-top: 10px solid #FF6136;\n" + 
				"      border-right: 18px solid #FF6136;\n" + 
				"      border-bottom: 10px solid #FF6136;\n" + 
				"      border-left: 18px solid #FF6136;\n" + 
				"    }\n" + 
				"    \n" + 
				"    @media only screen and (max-width: 500px) {\n" + 
				"      .button {\n" + 
				"        width: 100% !important;\n" + 
				"        text-align: center !important;\n" + 
				"      }\n" + 
				"    }\n" + 
				"    /* Attribute list ------------------------------ */\n" + 
				"    \n" + 
				"    .attributes {\n" + 
				"      margin: 0 0 21px;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .attributes_content {\n" + 
				"      background-color: #F4F4F7;\n" + 
				"      padding: 16px;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .attributes_item {\n" + 
				"      padding: 0;\n" + 
				"    }\n" + 
				"    /* Related Items ------------------------------ */\n" + 
				"    \n" + 
				"    .related {\n" + 
				"      width: 100%;\n" + 
				"      margin: 0;\n" + 
				"      padding: 25px 0 0 0;\n" + 
				"      -premailer-width: 100%;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .related_item {\n" + 
				"      padding: 10px 0;\n" + 
				"      color: #CBCCCF;\n" + 
				"      font-size: 15px;\n" + 
				"      line-height: 18px;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .related_item-title {\n" + 
				"      display: block;\n" + 
				"      margin: .5em 0 0;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .related_item-thumb {\n" + 
				"      display: block;\n" + 
				"      padding-bottom: 10px;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .related_heading {\n" + 
				"      border-top: 1px solid #CBCCCF;\n" + 
				"      text-align: center;\n" + 
				"      padding: 25px 0 10px;\n" + 
				"    }\n" + 
				"    /* Discount Code ------------------------------ */\n" + 
				"    \n" + 
				"    .discount {\n" + 
				"      width: 100%;\n" + 
				"      margin: 0;\n" + 
				"      padding: 24px;\n" + 
				"      -premailer-width: 100%;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"      background-color: #F4F4F7;\n" + 
				"      border: 2px dashed #CBCCCF;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .discount_heading {\n" + 
				"      text-align: center;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .discount_body {\n" + 
				"      text-align: center;\n" + 
				"      font-size: 15px;\n" + 
				"    }\n" + 
				"    /* Social Icons ------------------------------ */\n" + 
				"    \n" + 
				"    .social {\n" + 
				"      width: auto;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .social td {\n" + 
				"      padding: 0;\n" + 
				"      width: auto;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .social_icon {\n" + 
				"      height: 20px;\n" + 
				"      margin: 0 8px 10px 8px;\n" + 
				"      padding: 0;\n" + 
				"    }\n" + 
				"    /* Data table ------------------------------ */\n" + 
				"    \n" + 
				"    .purchase {\n" + 
				"      width: 100%;\n" + 
				"      margin: 0;\n" + 
				"      padding: 35px 0;\n" + 
				"      -premailer-width: 100%;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .purchase_content {\n" + 
				"      width: 100%;\n" + 
				"      margin: 0;\n" + 
				"      padding: 25px 0 0 0;\n" + 
				"      -premailer-width: 100%;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .purchase_item {\n" + 
				"      padding: 10px 0;\n" + 
				"      color: #51545E;\n" + 
				"      font-size: 15px;\n" + 
				"      line-height: 18px;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .purchase_heading {\n" + 
				"      padding-bottom: 8px;\n" + 
				"      border-bottom: 1px solid #EAEAEC;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .purchase_heading p {\n" + 
				"      margin: 0;\n" + 
				"      color: #85878E;\n" + 
				"      font-size: 12px;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .purchase_footer {\n" + 
				"      padding-top: 15px;\n" + 
				"      border-top: 1px solid #EAEAEC;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .purchase_total {\n" + 
				"      margin: 0;\n" + 
				"      text-align: right;\n" + 
				"      font-weight: bold;\n" + 
				"      color: #333333;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .purchase_total--label {\n" + 
				"      padding: 0 15px 0 0;\n" + 
				"    }\n" + 
				"    \n" + 
				"    body {\n" + 
				"      background-color: #F4F4F7;\n" + 
				"      color: #51545E;\n" + 
				"    }\n" + 
				"    \n" + 
				"    p {\n" + 
				"      color: #51545E;\n" + 
				"    }\n" + 
				"    \n" + 
				"    p.sub {\n" + 
				"      color: #6B6E76;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .email-wrapper {\n" + 
				"      width: 100%;\n" + 
				"      margin: 0;\n" + 
				"      padding: 0;\n" + 
				"      -premailer-width: 100%;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"      background-color: #F4F4F7;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .email-content {\n" + 
				"      width: 100%;\n" + 
				"      margin: 0;\n" + 
				"      padding: 0;\n" + 
				"      -premailer-width: 100%;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"    }\n" + 
				"    /* Masthead ----------------------- */\n" + 
				"    \n" + 
				"    .email-masthead {\n" + 
				"      padding: 25px 0;\n" + 
				"      text-align: center;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .email-masthead_logo {\n" + 
				"      width: 94px;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .email-masthead_name {\n" + 
				"      font-size: 16px;\n" + 
				"      font-weight: bold;\n" + 
				"      color: #A8AAAF;\n" + 
				"      text-decoration: none;\n" + 
				"      text-shadow: 0 1px 0 white;\n" + 
				"    }\n" + 
				"    /* Body ------------------------------ */\n" + 
				"    \n" + 
				"    .email-body {\n" + 
				"      width: 100%;\n" + 
				"      margin: 0;\n" + 
				"      padding: 0;\n" + 
				"      -premailer-width: 100%;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"      background-color: #FFFFFF;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .email-body_inner {\n" + 
				"      width: 570px;\n" + 
				"      margin: 0 auto;\n" + 
				"      padding: 0;\n" + 
				"      -premailer-width: 570px;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"      background-color: #FFFFFF;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .email-footer {\n" + 
				"      width: 570px;\n" + 
				"      margin: 0 auto;\n" + 
				"      padding: 0;\n" + 
				"      -premailer-width: 570px;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"      text-align: center;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .email-footer p {\n" + 
				"      color: #6B6E76;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .body-action {\n" + 
				"      width: 100%;\n" + 
				"      margin: 30px auto;\n" + 
				"      padding: 0;\n" + 
				"      -premailer-width: 100%;\n" + 
				"      -premailer-cellpadding: 0;\n" + 
				"      -premailer-cellspacing: 0;\n" + 
				"      text-align: center;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .body-sub {\n" + 
				"      margin-top: 25px;\n" + 
				"      padding-top: 25px;\n" + 
				"      border-top: 1px solid #EAEAEC;\n" + 
				"    }\n" + 
				"    \n" + 
				"    .content-cell {\n" + 
				"      padding: 35px;\n" + 
				"    }\n" + 
				"    /*Media Queries ------------------------------ */\n" + 
				"    \n" + 
				"    @media only screen and (max-width: 600px) {\n" + 
				"      .email-body_inner,\n" + 
				"      .email-footer {\n" + 
				"        width: 100% !important;\n" + 
				"      }\n" + 
				"    }\n" + 
				"    \n" + 
				"    @media (prefers-color-scheme: dark) {\n" + 
				"      body,\n" + 
				"      .email-body,\n" + 
				"      .email-body_inner,\n" + 
				"      .email-content,\n" + 
				"      .email-wrapper,\n" + 
				"      .email-masthead,\n" + 
				"      .email-footer {\n" + 
				"        background-color: #333333 !important;\n" + 
				"        color: #FFF !important;\n" + 
				"      }\n" + 
				"      p,\n" + 
				"      ul,\n" + 
				"      ol,\n" + 
				"      blockquote,\n" + 
				"      h1,\n" + 
				"      h2,\n" + 
				"      h3 {\n" + 
				"        color: #FFF !important;\n" + 
				"      }\n" + 
				"      .attributes_content,\n" + 
				"      .discount {\n" + 
				"        background-color: #222 !important;\n" + 
				"      }\n" + 
				"      .email-masthead_name {\n" + 
				"        text-shadow: none !important;\n" + 
				"      }\n" + 
				"    }\n" + 
				"    </style>\n" + 
				"    <!--[if mso]>\n" + 
				"    <style type=\"text/css\">\n" + 
				"      .f-fallback  {\n" + 
				"        font-family: Arial, sans-serif;\n" + 
				"      }\n" + 
				"    </style>\n" + 
				"  <![endif]-->\n" + 
				"  </head>\n" + 
				"  <body>\n" + 
				"    <span class=\"preheader\">This is a confirmation mail for your recent request of purchase on Media Planet.</span>\n" + 
				"    <table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" + 
				"      <tr>\n" + 
				"        <td align=\"center\">\n" + 
				"          <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" + 
				"            <tr>\n" + 
				"              <!--<td class=\"email-masthead\">\n" + 
				"                <a href=\"https://example.com\" class=\"f-fallback email-masthead_name\">\n" + 
				"                [Product Name]\n" + 
				"              </a>\n" + 
				"              </td>-->\n" + 
				"            </tr>\n" + 
				"            <!-- Email Body -->\n" + 
				"            <tr>\n" + 
				"              <td class=\"email-body\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" + 
				"                <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" + 
				"                  <!-- Body content -->\n" + 
				"                  <tr>\n" + 
				"                    <td class=\"content-cell\">\n" + 
				"                      <div class=\"f-fallback\">\n" + 
				"                        <h1>Hi "+user.getName()+"</h1>\n" + 
				"                        <p>Thanks for ordering . This is a confirmation mail for your recent request of purchase on Media Planet.</p>\n" + 
				"                       \n" + 
				"                        <!-- Discount \n" + 
				"                        <table class=\"discount\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" + 
				"                          <tr>\n" + 
				"                            <td align=\"center\">\n" + 
				"                              <h1 class=\"f-fallback discount_heading\">10% off your next purchase!</h1>\n" + 
				"                              <p class=\"f-fallback discount_body\">Thanks for your support! Here's a coupon for 10% off your next purchase if used by {{expiration_date}}.</p>\n" + 
				"                              Border based button\n" + 
				"           https://litmus.com/blog/a-guide-to-bulletproof-buttons-in-email-design \n" + 
				"                              <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" + 
				"                                <tr>\n" + 
				"                                  <td align=\"center\">\n" + 
				 
				"                                  </td>\n" + 
				"                                </tr>\n" + 
				"                              </table>-->\n" + 
				"                            </td>\n" + 
				"                          </tr>\n" + 
				"                        </table>\n" + 
				"                        <table class=\"purchase\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" + 
				"                          <tr>\n" + 
				"                            <td>\n" + 
				"                              <h3>Order-Id: #"+order.getId()+"<br>Address:<br>"+user.getAddress()+"</h3></td>\n" + 
				"                            <td>\n" + 
				"                              <h3 class=\"align-right\">Order Date<br>"+dateFormat.format(order.getCreatedAt())+"</h3></td>\n" + 
				"                          </tr>\n" + 
				"                          <tr>\n" + 
				"                            <td colspan=\"2\">\n" + 
				"                              <table class=\"purchase_content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" + 
				"                                <tr>\n" + 
				"                                  <th class=\"purchase_heading\" align=\"left\">\n" + 
				"                                    <p class=\"f-fallback\">Description</p>\n" + 
				"                                  </th>\n" + 
				"                                  <th class=\"purchase_heading\" align=\"right\">\n" + 
				"                                    <p class=\"f-fallback\">Amount</p>\n" + 
				"                                  </th>\n" + 
				"                                </tr>\n" + 
				 								
				 
														produtInfo.toString()+
				 
				"                                \n" + 
				"                                <tr>\n" + 
				"                                  <td width=\"80%\" class=\"purchase_footer\" valign=\"middle\">\n" + 
				"                                    <p class=\"f-fallback purchase_total purchase_total--label\">Total</p>\n" + 
				"                                  </td>\n" + 
				"                                  <td width=\"20%\" class=\"purchase_footer\" valign=\"middle\">\n" + 
				"                                    <p class=\"f-fallback purchase_total\">"+order.getTotalAmount()+"</p>\n" + 
				"                                  </td>\n" + 
				"                                </tr>\n" +
				"                              </table>\n" + 
				"                            </td>\n" + 
				"                          </tr>\n" + 
				"                        </table>\n" + 
				"                        <p>If you have any questions about this receipt, simply reply to this email or reach out to our <a href=\"{{support_url}}\">support team</a> for help.</p>\n" + 
				"                        <p>Cheers,\n" + 
				"                          <br>The Media Planet Team</p>\n" + 
				"                        <!-- Action \n" + 
				"                        <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" + 
				"                          <tr>\n" + 
				"                            <td align=\"center\">-->\n" + 
				"                              <!-- Border based button\n" + 
				"           https://litmus.com/blog/a-guide-to-bulletproof-buttons-in-email-design -->\n" + 
				"                              <!--<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" + 
				"                                <tr>\n" + 
				"                                  <td align=\"center\">\n" + 
				"                                    <a href=\"{{action_url}}\" class=\"f-fallback button button--blue\" target=\"_blank\">Download as PDF</a>\n" + 
				"                                  </td>\n" + 
				"                                </tr>\n" + 
				"                              </table>\n" + 
				"                            </td>\n" + 
				"                          </tr>\n" + 
				"                        </table>\n" + 
				"                        <!-- Sub copy \n" + 
				"                        <table class=\"body-sub\" role=\"presentation\">\n" + 
				"                          <tr>\n" + 
				"                            <td>\n" + 
				"                              <p class=\"f-fallback sub\">Need a printable copy for your records?</strong> You can <a href=\"{{action_url}}\">download a PDF version</a>.</p>\n" + 
				"                              <p class=\"f-fallback sub\">Moved recently? Have a new credit card? You can easily <a href=\"{{billing_url}}\">update your billing information</a>.</p>\n" + 
				"                            </td>\n" + 
				"                          </tr>\n" + 
				"                        </table>\n" + 
				"                      </div>\n" + 
				"                    </td>\n" + 
				"                  </tr>\n" + 
				"                </table>-->\n" + 
				"              </td>\n" + 
				"            </tr>\n" + 
				"            <tr>\n" + 
				"              <td>\n" + 
				"                <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" + 
				"                  <tr>\n" + 
				"                    <td class=\"content-cell\" align=\"center\">\n" + 
				"                      <p class=\"f-fallback sub align-center\">&copy; 2019 Mesia PLanet. All rights reserved.</p>\n" + 
				"                      <p class=\"f-fallback sub align-center\">\n" + 
				"                        [Company Name, LLC]\n" + 
				"                        <br>1234 Street Rd.\n" + 
				"                        <br>Suite 1234\n" + 
				"                      </p>\n" + 
				"                    </td>\n" + 
				"                  </tr>\n" + 
				"                </table>\n" + 
				"              </td>\n" + 
				"            </tr>\n" + 
				"          </table>\n" + 
				"        </td>\n" + 
				"      </tr>\n" + 
				"    </table>\n" + 
				"  </body>\n" + 
				"</html>\n" + 
				"";
	}
}
