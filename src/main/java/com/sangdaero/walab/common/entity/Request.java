package com.sangdaero.walab.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "request")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Request extends TimeEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 255, nullable = false)
	private String title;
	
	@ManyToOne
	@JoinColumn(name="interest_category", nullable=true)
	private InterestCategory interestCategory;
	
	@ManyToOne
	@JoinColumn(name="client", nullable=true)
	private User client;
	
	@Column(name="status", nullable = false)
	@ColumnDefault("0")
	private Byte status;
	
	@ManyToOne
	@JoinColumn(name="event_id", nullable=true)
	private EventEntity event;

	@Column(name="product_image", nullable = true)
	private String productImage;

	@Column(name="userType", nullable = false)
	@ColumnDefault("1")
	private Byte userType;

	@Column(nullable=true)
	private LocalDateTime startTime;

	@Column(nullable=true)
	private LocalDateTime endTime;

	@Column(columnDefinition = "TEXT", nullable=true)
	private String content;

	@Builder
	public Request(Long id, String title, InterestCategory interestCategory, User client, Byte status, EventEntity event,
				   String productImage, Byte userType, LocalDateTime startTime, LocalDateTime endTime, String content) {
		this.id = id;
		this.title = title;
		this.interestCategory = interestCategory;
		this.client = client;
		this.status = status;
		this.event = event;
		this.productImage = productImage;
		this.userType = userType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
	}
	
}
