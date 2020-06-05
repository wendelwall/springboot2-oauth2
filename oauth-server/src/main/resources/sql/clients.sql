CREATE TABLE `oauth_client_details` (
	`client_id` varchar(256) NOT NULL,
	`resource_ids` varchar(256) DEFAULT NULL,
	`client_secret` varchar(256) DEFAULT NULL,
	`scope` varchar(256) DEFAULT NULL,
	`authorized_grant_types` varchar(256) DEFAULT NULL,
	`web_server_redirect_uri` varchar(256) DEFAULT NULL,
	`authorities` varchar(256) DEFAULT NULL,
	`access_token_validity` int(11) DEFAULT NULL,
	`refresh_token_validity` int(11) DEFAULT NULL,
	`additional_information` varchar(4096) DEFAULT NULL,
	`autoapprove` varchar(256) DEFAULT NULL,
	PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO oauth_client_details (
	client_id,
	resource_ids,
	client_secret,
	scope,
	authorized_grant_types,
	web_server_redirect_uri,
	authorities,
	access_token_validity,
	refresh_token_validity,
	additional_information,
	autoapprove
)
VALUES
	(
		'client',
		NULL,
		'$2a$10$Dnu4Su7W1uZrr2M3l3c6ROviLP9NSta5zDD6afXXhRljWNBGDmfg6',
		'all',
		'password,authorization_code,refresh_token,implicit,client_credentials',
		'http://localhost:8085/login',
		NULL,
		NULL,
		NULL,
		NULL,
		'true'
	);

CREATE TABLE `oauth_access_token` (
	`token_id` varchar(128) DEFAULT NULL,
	`token` blob,
	`authentication_id` varchar(128) NOT NULL,
	`user_name` varchar(128) DEFAULT NULL,
	`client_id` varchar(128) DEFAULT NULL,
	`authentication` blob,
	`refresh_token` varchar(128) DEFAULT NULL,
	PRIMARY KEY (`authentication_id`) USING BTREE,
	KEY `token_id_index` (`token_id`) USING BTREE,
	KEY `user_name_index` (`user_name`) USING BTREE,
	KEY `client_id_index` (`client_id`) USING BTREE,
	KEY `refresh_token_index` (`refresh_token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `oauth_refresh_token` (
	`token_id` varchar(128) DEFAULT NULL,
	`token` blob,
	`authentication` blob,
	KEY `token_id_index` (`token_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;