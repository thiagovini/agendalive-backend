package com.test.agendalive.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.agendalive.entity.Live;
import com.test.agendalive.repository.LiveRepository;

@Service
public class LiveService {

	@Autowired
	LiveRepository liveRepository;

	public Page<Live> findAll(Pageable pageable, String flag) {

		if (flag != null && flag.equals("next")) {

			return liveRepository.findByLiveDateAfterOrderByLiveDateAsc(LocalDateTime.now(), pageable);

		} else if (flag != null && flag.equals("previous")) {

			return liveRepository.findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime.now(), pageable);

		} else {

			return liveRepository.findAll(pageable);

		}

	}

	public Optional<Live> findById(String id) {

		return liveRepository.findById(id);

	}

	public Live save(Live live) {

		return liveRepository.save(live);

	}

	public void delete(Live live) {

		liveRepository.delete(live);

	}

}
