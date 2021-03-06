package thredds.server.root;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import thredds.mock.web.MockTdsContextLoader;
import thredds.server.config.TdsContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/WEB-INF/applicationContext-tdsConfig.xml"},loader=MockTdsContextLoader.class)
public class RootControllerTest {
	
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;		
	private RequestBuilder requestBuilder;	
		
	@Test
	public void testRootRequest() throws Exception{							
		
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		requestBuilder = MockMvcRequestBuilders.get("/");
		MvcResult mvc = this.mockMvc.perform(requestBuilder).andReturn();
		//Check that "/" is redirected
		assertEquals(302, mvc.getResponse().getStatus());		
		assertEquals("redirect:/catalog.html", mvc.getModelAndView().getViewName());
	}


}
