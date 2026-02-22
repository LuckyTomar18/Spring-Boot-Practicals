package com.rays.ctl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CollegeDTO;
import com.rays.form.CollegeForm;
import com.rays.service.CollegeService;

@RestController
@RequestMapping(value = "college")
public class CollegeCtl extends BaseCtl {

	@Autowired
	public CollegeService service;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid CollegeForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();
		res = validate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}
		CollegeDTO dto = (CollegeDTO) form.getDto();
		long pk = service.add(dto);
		res.addData(dto);
		res.setSuccess(true);
		res.addMessage("College added successfully");
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid CollegeForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();
		res = validate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}
		CollegeDTO dto = (CollegeDTO) form.getDto();
		service.update(dto);
		res.addData(dto);
		res.setSuccess(true);
		res.addMessage("College updated successfully");
		return res;

	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();
		if (ids != null && ids.length > 0) {
			for (long pk : ids) {
				service.delete(pk);
				res.setSuccess(true);
				res.addMessage("College deleted successfully");
			}
		}

		return res;

	}
}
