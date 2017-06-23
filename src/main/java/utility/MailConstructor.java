package utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import vo.Order;
import vo.Mem_VO;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public SimpleMailMessage constructResetTokenEmail(
			String contextPath, Locale locale, String token, Mem_VO mem, String password
			) {
		
		String url = contextPath + "/newUser?token="+token;
		String message = "\n請點擊連結，至PetFriends更新您的暫時密碼，您的暫時密碼如下\n"+password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mem.getmem_mail());
		email.setSubject("PetFriends - 新使用者您好");
		email.setText(url+message);
		email.setFrom(env.getProperty("support.email"));
		return email;
		
	}
	
	public MimeMessagePreparator constructOrderConfirmationEmail (final Mem_VO mem, final Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", mem);
		context.setVariable("cartItemList", order.getCartItemList());
		final String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(mem.getmem_mail());
				email.setSubject("訂單確認 - "+order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("w.crows@gmail.com"));
			}
		};
		
		return messagePreparator;
	}
}
