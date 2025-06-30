package com.sakila.api.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.service.StoreService;

@RestController
@CrossOrigin
public class StoreController {
	private StoreService storeService;
	
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	// 한 행 조회
	@GetMapping("/storeOne/{storeId}")
	public ResponseEntity<StoreEntity> storeOne(@PathVariable int storeId) {
		return new ResponseEntity<StoreEntity>(storeService.findById(storeId), HttpStatus.OK);
	}
	
	// 조회
	@GetMapping("/store")
	public ResponseEntity<List<StoreEntity>> store(){
		return new ResponseEntity<List<StoreEntity>>(storeService.findAll(), HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/addStore")
	public ResponseEntity<Map<String,String>> addStore(@RequestBody StoreDto storeDto){
		storeService.save(storeDto);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result","추가 성공");
		return new ResponseEntity<Map<String,String>>(resultMap,HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/updateStore")
	public ResponseEntity<Map<String,String>> updateStore(@RequestBody StoreDto storeDto){
		storeService.update(storeDto);
		
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result","수정 성공");
		return new ResponseEntity<Map<String,String>>(resultMap,HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/deleteStore/{storeId}")
	public ResponseEntity<Map<String,String>> deleteStore(@PathVariable int storeId){
		Map<String,String> resultMap = new HashMap<String,String>();
		if(storeService.delete(storeId)) {
			resultMap.put("result","삭제성공");
			return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.OK);
		}
		resultMap.put("result","삭제실패");
		return new ResponseEntity<Map<String,String>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
