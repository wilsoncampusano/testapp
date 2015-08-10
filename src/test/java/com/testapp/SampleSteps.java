package com.testapp;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.testapp.config.ApplicationConfig;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationConfig.class})
public class SampleSteps {

	@Autowired
	private WebApplicationContext context;

	private WebClient webClient;

	private HtmlPage currentPage;
	
	@Before
	public void setup() throws Throwable {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		webClient = new WebClient();
		webClient.setWebConnection(new MockMvcWebConnection(mockMvc));
	}
	
	@Given("^I open the login page$")
	public void I_open_the_login_page() throws Throwable {
		currentPage = webClient.getPage("http://localhost:8080/testapp/login");
	}

	@When("^I input my user name and password with \"(.*)\" and \"(.*)\"$")
	public void I_input_my_user_name_and_password_with_and(String params1,
			String params2) {
		HtmlForm form = currentPage.getFormByName("loginForm");
		form.getInputByName("username").setValueAttribute(params1);
		form.getInputByName("password").setValueAttribute(params2);
	}

	@Then("^I should go to the home page by clicking the login button$")
	public void I_should_go_to_the_home_page_by_clicking_the_login_button()
			throws IOException {
		HtmlForm form = currentPage.getFormByName("loginForm");
		HtmlSubmitInput submit = form.getElementById("submitButton");
		currentPage = submit.click();
	}

	@And("^I should see the message \"(.*)\" from the welcome page$")
	public void I_should_see_the_message_from_the_welcome_page(String params1) {
		assertEquals(params1, currentPage.getElementById("message").getTextContent());
	}

}