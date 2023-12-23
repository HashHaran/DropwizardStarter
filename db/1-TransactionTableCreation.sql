CREATE TABLE payment (
	payment_id INT NOT NULL AUTO_INCREMENT,
	sender_vpa VARCHAR(100) NOT NULL,
	amount INT	NOT NULL,
	receiver_vpa VARCHAR(100) NOT NULL,
	STATUS ENUM('PROCESSING', 'FAILED', 'SUCCESSFUL') NOT NULL,
	initiated TIMESTAMP NOT NULL,
	last_updated TIMESTAMP NOT NULL,
	PRIMARY KEY ( payment_id )
);