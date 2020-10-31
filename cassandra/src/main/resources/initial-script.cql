CREATE KEYSPACE videodb WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
use videodb;
drop table videos;
create table videos (
 videoid int,
 videoname varchar,
 username varchar,
 description varchar,
 location map<varchar,varchar>,
 tags set<varchar>,
 upload_date timestamp,
 views bigint,
 PRIMARY key (videoid)
);
INSERT INTO videos (videoid, videoname, username, description, location, tags, upload_date,views) values
(1,'Lugocast','admin','First episode',{'BR':'/br/podcast/lugocast'},{'podcast','first episode'},'2020-10-30 16:32:00',10000);


INSERT INTO videos (videoid, videoname, username, description, location, tags, upload_date,views) values
(2,'Nerdcast','jovemnerd','Tech',{'BR':'/br/podcast/nerdcast'},{'podcast','nth episode'},'2020-10-30 16:36:00',50);

select * from videos;
