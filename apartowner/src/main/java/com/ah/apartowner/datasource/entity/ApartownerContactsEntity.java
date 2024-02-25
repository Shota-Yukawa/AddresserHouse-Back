package com.ah.apartowner.datasource.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apartowner_contacts")
@Data
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "pkey_seq", sequenceName = "apartowner_contacts_apartowner_contact_id_seq", allocationSize = 1)
public class ApartownerContactsEntity {

	@Id
	@Column(name = "apartowner_contact_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pkey_seq")
	private Integer apartownerContactsId;

	@OneToOne
	@JoinColumn(name = "apartowner_id", referencedColumnName = "apartowner_id")
	private ApartownersEntity apartowner;

	@Column(name = "can_phone", nullable = false)
	private boolean canPhone;

	@Column(name = "can_mail", nullable = false)
	private boolean canMail;

	@Column(name = "can_chat", nullable = false)
	private boolean canChat;
}
