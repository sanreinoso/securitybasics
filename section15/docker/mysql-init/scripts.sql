USE `BankDB`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `accounts`;
DROP TABLE IF EXISTS `account_transactions`;
DROP TABLE IF EXISTS `loans`;
DROP TABLE IF EXISTS `cards`;
DROP TABLE IF EXISTS `notice_details`;
DROP TABLE IF EXISTS `contact_messages`;

CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `pwd` varchar(500) NOT NULL,
  `role` varchar(100) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
);

INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`) VALUES ('Alvaro','are@corp.reinosoh.com','5334122365', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'admin',CURDATE());
INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`) VALUES ('Santiago','santi@sreinosoh.com','5334122366', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'user',CURDATE());
INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`) VALUES ('TestUser','test@sreinosoh.com','5334122367', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'user',CURDATE());
INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`) VALUES ('Maria','maria@corp.reinosoh.com','5334122368', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'user',CURDATE());
INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`) VALUES ('Juan','juan@corp.reinosoh.com','5334122369', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'user',CURDATE());
INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`) VALUES ('Lucia','lucia@corp.reinosoh.com','5334122370', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'user',CURDATE());

CREATE TABLE `accounts` (
  `customer_id` int NOT NULL,
   `account_number` int NOT NULL,
  `account_type` varchar(100) NOT NULL,
  `branch_address` varchar(200) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (1, 186576451, 'Savings', '123 Main Street, New York', CURDATE());
INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (2, 286576452, 'Checking', '456 Elm Street, Madrid', CURDATE());
INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (3, 386576453, 'Savings', '789 Oak Avenue, Barcelona', CURDATE());
INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (4, 486576454, 'Checking', '321 Pine Road, Sevilla', CURDATE());
INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (5, 586576455, 'Savings', '654 Maple Blvd, Valencia', CURDATE());
INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (6, 686576456, 'Checking', '987 Cedar Lane, Bilbao', CURDATE());

CREATE TABLE `account_transactions` (
  `transaction_id` varchar(200) NOT NULL,
  `account_number` int NOT NULL,
  `customer_id` int NOT NULL,
  `transaction_dt` date NOT NULL,
  `transaction_summary` varchar(200) NOT NULL,
  `transaction_type` varchar(100) NOT NULL,
  `transaction_amt` int NOT NULL,
  `closing_balance` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `customer_id` (`customer_id`),
  KEY `account_number` (`account_number`),
  CONSTRAINT `accounts_ibfk_2` FOREIGN KEY (`account_number`) REFERENCES `accounts` (`account_number`) ON DELETE CASCADE,
  CONSTRAINT `acct_user_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);


INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`, `closing_balance`, `create_dt`) VALUES (UUID(), 186576451, 1, DATE_SUB(CURDATE(), INTERVAL 7 DAY), 'Coffee Shop', 'Withdrawal', 30,34500,DATE_SUB(CURDATE(), INTERVAL 7 DAY));
INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`, `closing_balance`, `create_dt`) VALUES (UUID(), 286576452, 2, DATE_SUB(CURDATE(), INTERVAL 6 DAY), 'Uber', 'Withdrawal', 100,34400,DATE_SUB(CURDATE(), INTERVAL 6 DAY));
INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`, `closing_balance`, `create_dt`) VALUES (UUID(), 486576454, 3, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'Self Deposit', 'Deposit', 500,34900,DATE_SUB(CURDATE(), INTERVAL 5 DAY));
INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`, `closing_balance`, `create_dt`) VALUES (UUID(), 486576454, 4, DATE_SUB(CURDATE(), INTERVAL 4 DAY), 'Ebay', 'Withdrawal', 600,34300,DATE_SUB(CURDATE(), INTERVAL 4 DAY));
INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`, `closing_balance`, `create_dt`) VALUES (UUID(), 586576455, 5, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'OnlineTransfer', 'Deposit', 700,35000,DATE_SUB(CURDATE(), INTERVAL 2 DAY));
INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`, `closing_balance`, `create_dt`) VALUES (UUID(), 686576456, 6, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'Amazon.com', 'Withdrawal', 100,34900,DATE_SUB(CURDATE(), INTERVAL 1 DAY));

CREATE TABLE `loans` (
  `loan_number` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `start_dt` date NOT NULL,
  `loan_type` varchar(100) NOT NULL,
  `total_loan` int NOT NULL,
  `amount_paid` int NOT NULL,
  `outstanding_amount` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`loan_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `loan_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`) VALUES ( 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');
INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`) VALUES ( 2, '2021-01-10', 'Personal', 15000, 5000, 10000, '2021-01-10');
INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`) VALUES ( 3, '2022-03-15', 'Vehicle', 25000, 10000, 15000, '2022-03-15');
INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`) VALUES ( 4, '2023-07-20', 'Home', 120000, 20000, 100000, '2023-07-20');
INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`) VALUES ( 5, '2024-05-05', 'Personal', 8000, 3000, 5000, '2024-05-05');
INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`) VALUES ( 6, '2025-02-28', 'Vehicle', 30000, 5000, 25000, '2025-02-28');

CREATE TABLE `cards` (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(100) NOT NULL,
  `customer_id` int NOT NULL,
  `card_type` varchar(100) NOT NULL,
  `total_limit` int NOT NULL,
  `amount_used` int NOT NULL,
  `available_amount` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `card_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());
INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('3455XXXX8673', 2, 'Credit', 7500, 600, 6900, CURDATE());
INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('2359XXXX9346', 3, 'Credit', 20000, 4000, 16000, CURDATE());
INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('1234XXXX5678', 4, 'Debit', 5000, 1000, 4000, CURDATE());
INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('8765XXXX4321', 5, 'Credit', 15000, 2000, 13000, CURDATE());
INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('4321XXXX8765', 6, 'Debit', 7000, 500, 6500, CURDATE());

CREATE TABLE `notice_details` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `notice_summary` varchar(200) NOT NULL,
  `notice_details` varchar(500) NOT NULL,
  `notic_beg_dt` date NOT NULL,
  `notic_end_dt` date DEFAULT NULL,
  `create_dt` date DEFAULT NULL,
  `update_dt` date DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Interest Rate Hike by Central Bank', 'The central bank announces a 0.25% increase in interest rates to control inflation. The new rates will be effective starting from February 1, 2025.',
CURDATE() - INTERVAL 1 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);
INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Major Data Breach at Local Hospital', 'A significant data breach occurred at a local hospital affecting thousands of patients. Authorities are investigating the incident, and patient data is being secured.',
CURDATE() - INTERVAL 2 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);
INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('New Traffic Regulations in City', 'The city has introduced new traffic regulations to improve road safety. These changes will take effect from March 1, 2025.',
CURDATE() - INTERVAL 3 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);
INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Global Supply Chain Disruptions Expected', 'Experts predict continued disruptions in global supply chains, especially for electronics and automotive industries, until mid-2025.',
CURDATE() - INTERVAL 4 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);
INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('Weather Warning for Heavy Snowfall', 'A severe weather warning has been issued for northern regions as heavy snowfall is expected from January 25-28, 2025.',
CURDATE() - INTERVAL 5 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);
INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`) VALUES ('New Vaccine Approved for COVID-19', 'A new COVID-19 vaccine has been approved by health authorities. The vaccine will be available for distribution starting February 2025.',
CURDATE() - INTERVAL 6 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);


CREATE TABLE `contact_messages` (
  `contact_id` varchar(50) NOT NULL,
  `contact_name` varchar(50) NOT NULL,
  `contact_email` varchar(100) NOT NULL,
  `subject` varchar(500) NOT NULL,
  `message` varchar(2000) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
);


CREATE TABLE `authorities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'VIEWACCOUNT');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'VIEWCARDS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'VIEWLOANS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (1, 'VIEWBALANCE');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (2, 'VIEWACCOUNT');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (2, 'VIEWCARDS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (2, 'VIEWLOANS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (2, 'VIEWBALANCE');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (3, 'VIEWACCOUNT');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (3, 'VIEWCARDS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (3, 'VIEWLOANS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (3, 'VIEWBALANCE');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (4, 'VIEWACCOUNT');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (4, 'VIEWCARDS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (4, 'VIEWLOANS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (4, 'VIEWBALANCE');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (5, 'VIEWACCOUNT');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (5, 'VIEWCARDS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (5, 'VIEWLOANS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (5, 'VIEWBALANCE');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (6, 'VIEWACCOUNT');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (6, 'VIEWCARDS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (6, 'VIEWLOANS');
INSERT INTO `authorities` (`customer_id`, `name`) VALUES (6, 'VIEWBALANCE');
