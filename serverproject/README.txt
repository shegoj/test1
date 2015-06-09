mysql> LOAD DATA LOCAL INFILE '/root/capacityplanning/repository/serverproject/bandwidth.txt' INTO TABLE BANDWIDTHUTIL LINES TERMINATED BY '\r\n';
Query OK, 1 row affected, 1 warning (0.00 sec)
Records: 1  Deleted: 0  Skipped: 0  Warnings: 1

mysql> select * from BANDWIDTHUTIL;
+---------------+-------------+------+-------+----------+--------------+------+
| servername    | serverGroup | year | month | requests | bandwithUsed | Avg  |
+---------------+-------------+------+-------+----------+--------------+------+
| C638VBQKYCWEB | WEB         | 2015 | MARCH |  1103995 |        20.00 |   18 |
| C638VBQKYCWEB | WEB         | 2015 | MARCH |  1103995 |        19.50 |   18 |
+---------------+-------------+------+-------+----------+--------------+------+
2 rows in set (0.00 sec)

mysql> alter table BANDWIDTHUTIL modify COLUMN avg decimal (7,2);
Query OK, 2 rows affected (0.02 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql> LOAD DATA LOCAL INFILE '/root/capacityplanning/repository/serverproject/bandwidth.txt' INTO TABLE BANDWIDTHUTIL LINES TERMINATED BY '\r\n';
Query OK, 1 row affected (0.00 sec)
Records: 1  Deleted: 0  Skipped: 0  Warnings: 0

mysql> select * from BANDWIDTHUTIL;
