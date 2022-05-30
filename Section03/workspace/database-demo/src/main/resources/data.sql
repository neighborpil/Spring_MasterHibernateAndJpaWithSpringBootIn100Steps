/*
create table person(
	id integer not null,
	name varchar(255) not null,
	location varchar(255),
	birth_date timestamp,
	primary key(id)
);
*/
INSERT INTO person(
    id, name, location, birth_date
) values(
    10001, 'Neighbor', 'Busan', current_timestamp()
);

INSERT INTO person(
    id, name, location, birth_date
) values(
    10002, 'James', 'NY', current_timestamp()
);

INSERT INTO person(
    id, name, location, birth_date
) values(
    10003, 'John', 'Hong Kong', current_timestamp()
);

