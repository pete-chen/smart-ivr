package hcc.pete.smartivr;

import hcc.pete.smartivr.pojo.User;
import hcc.pete.smartivr.utils.JwtUtil;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.Cookie;


@AutoConfigureMockMvc
@SpringBootTest
class SmartIvrApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void login() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/login")
				.param("username", "root")
				.param("password", "root")
				.accept(MediaType.ALL))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getHello() throws Exception {
		User user = new User("root", "root");
		String token = JwtUtil.getToken(user);
		Cookie cookie = new Cookie("token", token);
		mockMvc.perform(MockMvcRequestBuilders.get("/user/hello")
				.cookie(cookie)
				.accept(MediaType.ALL))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("hello"));
	}



}
