package com.jobcho.workspace;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이 붙은 속성을 포함하는 생성자를 자동으로 만들어줌
@Controller
public class WorkspaceController {

    private final FolderService folderService;
	
	private final FolderRepository folderRepository;
	
	private final NotificationService notificationService;
	
	@GetMapping("/workspace/test")
	public String list(Model model) {
		List<Folders> folderList = this.folderRepository.findAll();
		model.addAttribute("folderList", folderList);
		return "data_test";
		}
	
	@GetMapping("/workspace")
	public String workspaceMain() {
		return "workspace/workspace";
	}
	
	@PostMapping("workspace/foldercreate")
	public String folderCreate(@RequestParam(value="folder_name") String folder_name) {
		this.folderService.create(1, folder_name, 1); //워크스페이스,이름,생성자_id
		return "workspace";
	}
	
	@PostMapping("workspace/notificationcreate")
	public String notificationcreate(@RequestParam(value="notification_content") String content) {
		this.notificationService.create(1, 1, content); // 챗룸, 이용자, 내용
		return "workspace";
	}
}
