CREATE TABLE `request` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `type` text NOT NULL,
  `jira` text NOT NULL,
  `application` text NOT NULL,
  `date` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
