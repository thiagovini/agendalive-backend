package com.test.agendalive.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Live {
	
	@Id
    private String id;
    
    private String liveName;
    
    private String channelName;
    
    private LocalDateTime liveDate;
    
    private String liveLink;
    
    private LocalDateTime registrationDate;

}
