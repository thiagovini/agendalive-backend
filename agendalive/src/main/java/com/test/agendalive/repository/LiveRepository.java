package com.test.agendalive.repository;


import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.agendalive.entity.Live;


@Repository
public interface LiveRepository extends JpaRepository<Live, String> {
	
	Page<Live> findByLiveDateAfterOrderByLiveDateAsc(LocalDateTime date, Pageable pageable);
	
	Page<Live> findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime date, Pageable pageable);

	Page<Live> findAll(Pageable pageable);

}
