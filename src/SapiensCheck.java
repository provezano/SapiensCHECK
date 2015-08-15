
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;

public class SapiensCheck implements Runnable{
	
	//Credits to http://www.mkyong.com
	private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
	private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

	private WebClient webClient;
	private static LinkedList<Subject> subjects;

	public void homePage() throws Exception {
		final WebClient webClient = new WebClient();
		webClient.getOptions().setUseInsecureSSL(true);
		
		final HtmlPage page = webClient
				.getPage("https://sapiens.dti.ufv.br/sapiens_crp/CheckLogin.asp");
		Assert.assertEquals("Sistema de Controle de Acesso",
				page.getTitleText());

		/*
		 * final String pageAsXml = page.asXml();
		 * Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
		 */

		final String pageAsText = page.asText();
		System.out.println(pageAsText);
		// Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));

		webClient.close();
	}

	public void submittingForm() throws Exception {
		subjects = new LinkedList<Subject>();
		webClient = new WebClient();

		webClient.getOptions().setUseInsecureSSL(true);
		
		// Get the first page
		final HtmlPage page1 = webClient
				.getPage("https://sapiens.dti.ufv.br/sapiens_crp/CheckLogin.asp");

		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		final HtmlForm form = page1.getFormByName("F1");

		// final HtmlImageInput button =
		// (HtmlImageInput)form.getHtmlElementsByTagName("Login");
		final HtmlInput Usuario = form.getInputByName("Usuario");
		final HtmlPasswordInput Senha = form.getInputByName("Senha");

		// Change the value of the Usuario
		Usuario.setValueAttribute("SUA MATRICULA");
		Senha.setValueAttribute("‏SUA SENHA");

		// Now submit the form by clicking the button and get back the second
		// page.

		final Page page2 = page1.executeJavaScript("Login_onclick()").getNewPage();
		final HtmlPage page3;
		
		if ( page2.isHtmlPage() ) {
			 page3 = webClient
				.getPage("https://sapiens.dti.ufv.br/sapiens_crp/aluno/avaliacoes.asp");
			 //System.out.println(page3.asXml());
			 //System.out.println(page3.asText());
			 
			 //Find subjects
			 Matcher matcherSubject = Pattern.compile("(?!BBT|CRP|PVA)[A-Z]{3}\\s\\d{3} - .* ").matcher(page3.asXml());
			 while(matcherSubject.find()) {
				subjects.add(new Subject(matcherSubject.group()));
				//System.out.println(matcherSubject.group());
			 }
			 
			 
			 //Find changes
			 int i = 0;
			 Matcher matcher = Pattern.compile("Última Alteração: "+ DATE_PATTERN +" às "+ TIME24HOURS_PATTERN).matcher(page3.asXml());
			 while(matcher.find()) {
				 subjects.get(i++).setLastChange(matcher.group());
				//System.out.println(matcher.group());
			 }
			 
			 subjects.forEach(x -> System.out.println(x));
			 System.out.println("");
		
			 //Xml parsing - test 2/23/14
			 /*StringBuffer code = new StringBuffer(page3.asXml());
			 StringBuffer header;
			 
			 header = code.delete(0, code.indexOf("Nome:"));
			 header.delete(code.indexOf("</tbody>"), header.length());
			 
			 System.out.println(header);
			 
			 Matcher matcher = Pattern.compile("(?!BBT|CRP|PVA)[A-Z]{3}\\s\\d{3}").matcher(page3.asXml());
			 while(matcher.find()) {
				System.out.println(matcher.group());
			 }
			 
			 System.out.println("");
			 
			 Matcher matcher2 = Pattern.compile("Nome: ").matcher(page3.asXml());
			 while(matcher2.find()) {
				 System.out.println(matcher2.group());
			 } */
		}
		
		webClient.close();
	}

	@Override
	public void run() {
		SapiensCheck sapiensCheck = new SapiensCheck();

		try {
			
			while (true) {
				sapiensCheck.submittingForm();
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao processar os dados :(");
		}
	}
}