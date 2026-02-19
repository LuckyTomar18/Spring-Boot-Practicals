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
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleService;

@RestController
@RequestMapping(value = "role")
public class RoleCtl extends BaseCtl {

	@Autowired
	RoleService roleService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;
		}

		RoleDTO dto = new RoleDTO();

		dto.setName(form.getName());
		dto.setDescription(form.getDescription());

		long id = roleService.add(dto);

		res.setSuccess(true);
		res.addMessage("role addedd successfully");
		res.addData(dto);

		return res;
	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;
		}

		RoleDTO dto = new RoleDTO();

		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setDescription(form.getDescription());

		roleService.update(dto);

		res.setSuccess(true);
		res.addMessage("role update successfully");
		// res.addData(dto);

		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {

			for (long id : ids) {

				roleService.delete(id);
				res.addMessage("recored deleted successfully");
				res.setSuccess(true);

			}
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();

		RoleDTO dto = roleService.findByPk(id);

		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		}

		return res;

	}

	@RequestMapping(value = "/search/{pageNo}" , method = {RequestMethod.GET , RequestMethod.POST})
	public ORSResponse search(@RequestBody RoleForm form, @PathVariable(required = false) int pageNo) {
		
		ORSResponse res = new ORSResponse();

		int pageSize = 5;

		List<RoleDTO> list = roleService.search(null, pageNo, pageSize);

		if (list.size() > 0) {
			res.setSuccess(true);
			res.addData(list);
			return res;
		}

		return res;
	}
}