package com.ah.apartowner.datasource.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apartowner_contacts")
@Data
@EqualsAndHashCode(callSuper = false)
public class ApartownerContactsEntity {

	@Id
	@Column(name = "apartowner_contacts_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer apartownerContactsId;

	@OneToOne
	@JoinColumn(name = "apartowner_id", referencedColumnName = "apartowner_id")
	private ApartownersEntity apartowner;

	@Column(name = "con_phone", nullable = false)
	private boolean canPhone;

	@Column(name = "con_mail", nullable = false)
	private boolean canMail;

	@Column(name = "can_chat", nullable = false)
	private boolean canChat;
}
