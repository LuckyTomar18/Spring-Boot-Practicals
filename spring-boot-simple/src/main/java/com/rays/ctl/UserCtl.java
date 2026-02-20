package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserCtl extends BaseCtl {

	@Autowired
	public UserService service;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();
		res = validate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();

		long id = service.add(dto);

		res.setSuccess(true);
		res.addMessage("user added successfully");
		res.addData(dto);

		return res;
	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid UserForm form, BindingResult bindinigResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindinigResult);
		if (!res.success) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();
		service.update(dto);

		res.setSuccess(true);
		res.addMessage("user updated successfully");
		res.addData(dto);
		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				service.delete(id);
				res.setSuccess(true);
				res.addMessage("user deleted successfully");
			}
		}

		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = service.findByPk(id);

		if (dto != null) {
			res.setSuccess(true);
		}
		res.addData(dto);
		return res;

	}

	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = (UserDTO) form.getDto();
		int pageSize = 5;
		List<UserDTO> list = service.search(dto, pageNo, pageSize);

		if (list != null && list.size() > 0) {

			res.setSuccess(true);
		}
		res.addData(list);
		return res;

	}
}
