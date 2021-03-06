package com.sangdaero.walab.request.controller;

import java.util.List;

import com.sangdaero.walab.activity.dto.ActivityForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sangdaero.walab.activity.dto.ActivityDto;
import com.sangdaero.walab.interest.application.dto.InterestDto;
import com.sangdaero.walab.interest.application.service.InterestService;
import com.sangdaero.walab.request.dto.RequestDto;
import com.sangdaero.walab.request.service.RequestService;
import com.sangdaero.walab.user.application.dto.SimpleUser;
import com.sangdaero.walab.user.application.service.UserService;

@Controller
@RequestMapping("/request")
public class RequestController {
	
	private RequestService mRequestService;
	private InterestService mInterestService;
	private UserService mUserService;
	
	// constructor
	public RequestController(RequestService requestService, InterestService interestService,
			UserService userService) {
		mRequestService = requestService;
		mInterestService = interestService;
		mUserService = userService;
	}
	
	@GetMapping("")
	public String requestPage(Model model,
			@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "interestType", defaultValue = "0") Integer interestType,
		  	@RequestParam(value = "status", defaultValue = "0") Integer status,
			@RequestParam(value = "sort", defaultValue = "1") Integer sortType) {

		List<RequestDto> requestDtoList = mRequestService.getRequestlist(pageNum, keyword, interestType, status, sortType);
        List<InterestDto> interestList = mInterestService.getInterestList(2);
		Integer firstPage = mRequestService.getFirstPage(pageNum, keyword, status, interestType);
		Long totalNum = mRequestService.getRequestCount(keyword, status, interestType);
		Long waitingNum = mRequestService.getWaitingRequestCount();

        model.addAttribute("requestList", requestDtoList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("interestType", interestType);
		model.addAttribute("status", status);
        model.addAttribute("sort", sortType);
        model.addAttribute("interests", interestList);

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("totalNum", totalNum);
		model.addAttribute("waitingNum", waitingNum);

        return "html/request/request.html";
	}
	
	@GetMapping("/register/{no}")
	public String registerRequest(@PathVariable("no") Long id, Model model) {
		RequestDto requestDto = mRequestService.getPost(id);
		List<InterestDto> interestList = mInterestService.getInterestList(2);
		List<SimpleUser> managerList = mUserService.getSimpleUserList("manager");
		List<SimpleUser> userList = mUserService.getSimpleUserList();

		ActivityForm activityForm = mRequestService.getActivityForm(requestDto);

		model.addAttribute("interests", interestList);
		model.addAttribute("managers", managerList);
		model.addAttribute("users", userList);
		model.addAttribute("activityForm", activityForm);
		
		return "html/activity/activityForm.html";
	}
	
	@GetMapping("/permit/{no}")
	public String permitRequest(@PathVariable("no") Long id, Model model) {
		Long eventId = mRequestService.registerRequestToEvent(id);
		
		return "redirect:/activity/" + eventId;
	}

}
