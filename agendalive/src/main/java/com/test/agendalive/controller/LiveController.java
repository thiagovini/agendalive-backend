package com.test.agendalive.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.agendalive.entity.Live;
import com.test.agendalive.service.LiveService;

@RestController
public class LiveController {

	@Autowired
	LiveService liveService;

	@GetMapping("/lives")
	public ResponseEntity<Page<Live>> getAllLives(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String flag) {
 
		Page<Live> livePage = liveService.findAll(pageable, flag);

		return new ResponseEntity<Page<Live>>(livePage, HttpStatus.OK);
	}

	@GetMapping("/lives/{id}")
	public ResponseEntity<Live> getOneLive(@PathVariable(value = "id") String id) {

		Optional<Live> liveO = liveService.findById(id);

		if (!liveO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Live>(liveO.get(), HttpStatus.OK);
		}
	}

	@PostMapping("/lives")
	public ResponseEntity<Live> saveLive(@RequestBody @Valid Live live) {
		live.setRegistrationDate(LocalDateTime.now());
		live.setId(UUID.randomUUID().toString());
		return new ResponseEntity<Live>(liveService.save(live), HttpStatus.CREATED);
	}

	@DeleteMapping("/lives/{id}")
	public ResponseEntity<?> deleteLive(@PathVariable(value = "id") String id) {

		Optional<Live> liveO = liveService.findById(id);

		if (!liveO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			liveService.delete(liveO.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/lives/{id}")
	public ResponseEntity<Live> updateLive(@PathVariable(value = "id") String id,
			@RequestBody @Valid Live liveDocument) {

		Optional<Live> liveO = liveService.findById(id);

		if (!liveO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			liveDocument.setId(liveO.get().getId());
			return new ResponseEntity<Live>(liveService.save(liveDocument), HttpStatus.OK);
		}
	}

}
