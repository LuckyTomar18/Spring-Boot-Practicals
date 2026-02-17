package com.rays.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.TestDTO;

@RestController
@RequestMapping(value = "ors")
public class OrsCtl {

	@GetMapping
	public ORSResponse display() {

		ORSResponse res = new ORSResponse();
		return res;
	}

	@GetMapping("display1")
	public ORSResponse display1() {
		TestDTO dto = new TestDTO();
		ORSResponse res = new ORSResponse();
		res.addMessage("User Register SuccessFully");
		res.setSuccess(true);
		return res;
	}

	@GetMapping("display2")
	public ORSResponse display2() {
		TestDTO dto = new TestDTO();
		ORSResponse res = new ORSResponse();
		res.addInputError("invalid  login and password");
		/* res.setSuccess(true); */
		return res;
	}

	@GetMapping("display3")
	public ORSResponse display3() {
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("firstName", "firstName is required");
		errors.put("LastName", "LastName is required");
		errors.put("login", "login is required");
		errors.put("password", "password is required");
		ORSResponse res = new ORSResponse();
		res.addInputError(errors);
		return res;
	}

	@GetMapping("display4")
	public ORSResponse display4() {
		
		List list = new ArrayList();
		TestDTO dto = new TestDTO();
		ORSResponse res = new ORSResponse();
		
		dto.setFirstName("lucky");
		dto.setLastName("Tomar");
		dto.setLogin("lucky@gmail.com");
		dto.setAddress("gwalior");
		
		list.add(dto);
		
         TestDTO dto1 = new TestDTO();
		
		dto1.setFirstName("ankit");
		dto1.setLastName("rawat");
		dto1.setLogin("ankit@gmail.com");
		dto1.setAddress("indore");
		list.add(dto1);
		
		res.addData(list);
		return res;
	}

}
